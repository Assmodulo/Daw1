const cajatexto = document.getElementById('cajatexto');

const boton = document.getElementById('boton');

const lista = document.getElementById('lista');

const borrarLista = document.getElementById('borrarLista');

const botonCompletar = document.getElementsByClassName('botonCompletar');

const listaTareas = [];

if(listaTareas.length === 0){
    borrarLista.style.visibility = 'hidden';
};

boton.addEventListener('click',() =>{
    let valor = cajatexto.value;
    listaTareas.push(valor);
    cajatexto.value ='';
    alert('Mensaje Añadido');
    generarLista();
});


function generarLista(){
    lista.innerHTML='';
    for(let i = 0;i<listaTareas.length;i++){
        const elemento = document.createElement('li');
        elemento.textContent = listaTareas[i];
        const botonCompletar = document.createElement('button');
        elemento.appendChild(botonCompletar);
        botonCompletar.textContent = 'Completar';
        botonCompletar.className = 'botonCompletar';
        botonCompletar.id = i;
        lista.appendChild(elemento);
        borrarLista.style.visibility = 'visible';
    }
}

borrarLista.addEventListener('click',()=>{
    lista.innerHTML='';
    listaTareas.length = 0;
    borrarLista.style.visibility = 'hidden';
});

botonCompletar.addEventListener('click', eliminarElementoPadre(i));


