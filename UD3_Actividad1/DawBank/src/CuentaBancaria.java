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
    public String insertarTitular(){
        Scanner teclado = new Scanner(System.in);
        String titular;

        System.out.println("AHORA NECESITAREMOS EL NOMBRE DEL TITULAR DE LA CUENTA BANCARIA");
        System.out.println("INTRODUZCA ESE DATO AHORA");
        titular = teclado.nextLine();

        return titular;
    }

    public void movimiento(String tipo, double cantidad, CuentaBancaria c){


        //Lo primero es asegurarme de que existe un array movimientos, lo que quiere decir que se ha creado una
        //cuenta, para que en caso contrario me avise y no me deje hacer movimientos
        if (c.movimientos != null) {
            switch(tipo){
                //Mediante el switch evaluo si el tipo de movimiento es depósito o reintegro, ya que cada uno evalua
                //aspectos distintos
                case "DEPOSITO":
                    //Si es depósito evaluo si cantidad es mayor que 0
                    if (cantidad > 0) {
                        //Despues simplemente compruebo si el ingreso es de más de 3000 euros
                        if(cantidad > 3000){
                            //Si es mayor de 3000 que nos lo indique
                            System.out.println("INGRESO POR ENCIMA DE LOS LIMITES. AVISAR A HACIENDA");
                        }
                        //Despues de todo esto, llamo a este método, con todos estos parámetros para que realice
                        //las operaciones necesarias
                        c.incluirMovimiento(c, tipo, cantidad, elementosTotales);
                        //Aunque sea un ingreso tengo que evaluar que el saldo final sea negativo, y si lo es,
                        //avisar al usuario
                        if(c.getSaldo() < 0 && c.getSaldo() > -51){
                            System.out.println("AVISO. SALDO NEGATIVO");
                        }
                    } else {
                        //Si el Depósito es menor o igual a cero aviso y no hago nada
                        System.out.println("NO SE PUEDEN HACER INGRESOS DE VALOR 0 O NEGATIVOS");
                    }
                    break;
                    //Ahora viene la opción del reintegro
                case "REINTEGRO":
                    //También valido si la cantidad indicada es mayor que cero
                    if (cantidad > 0) {
                        //Antes de hacer nada compruebo que el resultado del reintegro no sea menor de -50
                        //En caso de serlo no puedo hacer nada
                        if((c.getSaldo() - cantidad) < -50){
                            System.out.println("REINTEGRO NO PERMITIDO. CUENTA AL DESCUBIERTO POR ENCIMA DEL LIMITE");

                        }else{
                            //Si el reintegro es permitido, queda por hacer las operaciones necesarias
                            c.incluirMovimiento(c, tipo, cantidad, elementosTotales);
                            if(c.getSaldo() < 0){
                                System.out.println("SU CUENTA SE HA QUEDADO AL DESCUBIERTO. PRIMER AVISO.");
                            }
                        }
                    }else{
                        //Por si acaso se intenta hacer operaciones con cantidades negativas
                        System.out.println("NO SE PUEDEN RETIRAR CANTIDADES NEGATIVAS DE DINERO");
                    }
                    break;
                default:
                    //En principio, creo que lo tengo todo bien cubierto, pero por si acaso, marco un mensaje
                    //en caso de que algo falle
                    System.out.println("EN ALGÚN MOMENTO SE HA DADO UN ERROR EN LA OPERACIÓN");
                    break;
            }
        } else {
            //En caso de que no se haya creado una cuenta, no se pueden insertar movimientos y avisamos al usuario
            System.out.println("NO SE HA CREADO NINGUNA CUENTA BANCARIA");
        }
    }

    //Este método lo utilizo para indicar la cantidad de dinero que se va a sacar o a ingresar
    public double obtenerCantidad(){
        //Declaro variables de Scanner y una double que voy a retornar que va a ser la cantidad
        //con la que voy a hacer el movimiento

        Scanner teclado = new Scanner(System.in);
        double cantidad;
        System.out.println("INTRODUZCA LA CANTIDAD DE LA OPERACIÓN CORRESPONDIENTE");
        cantidad = teclado.nextDouble();
        return cantidad;
    }

    //Con este método recorro el array para mostrar todos los movimientos
    //Me dió unos cuantos problemas pero al final funciona como debe
    public void mostrarMovimientos(){
        //Declaro una variable de la clase Movimiento para almacenar los valores necesarios

        Movimientos m;

        //Recorro el array, voy almacenando en m el objeto almacenado en la posición i del array
        //Luego lo muestro por pantalla
        System.out.println("ESTOS SON LOS MOVIMIENTOS ACUMULADOS EN SU CUENTA");
        for(int i = 0; i < elementosTotales; i++){
                m = this.movimientos[i];
                System.out.println(m.infoMov());
        }
    }

    //Con este método muestro todos los movimientos almacenados sean del tipo ingreso
    public void mostrarIngresos(){
        //Es lo mismo que en mostrarMovimientos pero evaluando que el tipo de movimiento sea ingreso

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

    //Este método lo he creado para checkear el número de elemntos totales introducidos
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


    //Este método lo creé para refactorizar el almacenamiento de los movimientos, ya que la mayor parte del código
    //es lo mismo para Depósitos que para reintegros
    //Lo único que se añade es la evaluación de si el tipo de movimiento es Depósito o reintegro, que cada uno lleva
    //un método distinto para actualizar el saldo
    private void incluirMovimiento(CuentaBancaria c, String tipo, double cantidad, int elementos){

        //Primero declaro una variable de la clase Movimientos
        Movimientos m = new Movimientos();

        //Como elementosTotales lo inicie a cero y para llevar una buena cuenta de los elementos que llevo lo debo
        //de incrementar antes de crear el movimiento.
        elementosTotales++;

        //
        c.checkArrayLength(c, elementosTotales);
        m = new Movimientos(tipo, cantidad);
        c.movimientos[elementosTotales - 1] = m;
        if(tipo.equals("DEPOSITO")){
            c.incrementarSaldo(cantidad);
        } else if(tipo.equals("REINTEGRO")){
            c.reducirSaldo(cantidad);
        }
        System.out.println("OPERACIÓN REALIZADA");
    }

}
