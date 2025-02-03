import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ejercicio 1.1 de Excepciones

        int a;
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("Introduce un número entero por teclado");
            a = teclado.nextInt();
            System.out.println("Valor introducido...");
        }catch (InputMismatchException e){
            System.out.println("Debe de introducir un valor numérico");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
}