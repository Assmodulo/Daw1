import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Gato> gatos = new ArrayList<>();
        int contadorGatos = 0;
        Scanner teclado;

        String nombre;
        int edad;

        do{
            teclado = new Scanner(System.in);
            System.out.println("Ingrese el nombre: ");
            nombre = teclado.nextLine();
            System.out.println("Ingrese la edad:");
            try {
                edad = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un valor numérico");
                System.out.println(e.getMessage());
            }
        }while(contadorGatos < 5);

    }
}