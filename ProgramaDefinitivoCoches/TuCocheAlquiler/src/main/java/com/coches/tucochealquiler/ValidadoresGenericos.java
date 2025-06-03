package com.coches.tucochealquiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadoresGenericos {
    public static boolean validarVarchar50(String varchar50) {

        //Defino el patrón de los varchar de 50

        Pattern patronVarchar50 = Pattern.compile("[a-z A-Z]{2,50}");

        //Comprobamos el String recibido como parámetro con el patrón que hemos definido
        Matcher matcher = patronVarchar50.matcher(varchar50);
        //Retornamos el valor de la comprobación, que será true en caso de ser correcto, false en caso contrario
        return matcher.matches();
    }


    public static boolean validarVarchar100(String varchar100) {

        //Defino el patrón de los varchar de 100

        Pattern patronVarchar100 = Pattern.compile("[a-z A-Z]{2,100}");

        //Comprobamos el String recibido como parámetro con el patrón que hemos definido
        Matcher matcher = patronVarchar100.matcher(varchar100);
        //Retornamos el valor de la comprobación, que será true en caso de ser correcto, false en caso contrario
        return matcher.matches();
    }

    public static boolean validarVarchar30(String varchar30) {

        //Defino el patrón de los varchar de 30

        Pattern patronVarchar30 = Pattern.compile("[a-z A-Z]{2,30}");

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

    public static boolean validarEmail(String text) {
        return text.matches("[A-Za-z_.-]{8,30}[@][a-z]{2,15}[.][a-z]{2,5}");
    }
}
