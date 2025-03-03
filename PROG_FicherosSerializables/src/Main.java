import java.util.Date;
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

        //Declaro una variabla Libro
        Libro libro;
    }

    public static String opcionesMenu(){
        return """ 
                Elija una de las siguientes opciones:
                1.-Crear y registrar libro en la biblioteca
                2.-Mostrar libros existente
                2.-Eliminar libro por ISBN
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
        Pattern patron = Pattern.compile("[0-9]{3}-[0-9]{2}-[0-9]{3}-[0-9]{4}-[0-9]{1}");
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
}