import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"};

    public static String insertarDni(){
        Pattern formatoDni = Pattern.compile("[0-9]{8}");
        Matcher match;
        String dni = "";
        Scanner sc ;
        do{
            sc = new Scanner(System.in);
            System.out.println("Introduzca los dígitos que componen su dni, sin la letra");
            dni = sc.nextLine();
            match = formatoDni.matcher(dni);
        }while(!match.matches());
        int numero = Integer.parseInt(dni);
        dni = dni + letrasDni[numero % 23];
        return dni;
    }

    public static LocalDate insertarFecha() throws FechaPosteriorActualException{
        Scanner sc;
        String fecha = "";
        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        do{
            sc = new Scanner(System.in);
            System.out.println("INSERTE LA FECHA CON EL SIGUIENTE FORMATO:\n" +
                    "DD/MM/AAAA");
            fecha = sc.nextLine();
            match = patronFecha.matcher(fecha);
        }while(!match.matches());

        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatoFecha);
        validarFechaIntroducida(fechaNacimiento);

        return LocalDate.parse(fecha, formatoFecha);
    }

    public static void validarFechaIntroducida(LocalDate fechaNacimiento) throws FechaPosteriorActualException {
        if(fechaNacimiento.isAfter(LocalDate.now())){
            throw new FechaPosteriorActualException();
        }
    }


    public static String insertarCif() throws FormatoCifIncorrectoException{
        Scanner sc = new Scanner(System.in);
        Pattern formatoCif = Pattern.compile("[a-z][0-9]{10}");
        Matcher match;
        String cif = "";
            System.out.println("Inserte el CIF de la Empresa");
            cif = sc.nextLine();
            match = formatoCif.matcher(cif);
        if (!match.matches()) {
            throw new FormatoCifIncorrectoException();
        }
        return cif.toUpperCase();
    }

    public static Departamentos seleccionarDepartamento(){
        Scanner sc = new Scanner(System.in);

        Departamentos departamentoElegido = null;

        int opcionNumerica = -1;

        for(int i = 0; i < Departamentos.values().length; i++ ){
            System.out.println((i+1) + " " +Departamentos.values()[i]);
        }

        do{
            System.out.println("Seleccione un Departamento");
            try {
                opcionNumerica = sc.nextInt();
                opcionNumerica -= 1;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }while(opcionNumerica < 0 || opcionNumerica > Departamentos.values().length - 1 );

        return Departamentos.values()[opcionNumerica];
    }


    public static String formatearFecha(LocalDate fecha){
        return formatoFecha.format(fecha);
    }

    public static String formatearFechaHora(LocalDateTime fecha){
        return formatoFechaHora.format(fecha);
    }
}
