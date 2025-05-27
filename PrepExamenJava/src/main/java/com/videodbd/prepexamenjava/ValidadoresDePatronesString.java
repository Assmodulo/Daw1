package com.videodbd.prepexamenjava;

public class ValidadoresDePatronesString {

    //Solo hace falta poner uno. Los demás serían iguales. Se puede hacer así o con los métodos de Anuar
    public static String insertarDni(){
        Scanner sc;

        Pattern formatoDni = Pattern.compile("[0-9]{8}");

        Matcher match;

        String dni = "";


        do{
            sc = new Scanner(System.in);

            System.out.println("Introduzca los 8 dígitos que componen su dni, sin la letra");

            dni = sc.nextLine();

            match = formatoDni.matcher(dni);

        }while(!match.matches());

        int numero = Integer.parseInt(dni);

        dni = dni + letrasDni[numero % 23];

        return dni;
    }

}
