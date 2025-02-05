const areaTexto = document.getElementById('areaTexto');
const boton = document.getElementById('botonAgregar');

const lista = document.getElementById('idlista');

boton.addEventListener('click', agregarElementoLista);

let elementos = [];

function agregarElementoLista() {
    let elemento = document.getElementById('areaTexto').value;
    console.log(elemento);
}