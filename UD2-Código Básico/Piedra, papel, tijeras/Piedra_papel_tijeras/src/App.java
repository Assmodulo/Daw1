import java.util.Scanner;


public class App {

    //Voy a definir estas variables static aquí para poder usarlas en los métodos. Sirven para que el juego funcione al mejor
    //de n rondas y para saber si el juego está configurado a juego por rondas
    static int rondas;
    static boolean porRondas = false;

    //Debo declarar tambien dos contadores para saber cuantas victorias lleva cada jugador

    static int vicJug = 0;//Contador victorias jugador
    static int vicCpu = 0;//contador victorias Cpu
    static int rondasJuego = 0;//Contador de las rondas jugadas para este juego

    public static void main(String[] args) throws Exception {
        
        //Lo primero que tengo que hacer es crear un objeto de la clase Scanner porque lo voy a necesitar

        Scanner teclado = new Scanner(System.in);
       
        //Como voy a tener que crear un menú, o quizás varios, creo una variable String para almacenar la opción seleccionada

        String opcion;

        //Creo 3 variables string para las jugadas de cada jugador y el resultado
        // Después de una serie de cambios estas tres variables las dejo comentadas
        //Simplemente para sabes que en algún momento las usé
        // String jugadaPlayer = "", jugadaCpu = "", enfrentamiento = "";


        System.out.println("BIENVENIDO A PIEDRA-PAPEL-TIJERAS");

        //Creo el primer menú, que será sencillo, para jugar una sola mano, según vaya añadiendo cosas quizá se vaya haciendo más grande

        do { 
            
            //Como lo que queremos es que funcione sin parar mostramos la primera información al usuario aquí por ahora
            
            System.out.println("SELECCIONE LA OPCIÓN DESEADA PULSANDO EL NÚMERO DE LA IZQUIERDA");
            System.out.println();

            System.out.println(
                "1. - JUGAR UNA MANO" + "\n" +
                "2. - INSTRUCCIONES DEL JUEGO" + "\n" +
                "3. - CONFIGURACIÓN DE LA PARTIDA\n" +
                "4. - SALIR"
            );

            opcion = teclado.nextLine();

            //Evaluo las opciones con un switch

            switch (opcion) {
                case "1":
                    
                    //Al elegir jugar el jugador debe de elegir una de las opciones, esto es otro menú, con do while
                    do{
                        System.out.println("AHORA ELIJA SU JUGADA. MARQUE SU OPCIÓN, TANTO EN MAYÚSCULAS COMO MINÚSCULAS.\n" + 
                        "V. - VAMOS ALLÁ " + "\n" +
                        "S. - VOLVER ATRÁS");

                        System.out.println();

                        opcion = teclado.nextLine().toUpperCase(); //Me aseguro de hacer que la opción siempre entre en mayúsculas para comparar mejor

                        //Evaluo de nuevo con un switch
                        //Después de las pruebas vi que lo mejor era empezar a crear métodos para organizar mejor las cosas

                        switch (opcion) {
                            case "V":
                                // En este paso va a mostrar por pantalla el resultado de realizar ciertos métodos de forma encadenada

                                //Primero voy a evaluar si el juego está configurado para jugarse por rondas, y depende del resultado
                                //haré una cosa u otra
                                if(porRondas){
                                    System.out.println("JUEGO POR RONDAS");
                                    System.out.println();
                                    boolean salir = false;
                                    do { 
                                        rondasJuego++;
                                        System.out.println("RONDA " + rondasJuego);
                                        System.out.println(resultado(versus(eleccionJugador(),eleccionCpu())));
                                        System.out.println();
                                        //Muestro como va la ronda después de cada mano jugada

                                        System.out.println("JUGADOR: " + vicJug + " VS CPU: " + vicCpu);

                                        if(vicCpu == ((rondas / 2) + 1)){
                                            salir = true;
                                        }else if(vicJug == ((rondas / 2) + 1)){
                                            salir = true;
                                        }
                                        if( rondasJuego == rondas){
                                            salir = true;
                                        }
                                       
                                    } while (!salir);

                                    //cuando salga del do/while habrá un ganador, hay que evaluar quién, asi que creo un método
                                    evaluacionRondas();
                                    
                                }else{
                                    System.out.println("MANO ÚNICA");
                                    System.out.println(resultado(versus(eleccionJugador(),eleccionCpu())));
                                    System.out.println();
                                }
                                
                                break;
                            case "S":
                                System.out.println("SOLO LOS COBARDES SE RETIRAN, VUELVE CUANDO ESTÉS LISTO");
                                break;    
                            default:
                                System.out.println("ESTÁS NERVIOSO? HAS ELEGIDO UNA OPCIÓN INCORRECTA. VAMOS, PRUEBA OTRA VEZ");
                                System.out.println();
                        }
                      
                    }while(!"S".equals(opcion));
                    break;

                case "2":
                    tutorial();
                    break;

                case "3":
                    configuracion();
                    break;    

                case "4":
                    System.out.println("HASTA LA PRÓXIMA PARTIDA. VUELVE CON GANAS");
                    break;
                default:
                    //Por default, el usuario ha introducido una opción no recogida y debe de introducirla de nuevo otra vez
                    System.out.println("HA ELEGIDO UNA OPCIÓN INCORRECTA, PRUEBE OTRA VEZ");
                    System.out.println();
                    break;
            }

        } while (!"4".equals(opcion));

    }

    //Creo un método, llamado tutorial que simplemente nos va a mostrar como funciona el juego

    public static void tutorial(){
        //voy mostrando por pantalla como funciona

        System.out.println("POR SI NO CONOCIAS EL JUEGO TE VOY A EXPLICAR COMO SE JUEGA");
        System.out.println("EN PIEDRA-PAPEL-TIJERAS CADA JUGADOR ELIGE EN SECRETO UNA DE LAS TRES OPCIONES");
        System.out.println("CUANDO CADA UNO DE LOS JUGADORES HA TERMINADO SU ELECCIÓN SE MUESTRAN LAS DOS\n"
        +"DE FORMA SIMULTANEA");
        System.out.println("LUEGO HAY QUE VER QUIEN HA GANADO SEGÚN SU ELECCIÓN\n"
        +"PIEDRA SIEMPRE GANA A TIJERA, POR QUE LAS ROMPE\n"+
        "PAPEL SIEMPRE GANA A PIEDRAS, PORQUE LA ENVUELVE\n"+
        "TIJERAS SIEMPRE GANA A PAPEL, PORQUE LO CORTA");
        System.out.println();
        System.out.println("ASÍ DE SIMPLE ES EL JUEGO. LO MEJOR ES QUE JUEGUES UNA MANO EN CUANTO PUEDAS");
        System.out.println("YA VEREMOS SI ERES CAPAZ DE DERROTARME");
        System.out.println();
        System.out.println("SI TE HA QUEDADO ALGUNA DUDA PUEDES VOLVER AQUÍ CUANDO QUIERAS");
    }

    /*He creado un apartado que es configuración de la partida. Dentro de ese apartado voy a crear un menú que
     * tenga opciones para explicar como configurar la partida y para cambiar esas configuraciones
     */

    public static void configuracion(){
        
        String opcion;
        
        do{
            
            System.out.println();
            System.out.println("MENÚ DE CONFIGURACIÓN\n");
            System.out.println("1. - EXPLICACIÓN\n");
            System.out.println("2. - CONFIGURACIÓN\n");
            System.out.println("3. - REINICIAR CONFIGURACIÓN\n");
            System.out.println("4. - VOLVER");

            Scanner teclado = new Scanner(System.in);
    
            opcion = teclado.nextLine();

            switch(opcion){

                case "1":
                    tutorialConfiguracion();
                    break;

                case "2":
                    configuracionRondas();
                    break;
                
                case "3":
                    reiniciarConfiguracion();
                    break;
                    
                case "4":
                    System.out.println("CONFIGURACIÓN TERMINADA");
                    break;

                default:
                    System.out.println("OPCIÓN INCORRECTA. PRUEBA OTRA VEZ");
                    break;
            }

        }while(!"4".equals(opcion));
    }

    /*Voy a crear el método mediante el cual voy a configurar el mejor de X rondas */

    public static void configuracionRondas(){
        System.out.println();
        System.out.println("VAMOS A MODIFICAR LAS RONDAS A LAS QUE VAMOS A JUGAR");
        
        int n = 0;

        //vamos a usar un do while para que el valor sea el correcto
        do { 
            Scanner teclado = new Scanner(System.in);
            // Pedimos el valor por teclado y lo almacenamos en una variable n
            System.out.println("INTRODUZCA POR TECLADO EL MEJOR DE CUANTAS RONDAS VA A GANAR. ASEGURESE DE QUE EL VALOR ES IMPAR");
            n = teclado.nextInt();
            if(n % 2 == 1){
                rondas = n;
                porRondas = true;
            }

            // Evaluamos si el valor introducido es     
        } while (n%2!=1);
        
    }

    /*Creo el método que da una explicación de como funciona la configuración de la partida*/

    public static void tutorialConfiguracion(){
        System.out.println();

        System.out.println("LA COSA VA ASÍ. CADA JUGADA INDIVIDUAL ES UNA RONDA.");
        System.out.println("SI QUIERES JUGAR AL MEJOR DE X RONDAS, PUEDES CONFIGURARLO EN ESTE MENÚ");
        System.out.println("CUANDO ENTRES EN CONFIGURACIÓN SE TE PREGUNTARÁ AL MEJOR DE CUANTAS RONDAs QUIERES JUGAR");
        System.out.println("SI QUIERES VOLVER A JUGAR SÓLO A UNA RONDA ELIGE RESETEAR CONFIGURACIÓN");
        System.out.println("BUENA SUERTE");
        System.out.println();
    }

    //El método siguiente reincia la configuración por rondas para que vuelva a ser manos individuales
    public static void reiniciarConfiguracion(){
        rondas = 0;
        porRondas = false;
    }

    //Este método se encarga de dar al jugador una herramienta para elegir su jugada que es el string que devuelve

    public static String eleccionJugador(){

        String opcion = ""; //Variable opción para el menú, ya que la que definí dentro de main no me sirve

        Scanner teclado = new Scanner(System.in); //Una variable de Scanner, la de main no me vale

        do{
            System.out.println("AHORA ELIJA SU JUGADA. MARQUE SU OPCIÓN, TANTO EN MAYÚSCULAS COMO MINÚSCULAS.\n" + 
            "P. - PIEDRA" + "\n" +
            "L. - PAPEL" + "\n" +
            "T. - TIJERAS" + "\n");

            System.out.println();

            opcion = teclado.nextLine().toUpperCase(); //Me aseguro de hacer que la opción siempre entre en mayúsculas para comparar mejor

            //Evaluo de nuevo con un switch
            //Después de las pruebas vi que lo mejor era empezar a crear métodos para organizar mejor las cosas

            switch (opcion) {
                case "P":
                    
                    System.out.println("PIEDRA, EL ARMA DE UN BÁRBARO!!");
                    System.out.println();
                    break;
                case "L":

                    System.out.println("PAPEL, LA HERRAMIENTA DE UN ERUDITO");
                    System.out.println();
                    break;
                case "T":

                    System.out.println("TIJERAS, QUE ES LO QUE VAS A CORTAR?");
                    System.out.println();
                    break;
                default:
                    System.out.println("ESTÁS NERVIOSO? HAS ELEGIDO UNA OPCIÓN INCORRECTA. VAMOS, PRUEBA OTRA VEZ");
                    System.out.println();
            }
        }while(!opcion.equals("P") &&  !opcion.equals("L") && !opcion.equals("T"));

        return opcion;
}

    //Este método sirve para que la cpu elija una jugada. Para elegir 1 de entre las 3 jugadas posibles, lo que he hecho es
    //crear un array en el que almaceno la inicial de cada jugada. Luego hago que el método retorne una de las posiciones del 
    //array de forma aleatoria

    public static String eleccionCpu(){
        
        //Creo un array, de el va a coger sus opciones la CPU

        String[] cpu = {"P" , "L" , "T"};
        
        //Ahora la cpu hace su elección

        System.out.println();
        System.out.println("QUE ELEGIRÉ ESTA VEZ? DEJAME PENSAR");
        System.out.println();

        //Uso el metodo Math.random para generar un número aleatorio entre 0 y 2 y hacer que la jugadaCpu sea igual a la letra en esa posición
        //de su array

        //En un inicio se me fue la olla y no se porque puse sólo Math*2 y tijeras no salía nunca, pero en principio
        //ya esta

        return cpu[(int)(Math.random()*3)];
    }

    /*En este método se reciben dos parámetros, el primero la elección del jugador, el segundo, la elección de la cpu, en ese orden
     * siempre. He decidido mostrar por pantalla aquí cual ha sido la elección de la cpu. Al final del método se devuelve la concatenación
     * de la elección del jugador + la elección de la cpu
     */

    public static String versus(String jugador, String cpu){
        //Ya que aquí recibo por parámetro el String que ha generado el método eleccionCpu, voy a evaluarlo par informar al player
        //de la elección de la cpu

        if(cpu.equals("P")){
            System.out.println("VOY A ELEGIR LA PIEDRA, A VER SI TE DOY CON ELLA");
        }
        
        if(cpu.equals("L")){
            System.out.println("PAPEL QUIZÁ? TENDRÉ QUE SER IMAGINATIVO Y VER QUE TE HAGO CON EL");
        }

        if(cpu.equals("T")){
            System.out.println("LAS TIJERAS!!! A LA YUGULAR");
        }

        //Luego ya retorno la concatenación de las dos elecciones para evaluar el resultado
        return jugador + cpu;
    }

    /*En el método resultado, que recibe un parámetro que es el string que devuelve el método versus se evalua ese String
     * Como la elección del jugador va a ser siempre la primera letra y la elección de la cpu la segunda letra, si la evaluo
     * con un if y el método equals puedo saber si el jugador ha ganado la ronda, si han empatado o si la cpu ha ganado
     */

    public static String resultado(String evaluacion){
        
        //Defino una variable String para devolver una cadena

        String resultado = "";
        
        //Ahora uso un if para evaluar resultados según el contenido de la variable enfrentamiento. El método que uso es que
        //la primera letra es la elección del jugador, la de la derecha es la de la cpu, dependiendo de su combinación podemos saber
        // si ha ganado el jugador, la cpu o es empate.

        if(evaluacion.equals("PT") || evaluacion.equals("TL") || evaluacion.equals("LP")){
            resultado = "VAYA. PARECE QUE HAS SIDO MÁS HÁBIL QUE YO... ESTA VEZ\n\nDAME OTRA OPORTUNIDAD... VAMOS... ESTA VEZ GANARÉ YO";
            //Despues de añadir el juego por rondas debo evaluar aquí si es un juego por rondas para aumentar contadores
            if(porRondas){
                vicJug++;
            }
            } else if(evaluacion.equals("PP") || evaluacion.equals("TT") || evaluacion.equals("LL")){
            resultado = "NOS HEMOS LEIDO LA MENTE? HABRÁ QUE JUGAR UN DESEMPATE\n\nPREPÁRATE, AHÍ VAMOS DE NUEVO";
            } else {
                resultado ="NO ESTABAS PREPARADO. TENDRÁS QUE MEJORAR LA PROXIMA VEZ\n\nNO SERÍA JUSTO QUE NO TE DIESE OTRA OPORTUNIDAD, VAMOS, JUEGA DE NUEVO";
                if(porRondas){
                    vicCpu++;
                }
        }
        return resultado;
        
    }

    public static void evaluacionRondas(){

        //Después de unos días, al darme cuenta de que las rondas pueden acabar en empate, me di cuenta también de que
        //este código lo habia hecho mal, pues podia ser que al mejor de N tambien acabase en empate, asi que cambie
        //los terminos en los que se evalua

        if(vicJug > vicCpu){
            System.out.println("NOOOOOOOO..... IMPOSIBLE..... COMO HAS PODIDO?");
            System.out.println();
        }else if (vicCpu > vicJug){
            System.out.println("CREIAS QUE PODIAS GANAR?...DE VERDAD?.... TU?");
            System.out.println();
        }else{
            System.out.println("Y DE TODAS MANERAS NO HEMOS CONSEGUIDO DICTAMINAR UN VENCEDOR");
            System.out.println();
        }

        System.out.println("SEA COMO SEA, SIEMPRE PODEMOS JUGAR OTRA PARTIDA CUANDO QUIERAS. TE ANIMAS?");
        System.out.println();

        //antes de salir de este metodo, debo de reiniciar contadores de victoria y contador de rondas jugadas, por si el jugador
        //vuelve a jugar otra partida igual que no haya problema

        vicCpu = 0;
        vicJug = 0;
        rondasJuego = 0;
    }
}
