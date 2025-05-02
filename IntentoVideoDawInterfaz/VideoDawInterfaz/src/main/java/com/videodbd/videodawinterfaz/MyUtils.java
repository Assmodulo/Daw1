package com.videodbd.videodawinterfaz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    public static void validarFormatoCif(String cif) throws ErrorFormatoCifException {
        Pattern patronCif = Pattern.compile("[a-zA-Z][0-9]{8}");
        Matcher match;

        match = patronCif.matcher(cif);

        if(!match.matches()){
            throw new ErrorFormatoCifException("Formato del CIF erroneo");
        }
    }

    public static void validarFormatosNombDirVC(String dato) throws ErrorFormatoNomDirException {
        Pattern patronNomDir = Pattern.compile("[A-Z a-z]{8,50}");

        Matcher match;

        match = patronNomDir.matcher(dato);

        if(!match.matches()){
            throw new ErrorFormatoNomDirException("Error en el formato del dato");
        }

    }

    public static void validarFormatoTelefonoVC(String telefono) throws ErrorFormatoTelefonoException {
        Pattern patronTlf = Pattern.compile("[0-9]{9}");

        Matcher match;

        match = patronTlf.matcher(telefono);

        if(!match.matches()){
            throw new ErrorFormatoTelefonoException("El teléfono deben ser 9 dígitos");
        }
    }

    public static void pasarDatosParaGuardarBD(String cif, String nombre, String direccion, String telefono) {
        Videoclub videoclub = new Videoclub(cif, nombre, direccion, telefono);

        DBoperations db = new DBoperations();

        db.insertarNuevoVideoClub(videoclub);
    }
}
