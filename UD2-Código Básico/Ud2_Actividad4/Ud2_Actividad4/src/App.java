
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner teclado = new Scanner(System.in); // Declaro objeto de la clase Scanner
        //int numero = 0; //Declaro una variable número que veo que lo voy a necesitar en los primero ejercicios
        //Al final no uso la variable numero porque inserto los valores directamente en el array, la dejo comentada por si acaso falla en algun
        //punto que yo no me de cuenta, pero el compilador no dice nada por ahora

        int resultado = 0; //Otra variable int que va a ir almacenando resultados

// 1. Crea un programa que pida diez números reales por teclado, los almacene en un array,
// y luego muestre todos sus valores.
        System.out.println("El programa le va a solicitar que introduzca por teclado diez número naturales");
        System.out.println("Este programa va a almacenar esos números en un array y luego los mostrará por pantalla");

        int[] numeros = new int[10]; //Defino el array

        //Lo voy a completar con un for
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduzca un número entero por teclado");
            numeros[i] = teclado.nextInt();
        }

        //Vuelvo a escribir un for pero esta vez para mostrar el contenido del array
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("{" + numeros[i] + "}");
        }

        System.out.println("");

// 2. Crea un programa que pida diez números reales por teclado, los almacene en un array,
// y luego muestre la suma de todos los valores.

        System.out.println("El programa le va a solicitar diez números reales, los almacenará y le mostrará la suma de sus valores");
        //Voy a usar variables del ejercicio anterior
        //Creo que en un solo for voy a almacenar los valores y los voy a ir sumando
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduzca un número por teclado");
            numeros[i] = teclado.nextInt();
            resultado += numeros[i];
        }

        System.out.println("La suma de todos los números introducidos es " + resultado);

        System.out.println("");

// 3. Crea un programa que pida diez números reales por teclado, los almacene en un array,
// y luego lo recorra para averiguar el máximo y mínimo y mostrarlos por pantalla.
        System.out.println("El programa le va a pedir que introduzca diez números reales\n"
                + "Luego recorrerá el array y buscará el número mayor y el menor para mostrarlos mas tarde");

        //Usaré variables anteriores y array ya definido
        //completo el array
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduzca un número entero");
            numeros[i] = teclado.nextInt();
        }

        //Una vez completo el array lo ordeno de menor a mayor despues de importar Arrays
        Arrays.sort(numeros);

        System.out.println("El número mínimo almacenado es:" + numeros[0]);
        System.out.println("El número máximo almacenado es:" + numeros[numeros.length - 1]);

        System.out.println();

// 4. Crea un programa que pida veinte números enteros por teclado, los almacene en un
// array y luego muestre por separado la suma de todos los valores positivos y negativos.
        System.out.println("El progrma le va a pedir 20 números enteros,\n"
                + "tanto positivos como negativos y luego sumará los positivos y los negativos por separado");

        //Declaro dos variables para almacenar la suma de positivos y negativos
        int sumPos = 0, sumNeg = 0;

        //Reincio Scanner
        teclado = new Scanner(System.in);

        //redefino el tamaño del array numeros para que me sirva para este ejercicio
        numeros = new int[20];

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Introduzca un número entero");
            numeros[i] = teclado.nextInt();
            if (numeros[i] >= 0) {
                sumPos += numeros[i];       //Con este if evaluo el número que acabo de añadir
            } else {                          //y lo añado al sumatorio que corresponda
                sumNeg += numeros[i];
            }
        }

        System.out.println("La suma de todos los números positivos es: " + sumPos);
        System.out.println("La suma de todos los números negativos es: " + sumNeg);

        System.out.println();

// 5. Crea un programa que pida veinte números reales por teclado, los almacene en un array
// y luego lo recorra para calcular y mostrar la media: (suma de valores) / nº de valores.
        //reinicio scanner
        teclado = new Scanner(System.in);

        //Defino un array de 20 elementos, pero por cambiar del tipo double
        double[] numerosReales = new double[20];

        //Para completar el array usamos un for, además declaro primero una variable para almacenar la 
        //suma total
        double sumaReales = 0;

        System.out.println("El programa le va a solicitar ahora 20 números reales y luego calculará la media");
        System.out.println();

        for (int i = 0; i < numerosReales.length; i++) {
            System.out.println("Introduzca un número real");
            numerosReales[i] = teclado.nextDouble();
            sumaReales += numerosReales[i];
        }

        //Ahora muestro por pantalla el valor medio de los números
        System.out.println("El valor medio de los número introducidos es: " + (sumaReales / numerosReales.length));

        System.out.println();

// 6. Crea un programa que pida dos valores enteros N y M, luego cree un array de tamaño
// N, escriba M en todas sus posiciones y lo muestre por pantalla.
        //Declaro las variable N y M
        int n, m;

        System.out.println("El programa le va a pedir dos números enteros por teclado\n"
                + "El primer número, N, se usará para indicar el tamaño de un array que se va a crear\n"
                + "El segundo número, M, es el que va a completar todos los espacios del array");

        //Pido los dos valores por teclado
        System.out.println("Introduzca el número N");
        n = teclado.nextInt();

        System.out.println("Introduzca el número M");
        m = teclado.nextInt();

        //Creo el array de enteros de longitud n y lo completo con el valor m en todas sus posiciones
        int[] enteros = new int[n];
        Arrays.fill(enteros, m);

        //Ahora lo muestro por pantalla
        for (int i = 0; i < enteros.length; i++) {
            System.out.print("{" + enteros[i] + "}");
        }

        System.out.println();

// 7. Crea un programa que pida dos valores enteros P y Q, luego cree un array que contenga
// todos los valores desde P hasta Q, y lo muestre por pantalla.
        //Aunque se pidan unos valores p y q lo que voy a usar es las variables n y me del ejercicio anterior
        //Lo que si voy a hacer es volver a solicitar los valores por pantalla
        System.out.println("El programa va a solicitar dos números, P y Q, y luego creará una array que almacene los valores desde P hasta Q");

        System.out.println("Se le van a pedir dos números enteros por teclado");

        System.out.println("Introduzca el primer número");
        n = teclado.nextInt();

        System.out.println("Introduzca el segundo número");
        m = teclado.nextInt();

        //Ahora el programa va a calcular la diferencia entre P y Q para crear el array evaluando cual es
        //el número mayor para calcular la diferencia.
        if (n > m) {
            //La longitud del array sería la diferencia entre los números, pero para que se incluyan todos debo de sumarle 1

            enteros = new int[(n - m) + 1];
            //Dependiendo de cual sea el mayor, se evalua y se define de nuevo el array
            //Despues de evaluar los números hay que insertar el for para completar el array de acuerdo a las 
            //opciones. Dentro de cada for para completar el array, puedo ir mostrando el resultado
            for (int i = 0; i < enteros.length; i++) {
                enteros[i] = m + i;
                System.out.print("{" + enteros[i] + "}");
            }
        } else if (m > n) {
            enteros = new int[(m - n) + 1];
            for (int i = 0; i < enteros.length; i++) {
                enteros[i] = n + i;
                System.out.print("{" + enteros[i] + "}");
            }
        } else {
            System.err.println("El valor de los números es igual, o por alguna otra razón\n"
                    + "no se puede evaluar el valor ni crear el array");
        }

        System.out.println();

// 8. Crea un programa que cree un array con 100 números reales aleatorios entre 0.0 y 1.0,
// utilizando Math.random(), y luego le pida al usuario un valor real R. Por último, mostrará
// cuántos valores del array son igual o superiores a R.
        System.out.println("Este programa va a generar un array con números comprendidos entres 0.0 y 0.1.\n"
                + "Luego le solicitará un valor comprendido entre esos límites y comprobará cuantos números del array son iguales o mayores");

        //Voy a redefinir el array que ya tenia creado para numeros reales
        numerosReales = new double[100];

        //Creo una variable para almacenar por teclado el valor real que me pide
        double opcionReal;

        //Reinicio Scanner
        teclado = new Scanner(System.in);

        //Completo el array con valores aleatorios
        for (int i = 0; i < numerosReales.length; i++) {
            numerosReales[i] = Math.random();
        }

        //Ahora el programa le solicita al usuario un número real y le indico que los valores tienen que estar
        //comprendidos entre 0.0 y 1.0
        do {
            System.out.println("Introduzca un número real comprendido entre 0.0 y 1.0. Escribelo con coma que siempre te pasa y te da error");
            opcionReal = teclado.nextDouble();
            if (opcionReal < 0 || opcionReal > 1.0) {
                System.err.println("Introduzca un valor válido para comparar");
            }
        } while (opcionReal < 0 || opcionReal > 1.0);

        int contador = 0; //Creo esta variable para que lleve la cuenta de los números iguales o mayores a R

        for (double real : numerosReales) {
            if (real >= opcionReal) {
                contador++;
            }
        }

        //Mostramos por pantalla la cantidad de números iguales o mayores que r que hay en el array
        System.out.println("La cantidad de números iguales o mayores que su elección que existen en el array es: " + contador);

        System.out.println();

// 9. Crea un programa que cree un array de enteros de tamaño 100 y lo rellene con valores
// enteros aleatorios entre 1 y 10 (utiliza 1 + Math.random()*10). Luego pedirá un valor N
// y mostrará en qué posiciones del array aparece N.
        System.out.println("El programa va a crear un array de tamaño 100 y lo completará con valores aleatorios entre 1 y 10.\n"
                + "Después solicitará que le indiquemos un número y nos dirá en que posiciones del array lo ha encontrado.");
        //Redefino el tamaño del array de enteros creado en un ejercicio anterior
        enteros = new int[100];

        //Uso un for para completar el array
        for (int i = 0; i < enteros.length; i++) {
            enteros[i] = (int) (1 + Math.random() * 10);
        }

        //Para pedir el número por teclado primero reinicio el scanner
        teclado = new Scanner(System.in);

        //Solicito al usuario que introduzca un valor por teclado entre 1 y 10 asegurandome de que sea así
        //Creo una opcion int para evaluar
        int opcionEntera;

        do {
            System.out.println("Introduzca un número entero comprendido entre 1 y 10");
            opcionEntera = teclado.nextInt();
            if (opcionEntera < 1 || opcionEntera > 10) {
                System.err.println("Introduzca un valor válido para comparar");
                System.out.println();
            }
        } while (opcionEntera < 1 || opcionEntera > 10);

        System.out.println("El número que usted ha seleccionado aparece en la posición:");

        for (int i = 0; i < enteros.length; i++) {
            if (opcionEntera == enteros[i]) {
                System.out.print("{" + i + "}");
            }

        }

        System.out.println();

// 10. Crea un programa para realizar cálculos relacionados con la altura (en metros) de
// personas. Pedirá un valor N y luego almacenará en un array N alturas introducidas por
// teclado. Luego mostrará la altura media, máxima y mínima, así como cuántas personas
// miden por encima y por debajo de la media.
        //Esta vez voy a crear un nuevo array para trabajar con los datos despues de que se introduzca el número de datos
        teclado = new Scanner(System.in);

        //Para almacenar la variable uso una de ejercicios anteriores
        System.out.println("Introduzca la cantidad de alturas que va usted a almacenar");

        n = teclado.nextInt();

        //Defino ahora el array
        double[] alturas = new double[n];

        //Defino una variable para ir almacenando el sumatorio de las alturas
        double sumatorioAlturas = 0;

        //Completo ahora el array
        for (int i = 0; i < alturas.length; i++) {
            teclado = new Scanner(System.in);
            System.out.println("Introduzca un nuevo valor de altura por teclado");
            alturas[i] = teclado.nextDouble();
            sumatorioAlturas += alturas[i];
        }

        //Para trabajar más cómodo ahora, una vez completo, ordeno el array
        Arrays.sort(alturas);

        //Como más adelante debo de trabajar con la media, creo una variable para almacenarla
        double media = sumatorioAlturas / alturas.length;

        //Con esto es más facil calcular la altura mínima y la máxima. Lo muestro todo por pantalla
        System.out.println("La altura mínima introducida es: " + alturas[0] + "\n"
                + "La altura máxima introducida es: " + alturas[alturas.length - 1] + "\n"
                + "La altura media de todas las introducidas es: " + media);

        //Ahora debo de crear dos contadores para comparar aquellos que son más altos que la media y aquellos más bajos

        int contadorAltos = 0, contadorBajos = 0;

        //Recorro el array, cuento los que tenga altura igual a la media como superiores a la media

        for(double altura : alturas){
            if(altura >= media){
                contadorAltos++;
            }else{
                contadorBajos++;
            }
        }

        //Muestro por pantalla cuantos estan por encima de la media y cuantos por debajo
        System.out.println("Por encima de la media hay: " + contadorAltos + " y por debajo hay: " + contadorBajos);

        System.out.println();

// 11. Crea un programa que cree dos arrays de enteros de tamaño 100. Luego introducirá en
// el primer array todos los valores del 1 al 100. Por último, deberá copiar todos los valores
// del primer array al segundo array en orden inverso, y mostrar ambos por pantalla.
        //Para este ejercicio voy a crear dos arrays nuevos
        int[] arrayOrdenado = new int[100];
        int[] arrayInverso = new int[100];

        //Completo el array ordenado con un for
        for (int i = 0; i < arrayOrdenado.length; i++) {
            arrayOrdenado[i] = i + 1;
        }

        //Completo ahora el arrayInverso
        for (int i = 0; i < arrayOrdenado.length; i++) {
            arrayInverso[(arrayInverso.length - 1) - i] = arrayOrdenado[i];
        }

        //Recorro los dos arrays
        for (int ordenado : arrayOrdenado) {
            System.out.print("{" + ordenado + "}");
        }

        System.out.println();

        for (int desordenado : arrayInverso) {
            System.out.print("{" + desordenado + "}");
        }

        System.out.println();

// 12. Crea un programa que cree un array de 10 enteros y luego muestre el siguiente menú
// con distintas opciones:
// a. Mostrar valores.
// b. Introducir valor.
// c. Salir.
// La opción ‘a’ mostrará todos los valores por pantalla. La opción ‘b’ pedirá un valor V y una
// posición P, luego escribirá V en la posición P del array. El menú se repetirá indefinidamente hasta
// que el usuario elija la opción ‘c’ que terminará el programa.
        //Creo un nuevo array de enteros porque ya no me acuerdo de algunos de los anteriores
        int[] enteros10 = new int[10];

        //Después de la clase de hoy ya se que es mejor crear la opción a evaluar como String así que la creo así
        String opcion; //De primeras la inicialize con valor "" pero luego me di cuenta de que no me hacia falta inicializar

        //Como en este ejercicio no se pueden eliminar valores del array, voy a crear una variable contador para chequear que el array no esté completo
        //Se chequeará antes de introducir un valor para comprobar que el array no esté completo
        int valoresIntroducidos = 0;

        //creo el menú
        do {

            //Dentro del bucle, cada vez que itere, que reinicie el Scanner
            teclado = new Scanner(System.in);

            //Indico al usuario las opciones del menú
            System.out.println("Elija una de las siguientes opciones: \n"
                    + "a) Mostrar valores\n"
                    + "b) Introducir valores\n"
                    + "c) Salir\n");

            opcion = teclado.nextLine();

            //Paso todas las posible entradas de teclado a minúscula
            opcion.toLowerCase();

            switch (opcion) {
                case "a":
                    //Como esta opción es mostrar valores puedo usar el
                    //contador que he creado para evaluar si hay algún dato introducido, y si lo hay mostrarlos
                    if (valoresIntroducidos > 0) {
                        for (int i = 0; i < enteros10.length; i++) {
                                System.out.print("{" + enteros10[i] + "}");
                            
                        }
                    }else{
                        System.out.println("No se ha encontrado ningún valor almacenado.");
                        System.out.println();
                    }
                    System.out.println();
                    break;
                case "b":
                    //Gracias a la variable contador evaluo si el array está lleno y se decide lo que hace

                    if (valoresIntroducidos == enteros10.length) {
                        //Al ver que la variable contador es igual a la dimensión del array sabemos que está completo y se lo indicamos
                        //al usuario. Sólo lo evaluo a igual porque si he hecho bien las cosas en ningún momento contador va a valer más
                        //de 10
                        System.out.println("El array de números ya está completo. No se puede añadir ningún dato más");
                        System.out.println();

                    } else {
                        //Despues de la primera evaluación, si llegamos aquí es porque contador es menor de 10, lo que quiere decir que
                        //todavia queda espacio libre en el array y se puede añadir un nuevo dato
                        System.out.println("Introduzca un nuevo número para almacenar");

                        enteros10[valoresIntroducidos] = teclado.nextInt();    //Le indico que la posición del array en la que estamos ahora va a ser igual
                        //al dato que introducimos por teclado

                        System.out.println("Se ha añadido un nuevo dato");
                        System.out.println();  //Indico al usuario que un nuevo dato ha sido añadido

                        valoresIntroducidos++; //Incremento el contador para saber cuantos elementos llevo y que no se sobreescriba ninguno de los anteriores
                    }
                    break;
                case "c":
                    //En esta sección se le va a indicar al systema que ha terminado el programa
                    System.out.println("Finalizando programa");
                    break;
                default:
                    System.err.println("La opción elegida no es correcta. Tendrá que introducirla otra vez.");
                    break;
            }

        } while (!opcion.equals("c")); 

        System.out.println();

// 13. Crea un programa que permita al usuario almacenar una secuencia aritmética en un
// array y luego mostrarla. Una secuencia aritmética es una serie de números que
// comienza por un valor inicial V, y continúa con incrementos de I. Por ejemplo, con V=1
// e I=2, la secuencia sería 1, 3, 5, 7, 9… Con V=7 e I=10, la secuencia sería 7, 17, 27, 37… El
// programa solicitará al usuario V, I además de N (nº de valores a crear).
        //Lo primero le indicamos al usuario en que consiste el programa
        System.out.println("Este programa va a almacenar una secuencia aritmética. Se le va a solicitar en este orden:"
                + "1-El número inicial de la secuencia"
                + "2-El número por el que se va a incrementar la secuencia"
                + "3-La longitud de la secuencia en cantidad de veces que se debe incrementar el valor");

        //Para esto voy a necesitar tres variables del tipo entero que las voy a nombrar como las variables del enunciado, menos n que ya la
        //tengo definida en ejercicios anteriores
        int valorInicial, incremento;

        //Reinicio la variable de la clase Scanner
        teclado = new Scanner(System.in);

        //Voy a ir solicitando los datos
        System.out.println("Introduzca el Valor Inicial de la secuencia");
        valorInicial = teclado.nextInt();

        System.out.println("Introduzca el valor por el que se va a incrementar la secuencia cada salto");
        incremento = teclado.nextInt();

        System.out.println("Introduzca la cantidad de saltos que va a tener la secuencia");
        n = teclado.nextInt();

        //Ahora que ya tengo el numero de saltos creo el array
        int[] secuencia = new int[n];

        //Ahora hay que completar el array mediante un for
        for (int i = 0; i < secuencia.length; i++) {
            //Evaluo en este caso si la posición del bucle en la que estamos es la inicial, ya que esa debe de tener como valor
            //el valor inicial, podria haberlo indicado fuera diciendo directamente secuenacia[0] = valorInicial, quizá habría sido
            //más sencillo, pero creo que evaluarlo dentro del for quedaba mejor, aunque quiza´sea más costoso debido a que
            //en cada iteración del for evalua la condición del if
            if (i == 0) {
                secuencia[i] = valorInicial;
                valorInicial += incremento;
            } else {
                //Si no es la posición inicial le tengo que ir diciendo que incremente el valor de la secuencia para almacenarlo
                secuencia[i] = valorInicial;
                valorInicial += incremento;
            }
            //Siempre que termine la evaluación le digo que incremente de forma permanente el valor inicial para igualarlo con el último valor almacenado
            //y así se almacene el valor correcto en la siguiente iteración
            

            //Voy mostrando los valores por pantalla
            System.out.print("{" + secuencia[i] + "}");
        }

        System.err.println("");

// 14. Crea un programa que cree un array de enteros e introduzca la siguiente secuencia de
// valores: 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, etc. hasta introducir 10 diez veces, y luego la
// muestre por pantalla.
        //En este programa lo primero que necesito es calcular cual es la dimensión del array sin hacerlo de cabeza
        //Para eso voy a usar un contador y un bucle para calcular cual será el tamaño
        int contadorTamanio = 0;

        for (int i = 1; i <= 10; i++) {
            //Ahora debo de hacer un for anidado que me calculase como si fuese a hacer la operación requerida
            for (int x = 1; x <= i; x++) {
                contadorTamanio++;
            }
        }

        //Una vez tengo el tamaño del array debo declararlo
        int[] secuencia2 = new int[contadorTamanio];

        //Ahora debo de completarlo de la forma que indica el ejercicio
        //Para ello creo que voy a necesitar tambien una variable que tenga como valor el número que tenemos que manejar
        int valorSec = 1;   //Lo inicializo a 1 porque es el primer valor que va a tener

        for (int i = 0; i < secuencia2.length; i++) {
            //Debo crear otro for para que imprima el númeroN N veces
            for (int j = 1; j <= valorSec; j++) {
                secuencia2[i] = valorSec;
                if(j<valorSec){
                    i++;
                }
            }
            //Fuera del primer for debo asegurarme de incrementar el valor que se tiene que usar
            valorSec++;
        }

        //Podría hacerlo dentro de los for anteriores pero por no liarme lo voy a hacer con un foreach fuera
        for (int valSec : secuencia2) {
            System.out.print("{" + valSec + "}");
        }
    }

}
