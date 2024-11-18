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

    private void setSaldo(double saldo) {
        this.saldo = saldo;
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

    public static void movimiento(String tipo, double cantidad, CuentaBancaria c){
        Movimientos m = new Movimientos();


        switch(tipo){
            case "DEPOSITO":
                if (cantidad > 0) {
                    if(cantidad > 3000){
                        System.out.println("INGRESO POR ENCIMA DE LOS LIMITES. AVISAR A HACIENDA");
                    }
                    m = new Movimientos(tipo, cantidad);
                    c.movimientos[m.getIdMov()] = m;
                    c.setSaldo(c.getSaldo() + cantidad);

                    System.out.println("OPERACIÓN REALIZADA");

                    if(c.getSaldo() < 0 && c.getSaldo() > -51){
                        System.out.println("AVISO. SALDO NEGATIVO");
                    }
                } else {
                    System.out.println("NO SE PUEDEN HACER INGRESOS DE VALOR 0 O NEGATIVOS");
                }
                break;
            case "REINTEGRO":
                if (cantidad > 0) {
                    if((c.getSaldo() - cantidad) < -50){
                        System.out.println("REINTEGRO NO PERMITIDO. CUENTA AL DESCUBIERTO POR ENCIMA DEL LIMITE");

                    }else{
                        m = new Movimientos(tipo, cantidad);
                        c.movimientos[m.getIdMov()] = m;
                        System.out.println("OPERACIÓN REALIZADA");
                        c.setSaldo(c.getSaldo() - cantidad);

                    }
                }else{
                    System.out.println("NO SE PUEDEN RETIRAR CANTIDADES NEGATIVAS DE DINERO");
                }
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

public void mostrarMovimientos(){
        Movimientos m = new Movimientos();

        System.out.println("ESTOS SON LOS MOVIMIENTOS ACUMULADOS EN SU CUENTA");
        for(int i = 0; i < movimientos.length; i++){

            if (movimientos[i] != null) {
                m = movimientos[i];
                System.out.println(m.infoMov());
            }

        }
}

}
