import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ejercicio 2 introducir 2 inst, calcular su division y tratar excepciones
        int a,b;
        Scanner teclado = new Scanner(System.in);

        try{
            System.out.println("Se le van a solicitar dos números enteros y se va a ejecutar su división");
            System.out.println("Introduzca el primer número");
            a = teclado.nextInt();
            System.out.println("Introduzca el segundo número");
            b = teclado.nextInt();
            System.out.println("La división es :"  + (a/b));
        }catch (InputMismatchException e){
            System.out.println("Opción incorrecta, debe de introducir un número");
            System.out.println(e.getMessage());
        }catch (ArithmeticException e2){
            System.out.println("No se puede dividir un número por 0");
            System.out.println(e2.getMessage());
        }finally{
            System.out.println("Hemos llegado a la finalización del bloque catch");
        }
    }
}