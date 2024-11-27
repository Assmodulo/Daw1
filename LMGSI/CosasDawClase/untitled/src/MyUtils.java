import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class MyUtils {

    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
    "Q","V","H","L","C","K","E"};

    public static String formatoDni(){
        Pattern patron = Pattern.compile("[0-9]{8}");
        Matcher match;
        Scanner sc = new Scanner(System.in);
        String dni;

        do {
            System.out.println("INTRODUZCA SU NIE");
            dni = sc.nextLine();
            match = patron.matcher(dni);
        } while (!match.matches());

        int dniCalculo = Integer.parseInt(dni);
        dni = dni + letrasDni[dniCalculo%23];
        System.out.println("ESTE ES US DNI " + dni);
        return dni;
    }
}
