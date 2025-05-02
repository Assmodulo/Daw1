package com.videoDBD;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyUtils {


    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatoFechaSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static DateTimeFormatter formatoFechaHoraSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Array para calcular de forma automática la letra del dni al introducirlo
     */
    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"};


    public static String devolverOpcionesMenu(){
        return """
            Elija una de las opciones disponibles:
               1. Crear nuevo videoclub en la franquicia.
               2. Eliminar videoclub de la franquicia.
               3. Seleccionar videoclub en la franquicia para trabajar en el.
               4. Dar de alta un socio en el videoclub actual.
               5. Insertar un nuevo articulo en el videoclub actual.
               6. Eliminar Artículo por referencia en el videoclub actual.
               7. Alquilar un producto por un socio en el videoclub actual.
               8. Devolver un artículo.
               9. Salir y guardar variables en ficheros.
            """;
    }

    public static String seleccionarOpcion(){
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static Videoclub crearVideoclub(){

        Videoclub v;

        VideoclubBuilder videoclub = new VideoclubBuilder();

        v = videoclub
                .cif(insertarCif())
                .nombre(insertarNombre())
                .direccion(insertarDireccion())
                .telefono(insertarTelefono())
                .build();

        return v;
    }


    public static String insertarCif(){
        Scanner sc = new Scanner(System.in);

        Pattern formatoCif = Pattern.compile("[A-Za-z][0-9]{8}");

        Matcher match;

        String cif = "";

        do{
            System.out.println("Inserte el CIF del videoclub. Una Letra y 8 Dígitos. No distingue mayúsculas de minúsculas");

            cif = sc.nextLine();

            match = formatoCif.matcher(cif);

        }while(!match.matches());

        return cif.toUpperCase();
    }


    public static String insertarDireccion(){
        Scanner sc = new Scanner(System.in);

        String direccion = "";

        Pattern patronDireccion = Pattern.compile("[A-Z a-z0-9]{8,50}");

        Matcher match;

        do{
            System.out.println("Ingrese la dirección solicitada.Mínimo 8, Máximo 50 caracteres alfanuméricos.");

            direccion = sc.nextLine();

            match = patronDireccion.matcher(direccion);

        }while(!match.matches());

        return direccion.toUpperCase();
    }

    public static String insertarNombre(){
        Scanner sc = new Scanner(System.in);

        String nombre = "";

        Pattern patronNombre = Pattern.compile("[A-Z a-z]{8,25}");

        Matcher match;

        do{
            System.out.println("Ingrese el nombre del videoclub. Mínimo 8 caracteres, Máximo 50 caracteres.");

            nombre = sc.nextLine();

            match = patronNombre.matcher(nombre);

        }while(!match.matches());

        return nombre.toUpperCase();
    }

    public static String insertarTelefono(){
        Scanner sc = new Scanner(System.in);

        String telefono = "";

        Pattern patronTelefono = Pattern.compile("[0-9]{9}");

        Matcher match;

        do{
            System.out.println("Ingrese el telefono del videoclub. 9 números obligatorios.");

            telefono = sc.nextLine();

            match = patronTelefono.matcher(telefono);

        }while(!match.matches());

        return telefono;
    }

    public static boolean validarVideoClub(String cif, LinkedList<Videoclub> videoclubs){
        boolean resultado = false;

        for(Videoclub videoclub : videoclubs){
            if(videoclub.getCif().equals(cif)){
                resultado = true;
            }
        }

        return resultado;
    }

    public static String listadoVideoclubs(LinkedList<Videoclub> videoclubs){
        String listado = String.format("%-9S | %-50S | %-50S | %-9S","CIF", "NOMBRE","DIRECCION","TELEFONO");

        for(Videoclub videoclub : videoclubs){
            listado += "\n" + videoclub.toString();
        }

        return listado;
    }

    public static String insertarCodigo(String letra, String codigo_videoclub, int numero){
        String codigoCliente = "";

        DecimalFormat df = new DecimalFormat("0000");

        codigoCliente = letra.concat(codigo_videoclub.substring(0,3)).concat(df.format(numero));

        return codigoCliente;
    }


    public static String insertarNombreCliente(){
        Scanner sc;

        String nombre = "";

        Pattern patronNombre = Pattern.compile("[A-Z a-z]{8,25}");

        Matcher match;

        do{
            sc = new Scanner(System.in);

            System.out.println("Ingrese el nombre de la persona. 8 letras mínimo, 25 máximo.\n" +
                    "No importan mayúsculas o minúsculas.");

            nombre = sc.nextLine();

            match = patronNombre.matcher(nombre);

        }while(!match.matches());

        return nombre.toUpperCase();
    }

    public static String insertarApellidos(){
        Scanner sc;

        String apellidos = "";

        Pattern patronNombre = Pattern.compile("[A-Z a-z]{8,50}");

        Matcher match;

        do{
            sc = new Scanner(System.in);

            System.out.println("Ingrese los apellidos de la persona. 8 letras mínimo, 50 máximo.\n" +
                    "No importan mayúsculas o minúsculas.");

            apellidos = sc.nextLine();

            match = patronNombre.matcher(apellidos);

        }while(!match.matches());

        return apellidos.toUpperCase();
    }

    public static String insertarDni(){
        Scanner sc;

        Pattern formatoDni = Pattern.compile("[0-9]{8}");

        Matcher match;

        String dni = "";


        do{
            sc = new Scanner(System.in);

            System.out.println("Introduzca los 8 dígitos que componen su dni, sin la letra");

            dni = sc.nextLine();

            match = formatoDni.matcher(dni);

        }while(!match.matches());

        int numero = Integer.parseInt(dni);

        dni = dni + letrasDni[numero % 23];

        return dni;
    }

    public static LocalDate insertarFecha()throws MenorDeEdadException, FechaPosteriorActualException{
        Scanner sc;

        String fecha = "";

        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");

        Matcher match;
        do{

            sc = new Scanner(System.in);
            System.out.println("INSERTE LA FECHA CON EL SIGUIENTE FORMATO:\n" +
                    "DD/MM/AAAA");

            fecha = sc.nextLine();

            match = patronFecha.matcher(fecha);

        }while(!match.matches());

        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatoFecha);

        validarFechaIntroducida(fechaNacimiento);

        return LocalDate.parse(fecha, formatoFecha);
    }

    private static void validarFechaIntroducida(LocalDate fecha) throws MenorDeEdadException, FechaPosteriorActualException{
        if(fecha.isAfter(LocalDate.now())){
            throw new FechaPosteriorActualException("La fecha de nacimiento no puede ser posterior a la actual");
        }else if ((LocalDate.now().getYear() - fecha.getYear()) < 18){
            throw new MenorDeEdadException("El Cliente es menor de edad");
        }
    }

    public static String formatearFecha(LocalDate date){
        return formatoFecha.format(date);
    }

    public static String formatearFechaSQL(LocalDate date){
        return formatoFechaSQL.format(date);
    }

    public static String formatearFechaHora(LocalDateTime date){
        return formatoFechaHora.format(date);
    }

    public static String formatearFechaHoraSQL(LocalDateTime date){
        return formatoFechaHoraSQL.format(date);
    }

    public static Cliente darDeAltaNuevoCliente(String codigo_videoclub)throws FechaPosteriorActualException, MenorDeEdadException{
        Cliente c;

        ClienteBuilder cliente = new ClienteBuilder();

        c = cliente
                .cod_cliente(insertarCodigo("C",codigo_videoclub, Cliente.numeroTotalSociosFranquicia))
                .dni(insertarDni())
                .nombre(insertarNombreCliente())
                .apellidos(insertarApellidos())
                .f_nacim(insertarFecha())
                .telefono(insertarTelefono())
                .build();


        return c;
    }

    public static void validarSocioYaExistente(Cliente c, Videoclub v) throws DniExistenteException{
        DBoperations db = new DBoperations();

        LinkedList<Cliente> listado = db.obtenerClientesVideoclub(v);

        for(Cliente cliente : listado){
            if(c.getDni().equals(cliente.getDni())){
                throw new DniExistenteException("Ya existe un cliente con ese dni en nuestra BD");
            }
        }
    }


    public static void guardadoEnFicheroDeVariablesNecesarias() throws IOException {
        File fichero = new File("src/main/resources/variables.txt");

        fichero.createNewFile();

        String variable = variablesToString();

        try(FileWriter fic = new FileWriter("src/main/resources/variables.txt");
            BufferedWriter escritor = new BufferedWriter(fic)){

            escritor.write(variable);
            escritor.newLine();

        }catch (IOException e){
            System.out.println("Error al guardar datos en variables.txt. " + e.getMessage());
        }
    }

    private static String variablesToString(){
        return String.valueOf(Cliente.numeroTotalSociosFranquicia) + "," + String.valueOf(Articulo.cantidadTotalArticulos);
    }

    public static void leerDatosArchivosVariablesNecesarias() {
        File file = new File("src/main/resources/variables.txt");

        try(FileReader fichero = new FileReader(file);
            BufferedReader lector = new BufferedReader(fichero)){

            String linea = lector.readLine();

            while(linea != null){

                String [] datos = linea.split(",");

                Cliente.numeroTotalSociosFranquicia = Integer.parseInt(datos[0]);

                Articulo.cantidadTotalArticulos = Integer.parseInt(datos[1]);

                linea = lector.readLine();
            }


        }catch (IOException e){
            System.out.println("Error al leer los datos del archivo variables.dat. " + e.getMessage());
        }
    }

    public static String seleccionarTiposDeArticulos(){
        String tipo = "";

        Scanner sc;

        int opcionNumerica = -1;

        do{
            sc = new Scanner(System.in);
            System.out.println(mostrarTiposArticulos());
            try {
                opcionNumerica = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un número");
            }

        }while(opcionNumerica < 1 || opcionNumerica > TipoArticulo.values().length);

        tipo = TipoArticulo.values()[opcionNumerica - 1].getTipo();



        return tipo;
    }

    private static String mostrarTiposArticulos(){
        String listado = "Elija uno de los tipos de Artículo escribiendo su número";

        for(int i = 0; i < TipoArticulo.values().length; i++){
            listado +="\n" + (i+1) + " " + TipoArticulo.values()[i].getTipo();
        }

        return listado;
    }

    public static String seleccionarGeneroArticulo(String tipo){
        String genero = "";

        int opcionNumerica = -1;

        Scanner sc;

        if(tipo.equals("P")){

            do {

                System.out.println(mostrarGenerosDisponibles(tipo));

                sc = new Scanner(System.in);

                try{

                    opcionNumerica = sc.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("Debe de introducir un número correcto");
                }
            } while (opcionNumerica < 0 || opcionNumerica > GenerosPeliculas.values().length -1);

            genero = GenerosPeliculas.values()[opcionNumerica - 1].getGeneroP();


        }else{
            do {

                System.out.println(mostrarGenerosDisponibles(tipo));

                sc = new Scanner(System.in);

                try{

                    opcionNumerica = sc.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("Debe de introducir un número correcto");
                }
            } while (opcionNumerica < 0 || opcionNumerica > GenerosVideojuegos.values().length -1);

            genero = GenerosVideojuegos.values()[opcionNumerica - 1].getGeneroV();

        }

        return genero;
    }

    private static String mostrarGenerosDisponibles(String tipo){
        String listado = "Elija uno de los siguientes géneros indicando el número.";

        if(tipo.equals("P")){
            for(int i = 0; i < GenerosPeliculas.values().length; i++){
                listado += "\n" + (i + 1) + " " + GenerosPeliculas.values()[i].getGeneroP();
            }
        }else{
            for(int i = 0; i < GenerosVideojuegos.values().length;i++){
                listado += "\n" + (i + 1) + " " + GenerosVideojuegos.values()[i].getGeneroV();
            }
        }
        return listado;
    }

    public static String insertarTitulo(){
        String titulo = "";

        Pattern patron = Pattern.compile("[a-z ]{5,30}");

        Matcher match;

        Scanner sc;

        do{
            System.out.println("Inserte el título del artículo. Mínimo 5, Máximo 30 caracteres");

            sc = new Scanner(System.in);

            titulo = sc.nextLine();

            match = patron.matcher(titulo);

        }while(!match.matches());

        return titulo.toUpperCase();
    }

    public static Articulo crearNuevoArticulo(Videoclub v){
        Articulo a;

        ArticuloBuilder articulo = new ArticuloBuilder();

        String tipo = seleccionarTiposDeArticulos();

        if(tipo.equals("Pelicula")){
            a = articulo
                    .cod_articulo(MyUtils.insertarCodigo("P", v.getCif(), Articulo.cantidadTotalArticulos))
                    .tipo(tipo)
                    .nombre(insertarTitulo())
                    .genero(seleccionarGeneroArticulo("P"))
                    .build();
        }else{
            a = articulo
                    .cod_articulo(MyUtils.insertarCodigo("V", v.getCif(), Articulo.cantidadTotalArticulos))
                    .tipo(tipo)
                    .nombre(insertarTitulo())
                    .genero(seleccionarGeneroArticulo("V"))
                    .build();
        }

    return a;
    }

    public static String mostrarArticulosDeUnVideoclub(Videoclub v) {
        DBoperations db = new DBoperations();

        String listado = "Estos son los artículos disponibles en su videoclub\n";

        LinkedList<Articulo> articulos;

        articulos = db.obtenerArticulosDeUnVideoclub(v.getCif());

        if(articulos.isEmpty()){
            listado += "No hay Artículos registrados en este Videoclub";
        }else{
            for(Articulo a : articulos){
                listado += a.toString() + "\n";
            }
        }

        return listado;
    }

    public static boolean eliminarArticuloPorReferencia(Videoclub v){
        boolean eliminado = false;

        Articulo a = null;

        DBoperations db = new DBoperations();

        LinkedList<Articulo> articulos;

        articulos = db.obtenerArticulosDeUnVideoclub(v.getCif());

        String codigo = indicarCodigoEliminacion();

        for(Articulo articulo : articulos){
            if(codigo.equals(articulo.getCod_articulo())){
                a = articulo;
                if(db.eliminarArticulo(a) > 0){
                    eliminado = true;
                }
            }
        }

        return eliminado;
    }

    private static String indicarCodigoEliminacion(){
        String codigo;

        Scanner sc;

        System.out.println("Inserte el código del artículo que desea eliminar. No diferencia mayúscualas de minúsculas");

        sc = new Scanner(System.in);

        codigo = sc.nextLine();

        return codigo.toUpperCase();
    }

    public static LinkedList<Cliente> obtenerListadoClientesActivos(Videoclub v){
        LinkedList<Cliente> clientes;

        DBoperations db = new DBoperations();

        clientes = db.obtenerClientesVideoclubActivos(v.getCif());


        return clientes;
    }


    public static Cliente seleccionarCliente(LinkedList<Cliente> clientes) {

        Cliente c = null;

        String codigo = introducirCodigoClienteArticulo();

        for(Cliente cliente : clientes){
            if(cliente.getCod_cliente().equals(codigo)){
                c = cliente;
            }
        }

        return c;
    }

    private static String introducirCodigoClienteArticulo(){
        String codigo = "";

        Scanner sc = new Scanner(System.in);

        codigo = sc.nextLine();


        return codigo.toUpperCase();
    }

    public static LinkedList<Articulo> obtenerListadoArticulosAlquiler(Videoclub v) {

        LinkedList<Articulo> articulos;

        DBoperations db = new DBoperations();

        articulos = db.obtenerArticulosParaAlquiler(v.getCif());


        return articulos;
    }

    public static Articulo seleccionarArticulo(LinkedList<Articulo> articulos) {

        Articulo a = null;

        String codigo = introducirCodigoClienteArticulo();

        for(Articulo articulo : articulos){
            if(articulo.getCod_articulo().equals(codigo)){
                a = articulo;
            }
        }

        return a;
    }

    public static Alquiler seleccionarAlquilerFinalizar(Videoclub v) {

        DBoperations db = new DBoperations();

        ArrayList<Alquiler> alquileres;

        alquileres = db.obtenerListadoAlquileres(v.getCif());

        Alquiler a = seleccionarTransaccion(alquileres);

        return a;
    }

    private static Alquiler seleccionarTransaccion(ArrayList<Alquiler> alquileres){
        Alquiler a = null;

        String listado = "Estas son las devoluciones pendientes \n";

        for(int i = 0; i < alquileres.size(); i++){

            listado += (i + 1) +" " + alquileres.get(i).toString() + "\n";

        }

        System.out.println("Seleccione el alquiler a finalizar \n" + listado);

        int alquilerI = seleccionarIndiceArrayAlquileres(alquileres);

        a = alquileres.get(alquilerI);

        return a;
    }

    private static int seleccionarIndiceArrayAlquileres(ArrayList<Alquiler> alquileres){
        int indice = -1;

        Scanner sc;

        do{

            sc = new Scanner(System.in);

            try{

                indice = sc.nextInt();

                indice--;

            }catch(InputMismatchException e){

                System.out.println("Debe de incluir un número.");

            }

        }while(indice < 0 || indice >= alquileres.size());

        return indice;
    }

    public static String validarPeriodoDeDevolucion(Alquiler al) {

        String aviso = "";

        if(LocalDateTime.now().minusDays(2).isAfter(al.getF_alquiler())){
            aviso = "Se ha pasado el límite de 2 días y debe de pagar cargo adicional al devolver el producto";
        }else{
            aviso = "Se ha devuelto el artículo dentro del periodo normal";
        }

        return aviso;
    }
}
