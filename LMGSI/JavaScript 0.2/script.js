//Creo las const para cada elemento del documento
const boton1 = document.getElementById('boton1');
const boton2 = document.getElementById('boton2');
const boton3 = document.getElementById('boton3');
const boton4 = document.getElementById('boton4');
const boton5 = document.getElementById('boton5');
const boton6 = document.getElementById('boton6');
const boton7 = document.getElementById('boton7');
const boton8 = document.getElementById('boton8');
const boton9 = document.getElementById('boton9');
const boton10 = document.getElementById('boton10');
const boton11 = document.getElementById('boton11');
const boton12 = document.getElementById('boton12');
const boton13 = document.getElementById('boton13');
const boton14 = document.getElementById('boton14');
const parrafo1 = document.getElementById('parrafo1');
const parrafo2 = document.getElementById('parrafo2');

boton1.addEventListener('click', ejercicio1);
boton2.addEventListener('click', ejercicio2);
boton3.addEventListener('click', ejercicio3);
boton4.addEventListener('click', ejercicio4);
boton5.addEventListener('click', ejercicio5);
boton6.addEventListener('click', ejercicio6);
boton7.addEventListener('click', ejercicio7);
boton8.addEventListener('click', ejercicio8);
boton9.addEventListener('click', ejercicio9);
boton10.addEventListener('click', ejercicio10);
boton11.addEventListener('click', ejercicio11);
boton12.addEventListener('click', ejercicio12);
boton13.addEventListener('click', ejercicio13);
boton14.addEventListener('click', ejercicio14);

function ejercicio1() {
    let edad = prompt('Introduzca su edad');
    parrafo1.textContent = edad;
    if(edad >= 18){
        parrafo2.textContent = 'Es usted mayor de edad';
    }
}

function ejercicio2() {
    let edad = prompt('Indique su edad por teclado');

    parrafo1.textContent = 'Su edad es de ' + edad + ' años';
    if (edad >= 18){
        parrafo2.textContent = 'Es usted mayor de edad';
    }else{
        parrafo2.textContent = 'Es usted menor de edad';
    }
}

function ejercicio3() {
    let unoToDiez = '';
    let onceToVeinte = '';
    for (let i = 1; i < 11; i++) {
        unoToDiez +=  ' ' + i.toString();
    }

    for (let i = 11; i <= 20; i++) {
        onceToVeinte +=  ' ' + i;
    }

    parrafo1.textContent = unoToDiez;
    parrafo2.textContent = onceToVeinte;
}

function ejercicio4() {
    parrafo1.textContent = 'Números pares entre 1 y 200';
    for (let i = 2; i <= 200; i+=2) {
        if(i%2 == 0){
            parrafo2.textContent += ' ' + i;
        }
    }
}

function ejercicio5() {
    parrafo1.textContent = 'Números pares entre 1 y 200';
    for (let i = 1; i <= 200; i++) {
        if(i%2 == 0){
            parrafo2.textContent += ' ' + i;
        }
    }
}

function ejercicio6() {
    let numero = prompt('Introduzca un número N');
    parrafo1.textContent = 'Vamos a mostrar los números desde el 1 hasta el número que usted a introducido: ' + numero;
    for (let i = 1; i <= numero; i++) {
        parrafo2.textContent += ' ' + i;
    }
}

function ejercicio7() {
    let nota = prompt('Inserte la nota que cree usted que se merece');
    parrafo1.textContent = 'Esto es lo que le corresponde según su nota: ' + nota;
    switch (nota) {
        case '1':
            parrafo2.textContent = 'Muy Deficiente';
            break;
        case '2':
            parrafo2.textContent = 'Muy Deficiente';
            break;
        case '3':
            parrafo2.textContent = 'Insuficiente';
            break;
        case '4':
            parrafo2.textContent = 'Insuficiente';
            break;
        case '5':
            parrafo2.textContent = 'Bien';
            break;
        case '6':
            parrafo2.textContent = 'Notable';
            break;
        case '7':
            parrafo2.textContent = 'Notable';
            break;
        case '8':
            parrafo2.textContent = 'Notable';
            break;
        case '9':
            parrafo2.textContent = 'Sobresaliente';
            break;
        case '10':
            parrafo2.textContent = 'Sobresaliente';
            break;
        default:
            parrafo2.textContent = 'No creo que su nota sea null. No te escondas';
    }
}

function ejercicio8() {
    let factorial = prompt('Introduzca un número para que calculemos su factorial');
    factorial = parseInt(factorial);
    parrafo1.textContent = 'Vamos a calcular el factorial del número que ha introducido usted: ' + factorial;
    let resultadoFactorial = 1;
    if (factorial > 1) {
        for (let i = 2; i <= factorial; i++) {
            resultadoFactorial *= i;
        }
    }else if(factorial === 1 || factorial === 0){
        resultadoFactorial = 1;
    }
    parrafo2.textContent = resultadoFactorial;
}

function ejercicio9() {
    let hora = prompt('Introduzca Un valor de horas entre 0 y 23');
    hora = parseInt(hora);
    let minutos = prompt('Introduzca un valor de minutos entre 0 y 59');
    minutos = parseInt(minutos);
    let segundos = prompt('Introduzca un valor de segundos entre 0 y 59');
    segundos = parseInt(segundos);
    parrafo1.textContent = 'Si sumamos un segundo a su hora introducida que pasará: ' + hora +':'+ minutos + ':' + segundos + '';
    segundos += 1;
    if(segundos >= 60){
        segundos = 0;
        minutos += 1;
        if(minutos >= 60){
            minutos = 0;
            horas += 1;
            if(horas>= 24){
                horas = 0;
            }
        }
    }else{
        segundos += 1;
    }
    parrafo2.textContent = ' ' + segundos;
}

function ejercicio10() {

}

function ejercicio11() {

}

function ejercicio12() {

}

function ejercicio13() {

}

function ejercicio14() {

}