package org.example.tucochealquileres;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validacionesGenerales {
    public static boolean validarVarchar50(String varchar50) {

        //Defino el patrón de los varchar de 50
<<<<<<< HEAD
        Pattern patronVarchar50 = Pattern.compile("[a-z A-Z]{5,50}");
=======
        Pattern patronVarchar50 = Pattern.compile("[A-Za-z ]{5,50}");
>>>>>>> 4f036a8f1e410ffa80e1e829df1c5ba72fef0911
        //Comprobamos el String recibido como parámetro con el patrón que hemos definido
        Matcher matcher = patronVarchar50.matcher(varchar50);
        //Retornamos el valor de la comprobación, que será true en caso de ser correcto, false en caso contrario
        return matcher.matches();
    }


    public static boolean validarVarchar100(String varchar100) {

        //Defino el patrón de los varchar de 100
<<<<<<< HEAD
        Pattern patronVarchar100 = Pattern.compile("[a-z A-Z]{5,100}");
=======
        Pattern patronVarchar100 = Pattern.compile("[A-Za-z ]{5,100}");
>>>>>>> 4f036a8f1e410ffa80e1e829df1c5ba72fef0911
        //Comprobamos el String recibido como parámetro con el patrón que hemos definido
        Matcher matcher = patronVarchar100.matcher(varchar100);
        //Retornamos el valor de la comprobación, que será true en caso de ser correcto, false en caso contrario
        return matcher.matches();
    }

    public static boolean validarVarchar30(String varchar30) {

        //Defino el patrón de los varchar de 30
<<<<<<< HEAD
        Pattern patronVarchar30 = Pattern.compile("[a-z A-Z]{5,30}");
=======
        Pattern patronVarchar30 = Pattern.compile("[A-Za-z ]{5,30}");
>>>>>>> 4f036a8f1e410ffa80e1e829df1c5ba72fef0911
        //Comprobamos el String recibido como parámetro con el patrón que hemos definido
        Matcher matcher = patronVarchar30.matcher(varchar30);
        //Retornamos el valor de la comprobación, que será true en caso de ser correcto, false en caso contrario
        return matcher.matches();
    }


        public static boolean validarHoraHHmm(String hora) {

            Pattern patronHora = Pattern.compile("[0-9]{2}:[0-9]{2}");

            Matcher matcher = patronHora.matcher(hora);

            return matcher.matches();
        }


    }

