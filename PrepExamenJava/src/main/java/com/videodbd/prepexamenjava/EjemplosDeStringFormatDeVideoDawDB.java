package com.videodbd.prepexamenjava;

public class EjemplosDeStringFormatDeVideoDawDB {

    //Un ejemplo de String.format que use en VideoDawDB. Lo malo es que son todo strings, no se que tal irá con números
    //Voy a buscar ejemplos por si hace falta.
    return String.format("%-8S | %-10S | %-25S | %-30S | %-10S | %-10S | %-10S | %-6S",
            this.cod_cliente, this.dni, this.nombre, this.apellidos, MyUtils.formatearFecha(this.f_nacimiento),
            MyUtils.formatearFecha(this.f_alta),f_baja, this.telefono);


    /*
    %d para ints. Con un número antes para que tenga ese número de dígitos, con un -, alineado a la izquierda
    %s para Strings. Numero antes el número de espacios a ocupar, con un - alineado a la izquierda
    %f para floats. %.2f, dos decimales y así el resto, el menos como los otros
    %b para boolean. No lo he usado nunca pero ahí está.

    en los números si pongo un 0 después del %, rellenará los espacios no ocupados con ceros, como DecimalFormat
    Creo que con esto, más que suficiente.
     */
}
