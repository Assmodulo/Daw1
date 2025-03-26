
const dado = document.querySelectorAll(".dado");
const tablero = document.querySelector(".tablero");
const casillas = document.querySelectorAll(".casilla");
const ficha1 = document.getElementById("jugador1");

dado.forEach((dado,index) => {
    dado.addEventListener("click", tiraDado);
});

posicionInicial();

function tiraDado(){
    event.preventDefault();
    let caraActual = 0;
    let numeroAleatorio = Math.floor(Math.random() * 60 + 30);
    console.log(numeroAleatorio);
    let iteracion = 0;

        let intervalo = setInterval(() => {
            let caraAleatoria = Math.floor(Math.random() * (dado.length));
            console.log('la cara aleatoria es ' + caraAleatoria);
            dado[caraActual].style.zIndex = "1";
            dado[caraAleatoria].style.zIndex = "2";
            caraActual = caraAleatoria;

            iteracion++;
            console.log('iteracion es ' + iteracion);

            if(iteracion === numeroAleatorio){
                clearInterval(intervalo);
            }
        }, 100);
        return caraActual;
}

function posicionInicial(){
    let casillaInicial = document.getElementById("1");
    let numeroCasilla = parseInt(casillaInicial.id);
    console.log('La casilla inicial es ' + numeroCasilla);
    casillaInicial.appendChild(ficha1);
    ficha1.style.gridRowStart="1";
    ficha1.style.gridColumnStart="2";
}


