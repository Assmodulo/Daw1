
import javax.tools.FileObject;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //Declaro una vaiable Scanner para poder insertar los datos por teclado
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Creo una LinkedList para almacenar los objetos
        LinkedList<Libro> biblioteca = new LinkedList<>();

        //Declaro una variable Libro
        Libro libro;
        //Un String para la opcion del menú y aquellas necesarias para crear un objeto libro
        String opcion, isbn, titulo, autor, fecha,listado;

        //Una variable int para las opciones numéricas
        int opcionNumerica = 0;

        //Antes de empezar a hacer nada vamos a crear los ficheros necesarios y la estructura de directorios
        try {
            if (!creacionDeFicheros()) {
                lecturaDeFicheros(biblioteca);
            }
        } catch (IOException e) {
            System.out.println("Error en el manejo de ficheros");
        }

        do{
            System.out.println(opcionesMenu() + "\n");
            
            sc = new Scanner(System.in);
            opcion = sc.nextLine();

            switch(opcion){
                case "1":
                    try {
                        System.out.println("Registro de un nuevo libro");
                        System.out.println("Ingrese el isbn del libro");
                        isbn = insertarDatoIsbn();
                        confirmarIsbn(isbn, biblioteca);
                        System.out.println("Ingrese el título del libro");
                        titulo = insertarDatos();
                        System.out.println("Ingrese el autor del libro");
                        autor = insertarDatos();
                        System.out.println("Ingrese la fecha de publicación del libro");
                        fecha = insertarFecha();
                        validarFecha(fecha);
                        libro = new Libro(isbn, titulo, autor, fecha);
                        biblioteca.add(libro);
                    } catch (IsbnYaExistenteException e) {
                        System.out.println(e.getMessage() + " " + e);
                    } catch (FechaNoValidaException e){
                        System.out.println(e.getMessage() + " " + e);
                    }
                    break;
                case "2":
                    System.out.println("Elija el término de búsqueda:\n" + opcionesMenuListados());
                    do{
                        sc = new Scanner(System.in);
                        try{
                            opcionNumerica = sc.nextInt();
                        }catch(InputMismatchException e){
                            System.out.println("Introduzca un valor numérico");
                        }
                    }while(opcionNumerica < 1 || opcionNumerica > 4);
                    System.out.println("Inserte el dato que se le solicita");
                    listado = devolverListados(opcionNumerica,biblioteca);
                    if(listado.isEmpty()){
                        System.out.println("El libro no existe");
                    }else{
                        System.out.println(listado);
                    }
                    break;
                case "3":
                    System.out.println("Eliminar libros por ISBN");
                    if (!listadoLibros(biblioteca).isEmpty()) {
                        System.out.println(listadoLibros(biblioteca));
                        System.out.println("Introduzca el isbn del libro que quiere eliminar");
                        isbn = insertarDatoIsbn();
                        if(eliminarLibroPorIsbn(isbn, biblioteca)){
                            System.out.println("Libro eliminado de nuestra biblioteca");
                        }else{
                            System.out.println("Libro no eliminado");
                        }
                    } else {
                        System.out.println("No existen libros en nuestra biblioteca");
                    }
                    break;
                case "4":
                    System.out.println("Guardado de datos en almacenamiento local");
                    almacenamientoDeDatos(biblioteca);
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }

        }while(!opcion.equals("5"));
    }

    public static String opcionesMenu(){
        return """ 
                Elija una de las siguientes opciones:
                1.-Crear y registrar libro en la biblioteca
                2.-Mostrar libros existente
                3.-Eliminar libro por ISBN
                4.-Guardar libros en el fichero
                5.-Salir
                """;
    }

    public static String opcionesMenuListados(){
        return """
                Elija por que valor va a mostrar los libros
                1.-ISBN
                2.-Titulo
                3.-Autor
                4.-Fecha
                """;
    }

    public static String insertarDatoIsbn(){
        Pattern patron = Pattern.compile("[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{4}-[0-9]");
        Matcher match;
        String isbn;
        do{
            System.out.println("""
                Ingrese el ISBN con el siguiente formato
                000-00-000-0000-0
                """);
            isbn = sc.nextLine();
            match = patron.matcher(isbn);
        }while(!match.matches());
        return isbn;
    }

    public static void confirmarIsbn(String isbn, LinkedList<Libro> biblioteca) throws IsbnYaExistenteException{
        for(Libro libro : biblioteca){
            if(libro.getIsbn().equals(isbn)){
                throw new IsbnYaExistenteException();
            }
        }
    }

    public static String insertarFecha(){
        Pattern patron = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        String fecha;
        do{
            System.out.println("""
                    Ingrese la Fecha con el siguiente formato
                    DD/MM/YYYY
                    """);
            fecha = sc.nextLine();
            match = patron.matcher(fecha);
        }while(!match.matches());
        return fecha;
    }

    public static void validarFecha(String fecha) throws FechaNoValidaException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(LocalDate.parse(fecha,formatter).isAfter(LocalDate.now())){
            throw new FechaNoValidaException();
        }
    }

    public static String insertarDatos(){
        sc = new Scanner(System.in);
        return sc.nextLine().toUpperCase();
    }

    public static String listadoLibros(LinkedList<Libro> biblioteca){
        String listado = "Este es el listado de libros en nuestra biblioteca";
        for(Libro libro : biblioteca){
            listado += libro + "\n";
        }
        return listado;
    }

    public static boolean eliminarLibroPorIsbn(String isbn, LinkedList<Libro> biblioteca){
        boolean eliminado = false;
        Libro l = null;
        for(Libro libro : biblioteca){
            if(libro.getIsbn().equals(isbn)){
                l = libro;
                eliminado = true;
            }
        }
        if(eliminado){
            biblioteca.remove(l);
        }
        return eliminado;
    }

    public static boolean creacionDeFicheros() throws IOException {
        File fichero = new File("./resources/","Biblioteca.dat");
        File directorio = new File("./resources/");
        directorio.mkdir();
        return fichero.createNewFile();
    }

    public static void lecturaDeFicheros(LinkedList<Libro> biblioteca) throws IOException {
        int bitsRestantes = 0;
        try(FileInputStream fichero = new FileInputStream("./resources/Biblioteca.dat");
            ObjectInputStream objeto = new ObjectInputStream(fichero);){
            bitsRestantes = fichero.available();
            while(bitsRestantes > 0){
                Libro libro = (Libro) objeto.readObject();
                biblioteca.add(libro);
                bitsRestantes = fichero.available();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public static void almacenamientoDeDatos(LinkedList<Libro> biblioteca){
        try(FileOutputStream fichero = new FileOutputStream("./resources/Biblioteca.dat");
        ObjectOutputStream escritor = new ObjectOutputStream(fichero)){
            for(Libro libro : biblioteca){
                escritor.writeObject(libro);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static String devolverListados(int opcion, LinkedList<Libro> biblioteca){
        String listado = "";
        String dato = "";
        sc = new Scanner(System.in);
        switch(opcion){
            case 1:
                System.out.println("ISBN");
                dato = insertarDatoIsbn();
                listado = listadoISBN(dato,biblioteca);
                break;
            case 2:
                System.out.println("TITULO");
                dato = insertarDatos();
                listado = listadoTitulo(dato,biblioteca);
                break;
            case 3:
                System.out.println("AUTOR");
                dato = insertarDatos();
                listado = listadoAutor(dato,biblioteca);
                break;
            case 4:
                System.out.println("FECHA DE PUBLICACIÓN");
                dato = insertarFecha();
                listado = listadoFecha(dato,biblioteca);
                break;
        }
        return listado;
    }

    public static String listadoISBN(String isbn, LinkedList<Libro> biblioteca){
        String listado = "Este es el resultado de su búsqueda:\n";
        for(Libro libro : biblioteca){
            if(libro.getIsbn().equals(isbn)){
                listado += libro + "\n";
            }
        }
        return listado;
    }

    public static String listadoTitulo(String titulo, LinkedList<Libro> biblioteca){
        String listado = "Este es el resultado de su búsqueda:\n";
        for(Libro libro : biblioteca){
            if(libro.getTitulo().equals(titulo)){
                listado += libro + "\n";
            }
        }
        return listado;
    }

    public static String listadoAutor(String autor, LinkedList<Libro> biblioteca){
        String listado = "Este es el resultado de su búsqueda:\n";
        for(Libro libro : biblioteca){
            if(libro.getAutor().equals(autor)){
                listado += libro + "\n";
            }
        }
        return listado;
    }

    public static String listadoFecha(String fecha, LinkedList<Libro> biblioteca){
        String listado = "Este es el resultado de su búsqueda:\n";
        for(Libro libro : biblioteca){
            if(libro.getFechaPublicacion().equals(fecha)){
                listado += libro + "\n";
            }
        }
        return listado;
    }

}