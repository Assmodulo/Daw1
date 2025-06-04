package com.coches.tucochealquiler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyFechas {
    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatoFechaSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static DateTimeFormatter formatoFechaHoraSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter formatoHorario = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatearLocalTimeToSql(LocalTime localTime) {
        return localTime.format(formatoHorario);
    }

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

    public static boolean validarMayorEdad(LocalDate value) {
        return(LocalDate.now().minusYears(18).isAfter(value));
    }

    public static boolean validarFechaCorrecta(LocalDate value) {
        return(value.isAfter(LocalDate.now()));
    }
}
