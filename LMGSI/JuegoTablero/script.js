
const dado = document.querySelectorAll(".dado");

dado.forEach((dado) => {
    dado.addEventListener("click", tiraDado);
});

function tiraDado(){
    event.preventDefault();
    let caraActual = 0;
    let numeroAleatorio = Math.floor(Math.random() * 25 + 10);
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
        }, 250);
}


