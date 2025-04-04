package org.example;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
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

        Producto p = null;

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

                    System.out.println("Insertar un nuevo producto");

                    if(insertarNuevoProducto()){
                        System.out.println("Nuevo producto insertado en la tabla");
                    }else{
                        System.out.println("No se ha podido insertar ningún producto nuevo");
                    }
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

                    System.out.println("Actualizar datos de un producto: " +
                            "Descripción, Cantidad, Precio, Descuento, Aplicar Descuento");

                    listado = db.obtenerTodosProductos();

                    for (Producto producto : listado) {
                        System.out.println(producto.getReferencia() + " " +producto.getNombre());
                    }

                    System.out.println();
                    dato = solicitarReferencia();

                    listado.clear();

                    listado = db.buscarPorReferencia(dato);
                    if (listado.isEmpty()) {
                        System.out.println("No existe el referencia");
                    }else{
                        for (Producto producto : listado) {
                            p = producto;
                            if(modificarDatosProducto(p) > 0){
                                System.out.println("Producto modificado correctamente");
                            }else{
                                System.out.println("No se ha podido modificar el producto de forma correcta");
                            }
                        }
                    }
                    break;
                case "8":

                    System.out.println("Creación de un nuevo registro para la clase y tabla Tipo");

                    //t = creacionNuevoTipo();

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

    public static boolean insertarNuevoProducto(){

        String referencia, nombre, descripcion;
        int idTipo, cantidad, descuento, iva, filasAfectadas = -1;
        double precio;
        boolean insertadoCorrecto = false, aplicarIva = false;

        List<Producto> listado = new LinkedList<>();

        DBManagement db = new DBManagement();

        Producto p = null;

        System.out.println("Introduzca los datos según se le van solicitando");

        referencia = solicitarReferencia();

        listado = db.buscarPorReferencia(referencia);

        if(listado.isEmpty()){
            nombre = solicitarNombre();
            descripcion = solicitarDescripcion();
            idTipo = seleccionarTipoProductos();
            cantidad = solicitarCantidad();
            precio = solicitarPrecio();
            descuento = solicitarDescuento();
            iva = solicitarIva();

            p = new Producto(referencia, nombre, descripcion, idTipo, cantidad, precio, descuento, iva, aplicarIva);

            filasAfectadas = db.insertarNuevoProducto(p);
        }else{
            System.out.println("La referencia ya existe. No se puede insertar un nuevo producto con esa referencia");
        }

        if(filasAfectadas > 0){
            insertadoCorrecto = true;
        }

        return insertadoCorrecto;
    }

    public static String solicitarNombre(){
        Scanner teclado;

        Pattern patron = Pattern.compile("[A-Z a-z0-9]{10,49}");
        Matcher m;
        String nombre = "";

        do{

            System.out.println("Inserte el nombre con un mínimo de 10 y un máximo de 50 caracteres");

            teclado = new Scanner(System.in);

            nombre = teclado.nextLine();

            m = patron.matcher(nombre);

        }while(!m.matches());

        return nombre;
    }

    public static String solicitarDescripcion(){
        Scanner teclado;
        String descripcion = "";

        Pattern patron = Pattern.compile("[A-Z][A-z a-z-/.0-9]{10,99}");
        Matcher m;

        do{
            System.out.println("Introduzca la descripción del producto con un máximo de 100 caracteres");

            teclado = new Scanner(System.in);

            descripcion = teclado.nextLine();

            m = patron.matcher(descripcion);

        }while(!m.matches());

        return descripcion;
    }

    public static int solicitarCantidad(){
        Scanner teclado;
        int cantidad = 0;

        do{
            System.out.println("Introduzca la cantidad del producto. Debe de ser mayor que cero");
            teclado = new Scanner(System.in);

            try{
                cantidad = teclado.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Formato de la cantidad no válido." + e.getMessage());
            }

        }while(cantidad <= 0);
        return cantidad;
    }

    public static double solicitarPrecio(){
        Scanner teclado;
        double precio = 0;

        DecimalFormat df = new DecimalFormat("####,##");

        do{

            System.out.println("Inserte el valor de precio unitario con el siguiente formato: ####,##");

            teclado = new Scanner(System.in);

            try {
                precio = teclado.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error en el formato del precio introducico " + e.getMessage());
            }
        }while(precio <= 0);

        String precioFinal = df.format(precio);

        precio = Double.parseDouble(precioFinal);

        return precio;
    }

    public static int solicitarDescuento(){
        int descuento = -1;
        Scanner teclado;

        do{

            System.out.println("Inserte el valor del descuento. Como no nos gusta rebajar debe de estar comprendido" +
                    "entre 0 y 40% (y ya estamos perdiendo dinero)");

            teclado = new Scanner(System.in);

            try{
                descuento = teclado.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Opción para una cantidad incorrecta " + e.getMessage());
            }

        }while (descuento <0 || descuento > 40);

        return descuento;
    }

    public static int solicitarIva(){
        int iva = 0;
        String opcion;
        Scanner teclado;

        do{

            System.out.println("Elija el iva correspondiente a su producto");

            System.out.println("""
                        1. 7%
                        2. 11%
                        3. 21%
                        """);

            teclado = new Scanner(System.in);

            opcion = teclado.nextLine();

            switch(opcion){

                case "1":
                    iva = 7;
                    break;
                case "2":
                    iva = 11;
                    break;
                case "3":
                    iva = 21;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }

        }while(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        return iva;
    }

    public static int modificarDatosProducto(Producto producto){
        int rowsAfected = -1;

        DBManagement db = new DBManagement();

        String descripcion;
        int cantidad, descuento;
        double precio;
        boolean aplicarDto;

        descripcion = solicitarDescripcion();

        cantidad = solicitarCantidad();

        descuento = solicitarDescuento();

        precio = solicitarPrecio();

        aplicarDto = activarDto(producto);

        producto.setDescripcion(descripcion);
        producto.setCantidad(cantidad);
        producto.setDescuento(descuento);
        producto.setPrecio(precio);
        producto.setAplicarDescuento(aplicarDto);


        rowsAfected = db.modificarRegistro(producto);


        return rowsAfected;
    }

    public static boolean activarDto(Producto producto){
        Scanner teclado;
        String opcion;
        boolean aplicarDto = producto.isAplicarDescuento();

        do{

            System.out.println("El estado de aplicar el descuento de su producto es: " + aplicarDto);
            System.out.println("""
                    Indique si quiere cambiar esto?
                    1. Aplicar Descuento
                    2. No Aplicar Descuento
                    """);

            teclado = new Scanner(System.in);

            opcion = teclado.nextLine();

            if(opcion.equals("1")){
                aplicarDto = true;
            }else if(opcion.equals("2")){
                aplicarDto = false;
            }
        }while(!opcion.equals("1") && !opcion.equals("2"));

        return aplicarDto;
    }
}