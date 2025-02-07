import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Excepciones 1.3 llenar un array de tipo double

        double [] dobles = new double [5];
        double valor;

        Scanner teclado = new Scanner(System.in);
        int contadorValoresAlmacenados = 0;

        System.out.println("Vamos a rellenar un array de 5 con valores de tipo double por teclado");


            do{
                try{
                    teclado = new Scanner(System.in);
                    System.out.println("Inserte un valor del tipo double");
                    valor = teclado.nextDouble();
                    dobles[contadorValoresAlmacenados] = valor;
                    contadorValoresAlmacenados++;
                }catch (InputMismatchException e) {
                    System.out.println("Valor numérico invalido");
                    System.out.println(e.getMessage());
                } catch (NullPointerException e2) {
                    System.out.println("Valor vacio");
                    System.out.println(e2.getMessage());
                }catch (IndexOutOfBoundsException e3){
                    System.out.println("Has intentado completar una posición inexistente del array");
                    System.out.println("Esto es un error forzado");
                    System.out.println(e3.getMessage());
                }catch (Exception e4) {
                    System.out.println("Esto es por si explota cualquier cosa que desconozco");
                }
                finally {
                    System.out.println("Seguimos intentando rellenar el array");

                }
            }while(contadorValoresAlmacenados < 5);

        System.out.println("Bastante que hemos llegado hasta aquí");
    }
}