
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

turno();


function tiraDado(){
    let caraActual = 0;
    let numeroAleatorio = Math.floor(Math.random() * (30-15) + 16);
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


function turno(){

        if (estadoJugador1.turnoActivo === true) {
            tiraDado();
            setTimeout(movimientoFicha,3500);
        }
}

function movimientoFicha(){
    let casillaActual = casillas[estadoFicha1.posicion];
    let casillaObjetivo;
    console.log(casillaActual);
    let valorInicial = parseInt(casillaActual.id);
    console.log(valorInicial);
    let valor = obtenerValor();
    console.log(valor);


    for(let i = 1; i <= valor; i++) {
        casillaActual.removeChild(ficha1);
        casillaObjetivo = casillas[estadoFicha1.posicion + 1];
        casillaObjetivo.appendChild(ficha1);
        casillaActual = casillaObjetivo;
        estadoFicha1.posicion++;
    }

}

function obtenerValor(){
    let valorDado;
    dado.forEach((cara)=>{
        if(cara.style.zIndex === '2'){
            valorDado = parseInt(cara.getAttribute('valor'));
        }
    });
    return valorDado;
}

