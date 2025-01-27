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
const parrafo1 = document.getElementById('enunciado_ejercicio');
const soluccion1 = document.getElementById('resultado_ejercicio');


//Ejercicio 1

//Para cada ejercicio, en el boton corresponidente, busco el evento que necesito, que en todos es click y llamo a la función
//que necesito para resolver el ejercicio. Lo que no estoy seguro, es de si en el orden en el que lo he puesto, no se ejecuta
//cada uno hasta que no se ha resuelto el anterior

boton1.addEventListener('click', saludo);
function saludo(){
    parrafo1.textContent = 'Vamos a mostrar un saludo';
    soluccion1.textContent = 'Buenos dias';
}


//Ejercicio 2
boton2.addEventListener('click', calcularArea);
function calcularArea(){
    let lado = 5;
    let areaRectangulo = lado * lado;
    parrafo1.textContent = 'Vamos a calcular el area de un rectantulo de lado ' + lado;
    soluccion1.textContent = 'El area es ' + areaRectangulo;


//Ejercicio 3
    boton3.addEventListener('click', calcularAreaIntroduciendoDato);
    function calcularAreaIntroduciendoDato(){
        let lado = prompt('Introduzca el valor del lado de un cuadrado');
        let areaCuadrado = lado * lado;
        parrafo1.textContent = 'El valor del area de un cuadrado es:';
        soluccion1.textContent = areaCuadrado;
    }

//Ejercicio 4 En este no pido los valores por pantalla, ese se hace más adelante
    boton4.addEventListener('click', numeros);
function numeros(){
    let num1 = 2;
    let num2 = 3;

    let suma = num1 + num2;
    let resta = num1 - num2;
    let producto = num1 * num2;
    let division = num1 / num2;

    parrafo1.textContent = 'Vamos a realizar una serie de operaciones con los números: ' + num1 + ' y ' + num2;
    soluccion1.textContent = 'La suma es ' + suma + ', la resta es ' + resta + ', el producto es ' + producto + ' y la division es ' +division;
}

//Ejercicio 5
    boton5.addEventListener('click', radio);
    function radio(){
        let radio = prompt('Introduce el radio de un círculo');

        let circunferncia = 2 * 3.14 * radio;
        let area = 3.14 * radio * radio;
        let volumen = 4/3 *(3.14 * (radio * radio * radio));

        parrafo1.textContent = 'La circunferencia tendrá un valor de ' + circunferncia;
        soluccion1.textContent = 'El area será de ' + area + ' y el volumen será de ' + volumen;
    }

//Ejercicio 6
    boton6.addEventListener('click', calcularDescuento);
function calcularDescuento(){
    let precioInicial = prompt('Introduzca el precio total de un producto');
    let precioVenta = prompt('Cual es el precio final al que se vende el producto');

    let descuento = 100 - ((100 * precioVenta)/precioInicial);
    parrafo1.textContent = 'El descuento que ha recibido es de';
    soluccion1.textContent = descuento;
}

//Ejercicio 7

    boton7.addEventListener('click', millasAMetros);
    function millasAMetros(){
    let milla = 1.852;
    let metros = prompt('Introduzca una cantidad de metros y lo convertiremos a millas marinas')

    let equivalencia = metros * milla;

    parrafo1.textContent = 'El valor de ' + metros + ' metros en millas marinas es de:';
    soluccion1.textContent = equivalencia;
    }

//Ejercicio 8
    boton8.addEventListener('click', ordenarNumeros);
function ordenarNumeros() {
    let num1 = prompt('Introduzca un número');
    let num2 = prompt('Introduzca un segundo número');

    parrafo1.textContent = 'Vamos a ordenar los números de menor a mayor';
    if (num1 > num2) {
        soluccion1.textContent = num2 + '/' + num1;
    } else {
        soluccion1.textContent = num1 + '/' + num2;
    }
}

//Ejercicio 9 Me he confundido en el orden, en este no tendría que haber pedido los número por teclado y si en el ejercico
    // numero 12, pero bueno, lo repetiremos en el 12
    boton9.addEventListener('click', evaluarNumeros);
function evaluarNumeros(){
        let num1 = prompt('Introduzca un número por teclado');
        let num2 = prompt('Introduzca otro número por teclado');

        parrafo1.textContent = 'Vamos a ver cual de los dos números es mayor o si son iguales';
        if(num1 > num2){
            soluccion1.textContent ='El numero mayor es ' + num1;
        }if(num2 > num1){
            soluccion1.textContent ='El numero mayor es ' + num2;
        }else{
            soluccion1.textContent ='Los números son iguales';
        }
}

//Ejercicio 10
    boton10.addEventListener('click', numeroMayor);
function numeroMayor(){
        let num1 = prompt('Introduzca un número');
        let num2 = prompt('Introduzca un número');
        let num3 = prompt('Introduzca un número');

        parrafo1.textContent = 'Vamos a ver cual de los 3 números es mayor. Estos han sido los números : ' + num1 + ', ' + num2 + ' y ' +num3;
        if(num1 > num2){
            if(num1 > num3){
                soluccion1.textContent = 'El número mayor es el ' + num1;
            }else{
                soluccion1.textContent = 'El número mayor es el ' + num3;
            }
        }else if(num2 > num3){
            soluccion1.textContent = 'El número mayor es el ' + num2;
        }else{
            soluccion1.textContent = 'El número mayor es el ' + num3;
        }
}

//Ejercicio 11
    boton11.addEventListener('click', operacionesNumerosTeclado);

    function operacionesNumerosTeclado(){
        let num1 = prompt('Introduzca un número por teclado');
        let num2 = prompt('Introduzca el segundo número');

        let suma = num1 + num2;
        let resta = num1 - num2;
        let producto = num1 * num2;
        let division = num1 / num2;

        parrafo1.textContent = 'La suma de los dos números es ' + suma + ' y la resta es ' + resta;
        if (num2 == 0) {
            soluccion1.textContent = 'El producto de los dos números es ' + producto + ' y no se puede dividir un número por cero ';
        }else{
            soluccion1.textContent = 'El producto de los dos números es ' + producto + ' y la division es ' + division;
        }
    }

    //Ejercicio 12
    boton12.addEventListener('click', numeroMayorTeclado);
    function numeroMayorTeclado() {
        let num1 = prompt('Introduzca un número por teclado');
        let num2 = prompt('Introduzca otro número por teclado');

        parrafo1.textContent = 'Vamos a ver cual de los dos números es mayor o si son iguales';
        if(num1 > num2){
            soluccion1.textContent ='El numero mayor es ' + num1;
        }if(num2 > num1){
            soluccion1.textContent ='El numero mayor es ' + num2;
        }else{
            soluccion1.textContent ='Los números son iguales';
        }
    }

    boton13.addEventListener('click',evaluarUnSoloNumero)
    function evaluarUnSoloNumero() {
        let num1 = prompt('Introduzca un número por teclado');
        parrafo1.textContent = 'Vamos a evaluar el número introducido';
        if(num1 >= 0){
            soluccion1.textContent = num1 + ' es positivo';
        }else{
            soluccion1.textContent = num1 + ' es negativo';
        }
    }

}