import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Crea un programa que cree una matriz de tamaño 5x5 que almacene los números del 1 al 25
        // y luego muestre la matriz por pantalla.

            //Lo primero que voy a hacer es declarar una matriz de tamaño 5x5

            int[][] matriz = new int[5][5];

            //Voy a crear una variable contador que vaya aumentando para ir desde 1 hasta 25;

            int contador = 1;

            //Ahora debo de completar la matriz

            for(int i = 0; i < matriz.length; i++){
                for(int j = 0; j < matriz[i].length; j++){
                    matriz[i][j] = contador;
                    contador++;
                    System.out.print("{" + matriz[i][j] + "}");
                }
                System.out.println();
            }
            

            //Hago el clasico cambio de ejercicio
            System.out.println();
            System.out.println("*****SALTO DE EJERCICIO*****");
            System.out.println();


            // Crea un programa que cree una matriz de 10x10 e introduzca los valores de las tablas de
            // multiplicar del 1 al 10 (cada tabla en una fila). Luego mostrará la matriz por pantalla.

            //Declaro la matriz que me hace falta

            int tabMulti[][] = new int[10][10];
            
            //Creo el bucle necesario para completarla y mostrar el resultado por pantalla

            for(int i = 0; i < tabMulti.length; i++){
                for(int j = 0; j < tabMulti[i].length; j++){
                    tabMulti[i][j] = (i + 1) * (j + 1);
                    System.out.print("{" + tabMulti[i][j] + "}");
                }
                System.out.println();
            }

            //Hago el clasico cambio de ejercicio
            System.out.println();
            System.out.println("*****SALTO DE EJERCICIO*****");
            System.out.println();


            // Crea un programa que cree una matriz de tamaño NxM (tamaño introducido por teclado) e
            // introduzca en ella NxM valores (también introducidos por teclado). Luego deberá recorrer la
            // matriz y al final mostrar por pantalla cuántos valores son mayores que cero, cuántos son
            // menores que cero y cuántos son igual a cero.

        //Declaro primero el objeto de la clase Scanner y defino la matriz

        Scanner teclado = new Scanner(System.in);

        int matrizNM[][];

        //Le explico al usuario lo que hace el programa

        System.out.println("Este programa creará una matriz de datos de tamaño NxM, datos que usted introducirá por teclado.\n"
        +"Luego se completará la matriz con datos que usted también introducirá por teclado, se mostrará por pantalla\n"
        +"y el programá le indicará cuantos valores son positivos, cuantos negativos y cuantos iguales a cero.");

        //Declaro tres variables que actuarán como contadores y las variable n y m

        int contPos = 0, contNeg = 0, contCero = 0, n, m;

        //Solicito el tamaño de la matriz por teclado

        System.out.println();
        System.out.println("Introduzca el valor de las filas de la matriz:");

        n = teclado.nextInt();

        System.out.println("Introduzca el valor de las columnas de la matriz:");

        m = teclado.nextInt();

        //Declaro el tamaño de la matriz

        matrizNM = new int[n][m];

        //Creo el for para completar la matriz

        for(int i = 0; i < matrizNM.length; i++){
            for(int j = 0; j < matrizNM[i].length; j++){
                //A partir de aqui vuelvo a usar la variable n para almacenar los datos introducidos por teclado
                System.out.println("Introduzca un valor para almacenar en la matriz");
                n = teclado.nextInt();

                //Evaluo con un if el valor de n y aumento el contador correspondiente según el resultado

                if( n > 0){
                    contPos++;
                }else if( n < 0){
                    contNeg++;
                }else{
                    contCero++;
                }
            }
        }

        System.out.println("Vamos a mostrar el resultado del ejercicio.\n"
        +"La cantidad de valores positivos es de: " + contPos + "\n"
        +"La cantidad de valores negativos es de: " + contNeg + "\n"
        +"La cantidad de valores iguales a cero es de: " + contCero);

        //Hago el clasico cambio de ejercicio
        System.out.println();
        System.out.println("*****SALTO DE EJERCICIO*****");
        System.out.println();

        // Necesitamos crear un programa para almacenar las notas de 4 alumnos (llamados “Alumno
        // 1”, “Alumno 2”, etc.) y 5 asignaturas. El usuario introducirá las notas por teclado y luego el
        // programa mostrará la nota mínima, máxima y media de cada alumno.


        //Creamos la matriz, por sencillez del tipo int

        int notas[][] = new int[4][5];

        //Una variable para almacenar la suma de las notas de cada alumno

        int suma = 0;

        //Dos variables para almacenar la nota máxima y la mínima de cada alumno

        int maxima= 0; //Empieza a cero para poder evaluar las notas desde la primera
        int minima = 10; //Empieza a diez para poder evaluar las notas desde la primera

        //Declaro una variable para almacenar la nota introducida por teclado

        int nota;

        //Le indico al usuario que va a hacer el programa

        System.out.println("Este programa va a almacenar 5 notas de 4 alumnos diferentes.\n"
        +"Luego mostrará la nota más alta, la nota más baja y la nota media de cada alumno");

        //Creo el for para completar el array

        for (int i = 0; i < notas.length; i++) {
            for(int j = 0; j < notas[i].length; j++){
                System.out.println("Introduzca la primera nota del Alumno" + (i + 1));
                nota = teclado.nextInt();

                //Vamos evaluando el valor de nota y añadiendolo al sumatorio
                suma += nota;

                if(nota > maxima){
                    maxima = nota;
                } 
                if(nota < minima){
                    minima = nota;
                }
            }
            
            //Después de salir del bucle que completa las notas de un alumno tengo que hacer un par de acciones para que funcione todo bien
            //Muestro los resultados
            System.out.println("Estos son los datos del Alumno" + (i + 1) + ":\n"
            +"La nota máxima es igual a: " + maxima + "\n"
            +"La nota minima es igual a: " + minima + "\n"
            +"La nota media del alumno es: " +(double) (suma/notas[i].length));

            //Debo reiniciar el valor de las 3 variables auxiliares para que el programa haga lo que tiene que hacer en la siguiente iteracion
            suma = 0;
            minima = 10;
            maxima = 0;

            //sout para dejar un espacio para el siguiente alumno
            System.out.println();
        }

        //Hago el clasico cambio de ejercicio
        System.out.println();
        System.out.println("*****SALTO DE EJERCICIO*****");
        System.out.println();


        // Necesitamos crear un programa para registrar sueldos de hombres y mujeres de una empresa
        // y detectar si existe brecha salarial entre ambos. El programa pedirá por teclado la información
        // de N personas distintas (valor también introducido por teclado). Para cada persona, pedirá su
        // género (0 para varón y 1 para mujer) y su sueldo. Esta información debe guardarse en una única
        // matriz. Luego se mostrará por pantalla el sueldo medio de cada género.


        //Indico al usario lo que hace el programa

        System.out.println("Este programa va a almacenar los datos salariales de una serie de personas.\n"
        +"Primero se solicitará que se indique el número de personas de los que se van a almacenar datos\n"
        +"El formato de los datos se codifica de la siguiente manera:\n"
        +"Para indicar que es hombre se introduce un 0, para indicar que es una mujer se introduce un 1.\n"
        +"Cuando soliciten el salario se introduce el número con dos decimales. Acordarse de que el punto flotante\n"
        +"se escribe con una , no con un punto, que luego siempre me crashea en las pruebas y hay que empezar otra vez.");

        //Declaro una variable para almacenar por teclado el número de personas de las que se van a guardar datos

        int personas;
        
        String genero; //Defino genero como String para validarlo en condiciones con un do while dentro del for

        //Declaro dos variables que van a almacenar las sumas de los sueldos por género

        double sumHombres = 0, sumMujeres = 0;

        //Declaro dos variable int que serán los contadores el género hombre y para el genero mujer

        int hombres = 0, mujeres = 0;

        //Defino una matriz pero sin especificar tamaño. Como he decidido añadir los sueldos como double tengo que definir
        //la matriz como double. Al completarla ya me encargaré de modificar el valor del género para que sea double tambien

        double salarios[][];

        //Solicito que se indique por teclado el número de personas de las que se va a almacenar datos

        System.out.println();
        System.out.println("Introdzca el número de personas a evaluar");

        personas = teclado.nextInt();

        //Una vez se el número de personas se la longitud de i de la matriz. Se que la j va a ser dos porque sólo guardo dos datos por persona

        salarios = new double[personas][2];

        //Declaro una variable del tipo boolean para usar en un do/while posterior

        boolean correcto = true;

        //Ahora debo de completar la matriz, para almacenar el género de la persona usaré la variable personas
        
        
        for(int i = 0; i < salarios.length; i++){
            
            //Para evitar que el Scanner haga cosas raras lo reinicio cada vez que itero

            teclado = new Scanner(System.in);

            //Voy a pedir el género de la persona. Evaluo que está en formato correcto con un do/while
            do { 
                System.out.println("Introduzca el género de la persona introduciendo 0 para hombre o 1 para mujer");
                genero = teclado.nextLine();
                
                //Evaluo el valor dado por teclado. Según lo introducido el valor de [i][0] de la matriz será uno u otro

                if(genero.equals("0")){
                    salarios[i][0] = 0; //No necesito insertarlo en valor decimal. El propio lenguaje introducirá 0.0
                    correcto = false;
                }else if(genero.equals("1")){
                    salarios[i][0] = 1; //No necesito insertarlo en valor decimal. El propio lenguaje introducirá 1.0
                    correcto = false;
                }else{
                    System.err.println("La opción introducida no es válida. Insertela de nuevo");
                }
            } while (correcto);
            
            //Pido ahora el valor del salario asociado a esa persona

            System.out.println("Introduzca el salario de esa persona");
            salarios[i][1] = teclado.nextDouble();

            //Desde aquí dentro puedo evaluar el valor que tiene ahora mismo la variable gener para incrementar los sumatorios de sueldos
            if(genero.equals("0")){
                sumHombres += salarios[i][1]; //Hago que se incremente el sumatorio por el valor de el salario introducido
                hombres++;
            }else{
                sumMujeres += salarios[i][1]; //No me hace falta indicar la segunda condición si es que he hecho bien el do while, aquí solo puede ser 1
                mujeres++;   
            }

        }

        //Después de hacer esto mustro los resultados por pantalla formatendo el string para que se vea mejor

        System.out.println("El salario medio de los hombres es de: " +  String.format("%.2f",(sumHombres / hombres)));
        System.out.println("El salario medio de las mujeres es de: " + String.format("%.2f",(sumMujeres / mujeres)));


    }
}
