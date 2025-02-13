import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    //Como se ha visto en clase creo una clase MyUtils para tener aquí los métodos que voy llamando
    static Scanner sc;
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
    /*public static boolean saldoNegativoNoPermitido(double cantidad, CuentaBancaria c){
        boolean descubierto = false;
        if(c.getSaldo() - cantidad < -50){
            descubierto = true;
        }
        return descubierto;
    }*/

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

    public static String solicitarDato(String dato){
        return "Introduzca el siguiente dato: " + dato;
    }

    public static Cliente crearCliente(){

        Cliente cliente = null;


        return cliente;
    }

    public static String insertarTelefono(){
        String telefono ="";
        Pattern patron = Pattern.compile("[0-9]{9}");
        Matcher matcher;
        do{
            System.out.println(MyUtils.solicitarDato("Telefono"));
            sc = new Scanner(System.in);
            telefono = sc.nextLine();
            matcher = patron.matcher(telefono);
        }while(!matcher.matches());
        return telefono;
    }

    public static String insertarEmail(){
        String email ="";
        Pattern patron = Pattern.compile("[A-Za-z1-9_-]{6,20}[@]{1}[a-z]{4,10}[.]{1}[a-z]{2,4}");
        Matcher matcher;
        do{
            System.out.println("Inserte el email del contacto");
            sc = new Scanner(System.in);
            email = sc.nextLine();
            matcher = patron.matcher(email);
        }while(!matcher.matches());
        return email;
    }

    public static String insertarNombre(){
        String nombre = "";
        Pattern patron = Pattern.compile("[A-Z]{1}[a-z]{1,14}");
        Matcher matcher;
        do{
            System.out.println("Inserte el nombre del contacto con la primera letra en mayúscula");
            sc = new Scanner(System.in);
            nombre = sc.nextLine();
            matcher = patron.matcher(nombre);
        }while(!matcher.matches());
        return nombre;
    }
}
