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

    //Array para almacenar las letras del dni y hacer los calculos al introducir el nie
    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"};

    //Metodo para generar los codigos de socio y de película de forma automática y secuencial
    public static String formatoCodigo(String letra, int numero) {
        //En principio creo que se puede generar el código de forma automática, ya que no especifica nada el enunciado
        //del ejercicio
        String codigo;
        DecimalFormat df = new DecimalFormat("0000");
        codigo = letra + df.format(numero);
        return codigo;
    }

    //Formateador de fecha
    public static String formatearFecha(LocalDate fecha) {

        return formateador.format(fecha);
    }

    //Formateador de fecha y hora
    public static String formatearFHora(LocalDateTime fecha) {

        return formateador2.format(fecha);
    }

    //Devuelve String de las opciones del menú
    public static String devolverOpcionesMenu(){
        return "ELIIJA SU OPCIÓN\n1.- REGISTRAR VIDEOCLUB\n2.- REGISTRAR PELÍCULA EN VIDEOCLUB\n" +
                "3.- REGISTRAR CLIENTE EN VIDEOCLUB\n4.- ALQUILAR PELÍCULA\n5.- DEVOLVER PELÍCULA\n" +
                "6.- DAR DE BAJA CLIENTE\n7.- DAR DE BAJA PELÍCULA\n8.- SELECCIONAR VIDEOCLUB\n9.- SALIR";
    }

    //Metodo para validar el estado de un videoclub para las comprobaciones
    public static String estadoVideoclub(VideoDaw videoclub){
        String estado = "";
        if(videoclub != null){
            if(videoclub.getClientesTotales() == 0 || videoclub.getPeliculasTotales() == 0){
                estado = "NO HAY CLIENTES O PELICULAS REGISTRADOS EN EL VIDEOCLUB\n" + "NO SE PUEDE REALIZAR UN ALQUILER";
            }
        }else{
            estado = "NO HAY VIDEOCLUB CREADO O SELECCIONADO";
        }
        return estado;
    }

    //Metodo para validar el formato del CIF
    public static String obtenerCif() {
        sc = new Scanner(System.in);
        Pattern patron = Pattern.compile("[A-Z][0-9]{8}");
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

    //Metodo para reducir el número de llamadas al objeto de la clase scanner
    public static String obtenerDatoSolicitado(String dato) {
        sc = new Scanner(System.in);
        System.out.println("INTRODUZCA EL SIGUIENTE DATO: " + dato);
        return sc.nextLine();
    }

    //Necesito un método para mostrar los generos almacenados en el enum
    public static String listadoGeneros() {

        String lista = "";
            //Muestro por pantalla el contenido del enum generos para que se vean los disponibles
            for (int i = 0; i < Generos.values().length; i++) {
                lista += ((i + 1) + " " + Generos.values()[i].getGenero() + "\n");
            }
        return lista;
    }

    //Metodo para devolver el genero elegido por teclado de entre las opciones del enum
    public static String devolverGenero(int posicionEnum){
        return Generos.values()[posicionEnum].getGenero();
    }

    //Metodo para introducir el dni por teclado y calcular la letra correspondiente. Nos devuelve el string completo
    public static String formatoDni(){
        Pattern patron = Pattern.compile("[0-9]{8}");
        Matcher match;
        String dni;

        do {

            dni = obtenerDatoSolicitado("NIE");
            match = patron.matcher(dni);
        } while (!match.matches());

        int dniCalculo = Integer.parseInt(dni);
        dni = dni + letrasDni[dniCalculo%23];
        System.out.println("ESTE ES US DNI " + dni);
        return dni;
    }

    //Metodo que valida la entrada de fecha por teclado y lo transforma en un objeto LocalDate
    public static LocalDate insertarFPorTeclado(){
        LocalDate fecha;
        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        String fechaTeclado;

        do{
            System.out.println("INSERTE LA FECHA CON EL SIGUIENTE FORMATO:\n" +
                    "DD/MM/AAAA");
            fechaTeclado = obtenerDatoSolicitado("FECHA DE NACIMIENTO");
            match = patronFecha.matcher(fechaTeclado);
        }while(!match.matches());
        fecha = LocalDate.parse(fechaTeclado, formateador);
        if(LocalDate.now().minusYears(18).isBefore(fecha)){
            fecha = null;
        }
       return fecha;
    }

    //Metodo para validar la elección de un socio al alquilar una película
    public static boolean validarEleccion(String codigo){
        boolean correcto = false;
        Pattern patronCodigo = Pattern.compile("[0-9]{4}");
        Matcher match;
        match = patronCodigo.matcher(codigo);
        if(match.matches()){
            correcto = true;
        }
        return correcto;
    }

    //Metodo que comprueba si han pasado mas de 48 horas para la devolución de una película
    public static boolean comprobarFechaDevolucion(LocalDateTime fecha){

        return LocalDateTime.now().minusHours(48).isAfter(fecha);
    }
}
