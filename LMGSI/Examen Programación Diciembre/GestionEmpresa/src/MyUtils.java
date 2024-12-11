import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

public class MyUtils {

    public static Scanner teclado;
    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"}; //Calcular de forma automática la letra del dni

    private static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Muestra el menu de inicio
    public static String devolverOpcionesMenu(){
        return "ELIIJA SU OPCIÓN\n1.- CEAR PERSONA\n2.- REGISTRAR TRABAJADOR EN LA EMPRESA\n" +
                "3.- MOSTRAR INFORMACIÓN GENERAL DE LA EMPRESA\n4.- MOSTRAR EL NÚMERO DE TRABAJADORES ACTUALES\n" +
                "5.- MOSTRAR INFORMACIÓN DE TODOS LOS TRABAJADORES\n6.- ELIMINAR TRABAJADORES DE LA EMPRESA\n" +
                "7.- ELIMINAR PERSONA DEL PROGRAMA\n8.- SALIR";
    }
    public static String imprimirPantalla(String dato){
        return "INTRODUZCA EL SIGUIENTE DATO: " + dato;
    }
    public static String devolverDato(){
        teclado = new Scanner(System.in);
        return teclado.nextLine();
    }

    public static String formatearFecha(LocalDate fecha) {

        return formateador.format(fecha);
    }

    //Metodo para validar el formato del CIF
    public static String obtenerCif() {
        teclado = new Scanner(System.in);
        Pattern patron = Pattern.compile("[A-Z][0-9]{10}");
        Matcher match;
        String cif;
        do {
            System.out.println("INTRODUZCA UN VALOR VÁLIDO PARA EL CIF: UNA LETRA Y DIEZ DÍGITOS");
            cif = teclado.nextLine().toUpperCase();
            match = patron.matcher(cif);
        } while (!match.matches());
        System.out.println("CIF INTRODUCIDO CORRECTO");
        System.out.println();
        return cif;
    }

    public static String validarPatronFecha(){
        teclado = new Scanner(System.in);
        String fecha;
        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        do {
            System.out.println(imprimirPantalla("FECHA CON FORMATO DD/MM/YYYY"));
            fecha = devolverDato();
            match = patronFecha.matcher(fecha);
        } while (!match.matches());
        System.out.println("FECHA INTRODUCIDA CORRECTA");
        System.out.println();
        return fecha;
    }

    public static LocalDate transformarFecha(String fecha){
        return LocalDate.parse(fecha, formateador);
    }

    public static int validadNumeroTrabajadores(String dato){
        int numero = 0;
        if(Integer.parseInt(dato) > 0){
            numero = Integer.parseInt(dato);
        }
        return numero;
    }

    public static String formatoDni(){
        Pattern patron = Pattern.compile("[0-9]{8}");
        Matcher match;
        String dni;

        do {
            System.out.println(imprimirPantalla("NIE"));
            dni = devolverDato();
            match = patron.matcher(dni);
        } while (!match.matches());

        int dniCalculo = Integer.parseInt(dni);
        dni = dni + letrasDni[dniCalculo%23];
        System.out.println("ESTE ES US DNI " + dni);
        return dni;
    }

    public static String formatoNumSegSocial(int numero) {
        //En principio creo que se puede generar el código de forma automática, ya que no especifica nada el enunciado
        //del ejercicio
        String codigo;
        DecimalFormat df = new DecimalFormat("0000000000");
        codigo = df.format(numero);
        return codigo;
    }
}
