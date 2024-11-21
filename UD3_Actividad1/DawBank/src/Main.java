import java.util.Scanner;

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

        //Para el menú voy a tener que crear un bucle do while que se repita constantemente hasta que se seleccione
        //la opción de salir

        CuentaBancaria c = new CuentaBancaria();

        System.out.println("LO PRIMERO ES CREARSE UNA CUENTA. VAMOS A SOLICITARLE UNA SERIE DE DATOS\n");
        System.out.println("PRIMERO NECESITAMOS EL IBAN. INTRODUZCA DOS LETRAS SEGUIDAS DE 22 DÍGITOS\n"
        +"SI EL FORMATO NO ES CORRECTO EL PROGRAMA SE LO PEDIRÁ OTRA VEZ");

        //Declaro dos variable String para almacenar el iban y el titular
        String iban, titular;

        boolean correcto = false;

        do{
            teclado = new Scanner(System.in);

        }


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
                    break;
                case "2":
                    System.out.println("ESTA ES LA INFORMACIÓN DE LA CUENTA EN CUESTIÓN");
                    System.out.println(c.mostrarInformacion());
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
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
        }while(!"5".equals(opcion));
    }

}



