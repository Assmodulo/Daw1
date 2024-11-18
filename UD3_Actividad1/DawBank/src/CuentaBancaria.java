//Como algunos métodos en esta clase van a necesitar datos por teclado necesito importar la clase Scanner

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CuentaBancaria {
    private String iban;
    private String titular;
    private double saldo;
    private Movimientos[] movimientos;

    public CuentaBancaria() {

    }

    public CuentaBancaria(String iban, String titular) {
        this.iban = iban;
        this.titular = titular;
        this.saldo = 0;
        this.movimientos = new Movimientos[100];
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
        System.out.println("ESTOS SON LOS DATOS DE LA CUENTA BANCARIA EN CUESTIÓN");

        String info = "Nº Cuenta : " + getIban() + "\n"+
                "Titular: " + getTitular() + "\n" +
                "Saldo: " + getSaldo();

        return info;
    }

    public static String insertarIban(){
        Scanner teclado = new Scanner(System.in);
        Pattern p = Pattern.compile("[A-Z]{2}[0-9]{22}");
        Matcher m;
        String iban;

        do{
            System.out.println("INTRODUZCA UN NÚMERO CORRECTO IBAN\nINTRODUZCA PRIMERO DOS LETRAS Y LUEGO 22 DÍGITOS\n"+
                    "SI EL FORMATO NO ES CORRECTO EL PROGRAMA SE LO VOLVERÁ A PEDIR");
            iban = teclado.nextLine();
        }while(!p.matcher(iban).matches());

        System.out.println("IBAN INTRODUCIDO CORRECTO");

        return iban;
    }

    public static String insertarTitular(){
        Scanner teclado = new Scanner(System.in);
        String titular;

        System.out.println("AHORA NECESITAREMOS EL NOMBRE DEL TITULAR DE LA CUENTA BANCARIA");
        System.out.println("INTRODUZCA ESE DATO AHORA");
        titular = teclado.nextLine();

        return titular;
    }

    public static void movimiento(String tipo, double cantidad){
        Movimientos m = new Movimientos();

        switch(tipo){
            case "DEPOSITO":
                break;
            case "REINTEGRO":
                break;
            default:
                System.out.println("EN ALGÚN MOMENTO SE HA DADO UN ERROR EN LA OPERACIÓN");
                break;
        }
    }

    public static double obtenerCantidad(){
        Scanner teclado = new Scanner(System.in);
        double cantidad;
        System.out.println("INTRODUZCA LA CANTIDAD DE LA OPERACIÓN CORRESPONDIENTE");
        cantidad = teclado.nextDouble();
        return cantidad;
    }



}
