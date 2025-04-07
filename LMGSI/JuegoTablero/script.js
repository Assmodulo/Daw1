
const dado = document.querySelectorAll(".dado");
const tablero = document.querySelector(".tablero");
const casillas = document.querySelectorAll(".casilla");
const ficha1 = document.getElementById("jugador1");
const ficha2 = document.getElementById("jugador2");
const boton = document.getElementById("boton_accion");



boton.addEventListener("click", turno);

dado.forEach((dado,index) => {
    dado.setAttribute('valor', (index + 1).toString());
    console.log(dado.getAttribute('valor'));
});

estadoFicha1 = {
    posicion : 0
}

estadoFicha2 = {
    posicion : 0
}

estadoJugador1 = {
    turnoActivo : true,
    victoria : false
}

estadoJugador2 = {
    turnoActivo : false,
    victoria : false
}

posicionInicial();






function posicionInicial(){

    casillas.forEach(casilla => {
        if(casilla.hasChildNodes()){
            casilla.removeChild(casilla.firstChild);
        }
        if(casilla.classList.contains('FF1')){
            casilla.classList.toggle('FF1');
        }else if(casilla.classList.contains('FF2')){
            casilla.classList.toggle('FF2');
        }else if(casilla.classList.contains('FFAmbas')){
            casilla.classList.toggle('FFAmbas');
        }
    })


    let casillaInicial = casillas[0];
    casillaInicial.appendChild(ficha1);
    casillaInicial.appendChild(ficha2);
    estadoJugador1.turnoActivo = true;
    estadoJugador2.turnoActivo = false;

    estadoJugador1.victoria = false;
    estadoJugador2.victoria = false;

    estadoFicha1.posicion = 0;
    estadoFicha2.posicion = 0;

    casillaInicial.classList.add('FFAmbas');
}


function turno(){
        boton.disabled = true;

        if (estadoJugador1.turnoActivo === true) {
            tiraDado();
            setTimeout(movimientoFicha1, 3500);
        }else if(estadoJugador2.turnoActivo === true ){
            tiraDado();
            setTimeout(movimientoFicha2, 3500);
        }


}

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

    }, 100);


}

function movimientoFicha1(){
    let casillaActual = casillas[estadoFicha1.posicion];
    let casillaObjetivo;

    let valor = obtenerValor();

    for(let i = 1; i <= valor; i++) {
        casillaActual.removeChild(ficha1);
        casillaObjetivo = casillas[estadoFicha1.posicion + 1];
        casillaObjetivo.appendChild(ficha1);
        casillaActual = casillaObjetivo;
        if(casillaActual.id === '20'){
            estadoJugador1.victoria = true;
            posicionInicial();
            break;
        }
        estadoFicha1.posicion++;
        if(casillaActual.classList.contains('FF2')){
            casillaActual.classList.toggle('FFAmbas');
            casillaActual.classList.toggle('FF2');
        }else{
            casillaActual.classList.toggle('FF1');
        }
    }
    estadoJugador2.turnoActivo = true;
    estadoJugador1.turnoActivo = false;

    boton.disabled = false;
}


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
            posicionInicial();
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

function obtenerValor(){
    let valorDado;
    dado.forEach((cara)=>{
        if(cara.style.zIndex === '2'){
            valorDado = parseInt(cara.getAttribute('valor'));
        }
    });
    return valorDado;
}

