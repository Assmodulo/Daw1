import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    private static Scanner sc;

    public static String insertarNombre(){
        String nombre = "";
        Pattern patron = Pattern.compile("[A-Z]{1}[a-z]{1,14}");
        Matcher matcher;
        do{
            System.out.println("Inserte el nombre del nuevo contacto");
            sc = new Scanner(System.in);
            nombre = sc.nextLine();
            matcher = patron.matcher(nombre);
        }while(!matcher.matches());
        return nombre;
    }
}
