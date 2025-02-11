document.addEventListener('DOMContentLoaded', () => {


const areaTexto = document.getElementById('areaTexto');
const formulario = document.getElementById('formulario');
const contenedorLista = document.getElementById('idlista');


//Vamos a ver, muchas de las cosas que he intentado han sido siguiendo tu ejemplo. Luego he leido en profundidad un articulo
//en MDN para entender como funciona lo de JSON. Así que ahora voy a intentar ir poco a poco para ver si consigo que esto funcione

//En la web no hablaban de local storage, eso solo lo he visto en tu ejemplo, así que lo usaré como lo pusiste tú pero con mi nombre
//La suerte es que intellig directamente en cuanto escribo JSON me autocompleta con lo mismo que tú has puesto.

let elementos = JSON.parse(localStorage.getItem('elementos')) || [];

//Antes de mirar todo por internet y los apunte había creado un contador, por la cosa de llevar un registro de cuantos elementos
//había creado, no se si lo usaré ahora, pero aquí se queda por si las moscas.
let contadorElementos = 0;


//Este es el evento para guardar los datos. Yo lo había intentado de mil formas raras y no había manera. Ahora, siguiendo tu
//ejemplo y lo que veo en la web, lo voy a hacer un poco a mi manera, aunque tampoco es que tenga muchas maneras ahora mismo
//Las primeras veces lo habia creado dentro de un addeventlistener para el botón haciendo click, y ahí no funcionaba nada
formulario.addEventListener('submit',function(event) {
    event.preventDefault();//Esto entiendo para que es, lo que no se es cual es el funcionamiento predeterminado del formulario
    let actividad = areaTexto.value.trim();//Esto yo solo había probado con value. Lo de trim no me sonaba ni de lejos
    elementos.push(actividad);//Esto viendo los apuntes era fácil. Como otros arrays en java
    localStorage.setItem('elementos', JSON.stringify(actividad)); //Esto todavia no entiendo del todo de que va, todo lo de localstorage y eso
    mostrarLista()
    areaTexto.value = '';
    //window.alert(elementos[contadorElementos - 1]); Esta la había puesto aquí en los primeros intentos, para que mostrase
    //Lo que acabo de añadir. No saltaba nunca, pero como tampoco sabía bien lo que hacia, era de esperar
})




function mostrarLista(){
    //No me voy a molestar en limpiar la lista y esas cosas todavía. Por ahora tengo que ver si las cosas pitan
    elementos.forEach((elemento, indice) => {
        const elementoLista = document.createElement('li');
        elementoLista.innerHTML = `
            <span>${elemento}</span>
            <button>Eliminar</button>
        `;//Casi ni veía las comillas esas raras en el ejemplo. No me complico, creo esto por ahora, solo básico
    contenedorLista.appendChild(elementoLista);
    });
}

})
