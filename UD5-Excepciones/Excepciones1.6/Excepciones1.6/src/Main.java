import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Gato> gatos = new ArrayList<>();
        int contadorGatos = 0;
        Scanner teclado;
        Gato gato;
        String nombre;
        int edad = 0;

        do{

            try {
                teclado = new Scanner(System.in);
                System.out.println("Ingrese el nombre: ");
                nombre = teclado.nextLine();
                System.out.println("Ingrese la edad:");
                edad = teclado.nextInt();
                gato = new Gato(nombre, edad);
                gatos.add(gato);
                contadorGatos++;
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un valor numérico");
                System.out.println(e.getMessage());
            }catch (LongitudNombreException e1){
                System.out.println("El nombre asignado al gato no es el correcto " + e1.getMessage());
            }catch(EdadException e2){
                System.out.println("La edad es incorrecta " + e2.getMessage());
            }
            System.out.println("Ha acumulado usted un total de " + contadorGatos + " gato(s)");
        }while(contadorGatos < 5);

        for(Gato g : gatos){
            System.out.println(g);
        }
    }
}