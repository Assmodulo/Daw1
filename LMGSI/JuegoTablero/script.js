
const dado = document.querySelectorAll(".dado");
const tablero = document.querySelector(".tablero");
const casillas = document.querySelectorAll(".casilla");
const ficha1 = document.getElementById("jugador1");

dado.forEach((dado,index) => {
    dado.setAttribute('valor', (index + 1).toString());
    console.log(dado.getAttribute('valor'));
});

estadoFicha1 = {
    posicion : 0
}

estadoJugador1 = {
    turnoActivo : true,
    posicionTablero : estadoFicha1.posicion,
    victoria : false
}

posicionInicial();

juego();

function tiraDado(){
    let caraActual = 0;
    let numeroAleatorio = Math.floor(Math.random() * 60 + 30);
    console.log(numeroAleatorio);
    let iteracion = 0;


        let intervalo = setInterval(() => {
            let caraAleatoria = Math.floor(Math.random() * (dado.length));
            dado[caraActual].style.zIndex = "1";
            dado[caraAleatoria].style.zIndex = "2";
            caraActual = caraAleatoria;



            iteracion++;

            if(iteracion === numeroAleatorio){
                console.log(caraActual);

                clearInterval(intervalo);

            }
            return Number(dado[caraActual].getAttribute('valor'));

        }, 100);


}

function posicionInicial(){

    let casillaInicial = casillas[estadoFicha1.posicion];
    casillaInicial.appendChild(ficha1);
    estadoJugador1.turnoActivo = true;
}


function juego(){


        if (estadoJugador1.turnoActivo === true) {
            let casillaActual = casillas[estadoFicha1.posicion];
            let tirada = tiraDado();
            console.log('El valor de la tirada es ' + tirada);
            estadoJugador1.turnoActivo = false;
        }
}

