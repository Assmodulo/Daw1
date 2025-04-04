package org.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        controlDeFicheros();

        DBManagement db = new DBManagement();

        List<Producto> listado = new LinkedList<>();

        Scanner sc;

        String dato;

        String opcion = "";
        int opcionNumerica = -1, resultadoQuery = -1;

        Tipo t = null;

        do{

            sc = new Scanner(System.in);

            System.out.println(opcionesMenu());

            opcion = sc.nextLine();

            switch(opcion){
                case "1":

                    System.out.println("Mostrar todos los productos en la base de datos");

                    listado = db.obtenerTodosProductos();

                    for (Producto producto : listado) {
                        System.out.println(producto);
                    }


                    break;
                case "2":

                    System.out.println("Buscar producto por referencia");

                    dato = solicitarReferencia();

                    listado = db.buscarPorReferencia(dato);

                    if (listado.isEmpty()) {
                        System.out.println("No existe el referencia");
                    }else{
                        for (Producto producto : listado) {
                            System.out.println(producto);
                        }
                    }

                    break;
                case "3":

                    System.out.println("Buscar productos por Tipo");

                    opcionNumerica = seleccionarTipoProductos();

                    listado = db.buscarPorIdTipo(opcionNumerica);

                    if (listado.isEmpty()) {
                        System.out.println("No existen datos que coincidan con el tipo solicitado");
                    }else{
                        for (Producto producto : listado) {
                            System.out.println(producto);
                        }
                    }

                    break;
                case "4":

                    System.out.println("Buscar productos por la cantidad de los mismos");

                    do{
                        try {
                            opcionNumerica = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Ingrese un numero mayor que cero" + e.getMessage());
                        }
                    }while(opcionNumerica < 0);

                    System.out.println();

                    listado = db.buscarPorCantidad(opcionNumerica);

                    if (listado.isEmpty()) {
                        System.out.println("No existe el referencia");
                    }else{
                        for (Producto producto : listado) {
                            System.out.println(producto);
                        }
                    }
                    break;
                case "5":
                    break;
                case "6":

                    System.out.println("Eliminar producto por referencia");

                    dato = solicitarReferencia();

                    listado = db.buscarPorReferencia(dato);

                    if (listado.isEmpty()) {
                        System.out.println("No existe el producto buscado con la referencia indicada. No hace falta eliminar");
                    }else{
                        resultadoQuery = db.eliminarPorReferencia(dato);
                        if (resultadoQuery == -1) {
                            System.out.println("No se ha podido eliminar el producto solicitado");
                        }else{
                            System.out.println("Se han eliminado " + resultadoQuery + " producto/s");
                        }
                    }
                    break;
                case "7":
                    break;
                case "8":

                    System.out.println("Creación de un nuevo registro para la clase y tabla Tipo");

                    t = creacionNuevoTipo();

                    break;
                case "9":
                    break;
                default:
                    break;
            }

        }while(!opcion.equals("9"));

    }

    public static void controlDeFicheros() {

        File carpeta = new File("./Recursos");
        carpeta.mkdir();

        File fichero = new File(carpeta,"application.dat");
        try {
            if(!fichero.exists()){

                fichero.createNewFile();
                if(fichero.length() == 0){
                    guardarDatosEnFichero();
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo " + e.getMessage());
        }
    }

    public static void guardarDatosEnFichero() {

        try(FileWriter fichero = new FileWriter("./Recursos/application.dat");
            BufferedWriter escritor = new BufferedWriter(fichero)){

            escritor.write("com.mysql.cj.jdbc.Driver,jdbc:mysql://localhost:3306/,dawdata,developer,1234");

        }catch (IOException e) {
            System.out.println("Error al guardar datos en el archivo " + e.getMessage());
        }
    }

    public static String opcionesMenu(){
        return """
                   Elija una de las opciones disponibles:
                   1. Mostrar todos los Productos en el Inventario.
                   2. Buscar producto por referencia.
                   3. Buscar productos por tipo.
                   4. Buscar producto por cantidad.
                   5. Insertar un nuevo producto (no permitir referencias repetidas).
                   6. Eliminar Producto por referencia.
                   7. Actualizar producto (descripción, cantidad, precio, descuento, AplicarDto).
                   8. Insertar un nuevo tipo de producto.
                   9. Salir.
                """;
    }

    public static String solicitarReferencia(){
        String referencia = "";
        Scanner teclado;

        Pattern patron = Pattern.compile("[A-Z]{3}-[0-9]{5}");
        Matcher m;

        do{
            teclado = new Scanner(System.in);
            System.out.println("Ingrese la referencia a buscar con el siguiente formato" +
                    "AAA-00000");
            referencia = teclado.nextLine();

            m = patron.matcher(referencia);

        }while(!m.matches());

        return referencia;
    }

    public static int seleccionarTipoProductos() {
        int opcion = -1;
        Scanner teclado;


        for(int i = 0; i < Tipos.values().length; i++){
            System.out.println( (i + 1) + " " + Tipos.values()[i]);
        }

        System.out.println();

        do {
            teclado = new Scanner(System.in);
            System.out.println("Seleccione la categoria de tipo del producto");
            try {
                opcion = teclado.nextInt();
                opcion--;
            } catch (InputMismatchException e) {
                System.out.println("Tipo de eleccion no valida" + e.getMessage());
            }
        } while (opcion < 0 || opcion > Tipos.values().length - 1);

        return opcion;
    }
}