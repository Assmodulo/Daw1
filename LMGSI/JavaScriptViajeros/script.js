const areaTexto = document.getElementById('areaTexto');
const boton = document.getElementById('botonAgregar');
const formulario = document.getElementById('formulario');

const lista = document.getElementById('idlista');



let elementos = [];
let contadorElementos = 0;

formulario.addEventListener('submit',function(event) {
    event.preventDefault();
    let actividad = areaTexto.value;
    elementos.push(actividad);
    areaTexto.value = '';
    window.alert(elementos[contadorElementos - 1]);
})


