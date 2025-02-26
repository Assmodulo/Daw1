// Creo constantes para cada elemento que necesite del documento
const cajatexto = document.getElementById('cajatexto');

const boton = document.getElementById('boton');

const lista = document.getElementById('lista');

const borrarLista = document.getElementById('borrarLista');


// Defino el array que almacena los elementos
const listaTareas = [];

// Aquí simplemente compruebo la longitud del array, si es cero, el boton borrarLista no se muestra
if(listaTareas.length === 0){
    borrarLista.style.visibility = 'hidden';
};

// Función para guardar el texto introducido en el text en el array
boton.addEventListener('click',() =>{
    let valor = cajatexto.value;
    if(cajatexto.value != ''){
        listaTareas.push(valor);
        cajatexto.value ='';
        // No es muy allá pero muestro un mensaje cada vez que se añade un elemento
        alert('Mensaje Añadido');
        // Llamo al método generarLista que va a crear la lista de elementos
        generarLista();
    }else{
        alert('Debe de introducir un texto');
    }
});

// Método que va creando la lista y la va actualizando
function generarLista(){
    // Por si acaso vacio la lista
    lista.innerHTML='';
    // Recorro el array con un bucle for
    for(let i = 0;i<listaTareas.length;i++){
        // En cada iteración creo un nuevo elemento li
        const elemento = document.createElement('li');
        // Inidico que el elemento tiene como contenido el valor de esa posición del array
        elemento.textContent = listaTareas[i];
        // Para cada elemento li, creo un botón, que podrá ser para lo que sea
        const botonCompletar = document.createElement('button');
        // Añado el botón al elemento li
        elemento.appendChild(botonCompletar);
        //Le doy un texto al botón, por probar le doy una clase y una id
        botonCompletar.textContent = 'Completar';
        botonCompletar.className = 'botonCompletar';
        botonCompletar.id = i;
        //Añado el elemento completo a la lista
        lista.appendChild(elemento);
        //Hago visible, ya que hay por lo menos un elemento si llego hasta aquí, el botón de eliminarLista
        borrarLista.style.visibility = 'visible';
    }
}

//Función de borrar Lista
borrarLista.addEventListener('click',()=>{
    //Borro todo el contenido de la lista
    lista.innerHTML='';
    //Borro el contenido de la lista indicándole que su nueva longitud es 0
    listaTareas.length = 0;
    //Vuelvo a esconder el boton
    borrarLista.style.visibility = 'hidden';
});



function eliminarFila(){
    //En esta he intentado de todo, pero no me ha salido nada
    //La dejo aquí con caracter informativo
};




