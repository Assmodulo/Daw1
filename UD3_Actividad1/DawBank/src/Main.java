import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Voy a definir un objeto de la clase Scanner
        Scanner teclado = new Scanner(System.in);

        //Declaro una variable String, ya que para hacer un menú, necesito una opción para evaluar
        String opcion;

        //Declaro una variable de la clase CuentaBancaria
        CuentaBancaria c;

        //Por ahora en principio, en esta clase no voy a necesitar más variables, ya que creo que todas las que necesite
        //las definiré como variables locales en los métodos que necesite

        System.out.println("BIENVENIDO A DAWBANK. TE SANGRAMOS IGUAL QUE LOS DEMÁS, PERO MÁS MAJOS");
        System.out.println("**********************************************************************\n");

        //Para el menú voy a tener que crear un bucle do while que se repita constantemente hasta que se seleccione
        //la opción de salir

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
                    break;
                case "2":
                    break;
                case "3":
                    c.menuMovimientos();
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
}