import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
    //Esta clase es para métodos estáticos que necesite utilizar
    private static Scanner sc;
    private static DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formateador2 = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");

    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"};

    public static String formatoCodigo(String letra, int numero) {
        //En principio creo que se puede generar el código de forma automática, ya que no especifica nada el enunciado
        //del ejercicio
        String codigo;
        DecimalFormat df = new DecimalFormat("0000");
        codigo = letra + df.format(numero);
        return codigo;
    }


    public static String formatearFecha(LocalDate fecha) {

        return formateador.format(fecha);
    }

    public static String formatearFHora(LocalDateTime fecha) {

        return formateador2.format(fecha);
    }

    public static String obtenerCif() {
        sc = new Scanner(System.in);
        Pattern patron = Pattern.compile("[A-Z]{1}[0-9]{8}");
        Matcher match;
        String cif;
        do {
            System.out.println("INTRODUZCA UN VALOR VÁLIDO PARA EL CIF: UNA LETRA Y OCHO DÍGITOS");
            cif = sc.nextLine().toUpperCase();
            match = patron.matcher(cif);
        } while (!match.matches());
        System.out.println("CIF INTRODUCIDO CORRECTO");
        return cif;
    }

    public static String obtenerDireccion() {
        sc = new Scanner(System.in);
        System.out.println("INDIQUE LA DIRECCIÓN DEL ESTABLECIMIENTO");
        return sc.nextLine();
    }

    //Necesito un método para asignar un género a una película asi que voy a crear uno
    public static String asignarGenero() {
        sc = new Scanner(System.in);
        String opcionElegida;
        String genero = "";
        boolean salir = false;
        do {
            System.out.println("LISTA DE GÉNEROS DISPONIBLES\n");
            //Muestro por pantalla el contenido del enum generos para que se vean los disponibles
            for (int i = 0; i < Generos.values().length; i++) {
                System.out.println((i + 1) + " " + Generos.values()[i].getGenero() + " ");
            }
            System.out.println();
            System.out.println("ELIJA UNO DE LOS GÉNEROS DISPONIBLES");
            opcionElegida = sc.nextLine();
            switch (opcionElegida) {
                case "1":
                    genero = Generos.values()[0].getGenero();
                    salir = true;
                    break;
                case "2":
                    genero = Generos.values()[1].getGenero();
                    salir = true;
                    break;
                case "3":
                    genero = Generos.values()[2].getGenero();
                    salir = true;
                    break;
                case "4":
                    genero = Generos.values()[3].getGenero();
                    salir = true;
                    break;
                case "5":
                    genero = Generos.values()[4].getGenero();
                    salir = true;
                    break;
                case "6":
                    genero = Generos.values()[5].getGenero();
                    salir = true;
                    break;
                case "7":
                    genero = Generos.values()[6].getGenero();
                    salir = true;
                    break;
                case "8":
                    genero = Generos.values()[7].getGenero();
                    salir = true;
                    break;
                default:
                    System.out.println("OPCION NO VALIDA");
                    break;
            }
        } while (!salir);
        return genero;
    }

    public static String formatoDni(){
        Pattern patron = Pattern.compile("[0-9]{8}");
        Matcher match;
        Scanner sc = new Scanner(System.in);
        String dni;

        do {
            System.out.println("INTRODUZCA SU NIE");
            dni = sc.nextLine();
            match = patron.matcher(dni);
        } while (!match.matches());

        int dniCalculo = Integer.parseInt(dni);
        dni = dni + letrasDni[dniCalculo%23];
        System.out.println("ESTE ES US DNI " + dni);
        return dni;
    }

    public static LocalDate insertarFPorTeclado(){
        LocalDate fecha;
        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        String fechaTeclado;
        sc = new Scanner(System.in);
        do{
            System.out.println("INSERTE LA FECHA CON EL SIGUIENTE FORMATO:\n" +
                    "DD/MM/AAAA");
            fechaTeclado = sc.nextLine();
            match = patronFecha.matcher(fechaTeclado);
        }while(!match.matches());
        fecha = LocalDate.parse(fechaTeclado, formateador);
        if(fecha.minusYears(18).isAfter(LocalDate.now())){
            return fecha;
        }
       return null;
    }

    //Metodo para validad la elección de un socio al alquilar una película
    public static boolean validarEleccionSocio(String codigo){
        boolean correcto = false;
        Pattern patronCodigo = Pattern.compile("[0-9]{4}");
        Matcher match;
        match = patronCodigo.matcher(codigo);
        if(match.matches()){
            correcto = true;
        }
        return correcto;
    }

    //Metodo para validar el código de una película cuando se quiere alquilar, es lo mismo que con socios
    //Pero por ahora he preferido no pensar en un método para las dos
    public static boolean validarEleccionPelicula(String codigo){
        boolean correcto = false;
        Pattern patronCodigo = Pattern.compile("[0-9]{4}");
        Matcher match;
        match = patronCodigo.matcher(codigo);
        if(match.matches()){
            correcto = true;
        }
        return correcto;
    }

    public static boolean comprobarFechaDevolucion(LocalDateTime fecha){
        boolean correcto = false;
        if(LocalDateTime.now().minusHours(48).isAfter(fecha)){
            correcto = true;
        }
        return correcto;
    }
}
