package com.videodbd.prepexamenjava;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadoresYPatronesDeFechas {

    //Formateadores en el caso de que me hagan falta

    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatoFechaSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static DateTimeFormatter formatoFechaHoraSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    //función que me puede hacer falta, aunque en javaFX no creo

    public static LocalDate insertarFecha()throws MenorDeEdadException, FechaPosteriorActualException{
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


    //Los diferentes métodos que me devuelven Strings tanto para interfaz como para la BD

    public static String formatearFecha(LocalDate date){
        return formatoFecha.format(date);
    }

    public static String formatearFechaSQL(LocalDate date){
        return formatoFechaSQL.format(date);
    }

    public static String formatearFechaHora(LocalDateTime date){
        return formatoFechaHora.format(date);
    }

    public static String formatearFechaHoraSQL(LocalDateTime date){
        return formatoFechaHoraSQL.format(date);
    }

    //Este método sólo está aquí para recordar como trabajar con localDates

    public static String validarPeriodoDeDevolucion(Alquiler al) {

        String aviso = "";

        if(LocalDateTime.now().minusDays(2).isAfter(al.getF_alquiler())){
            aviso = "Se ha pasado el límite de 2 días y debe de pagar cargo adicional al devolver el producto";
        }else{
            aviso = "Se ha devuelto el artículo dentro del periodo normal";
        }

        return aviso;
    }


}
