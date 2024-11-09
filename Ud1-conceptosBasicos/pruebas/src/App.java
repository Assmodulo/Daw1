import java.util.Scanner;


public class App {
    
    public static void main(String[] args) throws Exception {
        
        // Necesitamos crear un programa para registrar sueldos de hombres y mujeres de una empresa
        // y detectar si existe brecha salarial entre ambos. El programa pedirá por teclado la información
        // de N personas distintas (valor también introducido por teclado). Para cada persona, pedirá su
        // género (0 para varón y 1 para mujer) y su sueldo. Esta información debe guardarse en una única
        // matriz. Luego se mostrará por pantalla el sueldo medio de cada género.

        Scanner teclado = new Scanner(System.in);

        //Indico al usario lo que hace el programa

        System.out.println("Este programa va a almacenar los datos salariales de una serie de personas.\n"
        +"Primero se solicitará que se indique el número de personas de los que se van a almacenar datos\n"
        +"El formato de los datos se codifica de la siguiente manera:\n"
        +"Para indicar que es hombre se introduce un 0, para indicar que es una mujer se introduce un 1.\n"
        +"Cuando soliciten el salario se introduce el número con dos decimales. Acordarse de que el punto flotante\n"
        +"se escribe con una , no con un punto.");

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
