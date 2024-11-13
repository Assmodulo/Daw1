//Como algunos métodos en esta clase van a necesitar datos por teclado necesito importar la clase Scanner
import java.util.Scanner;



public class CuentaBancaria {
    private String iban;
    private String titular;
    private double saldo;
    private Movimientos[] movimientos = new Movimientos[100];

    public CuentaBancaria(String iban, String titular) {
        this.iban = iban;
        this.titular = titular;
        this.saldo = 0;
    }

    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void incrementarSaldo(double saldo) {
        this.saldo += saldo;
    }

    public void reducirSaldo(double saldo) {
        this.saldo -= saldo;
    }

    public String mostrarInformacion(){
        String info = "Nº Cuenta : " + getIban() + "\n"+
                "Titular: " + getTitular() + "\n" +
                "Saldo: " + getSaldo();

        return info;
    }

    //Creamos un método para el menu de movimientos
    public void menuMovimientos(){
        //Voy a crear un nuevo menú aquí, por lo tanto necesito las variables normales que uso otras veces para hacer
        //un menu
        String opcion;
        Scanner teclado = new Scanner(System.in);

        //Como también tengo que definir un tipo de operación necesito una variable String para almacenar ese caso
        char tipoOperacion;

        //Vamos mostrando al usuario lo que tiene que hacer
        System.out.println("ELIJA LAS OPCIONES NECESARIAS");
        System.out.println("*****************************\n");

        do {
            System.out.println("1. Depositar\n2. Retirar\n3. Salir");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    tipoOperacion = 'D';
                    break;
                case "2":
                    tipoOperacion = 'R';
                    break;
                case "3":
                    System.out.println("HASTA EL SIGUIENTE SABLAZO.... DIGO... MOVIMIENTO");
                    System.out.println("*************************************************\n");
                    break;
                default:
                    System.out.println("OPCION NO VALIDA");
                    break;
            }

        }while(!"3".equals(opcion));


    }
}
