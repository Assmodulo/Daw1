import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("BIENVENIDO A DAWBANK. TE SANGRAMOS IGUAL QUE LOS DEMÁS, PERO MÁS MAJOS");
        System.out.println("**********************************************************************\n");

        menu1();

    }

    public static void menu1(){
        //Voy a definir un objeto de la clase Scanner
        Scanner teclado = new Scanner(System.in);

        //Declaro una variable String, ya que para hacer un menú, necesito una opción para evaluar
        String opcion;

        //Para el menú voy a tener que crear un bucle do while que se repita constantemente hasta que se seleccione
        //la opción de salir

        //Declaro una variable de la clase CuentaBancaria
        CuentaBancaria c;

        do{
            //Muestro el primer menú y las opciones a elegir
            System.out.println("ELIJA UNA OPCIÓN ACORDE A LO QUE LE MUESTRA EL MENÚ");
            System.out.println("1. - DAR DE ALTA UNA CUENTA NUEVA\n"+
                                "2. - MOSTRAR INFO DE LA CUENTA\n"+
                                "3. - REALIZAR MOVIMIENTO SOBRE LA CUENTA\n"+
                                "4. - SALIR");

            //Hay que indicarlo por teclado, asi que se pide el dato necesario
            opcion = teclado.nextLine();

            switch (opcion){
                case "1":
                    String dato1, dato2;
                    dato1 = CuentaBancaria.insertarIban();
                    dato2 = CuentaBancaria.insertarTitular();
                    c = new CuentaBancaria(dato1, dato2);
                    c.mostrarInformacion();
                    break;
                case "2":
                    break;
                case "3":
                    menu2();
                    break;
                case "4":
                    System.out.println("NO SE OLVIDE DE VOLVER, QUEREMOS SU DINERO");
                    break;
                default:
                    System.out.println("A QUE ESTAMOS JUGANDO. SEA SERIO Y ELIJA UNA OPCIÓN CORRECTA");
                    System.out.println("************************************************************");
                    System.out.println();
                    break;
            }
        }while(!"4".equals(opcion));
    }

    public static void menu2(){
        //Voy a crear un nuevo menú aquí, por lo tanto necesito las variables normales que uso otras veces para hacer
        //un menu
        String opcion;
        Scanner teclado = new Scanner(System.in);

        //Como también tengo que definir un tipo de operación necesito una variable String para almacenar ese caso
        String tipoOperacion;

        //Vamos mostrando al usuario lo que tiene que hacer
        System.out.println("ELIJA LAS OPCIONES NECESARIAS");
        System.out.println("*****************************\n");

        do {
            System.out.println("1. DEPOSITAR\n2. RETIRAR\n3. SALIR");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    tipoOperacion = "DEPOSITO";
                    break;
                case "2":
                    tipoOperacion = "REINTEGRO";
                    break;
                case "3":
                    System.out.println("HASTA EL SIGUIENTE SABLAZO.... DIGO... MOVIMIENTO");
                    System.out.println("*************************************************\n");
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA");
                    break;
            }

        }while(!"3".equals(opcion));

    }
}