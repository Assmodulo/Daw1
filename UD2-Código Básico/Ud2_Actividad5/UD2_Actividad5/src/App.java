import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        // Defino como simpre lo primero un objeto de la clase Scanner
        Scanner teclado = new Scanner(System.in);

        // Realiza los siguientes ejercicios relacionados con la segunda unidad
        // didáctica.
        // NOTA : Utiliza métodos de la clase ‘ String ’ cuando sea necesario para
        // ayudarte a resolverlos
        // 1. Crea un programa que pida una cadena de texto por teclado y luego muestre
        // cada
        // palabra de la cadena en una línea distinta.

        String cadena; // Defino una variable de tipo String que almacenará la cadena que voy a
                       // introducir

        System.out.println(
                "El programa le va a solicitar que incluya una cadena y luego mostrará esa misma cadena pero con cada palabra en una linea nueva");

        System.out.println("Introduzca una nueva cadena de texto");

        cadena = teclado.nextLine();

        String cadena2; // Defino una segunda variable de tipo String para almacenar la cadena
                        // modificada

        // Como se explicó en clase el otro dia voy a hacerlo primero con el método split
        //Defino primero el array en el que lo va a almacenar, luego lo recoreré con un for:each

        String[] palabras = cadena.split(" ");

        for(String palabra:palabras){
            System.out.println(palabra);
        }


        /*
         * Con la funcion replace consigo que se cambien los espacios en blanco por el
         * caracter de escape del salto de linea y
         * lo almacena en una segunda cadena.
         */
        cadena2 = cadena.replace(" ", "\n");

        // Muestro por pantalla el resultado, primero hago un sysout para que quede un
        // espacio con la accion anterior y se vea mejor

        System.out.println();

        System.out.println(cadena2);

        // Sysout y syserr para dejar un espacio con el ejercicio siguiente y que se vea
        // bien

        System.out.println();
        System.err.println("********SALTO DE EJERCICIO************");
        System.out.println();

        // 2. Crea un programa que pida dos cadenas de texto por teclado y luego indique
        // si son
        // iguales, además de si son iguales sin diferenciar entre mayúsculas y
        // minúsculas.

        // Utilizo las dos variables, cadena y cadena 2, usadas en el ejercicio anterior

        // Le indico al usuario que es lo que va a hacer el programa

        System.out.println("Este programa le va a pedir que introduzca dos cadenas de texto.\n"
                + "Después el programa va a comparar las cadenas de texto para ver si son iguales\n"
                + "En el último paso el programa va a comparar las cadenas de nuevo pero ignorando mayúsculas.");

        // Solicito las cadenas

        System.out.println("Introduzca la primera cadena de texto");

        cadena = teclado.nextLine();

        System.out.println();

        System.out.println("Introduzca la segunda cadena de texto");

        cadena2 = teclado.nextLine();

        // Evaluamos si las cadenas son iguales

        System.out.println("Son las cadenas iguales? : " + cadena.equals(cadena2));

        // Evaluamos si las cadenas son iguales ignorando mayúsculas

        System.out.println(
                "Son las cadenas iguales ignorando las letras mayúsculas? : " + cadena.equalsIgnoreCase(cadena2));

        // También podemos hacer que el resultado quede mejor con un operador teciario

        String resultado = (cadena.equals(cadena2)) ? "Son iguales" : "No son igules";

        String resultadoIgnore = (cadena.equalsIgnoreCase(cadena2)) ? "Son iguales ignorando mayúsculas"
                : "Aún ignorando mayúscualas son distintas";

        System.out.println("Vamos a comprobar los resultados de las comparaciones");
        System.out.println();
        System.out.println(resultado);
        System.out.println();
        System.out.println(resultadoIgnore);

        System.out.println();
        System.err.println("********SALTO DE EJERCICIO************");
        System.out.println();

        // 3. Crea un programa que pida por teclado tres cadenas de texto: nombre y dos
        // apellidos.
        // Luego mostrará un código de usuario (en mayúsculas) formado por la
        // concatenación de
        // las tres primeras letras de cada uno de ellos. Por ejemplo, si se introduce
        // “Lionel”,
        // “Tarazón” y “Alcocer” mostrará “LIOTARALC”.

        // Utilizo las dos variables, cadena y cadena 2, usadas en el ejercicio anterior
        // y creo otra variable cadena tres

        String cadena3;

        // Indico al usuario lo que hace el programa

        System.out.println("El programa le va a solicitar 3 cadenas distintas:\n"
                + "La primera será un nombre\n"
                + "La segunda será el primer apellido\n"
                + "Y por último la tercera será el segundo apellido");

        System.out.println();

        System.out.println(
                "Despues de todo esto el programa formará una cadena con las tres primeras letras de cada cadena anterior en mayúsculas");

        // Solicito las cadenas

        System.out.println();

        System.out.println("Introduzca la primera cadena");

        cadena = teclado.nextLine();

        System.out.println();

        System.out.println("Introduzca la segunda cadena");

        cadena2 = teclado.nextLine();

        System.out.println();

        System.out.println("Introduzca la tercera cadena");

        cadena3 = teclado.nextLine();

        // A partir de ahora debemos de usar el método substring en cada cadena para
        // obtener los tres primeros caracteres, los pasaremos a mayúscuals y los
        // concatenaremos

        // Defino una variable que almacene el resultado

        String concatenacion = cadena.substring(0, 3) + cadena2.substring(0, 3) + cadena3.substring(0, 3);

        // Convierto a mayúsculas la cadena resultado

        concatenacion = concatenacion.toUpperCase();

        // Muestro el resultado

        System.out.println(concatenacion);

        // Después de volver a revisar el ejercicio me he dado cuenta de que se podría hacer en menos lineas de la siguiente forma
        System.out.println("Introduzca su nombre y sus dos apellidos pulsando enter entre cada uno de ellos");
        System.out.println(teclado.nextLine().toUpperCase().substring(0, 3) + teclado.nextLine().toUpperCase().substring(0, 3) + teclado.nextLine().toUpperCase().substring(0, 3));

        //De todas formas hay que reconocer que este es un comando muy largo y puede liarse uno al leerlo
        System.out.println();
        System.err.println("********SALTO DE EJERCICIO************");// pongo system err porque en eclipse sale en rojo
                                                                     // y es más visual pero aquí no
        System.out.println();



        // 4. Crea un programa que muestre por pantalla cuantas vocales de cada tipo hay
        // (cuantas
        // ‘a’, cuantas ‘e’, etc.) en una frase introducida por teclado. No se debe
        // diferenciar entre
        // mayúsculas y minúsculas. Por ejemplo, dada la frase “Mi mama me mima” dirá
        // que hay:
        // Nº de A's: 3
        // Nº de E's: 1
        // Nº de I's: 2
        // Nº de O's: 0
        // Nº de U's: 0

        // Lo primero indicar que voy a usar la primera variable string que he creado,
        // cadena

        // Después le indico al usuario lo que tiene que hacer

        System.out.println(
                "Este programa le pedirá que introduzca una cadena por teclado y le indicará el número de veces que aparece\n"
                        + "cada vocal");

        // Solicitamos que se introduzca la cadena

        cadena = teclado.nextLine();

        // Para hacerlo más fácil lo pasamos todo a mayúsculas o minúsculas

        cadena = cadena.toUpperCase();

        // Creo que la mejor forma de resolver esto es con un for que recorra todas las
        // posiciones del String y vaya evaluando
        // Para contar las veces hay que crear contadores para cada vocal

        int contA = 0, contE = 0, contI = 0, contO = 0, contU = 0;

        // Declaro también una variable para almacenar la letra correspondiente de forma
        // temporal

        char letra;

        // Empiezo con el for

        for (int i = 0; i < cadena.length(); i++) {
            // Almaceno el caracter que está en la posición i en la variable letra
            letra = cadena.charAt(i);

            // Uso un switch para evaluar las opciones

            switch (letra) {
                case 'A':
                    contA++;
                    break;
                case 'E':
                    contE++;
                    break;
                case 'I':
                    contI++;
                    break;
                case 'O':
                    contO++;
                    break;
                case 'U':
                    contU++;
                    break;
                default:
                    break; // No colocon ninguna instrucción en default pues no tiene nada que hacer
            }
        }

        // Una vez se ha finalizado de recorrer el string muestro por pantalla los
        // resultados

        System.out.println("La cantidad de cada vocal en la cadena es la siguiente:\n"
                + "La cantidad de 'A' es: " + contA
                + "\nLa cantidad de 'E' es: " + contE
                + "\nLa cantidad de 'I' es: " + contI
                + "\nLa cantidad de 'O' es: " + contO
                + "\nLa cantidad de 'U' es: " + contU);

        System.out.println();
        System.err.println("********SALTO DE EJERCICIO************");
        System.out.println();

        // 5. Realiza un programa que lea una frase por teclado e indique si la frase es
        // un palíndromo
        // o no (ignorando espacios y sin diferenciar entre mayúsculas y minúsculas).
        // Supondremos que el usuario solo introducirá letras y espacios (ni comas, ni
        // puntos, ni
        // acentos, etc.). Un palíndromo es un texto que se lee igual de izquierda a
        // derecha que de
        // derecha a izquierda.
        // Por ejemplo:
        // Amigo no gima
        // Dabale arroz a la zorra el abad
        // Amo la pacífica paloma
        // A man a plan a canal Panama

        // Voy a volver a usar la variable cadena

        // Indico al usuario lo que hace el programa
        System.out.println("Este programa nos dirá si una cadena introducida por teclado es un palíndromo o no");

        // Le solicitamos la cadena al usuario

        System.out.println("Introduzca la cadena a evaluar");

        cadena = teclado.nextLine();

        // Lo convertimos a minúsculas

        cadena = cadena.toLowerCase();

        // uso tochararray para pasar la cadena a un array

        char cad[] = cadena.toCharArray();

        // Creo un array para almacenar el primer array de forma invertida
        char cad2[] = new char[cad.length];

        // uso un for para copiar el array de forma invertida

        for (int i = 0; i < cad.length; i++) {
            cad2[(cad2.length - 1) - i] = cad[i];
        }

        // con otro for voy a ir añadiendo caracter a caracter los que estan almacenados
        // en cad2[] a la variable string cadena2
        // Para eso reinicio la variable cadena2

        cadena2 = "";

        for (int i = 0; i < cad2.length; i++) {
            cadena2 = cadena2 + String.valueOf(cad2[i]);
        }

        // Simplemente para comprobar que todo va bien muestro por pantalla cadena 2
        System.out.println(cadena2);

        // Para saber si son iguales debo de eliminar los espacios en blanco de las
        // cadenas, así sabré si son exacatamente iguales
        System.out.println("Son las cadenas iguales?: " + (cadena.replace(" ", "").equals(cadena2.replace(" ", ""))));

        //Dado que en la clase de hoy se ha indicado XDDDD que esta forma es la fuerza bruta y que hay una más fina y elegante voy a intentar
        // hacerla de esa manera
        // guiño guiño codazo codazo XDDDDD
        
        // La idea es recorrer la cadena con un for comparando el caracter de la posición correspondiente con el opuesto, si en algún momento
        //son distintos ya sabemos que no es un palíndromo
        String cadenaNueva;

        System.out.println("Introduce una nueva cadena para evaluarla de forma más fina");

        cadenaNueva = teclado.nextLine().toLowerCase().replace(" ", "");
        
        int contFallos = 0;

        for(int i = 0; i < cadenaNueva.length()-1-i; i++){
            if(cadenaNueva.charAt(i) != cadenaNueva.charAt(cadenaNueva.length()-1-i)){
                contFallos++;
            }
        }
        // El único problema con este for es que está comparando la cadena completa, por lo que está haciendo iteraciones de más
        // Por lo tanto, hay que buscar una forma de que solo lo haga por la mitad de la cadena
        // Con lo cual hay que tener en cuenta si la longitud de la cadena es par o impar
        //Despúes de hacer que el for recorra la cadena entera y compara todos los caracteres debo de cambira el i<cadenaNueva.length()
        //por i<=cadenaNueva.length()-1-i

        //Después de probar que ese cambio funciona tanto con cadenas pares o impares sólo me queda la forma de mostrar por pantalla el resultado
        //Lo hago declarando antes del for contador de fallos. Si al final es mayor de cero no es un palíndromo

        //Evaluo el valor del contador de fallos
        if(contFallos>0){
            System.out.println("La cadena introducida no es un palíndromo");
        }else{
            System.out.println("La cadena introducida es un palíndromo");
        }
    }
}
