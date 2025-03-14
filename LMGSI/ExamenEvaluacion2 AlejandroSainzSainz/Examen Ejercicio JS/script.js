const titulo = document.getElementById("titulo_ejercicio");
const resultado = document.getElementById("resultado");


bienvenida();
function bienvenida(){
    titulo.textContent = 'Ejercicio 1';
    let celsius = prompt('Introduzca una cantidad de grados celsius');

    let farhen = (celsius * 9/5) + 32;
    let kelvin = celsius + 273.15;

    resultado.textContent = celsius + ' grados celsius son: ' + farhen + ' grados farhen ' + kelvin + ' grados kelvin';

    setTimeout(ejercicio2, 2000);
}

function ejercicio2(){
    titulo.textContent = 'Ejercicio 2';

    let numero = parseInt(prompt('Introduzca una numero positivo'));

    if(numero < 0){
        setTimeout(ejercicio2, 2000);
    }else{
        let primo = true;
        for(let i = 2; i < numero; i++){
            if(numero%i === 0){
                primo = false;
            }
        }
        if(primo){
            resultado.textContent = 'El numero ' + numero + ' es primo';
        }else{
            resultado.textContent = 'El numero ' + numero + ' no es primo';
        }
    }
    setTimeout(mostrarPrimos, 2000, numero);
}


function mostrarPrimos(numero){
    let numeroReferencia = 2;
    let primos = [];

    while(numeroReferencia < numero) {
        if (calcularPrimo(numeroReferencia)) {
            primos.push(numeroReferencia);
        }
        numeroReferencia++;
    }

    let cadena;
    primos.forEach(primo => {
        cadena += primos;
    })

    resultado.textContent = cadena;

    setTimeout(ejercicio3, 2000);
}

function calcularPrimo(numero) {
    let primo = false;

    for (let i = 2; i < numero; i++) {
        if (numero % i === 0) {
            primo = true;
        }
        return primo;
    }
}


function ejercicio3(){
    titulo.textContent = 'Ejercicio 3';
    let numero = parseInt(prompt('Introduzca un numero positivo'));

    let numeroResultado = 1;
    if(numero < 0){
        resultado.textContent = 'El numero que ha introducido debe de ser mayor que cero';
        setTimeout(ejercicio3, 2000);
    }else{
        for(let i = 2; i < numero; i++){
            numeroResultado *= i;
        }
    }

    resultado.textContent = 'El factorial del número ' + numero + ' es igual a ' + numeroResultado;

    setTimeout(descuentoCompra, 2000);
}

function descuentoCompra(){
    titulo.textContent = 'Ejercicio 4';

    let precio = parseInt(prompt('Introduzca el precio de un producto'));
    let cantidad = parseInt(prompt('Introduzca la cantidad comprada'));

    if((precio * cantidad) > 100){
        resultado.textContent = 'Al superar los 100 euros de compra usted pagará finalmente: ' + ((precio * cantidad) * 0.9);
    }else{
        resultado.textContent = 'Al no superar los 100 euros de compra usted pagará finalmente: ' + (precio * cantidad);
    }

    setTimeout(ejercicio5, 2000);
}

function ejercicio5(){
    titulo.textContent = 'Ejercicio 5';
    let intentos = 0;
    let min = 1;
    let max = 25;
    let numeroAleatorio =Math.floor( Math.random() * ((max - min + 1) + min));

    console.log(numeroAleatorio);

    let numeroUsuario = 0;


    do{
        numeroUsuario = parseInt(prompt('Introduzca un numero'));
        if(numeroUsuario > numeroAleatorio){
            window.alert('Debe de introducir un número menor');
            intentos ++;
        }else if (numeroUsuario < numeroUsuario){
            window.alert('Debe de introducir un número mayor');
            intentos++ ;
        }
    }while(numeroUsuario !== numeroAleatorio);

    resultado.textContent = 'Ha identificado el número aleatorio que era ' + numeroAleatorio + ' en ' + intentos + ' intentos';

    setTimeout(ejercicio6, 2000);

}


function ejercicio6(){
    titulo.textContent = 'Ejercicio 6';
    let salario = parseInt('Introduzca el salario de un empleado');
    let ventas = parseInt('Indique las ventas realizadas por el empleado este mes');

    if(ventas > 1000){
        resultado.textContent = 'Al superar 1000€ de ventas el salario final será de: ' +(salario + ((ventas - 1000) * 0.10));
    }else{
        resultado.textContent = 'Al no superar 1000€ de ventas el salario final será de: ' + salario;
    }

    setTimeout(ejercicio7, 2000);
}

function ejercicio7(){
    titulo.textContent = 'Ejercicio 7';

    let numeros = [];
    let contador = 1;

    do{
        let numero = parseInt('Introduzca un número entero');
        numeros.push(numero);
        contador++;
    }while(contador < 5);

    numeros.sort((a, b) => a - b);

    let cadena;
    numeros.forEach(numero =>{
       cadena += numero + ' ';
    });

    resultado.textContent = cadena;

    setTimeout(ejercicio8, 2000);
}

function ejercicio8(){
    titulo.textContent = 'Ejercicio 8';

    let palabra  = ('Inserte una palabra');
    let contador = 0;

    for(let i = 0; i < palabra.length;i++){
        if(palabra[i] === 'a' ||palabra[i] === 'e' || palabra[i] === 'i' || palabra[i] === 'o' ||palabra[i] === 'u'){
            contador++;
        }
    }

    resultado.textContent = 'El número total de vocales es : ' +contador;
}