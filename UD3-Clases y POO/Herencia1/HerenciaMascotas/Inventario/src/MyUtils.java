import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    static Scanner sc;
    static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Formateador de fecha
    public static String formatearFecha(LocalDate fecha) {

        return formateador.format(fecha);
    }

    public static String calcularEdad(LocalDate fecha){
        String edad;
        int anios = LocalDate.now().getYear() - fecha.getYear();
        edad = String.valueOf(anios);
        return edad;
    }

    //Metodo que valida la entrada de fecha por teclado y lo transforma en un objeto LocalDate
    public static LocalDate insertarFPorTeclado(){
        LocalDate fecha;
        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        String fechaTeclado;
        sc = new Scanner(System.in);
        do{
            System.out.println("INSERTE LA FECHA DE NACIMIENTO DE SU MASCOTA CON EL SIGUIENTE FORMATO:\n" +
                    "DD/MM/AAAA");
            fechaTeclado = sc.nextLine();
            match = patronFecha.matcher(fechaTeclado);
        }while(!match.matches());
        fecha = LocalDate.parse(fechaTeclado, formateador);
        return fecha;
    }

    //Seleccionar raza de perro
    public static String razaDePerro(){
        String listadoRazas ="";
        for(int i = 0; i < RazasPerro.values().length; i++){
            listadoRazas += (i + 1) + " " + RazasPerro.values()[i].toString() + "\n";
        }
        return listadoRazas;
    }

    public static String seleccionarRaza(int seleccion){
        return RazasPerro.values()[seleccion].toString();
    }

    public static String color(){
        String color;
        System.out.println("INDIQUE EL COLOR DE SU MININO");
        color = sc.nextLine();
        return color;
    }

    public static String pico(){
        String pico;
        System.out.println("INDIQUE EL TIPO DE PICO DE SU PAJARO");
        pico = sc.nextLine();
        return pico;
    }

    public static String origen(){
        String origen;
        System.out.println("INDIQUE EL ORIGEN DE SU PAJARO");
        origen = sc.nextLine();
        return origen;
    }

    public static boolean pelaje(){
        boolean pelaje = false;
        String seleccion = "";
        sc = new Scanner(System.in);
        do{
            System.out.println("COMO ES EL PELO DE SU GATO?: 1. CORTO 2. LARGO");
            seleccion = sc.nextLine();
        }while(Integer.parseInt(seleccion) < 1 || Integer.parseInt(seleccion) > 2);
        if(seleccion.equals("2")){
            pelaje = true;
        }
        return pelaje;
    }

    /*public static int seleccionarTipoMascota(){

    }*/

}
