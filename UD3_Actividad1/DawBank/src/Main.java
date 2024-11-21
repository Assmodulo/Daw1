import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        //Dentro de main sólo voy a imprimir un texto por pantalla y llamar al método menú que es el método principal
        //Dentro de menú, dependiendo de la opción escogida hará unas cosas u otras

        System.out.println("BIENVENIDO A DAWBANK. TE SANGRAMOS IGUAL QUE LOS DEMÁS, PERO MÁS MAJOS");
        System.out.println("**********************************************************************\n");


        //Voy a definir un objeto de la clase Scanner
        Scanner teclado = new Scanner(System.in);

        //Declaro una variable String, ya que para hacer un menú, necesito una opción para evaluar
        String opcion;

        //Creo una variable de la clase CuentaBancaria con el constructor por defecto, luego ya iré trabajando con ella
        CuentaBancaria c = new CuentaBancaria();
        Movimientos m = new Movimientos();

        System.out.println("LO PRIMERO ES CREARSE UNA CUENTA. VAMOS A SOLICITARLE UNA SERIE DE DATOS\n");
        System.out.println("PRIMERO NECESITAMOS EL IBAN. INTRODUZCA DOS LETRAS SEGUIDAS DE 22 DÍGITOS\n"
        +"SI EL FORMATO NO ES CORRECTO EL PROGRAMA SE LO PEDIRÁ OTRA VEZ");

        //Declaro dos variable String para almacenar el iban y el titular
        String iban, titular, tMovimiento;

        //Declaro una variable double para almacenar la cantidad de dinero para los movimientos
        double cantidad;



        do{
            teclado = new Scanner(System.in);
            System.out.println("INTRODUZCA UN IBAN CORRECTO");
            iban = teclado.nextLine().toUpperCase();
        }while(!validarFormatoIban(iban));

        System.out.println("AHORA VAMOS A SOLICITAR EL NOMBRE DEL TITULAR");
        teclado = new Scanner(System.in);
        titular = teclado.nextLine().toUpperCase();

        //procedo a crear la cuenta
        c= new CuentaBancaria(iban,titular);
        System.out.println();


        do{
            //Muestro el primer menú y las opciones a elegir
            System.out.println("ELIJA UNA OPCIÓN ACORDE A LO QUE LE MUESTRA EL MENÚ");
            System.out.println("1. - DATOS DE LA CUENTA\n"+
                    "2. - IBAN\n"+
                    "3. - TITULAR\n"+
                    "4. - SALDO\n"+
                    "5. - INGRESO\n"+
                    "6. - RETIRADA\n"+
                    "5. - MOVIMIENTOS\n"+
                    "5. - SALIR\n");

            //Hay que indicarlo por teclado, asi que se pide el dato necesario
            opcion = teclado.nextLine();

            switch (opcion){
                case "1":
                    System.out.println("ESTA ES LA INFORMACIÓN DE LA CUENTA EN CUESTIÓN");
                    System.out.println(c.mostrarInformacion());
                    break;
                case "2":
                    System.out.println("ESTE ES EL IBAN DE LA CUENTA QUE SE HA CREADO");
                    System.out.println(c.getIban());
                    System.out.println();
                    break;
                case "3":
                    System.out.println("ESTE ES EL NOMBRE DEL TITULAR DE LA CUENTA");
                    System.out.println(c.getTitular());
                    System.out.println();
                    break;
                case "4":
                    System.out.println("ESTE ES EL SALDO DE LA CUENTA ASOCIADA");
                    System.out.println(c.getSaldo());
                    System.out.println();
                    break;
                case "5":
                    teclado = new Scanner(System.in);
                    System.out.println("INGRESO\n");
                    tMovimiento = TipoMovimiento.INGRESO.getTipo();
                    do {
                        System.out.println("INDIQUE LA CANTIDAD QUE VA A INGRESAR. DEBE SER MAYOR QUE 0\n");
                        cantidad = teclado.nextDouble();
                    } while (cantidad <= 0);
                    break;
                case "6":
                    teclado = new Scanner(System.in);
                    System.out.println("REINTEGRO\n");
                    tMovimiento = TipoMovimiento.RETIRADA.getTipo();
                    do {
                        System.out.println("INDIQUE LA CANTIDAD QUE VA A RETIRAR\n");
                        cantidad = teclado.nextDouble();
                    } while (cantidad <= 0);
                    if (saldoNegativo(cantidad, c)){
                        System.out.println("SU OPERACIÓN VA A DEJAR SU CUENTA POR DEBAJO DE -50 EUROS");
                        System.out.println("OPERACIÓN NO PERMITIDA\n");
                    }else{
                        m = new Movimientos(tMovimiento,cantidad);
                        c.reducirSaldo(cantidad);
                        if(c.getSaldo() < 0){
                            System.out.println("AVISO: SALDO NEGATIVO\n");
                        }

                    }
                    break;
                case "7":
                    break;
                case "8":
                    //Opción para salir
                    System.out.println("NO SE OLVIDE DE VOLVER, QUEREMOS SU DINERO");
                    break;
                default:
                    //Cuando se pulsa una opción incorrecta
                    System.out.println("A QUE ESTAMOS JUGANDO. SEA SERIO Y ELIJA UNA OPCIÓN CORRECTA");
                    System.out.println("************************************************************");
                    System.out.println();
                    break;
            }
        }while(!"8".equals(opcion));

    }
    //Creo un método para validar el patrón de un iban de una cuenta estandar
    public static boolean validarFormatoIban(String iban){
        boolean correcto = false;
        //Defino una variable de la clase pattern y un patrón, el del iban
        Pattern p = Pattern.compile("[A-Z]{2}[0-9]{22}");
        correcto = p.matcher(iban).matches();

        return correcto;
    }

    public static boolean saldoNegativo(double cantidad, CuentaBancaria c){
        boolean descubierto = false;
        if(c.getSaldo() + cantidad < -50){
            descubierto = true;
        }
        return descubierto;
    }
}
