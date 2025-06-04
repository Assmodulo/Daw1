package org.example.tucochealquileres;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase está pensada para aquellos datos que tienen un patrón muy concreto dentro de los datos.
 * Se crea una clase solo para este tipo de datos para mantener un poco de limpieza y separarlo de otros datos
 * con patrones más genéricos, por ejemplo aquellos Strings con un patrón menos específico y que suelen ser campos
 * descriptivos
 */
public class ValidacionesYPatronesConcretos {

    /**
     * Array para calcular de forma automática la letra del dni al introducirlo
     */
    private static final String[] letrasDni = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S",
            "Q","V","H","L","C","K","E"};

    /**
     * Este método va a validar un teléfono que se introducirá en la aplicación y validará que está en un formato correcto
     * @param telefono Un String con el valor del número de teléfono que se va a validar
     * @return Un boolean. True si el formato del número de teléfono es correcto, false en caso contrario
     */
    public static boolean validarPatronTelefono(String telefono) {

        //Defino el patrón del número de teléfono
        Pattern patronTelefono = Pattern.compile("[0-9]{9}");

        //Comprobamos el String recibido como parámetro con el patrón que hemos definido
        Matcher matcher = patronTelefono.matcher(telefono);

        //Retornamos el valor de la comprobación, que será true en caso de ser correcto, false en caso contrario
        return matcher.matches();

    }

    /**
     * Método para validar que se ha introducido una matrícula con su formato correcto
     * @param matricula String con el valor de la matrícula a comparar
     * @return Un boolean. True si el formato de la matrícula es correcto, false en caso contrario
     */
    public static boolean validarPatronMatricula(String matricula) {

        //Patrón de una matrícula. El espacio entre medias indica que debe de existir un espacio en blanco entre números y letras
        //Deberiamos de introducir los datos(bloque numérico y letras) por separado en la aplicación. Luego mediante concatenaciones
        //Se puede añadir el espacio en blanco en medio. Si no os apetece lo cambiamos y que vaya todo junto.
        Pattern patronMatricula = Pattern.compile("[0-9]{4} [A-Z]{3}");

        //Como en el metodo anterior comparamos el patrón con el string recibido
        Matcher matcher = patronMatricula.matcher(matricula);

        //retornamos el resultado de matches() y ya según eso trabajamos.
        return matcher.matches();
    }

    /**
     * Método que valida el formato de un Código Postal que hemos introducido en la aplicación y que se va a intentar
     * validar
     * @param postal String con el valor a comparar de un Código Postal
     * @return Un Boolean. True si el valor se ha introducido en un formato correcto, false en caso contrario
     */
    public static boolean validarCodigoPostal(String postal) {

        /*
         * No quiero comentar de más. Lo mismo que en los método anteriores
         */

        Pattern patronPostal = Pattern.compile("[0-9]{5}");

        Matcher matcher = patronPostal.matcher(postal);

        return matcher.matches();
    }

    /**
     * Método que simplemente va a validar que se haya introducido un formato de dato válido para el NIE, la letra se calcula
     * por separado
     * @param nie Un String que se va a comparar con el patrón correspondiente para ver si es correcto
     * @return Un booleano que nos devuelve true si el String está en formato correcto, false en caso contrario
     */
    public static boolean validarFormatoNie(String nie){

        /*
        De nuevo me ahorro el exceso de comentarios
         */
        Pattern patronNie = Pattern.compile("[0-9]{8}");

        Matcher matcher = patronNie.matcher(nie);

        return matcher.matches();

    }

    /**
     * Este método simplemente nos devuelve la letra correspondiente al valor del nie que hemos introducido
     * @param nie recibe un String que será transformado a un int para luego calcular con ese número la letra correspondiente
     *            almacenada en un array según el valor del resto de dividir ese número por 23
     * @return Una letra que es la correspondiente de un NIF según el valor del NIE
     */
    public static String obtenerLetraDni(String nie) {


        int numero;
        String letra = "";
        try {
            numero = Integer.parseInt(nie);
            letra = letrasDni[numero % 23];
        } catch (NumberFormatException e) {
            letra = "";
        }

        return letra;
    }

    public static boolean validarMatricula(String text) {
        return false;
    }
}
