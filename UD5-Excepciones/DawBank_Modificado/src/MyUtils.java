import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyUtils {
    //Como se ha visto en clase creo una clase MyUtils para tener aquí los métodos que voy llamando

    //Creo un método para validar el patrón de un iban de una cuenta estandar
    public static boolean validarFormatoIban(String iban){
        boolean correcto = false;
        //Defino una variable de la clase pattern y un patrón, el de él iban
        Pattern patron = Pattern.compile("[A-Z]{2}[0-9]{22}");
        correcto = patron.matcher(iban).matches();

        return correcto;
    }

    //Creo un método que evalua si al introducir una cantidad en reintegros, dejaria nuestro saldo por debajo de
    //-50
    public static boolean saldoNegativoNoPermitido(double cantidad, CuentaBancaria c){
        boolean descubierto = false;
        if(c.getSaldo() - cantidad < -50){
            descubierto = true;
        }
        return descubierto;
    }

    //Creo otro método para evaluar que el importe de una operación introducido por teclado sea mayor que cero
    public static double introducirImporte(){
        Scanner teclado;
        double cantidad = 0;
        do {
            try {
                teclado = new Scanner(System.in);
                cantidad = teclado.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Inroduzca un valor numérico correcto");
            } catch (Exception e) {
                System.out.println("Error inesperado, intentelo de nuevo");
            }
        } while (cantidad <= 0);
        return cantidad;
    }
}
