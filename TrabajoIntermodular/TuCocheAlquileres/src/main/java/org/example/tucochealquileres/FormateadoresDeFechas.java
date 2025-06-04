package org.example.tucochealquileres;

import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * En esta clase vamos a incluir diferentes métodos y formateadores para trabajar con campos de tipo LocalDate de java
 * y Date de MySQL y habrá que mirar también los de LocalTime o cambiarlos a LocalDate Time
 */
public class FormateadoresDeFechas {

    /*
    Defino varios formateadores preparados para, o bien devolver el String de un LocalDate con el formato que nos interesa
    en Java, o para pasar un LocalDate a un formato que funciones en MySQL, ya que su formato es diferente
     */
    public static DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatoFechaSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static DateTimeFormatter formatoFechaHoraSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter formatoHoraJavaSQL = DateTimeFormatter.ofPattern("HH:mm:ss");
    /**
     * Método que sirve para convertir un objeto de tipo LocalDate a un formato String adecuado para Java
     * @param date Un LocalDate en un formato de Java
     * @return Un String que muestra el LocalDate en el formato que le hemos indicado
     */
    public static String formatearFecha(LocalDate date){
        return formatoFecha.format(date);
    }

    /**
     * Método que sirve para convertir un objeto de tipo LocalDate a un String de tipo Date de MySQL y que no de fallo
     * @param date Un LocalDate en un formato de Java
     * @return un String con el formato de MySQL
     */
    public static String formatearFechaSQL(LocalDate date){
        return formatoFechaSQL.format(date);
    }

    /**
     * Método que sirve para convertir un objeto LocalDateTime a un String en un formato determinado
     * @param date Un objeto LocalDateTime en un formato de Java
     * @return un string con el formato que le hemos indicado
     */
    public static String formatearFechaHora(LocalDateTime date){
        return formatoFechaHora.format(date);
    }

    /**
     * Método que nos sirve para convertir un objeto LocalDateTime de Java en un objeto Date de MySQL
     * @param date Un LocalDateTime en un formate de Java
     * @return Un String con un formato adecuado para MySQL
     */
    public static String formatearFechaHoraSQL(LocalDateTime date){
        return formatoFechaHoraSQL.format(date);
    }

    /**
     * Método que nos sirve para convertir un objeto LocalTime de java en un String apto para java y para
     * un dato de tipo Time de MySQL
     * @param time un objeto de tipo time
     * @return un string válido tanto en java como en MySql
     */
    public static String formatearHora(LocalTime time){
        return formatoHoraJavaSQL.format(time);
    }

}
