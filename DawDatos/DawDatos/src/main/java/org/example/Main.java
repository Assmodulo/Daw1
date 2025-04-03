package org.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        controlDeFicheros();

        DBManagement db = new DBManagement();

        Scanner sc;

        String opcion = "";
        int opcionNumerica;

        do{

            sc = new Scanner(System.in);

            System.out.println(opcionesMenu());

            opcion = sc.nextLine();

            switch(opcion){
                case "1":

                    System.out.println("Mostrar todos los productos en la base de datos");

                    List<Producto> listado = db.obtenerTodosProductos();

                    for (Producto producto : listado) {
                        System.out.println(producto);
                    }


                    break;
                case "2":

                    System.out.println("Buscar producto por referencia");
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
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
}