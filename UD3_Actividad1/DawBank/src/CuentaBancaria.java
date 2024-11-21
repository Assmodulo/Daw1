//Como algunos métodos en esta clase van a necesitar datos por teclado necesito importar la clase Scanner

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;


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
                "Saldo: " + getSaldo() + "\n";


        return info;
    }

    //Metodo para almacenar los movimientos en el array
    public void almacenarMovimientos(){

    }

    //Este método lo he creado para checkear el número de elementos totales introducidos
    //Si ese número es igual a la length del array movimientos, crear un array auxiliar, copia los datos a ese array
    //Despues redimensiona el array existente a su longitud actual +50 y luego vuelca el contenido de auxiliar
    //de nuevo en el array original redimensionado
    private void checkArrayLength(CuentaBancaria c, int elementos){
        if(elementos >= c.movimientos.length){
            Movimientos[] movimientosAuxiliares = new Movimientos[c.movimientos.length];
            System.arraycopy(c.movimientos, 0, movimientosAuxiliares, 0, movimientos.length);
            c.movimientos = new Movimientos[c.movimientos.length + 50];
            System.arraycopy(movimientosAuxiliares, 0, c.movimientos, 0, movimientosAuxiliares.length);
        }
    }



}
