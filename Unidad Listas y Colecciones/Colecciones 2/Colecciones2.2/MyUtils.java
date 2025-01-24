import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    //Defino en esta clase todos los métodos que creo que voy a necesitar

    //Defino una variable static de scanner para poder usarla en todos los métodos de esta clase
    private static Scanner sc;

    //Método para insertar un nombre que devuelve un string después de validar que su patrón sea correcto
    public static String insertarNombre(){
        String nombre = "";
        Pattern patron = Pattern.compile("[A-Z]{1}[a-z]{1,14}");
        Matcher matcher;
        do{
            System.out.println("Inserte el nombre del nuevo contacto con la primera letra en mayúscula");
            sc = new Scanner(System.in);
            nombre = sc.nextLine();
            matcher = patron.matcher(nombre);
        }while(!matcher.matches());
        return nombre;
    }

    //Método que devuelve un string que sirve para insertar un número de teléfono válido despues de compararlo con el
    //patrón correspondiente
    public static String insertarTelefono(){
        String telefono ="";
        Pattern patron = Pattern.compile("[0-9]{9}");
        Matcher matcher;
        do{
            System.out.println("Inserte el telefono del nuevo contacto");
            sc = new Scanner(System.in);
            telefono = sc.nextLine();
            matcher = patron.matcher(telefono);
        }while(!matcher.matches());
        return telefono;
    }

    //Método que devuelve un String que sirve para insertar el email despúes de compararlo con un patrón
    public static String insertarEmail(){
        String email ="";
        Pattern patron = Pattern.compile("[A-Za-z1-9_-]{6,20}[@]{1}[a-z]{4,10}[.]{1}[a-z]{2,4}");
        Matcher matcher;
        do{
            System.out.println("Inserte el email del nuevo contacto");
            sc = new Scanner(System.in);
            email = sc.nextLine();
            matcher = patron.matcher(email);
        }while(!matcher.matches());
        return email;
    }

    //Método que devuelve un String que son las posibles opciones a elegir dentro del menú
    public static String mostrarOpcionesMenu(){
        return "ELIJA UNA DE LAS SIGUIENTES OPCIONES:\n1.- AÑADIR CONTACTO\n2.- BUSCAR CONTACTO\n3.- ELIMINAR CONTACTO" +
                "\n4.- VISUALIZAR AGENDA\n5.- NÚMERO DE CONTACTOS TOTALES\n6.- SALIR";
    }
}
