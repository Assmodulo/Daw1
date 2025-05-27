package com.videodbd.prepexamenjava;

public class EPOCHSoloUsarSiEstarSeguro {

    //No lo tengo claro del todo todavía pero lo dejo por si hace falta

    LocalDateTime fechaHora = LocalDateTime.now();//Obtener el LocalDateTime

    ZoneId zona = ZoneId.systemDefault();//Con esto defino la zona. Con systemDefault no fallo

    long epochMillis = fechaHora.atZone(zona).toInstant().toEpochMilli(); //Defino un long y almaceno en el LocalDate
                                                                          //pasado a long


    //Recuperarlo de una base de datos
    LocalDateTime recuperado = Instant.ofEpochMilli(epochMillis).atZone(zona).toLocalDateTime();

    //Defino un objeto LocalDateTime y luego obtengo el instante de la variable recuperada, según la zona definida y lo paso
    //a localdatetime

    //Pasar LocalDate a milis o seconds, lo que yo vea. Creo que seconds mejor, voy más rápido
    LocalDate fecha = LocalDate.now();

    // Para segundos desde EPOCH. Lo de atStartofDay, bueno, porque lo tengo aquí y lo he probado. SystemDefault para
    //no indicar región concreta, sino la que marca el ordenador
    long epochSeconds = fecha.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    // Para milisegundos desde EPOCH
    long epochMillis = fecha.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

    //Para recuperar desde BD lo mismo que en LOcalDatetime
}
