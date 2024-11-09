// Lo primero que voy a hacer es importar lo que creo que voy a necesitar
import java.util.Scanner; //Scanner porque lo voy a necesitar seguro

public class App {
    
    public static void main(String[] args) throws Exception {
        
        // Lo primero es crear una variable de la clase Scanner
        Scanner teclado = new Scanner(System.in);

        // En algunos momentos voy a necesitar que el usuario indique una posición, creo una variable int para ello
        //Tambien voy a necesitar un contador que me indique el número de valores introducidos en el array
        int posicion, valoresTotales = 0;

        //tambien necesitare una variable double para transferir valores por teclado

        double valor;

        //Defino un array de números
        double numeros[];

        //Tambien defino un array auxiliar que quizá necesite para otras operaciones

        double numerosAuxiliar[];
        
        //Despues de crear la estructura de menús me doy cuenta de que podría necesitar algunas variables auxiliares

        double media = 0, valorMin = 0, valorMax = 0, suma = 0; //Algunas quizá no las use pero las tengo definidas por si acaso

        //Para los submenus como no hay opcion de salir al elegir defino un boolean

        boolean salir = false;

        // Le indico al usuario lo que va a hacer el programa en el inicio y como dice el enunciado
        //pido esa cantidad de números al inicio

        System.out.println("Este programa va a almacenar una cantidad de números que usted indicará por teclado\n"
        +"Luego mostrará una serie de opciones que usted podrá ejecutar mediante un menú."+
        "Pero primero debe usted indicar la cantidad de números que usted va a almacenar");

        System.out.println();

        //Pedimos al usuario que introduzca la cantidad de números por teclado
        System.out.println("Introduzca el total de números que vamos a almacenar");

        numeros = new double[teclado.nextInt()]; //Para ahorrar lineas a la vez que el usuario introduce el número defino
                                                    //la longitud del array

        //tambien defino el tamaño del vector auxiliar

        numerosAuxiliar = new double[numeros.length];

        //Defino un contador para los elementos del vector auxiliar
        int contAuxiliar = 0;

        //Ahora hay que crear la estructura del menú. La crearé primero del todo y luego iré añadiendo lo que hace cada cosa

        String opcion =""; //Defino una variable que va a almacenar la opción elegida para compararla en el switch

        //Como el swithc debe de ejecutarse continuamente lo debo de definir dentro de un bucle do-while

        System.out.println();

        do { 
            System.out.println();
            teclado = new Scanner(System.in);
            //Vamos indicanco paso a paso al usuario lo que tiene que hacer
            System.out.println("INDIQUE LO QUE DESEA HACER INTRODUCIENDO LA LETRA QUE APARECE A LA IZQUIERDA DE LA OPERACIÓN\n"+
            "M. - MODIFICAR COLECCIÓN\n"+
            "E. - ESTADISTICAS DE LA COLECCIÓN\n" + 
            "A. - AMPLIAR COLECCIÓN\n"+
            "S. - SALIR");

            //Introducimos la opción elegida

            opcion = teclado.nextLine().toUpperCase(); //Me aseguro de que las opciones esten en mayúscula

            switch (opcion) {
                case "M":

                    //Como esta opción tiene un menú creo la estructura primero
                    System.out.println("INDIQUE LO QUE DESEA HACER INTRODUCIENDO LA LETRA QUE APARECE A LA IZQUIERDA DE LA OPERACIÓN\n"+
                    "I. - INSERTAR NÚMERO\n"+
                    "B. - BORRAR NÚMERO DE UNA POSICIÓN ESPECÍFICA\n" + 
                    "M. - MODIFICAR NÚMERO DE UNA POSICIÓN ESPECÍFICA\n");

                    opcion = teclado.nextLine().toUpperCase();

                    switch(opcion){
                        case "I":
                            //Lo primero es evaluar que no este completo el array
                            if(numeros.length == valoresTotales - 1){
                                System.out.println("EL ARRAY ESTA COMPLETO, NO SE PUEDE ASIGNAR MÁS VALORES\n"+
                                "QUIZÁS DEBE DE REDIMENSIONAR EL ARRAY");
                            }else{
                                //Si no está completo añado un nuevo valor e incremento el valor del contador de números
                                System.out.println("INSERTE UN VALOR PARA ALMACENAR");
                                numeros[valoresTotales] = teclado.nextDouble();

                                //Para agilizar añado al valor suma el valor del dato introducido
                                suma += numeros[valoresTotales];

                                //Cuando inserte el primer número, dado que en ese momento valoresTotales es 0, el valorMax y el valorMIn
                                //van a ser el mismo. Después de eso hay que comparar

                                if(valoresTotales == 0){
                                    valorMax = numeros[valoresTotales];
                                    valorMin = numeros[valoresTotales];
                                }

                                //Evaluo si el número introducido es mayor que el valorMáximo existente
                                if(numeros[valoresTotales] > valorMax){
                                    valorMax = numeros[valoresTotales];
                                }else if(numeros[valoresTotales] < valorMin){
                                    valorMin = numeros[valoresTotales];
                                }
                                //Incremento el valor de valoresTotales
                                valoresTotales++;
                                

                            }
                            break;
                        case "B":

                            //Aquí lo que tengo que hacer es pedirle al usuario que indique la posición del array que desea borrar
                            //Tengo que asegurarme de que introduzca un valor correcto o de que haya algún valor en el array

                            if(valoresTotales == 0){
                                System.out.println("NO HAY NINGUN VALOR ALMACENADO EN EL ARRAY");
                            }else{
                                do { 
                                    System.out.println("INDIQUE LA POSICIÓN QUE DESEA BORRAR. TENGA EN CUENTA QUE LA POSICIÓN\n"+
                                    "EMPIEZA A CONTAR DESDE 0");
                                    posicion = teclado.nextInt();
                                    
                                    //Evaluo
                                    if(posicion < 0 || posicion > numeros.length){
                                        System.out.println("POSICIÓN INDICADA INCORRECTA. INTENTE DE NUEVO");
                                    }else{
                                        //Ya que supuestamente está bien, primero resto al valor de suma el valor que estaba ya almacenado
                                        suma -= numeros[posicion];
                                        //Después ya modifico el valor de la posición seleccionada por 0
                                        numeros[posicion] = 0;
                                        //Disminuyo el valor de valoresTotales ya que he eliminado un elemento con valor
                                        valoresTotales--;
                                    }
                                } while (posicion < 0 || posicion > numeros.length);
                            }

                            //Despues de eliminar un elemento, sea la posición que sea, deberia de volver a colocar todos sus elementos
                            //como si fuese una fila, para ello uso el vector auxiliar
                            for(int i = 0; i < numeros.length ; i++){
                                if(numeros[i] != 0){
                                    numerosAuxiliar[contAuxiliar] = numeros[i];
                                    contAuxiliar++;
                                }
                            }

                            //Con esto ya habría copiado los números de array original en el auxiliar eliminando los que tienen valor 0
                            //Vuelvo a copiar esos valores ahora en el array original

                            for(int i = 0; i < numerosAuxiliar.length; i++){
                                numeros[i] = numerosAuxiliar[i];
                            }

                            //Después de esto vuelvo el contadorAuxiliar a cero
                            contAuxiliar = 0;

                            break;
                        case "M":

                            //Este caso es parecido al apartado de borrar, pero hay que evaluar alguna otra cosa distinta

                            do { 
                                System.out.println("INDIQUE LA POSICIÓN QUE DESEA MODIFICAR. TENGA EN CUENTA QUE LA POSICIÓN\n"+
                                "EMPIEZA A CONTAR DESDE 0");
                                posicion = teclado.nextInt();
                                
                                //Evaluo
                                if(posicion < 0 || posicion > numeros.length){
                                    System.out.println("POSICIÓN INDICADA INCORRECTA. INTENTE DE NUEVO");
                                }else{
                                    System.out.println("INSERTE EL NUEVO VALOR A ALMACENAR");
                                    //tengo que usar un do while para evaluar un para de opciones
                                    do { 
                                        valor = teclado.nextDouble();
                                        if (valor == numeros[posicion]){
                                            System.out.println("EL NUEVO VALOR YA ES EL MISMO QUE EL ALMACENADO");
                                        }else{
                                            suma -= numeros[posicion];
                                            numeros[posicion] = valor;
                                            suma += valor;
                                            salir = true;
                                        }
                                    } while (!salir);
                                    //Una vez salgo de un bucle usando el boolean salir debo de asegurarme de volver su valor a false
                                    salir = false;
                                }
                            } while (posicion < 0 || posicion > numeros.length);
                            break;
                        default:
                            System.out.println("OPCION INCORRECTA, INDIQUELA DE NUEVO");        
                            break;
                    }
                    
                    break;
                case "E":
                    
                    //Añadimos aquí también el menú necesario
                    System.out.println("INDIQUE LO QUE DESEA HACER INTRODUCIENDO LA LETRA QUE APARECE A LA IZQUIERDA DE LA OPERACIÓN\n"+
                    "Z. - MEDIA DE LOS VALORES NO NULOS\n"+
                    "X. - SUMA DE TODOS LOS VALORES\n" + 
                    "M. - MÁXIMO DE LA COLECCIÓN\n"+
                    "N. - MÍNIMO DE LA COLECCIÓN\n"+
                    "C. - MOSTRAR ARRAY");

                    opcion = teclado.nextLine().toUpperCase();

                    switch(opcion){
                        case "Z":
                            //Si he hecho bien las cosas, los numeros distinto de cero deberían de estar en las primeras posiciones
                            //voy recorriendo con un bucle para ir sumando valores. Usaré media para sumar los valores distintos de cero
                            //y como tengo una variable que cuenta ya los números totales introducidos no debería de tener problemas para
                            //dividir

                            for(int i = 0; i < numeros.length; i++){
                                //Solo tengo que sumar los números que sean distintos de cero
                                if(numeros[i] != 0){
                                    media+=numeros[i];
                                }
                            }

                            //Una vez hechas las prubas me dí cuenta de que cada vez que borrarba o modificaba tenia que hacer cmabios
                            //en media o suma, asi que lo voy a hacer con un foreach en cada parte

                            //reinicio media primero

                            media = 0;

                            for(double valorAuxiliar2 : numeros){
                                media += valorAuxiliar2;
                            }

                            System.out.println("EL VALOR DE LA MEDIA DE LOS NUMEROS ALMACENADOS ES: "+
                            media/valoresTotales);
                            break;
                        case "X":

                            //Como ya he encontrado alguna cosa que no me cuadra modifico aquí y lo hago con un foreach
                            //primero reinicio el valor de suma que habia usado antes, porque habia cosas que daban fallo
                            //y no iba bien

                            for(double valoresAuxiliares3 : numeros){
                                suma += valoresAuxiliares3;
                            }
                            System.out.println("EL VALOR TOTAL DE LA SUMA DE LOS ELEMENTOS ES: " + 
                            suma);
                            break;
                        case "M":
                            
                            //Como no tuve en cuenta ciertas cosas de valor máximo y mínimo lo voy a tener que obtener
                            //recorriendo por completo el array con un for para determinarlo

                            for(int i = 0; i < numeros.length; i++){
                                if(i == 0){
                                    valorMax = numeros[i];
                                }
                                if(numeros[i] != 0){
                                    if(numeros[i] > valorMax){
                                        valorMax = numeros[i];
                                    }
                                }
                            }

                            System.out.println("EL VALOR MÁXIMO DE LOS ELEMENTOS ES: " + 
                            valorMax);
                            break;
                        case "N":
                            //Hago lo mismo que para el valor máximo pero modificando alguna cosas
                            for(int i = 0; i < numeros.length; i++){
                                if(i == 0){
                                    valorMin = numeros[i];
                                }
                                if(numeros[i] != 0){
                                    if(numeros[i] < valorMin){
                                        valorMin = numeros[i];
                                    }
                                }
                            }

                            System.out.println("EL VALOR MINIMO DE LOS ELEMENTOS ES: " + 
                            valorMin);
                            break;   
                        case "C":
                            //Uso un foreach para mostrar el array
                            for(double valorMostrar : numeros){
                                System.out.print("{" + valorMostrar +"}");
                            }
                            break;
                        default:
                            System.out.println("OPCION INCORRECTA, INDIQUELA DE NUEVO");        
                            break;
                    }

                    break;
                case "A":
                    System.out.println("INSERTE EL NUEVO VALOR DE LA COLECCIÓN, RECUERDE QUE HA DE SER MAYOR QUE EL EXISTENTE");
                    
                    //Defino un int para almacenar un valor intermedio del nuevo tamaño del array
                    int nuevaLongitud;

                    do { 
                        
                        //Indicamos un nuevo valor para la longitud del array y comprobamos si ya es menor que el existente

                        nuevaLongitud = teclado.nextInt();
                        if(nuevaLongitud < numeros.length){
                            System.out.println("NO TIENE SENTIDO AUMENTAR UNA COLECCION SI EL VALOR ES MENOR QUE EL EXISTENTE");
                        }else{

                            //Antes de nada copio todos los valores del array original en el auxiliar
                            System.arraycopy(numeros, 0, numerosAuxiliar,0, numeros.length);

                            numeros = new double[nuevaLongitud]; //Si el valor es mayor redimensionamos el array

                            //copio los valors del array auxiliar en el nuevo array numeros

                            System.arraycopy(numerosAuxiliar,0, numeros, 0, numerosAuxiliar.length);
                        }
                    } while (nuevaLongitud < numeros.length);
                    break;
                case "S":
                    //Al elegir esta simplemente deberia de salir
                    break;    
                default:
                    System.out.println("OPCION NO SOPORTADA, INDIQUE UNA NUEVA");
                    break;
            }
            
        } while (!"S".equals(opcion));
    }
}
