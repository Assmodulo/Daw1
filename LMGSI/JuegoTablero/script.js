/*Este es es script. Lo primero que hago es declarar las constantes, con getElementbyId o quearyselector, dependiendo
* de lo que necesite. Declaré también una constante para el tablero, pense que la iba a utilizar, al final no fue así, pero
* la dejé por si a ultima hora o en algun momento añadia algo*/

const dado = document.querySelectorAll(".dado");
const tablero = document.querySelector(".tablero");
const casillas = document.querySelectorAll(".casilla");
const ficha1 = document.getElementById("jugador1");
const ficha2 = document.getElementById("jugador2");
const boton = document.getElementById("boton_accion");
const jugador1 = document.querySelector(".jugador1");
const jugador2 = document.querySelector(".jugador2");
const activo = document.querySelector(".jugador_activo");
const ganador = document.querySelector(".ganador");


/*Al boton le debo de añadir un event Listener para que lance cada turno cuando corresponda*/
boton.addEventListener("click", turno);

/*Lo que hago con este foreach es darle un atributo a cada cara del dado para que tenga un valor. Al conseguir que todo esto
funcionase me di cuenta, ya muy tarde, que podía conseguir el mismo efecto usando el index de cada cara dentro del array que se
forma al usar queryselectorall, que es lo mismo que necesito si le sumo 1. De todas formas, en un principio esto me ayudó bastante*/
dado.forEach((dado,index) => {
    dado.setAttribute('valor', (index + 1).toString());

});

/*Creo una sere de objetos, los de estado de ficha podrían haber sido variables simplemente, pero lo hice así. En los objetos
* para las fichas que se almacene su posición.*/
estadoFicha1 = {
    posicion : 0
}

estadoFicha2 = {
    posicion : 0
}

/*En los objetos que corresponde a cada jugador que se indique de quien es el turno activo y si el jugador a obtenido la victoria*/
estadoJugador1 = {
    turnoActivo : true,
    victoria : false
}

estadoJugador2 = {
    turnoActivo : false,
    victoria : false
}

/*Posición inicial es un método para ordenar todo al cargar la página, y que también me sirve para que se reincie cuando alguién
gana la partida*/
posicionInicial();


/*A partir de aquí defino la función posición inicial*/

function posicionInicial(){

    /*Como no se me ocurrió una forma mejor de hacerlo, para que tambíen ocurra al reiniciar una partida después de
    * una victoria, lo que hice fue recorres todas las casillas del tablero, y si alguna tenia un nodo hijo lo quitase de
    * allí, para asegurarme de que las fichas se retiraban del tablero*/
    casillas.forEach(casilla => {
        if(casilla.hasChildNodes()){
            casilla.removeChild(casilla.firstChild);
        }

        /*Después me aseguro de quitar todos los estilos a todas las casillas, con la función toogle. Creo que esta era
        * la unica forma de hacerlo pués no todas las casillas tendrían el mismo estilo, así que tengo que checkear las tres
        * clases
        * */
        if(casilla.classList.contains('FF1')){
            casilla.classList.toggle('FF1');
        }else if(casilla.classList.contains('FF2')){
            casilla.classList.toggle('FF2');
        }else if(casilla.classList.contains('FFAmbas')){
            casilla.classList.toggle('FFAmbas');
        }

        jugador2.style.visibility = 'hidden';
        ganador.style.visibility = 'hidden';
        activo.style.top = "20%";
    })

    /*Una vez limpiado el tablero selecciono la que es la casilla inicial, y le añado como nodos las fichas*/
    let casillaInicial = casillas[0];
    casillaInicial.appendChild(ficha1);
    casillaInicial.appendChild(ficha2);

    /*Aunque pueda parecer injusto, el jugador uno siempre empieza, como las blancas en ajedrez. Para el jugador2 turnoActivo
    * es igual a false*/
    estadoJugador1.turnoActivo = true;
    estadoJugador2.turnoActivo = false;

    /*Los estados de victoria de los dos jugadores son marcados como false*/
    estadoJugador1.victoria = false;
    estadoJugador2.victoria = false;

    /*La posición de cada ficha se resetea a cero*/
    estadoFicha1.posicion = 0;
    estadoFicha2.posicion = 0;

    /*Como ambas fichas empiezan en la casilla 1 esta debe de tener la clase FFAmbas, para que se le aplique el estilo
    * correspondiente*/
    casillaInicial.classList.add('FFAmbas');
}


/*La función turno, dependiendo del jugador activo, lo que va a hacer es ejecutar el turno del jugador 1 o 2*/
function turno(){
        //Para no poder tirar el dado indefinidamente, le deshabilito hasta que termine cada turno
        boton.disabled = true;

        /*Aquí vino uno de los problemas que tuve. Se supone que te iba a mandar el código para que le hechases un vistazo
        * pero al final encontré una solucción por mi cuenta. Como la función tiraDado tarda un poco, el programa siempre elegia
        * la misma cara, que era la inicial, todas las tiradas eran como 1. Lo que hice fue, calcular más o menos lo que tarda en
        * generar la tirada y con setTimeout esperar 3 segundos y medio hasta que sigue ejecutando el código, así conseguí que la cara
        * con zindex dos fuese el resultado que necesitaba. Los bloques dentro de los ifs son iguales, pero tal y como habia planteado
        * el programa, no se me ocurrió otra forma de hacerlo, ni siquiera pasando parámetros a las funciones, pués no se como
        * pasar un objeto como parametro en JS*/
        if (estadoJugador1.turnoActivo === true) {
            jugador2.style.visibility = 'hidden';
            jugador1.style.visibility = 'visible';
            activo.style.top = "20%";
            tiraDado();
            setTimeout(movimientoFicha1, 3500);
        }else if(estadoJugador2.turnoActivo === true ){
            jugador1.style.visibility = 'hidden';
            jugador2.style.visibility = "visible";
            activo.style.top = "80%";
            tiraDado();
            setTimeout(movimientoFicha2, 3500);
        }


}

/*Cuando se hace click en el botón se lanza esta función.*/
function tiraDado(){

    /*Indico una cara actual cero, que coincide con el index del uno. Marco un número aleatorio, que va a ser las veces
    * que el dado va a cambiar de cara. Inicio una variable iteración que va a llevar la cuenta de porque parte del ciclo
    * va el dado*/
    let caraActual = 0;
    let numeroAleatorio = Math.floor(Math.random() * (30-15) + 16);
    let iteracion = 0;


    /*Esto tuve que buscarlo pues no sabia como hacerlo de otra manera. Si le hacía de la forma normal con un for, el
    * proceso va tan rápido que salta a la cara final sin que se vea ningun cambio. Con esto lo que consigo es que cada
    * x milisegundo se cambie la cara del dado y que podamos notar ese cambio.*/
    let intervalo = setInterval(() => {
        //Con este número aleatorio elijo una cara aleatoria del dado
        let caraAleatoria = Math.floor(Math.random() * (dado.length));
        //La cara actual, que en un inicio es 1 pasa a tener un zindex de 1, la cara aleatoria un zindex de 2
        dado[caraActual].style.zIndex = "1";
        dado[caraAleatoria].style.zIndex = "2";
        //Para que todo se vuelvar a ejecutar debo de hacer que la cara actual sea igual a la aleatoria, así puedo
        //cambiar correctamente las posiciones
        caraActual = caraAleatoria;

        //Incremento el valor de la iteración para que se compare con el primer número aleatorio que equivale al número de
        //veces que se tiene que ejecutar este ciclo
        iteracion++;

        //Compruebo si iteracion es igual al primer numero aleatoria, de ser así, rompe el intervalo para que no se reincie
        //ni se repita más
        if(iteracion === numeroAleatorio){

            clearInterval(intervalo);

        }
        //Fui probando con diferentes duraciones de intervalo hasta que el resultado me gusto. Finalmente fueron 100 milisegundos
    }, 100);


}

/*Método para realizar el movimiento de la ficha. Intenté de todas formas que el movimiento de las fichas se realizase
*    de casilla en casilla, pero siempre acababa explotando todo por algún lado. Así que como no supe hacerlo de otra forma
*    ha tenido que ser finalmente así*/
function movimientoFicha1(){
    //Indico la casillaActual de una ficha buscando su posición, que la tengo almacenada
    let casillaActual = casillas[estadoFicha1.posicion];
    //Declaro otra variable que será la casilla objetivo
    let casillaObjetivo;

    //Llamo una función que va a obtener el valor del dado
    let valor = obtenerValor();

    /*Con este for, la ficha se iría moviendo de casilla en casilla, pero como en la tirada de dado, va hasta el final
    * Pensé en usar otro interval pero como no entendí muy bien como funciona de la manera que lo usé para el dado, decidí
    * no complicarme y que se hiciese todo de golpe*/
    for(let i = 1; i <= valor; i++) {
        //Según esto se va moviendo de una en una, pero al final se ve sólo el resultado final
        casillaActual.removeChild(ficha1);
        casillaObjetivo = casillas[estadoFicha1.posicion + 1];
        casillaObjetivo.appendChild(ficha1);
        casillaActual = casillaObjetivo;

        //Evaluo si se llega a la casilla con id 20 para declarar un ganador, llamar a la función posición inicial y romper
        //el bucle
        if(casillaActual.id === '20'){
            estadoJugador1.victoria = true;
            jugador2.style.visibility = 'hidden';
            activo.style.visibility = 'hidden';
            ganador.style.visibility = 'visible';
            ganador.style.top = "20%";
            setTimeout(posicionInicial, 3000);
            break;
        }
        //Si la casilla objetivo no es la 20, lo que se hace es actualizar la posición de la ficha y el estilo de la
        //casilla según las fichas que hayan pasado por encima de ella
        estadoFicha1.posicion++;
        if(casillaActual.classList.contains('FF2')){
            casillaActual.classList.toggle('FFAmbas');
            casillaActual.classList.toggle('FF2');
        }else{
            casillaActual.classList.toggle('FF1');
        }
    }
    //Cambio los estados del turnoActivo
    estadoJugador2.turnoActivo = true;
    estadoJugador1.turnoActivo = false;

    //Vuelvo a habilitar el botón una vez terminado todo el proceso
    boton.disabled = false;
}

/*Como no supe como hacerlo pasando parámetros a las funciones, al final he tenido que hacer el mismo método dos veces
* siendo uno específico para un jugador y otro para otro*/
function movimientoFicha2(){
    let casillaActual = casillas[estadoFicha2.posicion];
    let casillaObjetivo;

    let valor = obtenerValor();

    for(let i = 1; i <= valor; i++) {
        casillaActual.removeChild(ficha2);
        casillaObjetivo = casillas[estadoFicha2.posicion + 1];
        casillaObjetivo.appendChild(ficha2);
        casillaActual = casillaObjetivo;
        if(casillaActual.id === '20'){
            estadoJugador2.victoria = true;
            jugador1.style.visibility = 'hidden';
            activo.style.visibility = 'hidden';
            ganador.style.visibility = 'visible';
            ganador.style.top = "90%";
            setTimeout(posicionInicial, 3000);
            break;
        }
        estadoFicha2.posicion++;
        if(casillaActual.classList.contains('FF1')){
            casillaActual.classList.toggle('FFAmbas');
            casillaActual.classList.toggle('FF1');
        }else{
            casillaActual.classList.toggle('FF2');
        }
    }

    estadoJugador2.turnoActivo = false;
    estadoJugador1.turnoActivo = true;

    boton.disabled = false;
}

//Esta función, como ya dije el principio, va a basarse en el atributo valor de la cara del dado. Podía haber usado el index
function obtenerValor(){
    //Declaro una variable que almacene el valor
    let valorDado;
    //recorro el array dado, que almacena todas las caras
    dado.forEach((cara)=>{
        //Busco aquella cara cuyo zindex sea 2
        if(cara.style.zIndex === '2'){
            //Para obtener el valor parseo el atributo valor de la cara, ya que los atributos siempre son string, hasta
            //donde yo se
            valorDado = parseInt(cara.getAttribute('valor'));
        }
    });
    //Devuelvo el valor obtenido
    return valorDado;
}

