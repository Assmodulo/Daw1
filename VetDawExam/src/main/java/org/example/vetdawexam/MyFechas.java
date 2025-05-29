package org.example.vetdawexam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyFechas {

    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatoFechaSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static DateTimeFormatter formatoFechaHoraSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

    public static boolean validarFechaAnterior(String fecha) throws FechaIncorrectaException {
        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatoFecha);

        boolean correcto = false;

        if(fechaNacimiento.isAfter(LocalDate.now())){
            throw new FechaIncorrectaException("Fecha posterior a la actual");
        }else{
            correcto = true;
        }
        return correcto;
    }

}
