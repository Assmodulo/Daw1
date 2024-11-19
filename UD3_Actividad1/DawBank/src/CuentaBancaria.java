//Como algunos métodos en esta clase van a necesitar datos por teclado necesito importar la clase Scanner

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CuentaBancaria {

    //Defino los atributos de la clase
    private String iban;
    private String titular;
    private double saldo;
    private Movimientos[] movimientos;

    //Defino una variable static que me sirve para llevar la cuenta de los elementos totales almacenados en el array
    //movimientos
    private static int elementosTotales = 0;

    //Defino un constructor por defecto para poder declarar variables del tipo CuentaBancaria
    public CuentaBancaria() {

    }

    //Constructor completo de la clase CuentaBancaria. Solo necesita dos parámetros, el resto se definen al crear el
    //objeto
    public CuentaBancaria(String iban, String titular) {
        this.iban = iban;
        this.titular = titular;
        //El saldo inicial siempre va a ser cero
        this.saldo = 0;
        //Siempre va a almacenar un máximo de 100 movimientos que se guardan en este array
        this.movimientos = new Movimientos[100];
    }


    //Defino los getters que me hacen falta, los setters no porque voy a modificar todo mediante métodos
    public String getIban() {
        return iban;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    //Este método sirve para aumentar el valor de saldo cuando hay un ingreso
    public void incrementarSaldo(double saldo) {
        this.saldo += saldo;
    }

    //Este método actualiza el valor de saldo cuando hay un reintegro
    public void reducirSaldo(double saldo) {
        this.saldo -= saldo;
    }

    //Este es el equivalente al método toString
    public String mostrarInformacion(){
        System.out.println("ESTOS SON LOS DATOS DE LA CUENTA BANCARIA EN CUESTIÓN");

        String info = "Nº Cuenta : " + getIban() + "\n"+
                "Titular: " + getTitular() + "\n" +
                "Saldo: " + getSaldo();

        return info;
    }


    //Creo un método para validar el patrón de un iban de una cuenta estandar
    public static String insertarIban(){
        Scanner teclado = new Scanner(System.in);

        //Defino una variable de la clase pattern y un patrón, el del iban
        Pattern p = Pattern.compile("[A-Z]{2}[0-9]{22}");
        //Defino una variable de la clase Matcher
        Matcher m;
        String iban;

        //Mediante un do while me aseguro de que se repita la solicitud hasta que el iban sea correcto
        do{
            System.out.println("INTRODUZCA UN NÚMERO CORRECTO IBAN\nINTRODUZCA PRIMERO DOS LETRAS Y LUEGO 22 DÍGITOS\n"+
                    "SI EL FORMATO NO ES CORRECTO EL PROGRAMA SE LO VOLVERÁ A PEDIR");
            iban = teclado.nextLine();
            //El bucle se repite mientras no sea cierto que el iban sigue el patron indicado
        }while(!p.matcher(iban).matches());

        //Si el patrón es correcto lo indico po pantalla
        System.out.println("IBAN INTRODUCIDO CORRECTO");

        //Devuelvo el valor del iban
        return iban;
    }


    //Creo otro método para insertar el nombre del titular, para que todo quede dentro de un método en vez de estar todo
    //el código en el menú
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


        if (c.movimientos != null) {
            switch(tipo){
                case "DEPOSITO":
                    if (cantidad > 0) {
                        if(cantidad > 3000){
                            System.out.println("INGRESO POR ENCIMA DE LOS LIMITES. AVISAR A HACIENDA");
                        }
                        elementosTotales++;
                        m = new Movimientos(tipo, cantidad);
                        c.movimientos[elementosTotales - 1] = m;
                        c.incrementarSaldo(cantidad);
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
                            elementosTotales++;
                            m = new Movimientos(tipo, cantidad);
                            c.movimientos[elementosTotales - 1] = m;
                            System.out.println("OPERACIÓN REALIZADA");
                            c.reducirSaldo(cantidad);

                            if(c.getSaldo() < 0){
                                System.out.println("SU CUENTA SE HA QUEDADO AL DESCUBIERTO. PRIMER AVISO.");
                            }
                        }
                    }else{
                        System.out.println("NO SE PUEDEN RETIRAR CANTIDADES NEGATIVAS DE DINERO");
                    }
                    break;
                default:
                    System.out.println("EN ALGÚN MOMENTO SE HA DADO UN ERROR EN LA OPERACIÓN");
                    break;
            }
        } else {
            System.out.println("NO SE HA CREADO NINGUNA CUENTA BANCARIA");
        }
    }

    //Este método lo utilizo para indicar la cantidad de dinero que se va a sacar o a ingresar
    public static double obtenerCantidad(){
        Scanner teclado = new Scanner(System.in);
        double cantidad;
        System.out.println("INTRODUZCA LA CANTIDAD DE LA OPERACIÓN CORRESPONDIENTE");
        cantidad = teclado.nextDouble();
        return cantidad;
    }

    //Con este método recorro el array para mostrar todos los movimientos
    //Me dió unos cuantos problemas pero al final funciona como debe
    public void mostrarMovimientos(){
        Movimientos m;

        System.out.println("ESTOS SON LOS MOVIMIENTOS ACUMULADOS EN SU CUENTA");
        for(int i = 0; i < elementosTotales; i++){
                m = this.movimientos[i];
                System.out.println(m.infoMov());
        }
}
    //Con este método muestro todos los movimientos almacenados sean del tipo ingreso
    public void mostrarIngresos(){
        Movimientos m;

        System.out.println("ESTOS SON LOS INGRESOS ACUMULADOS EN SU CUENTA");
        for(int i = 0; i < elementosTotales; i++){
            m = this.movimientos[i];
            if (m.getTipo().equals("DEPOSITO")) {
                System.out.println(m.infoMov());
            }
        }
    }

    //Este método hace lo mismo que el anterior pero con los reintegros
    public void mostrarReintegros(){
        Movimientos m;

        System.out.println("ESTOS SON LOS REINTEGROS ACUMULADOS EN SU CUENTA");
        for(int i = 0; i < elementosTotales; i++){
            m = this.movimientos[i];
            if (m.getTipo().equals("REINTEGRO")) {
                System.out.println(m.infoMov());
            }
        }
    }

    //Para este resumen corto simplemente me he basado en la página principal de la app de la caixa que te muestra en
    //pantalla tu saldo y los últimos 3 movimientos, sólo que aqui muestra saldo y los 5 últimos
    public void resumenCorto(){
        Movimientos m;

        System.out.println("SU SALDO ACTUAL ES DE:");
        System.out.println(this.getSaldo() + "\n");
        System.out.println("*************************\n");
        System.out.println("SUS ÚLTIMOS 5 MOVIMIENTOS SON:");
        System.out.println("************************************\n");

        if(elementosTotales == 0){
            System.out.println("NO TIENE MOVIMIENTOS ALMACENADOS EN SU CUENTA");
        }else{
            //Como debía de evaluar varias condiciones para que no cascase el programa usé un while que evaluaba una
            //variable contador
            int contador = elementosTotales -1;
            while(contador >= 0 && contador >= elementosTotales - 5){
                m = this.movimientos[contador];
                System.out.println(m.infoMov());
                contador--;
            }
        }
}

}
