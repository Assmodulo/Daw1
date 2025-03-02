import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class MyUtils {

    private static Scanner sc;
    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Array para calcular de forma automática la letra del dni al introducirlo
     */
    private static String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"};

    /**
     * Método para introducir el nombre de un usuario por teclado y comprobar que tenga un formato indicado
     * @return String, con el valor del nombre
     */
    public static String insertarNombre(){
        String nombre = "";
        Pattern patronNombre = Pattern.compile("[A-Z a-z]{8,25}");
        Matcher match;
        do{
            sc = new Scanner(System.in);
            System.out.println("Ingrese el nombre de la persona");
            nombre = sc.nextLine();
            match = patronNombre.matcher(nombre);
        }while(!match.matches());
        return nombre.toUpperCase();
    }


    /**
     * Metodo para introducir el dni por teclado, solo los dígitos, y que nos devuelve el dni completo con su letra
     * correspondiente, basado en el cálculo estandar.
     * @return String que es el valor del dni completo
     */
    public static String insertarDni(){
        Pattern formatoDni = Pattern.compile("[0-9]{8}");
        Matcher match;
        String dni = "";
        sc = new Scanner(System.in);
        do{
            System.out.println("Introduzca los dígitos que componen su dni, sin la letra");
            dni = sc.nextLine();
            match = formatoDni.matcher(dni);
        }while(!match.matches());
        int numero = Integer.parseInt(dni);
        dni = dni + letrasDni[numero % 23];
        return dni;
    }

    /**
     * Método para introducir la dirección de un posible socio por teclado validando un patrón determinado
     * @return String, con el valor de la dirección
     */
    public static String insertarDireccion(){
        sc = new Scanner(System.in);
        String direccion = "";
        Pattern patronDireccion = Pattern.compile("[A-Z a-z]{8,25}[0-9]{0,3}");
        Matcher match;
        do{
            System.out.println("Ingrese la dirección solicitada");
            direccion = sc.nextLine();
            match = patronDireccion.matcher(direccion);
        }while(!match.matches());
        return direccion.toUpperCase();
    }


    public static LocalDate insertarFecha(){
        sc = new Scanner(System.in);
        String fecha = "";
        Pattern patronFecha = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher match;
        do{
            System.out.println("INSERTE LA FECHA CON EL SIGUIENTE FORMATO:\n" +
                    "DD/MM/AAAA");
            fecha = sc.nextLine();
            match = patronFecha.matcher(fecha);
        }while(!match.matches());

        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatoFecha);

        validarFechaIntroducida(fechaNacimiento);

        return LocalDate.parse(fecha, formatoFecha);
    }


    private static void validarFechaIntroducida(LocalDate fecha) throws MenorDeEdadException, FechaPosteriorActualException{
        if(fecha.isAfter(LocalDate.now())){
            throw new FechaPosteriorActualException();
        }else if ((LocalDate.now().getYear() - fecha.getYear()) < 18){
            throw new MenorDeEdadException();
        }
    }

    /**
     * Metodo para insertar por teclado el CIF de un videoclub y validar a su vez que el formato sea el correcto
     * @return String, valor del CIF
     */
    public static String insertarCif(){
        sc = new Scanner(System.in);
        Pattern formatoCif = Pattern.compile("[a-z][0-9]{8}");
        Matcher match;
        String cif = "";
        do{
            System.out.println("Inserte el CIF del videoclub");
            cif = sc.nextLine();
            match = formatoCif.matcher(cif);
        }while(!match.matches());
        return cif.toUpperCase();
    }

    /**
     * Metodo para transformar un LocalDate en un String
     * @param date, un objeto LocalDate
     * @return String, la fecha recibida por parametro como un String con un formato determinado
     */
    public static String formatearFecha(LocalDate date){
        return formatoFecha.format(date);
    }

    /**
     * Metodo para transformar un LocalDateTime en un String
     * @param date, un objeto LocalDateTime
     * @return String, la fecha y horas recibidas por parametro como un String con un formato concreto
     */
    public static String formatearFechaHora(LocalDateTime date){
        return formatoFechaHora.format(date);
    }

    /**
     * Método para elegir los distintos géneros de artículos, tanto si son películas como videojuegos
     * @param opcion este int nos indicará si va a ser un listado de películas o videojuegos
     *
     * @return un listado, de videojuegos o películas, según la opción que se manda como parámetro
     */
    public static String listadoGeneros(int opcion) {

        String lista = "Elija uno de los siguiente géneros\n";

        switch(opcion){
            case 1:
                //Muestro por pantalla el contenido del enum generospeliculas para que se vean los disponibles
                for (int i = 0; i < GenerosPeliculas.values().length; i++) {
                    lista += ((i + 1) + " " + GenerosPeliculas.values()[i] + "\n");
                }
                break;
            case 2:
                //Muestro por pantalla el contenido del enum generospeliculas para que se vean los disponibles
                for (int i = 0; i < GenerosVidejuegos.values().length; i++) {
                    lista += ((i + 1) + " " + GenerosVidejuegos.values()[i] + "\n");
                }
                break;
        }

        return lista;
    }

    /**
     * Método que nos devuelve el genero de una película para crear un objeto de tipo Película
     * @param posicionEnum Recibe un int que nos indicará la posición del enum que necesitamos
     * @return un objeto de tipo GeneroPelicula del enum que tenemos creado
     */
    public static GenerosPeliculas devolverGeneroP(int posicionEnum){
        return GenerosPeliculas.values()[posicionEnum];
    }

    /**
     * Método que nos devuelve el genero concreto de un videojuego para crear un objeto de tipo videojuego
     * @param posicionEnum Recibe un int que nos indicará la posición del enum que necesitamos
     * @return un objeto de tipo GeneroVideojuegos que tenemos creado
     */
    public static GenerosVidejuegos devolverGeneroV(int posicionEnum){
        return GenerosVidejuegos.values()[posicionEnum];
    }

    /**
     * Método que genera todos los códigos necesarios en el programa de forma automática
     * @param letra Una letra que nos va a indicar el tipo de elemento
     * @param numero Un número que pasará por el decimal format para que nos quede de una forma determinada
     * @return Un string con el valor del código en cuestión
     */
    public static String generadorCodigos(String letra, int numero){
        DecimalFormat df = new DecimalFormat("0000");
        return letra + df.format(numero + 1);
    }
}
