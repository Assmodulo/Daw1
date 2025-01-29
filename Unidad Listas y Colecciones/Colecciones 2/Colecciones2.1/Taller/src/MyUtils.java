import java.util.Scanner;

public class MyUtils {

    static Scanner teclado;

    public static String insertarDato(String dato){
        teclado = new Scanner(System.in);
        String datoAInsertar;
        System.out.println("Introduzca " + dato + " del coche");
        datoAInsertar = teclado.nextLine();
        return datoAInsertar;
    }

}
