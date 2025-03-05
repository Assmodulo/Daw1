//Esto son los enlazadores de los elementos de html para poder trabajar con ellos en javascript
const bottonVisualizacion = document.getElementById("cambiar_estilos");
const botonesAgregar = document.querySelectorAll(".botones_agregar");
const carritoHtml = document.querySelector(".carrito_compra");
const carritoBoton = document.getElementById("carro_compra");
const total = document.getElementById("total");
let totalCompra = document.getElementById("carrito_total");
const body = document.querySelector('body');
const cabecera = document.querySelector('header');
const documento = document.querySelector('html');
const tarjetas = document.querySelectorAll(".burger-card");

//Modifico algunas propiedades de elementos que no quiero que se vean al cargar la clase
total.style.visibility = 'hidden';
totalCompra.style.visibility = 'hidden';

//Añado funcionalidad y estilos al botón de mostrar carrito de la compra
carritoBoton.addEventListener("click",revisarEstadoCarro);
carritoBoton.style.visibility = "hidden";


//Defino un array para almacenar los elementos agregados previamente a añadirlos a la web
let objetosAlmacenados = [];

//Llamo a la función bienvenida para lanzar un mensaje al inicio
bienvenida();

//Función bienvenida que modifica la visualización de la página y crea varios elementos para mostrar un mensaje
function bienvenida(){
    //Creo un div que va a contener un mensaje
    const mensaje = document.createElement('div');

    //reduzco la opacidad y aplico blur al body y la cabecera para que se muestren mas atenuados
    body.style.opacity = '25%';
    body.style.filter = 'blur(10px)';
    cabecera.style.opacity = '25%';
    cabecera.style.filter = 'blur(10px)';

    //Modifico los estilos del contenedor del mensaje
    mensaje.style.width = '75%';
    mensaje.style.heigth = '300px';
    mensaje.style.backgroundImage = 'linear-gradient(-90deg, darkred 45%, lightcoral)';
    mensaje.style.margin = 'auto';
    mensaje.style.position = 'absolute';
    mensaje.style.top = '50%';
    mensaje.style.left = '50%';
    mensaje.style.transform = 'translate(-50%, -50%)';
    mensaje.style.borderRadius = '20px';

    //Creo un elemento h3 dentro del elemento mensaje para mostrar un texto y modifico su estilo
    const parrafo = document.createElement('h3');
    parrafo.textContent = 'Bienvenido a Burger Factory';
    parrafo.style.color = 'white';
    parrafo.style.fontWeight = 'bold';
    parrafo.style.textAlign = 'center';
    parrafo.style.fontStyle = 'italic';
    parrafo.style.textShadow = '3px 3px 3px black';
    parrafo.style.fontSize = '2em';

    //Añado estos elementos a la estructura de la página
    mensaje.appendChild(parrafo);
    documento.appendChild(mensaje);

    //Uso timeout para desvanecer el mensaje
    setTimeout(desvanecerMensaje, 2000, mensaje,parrafo);
}

//Funcion para desvanecer el mensaje y volver la visibilidad de la página a su estado normal
function desvanecerMensaje(mensaje, parrafo){
    body.style.opacity = '100%';
    cabecera.style.opacity = '100%';
    cabecera.style.filter = 'none';
    body.style.filter = 'none';
    body.style.transitionDuration = '5s';
    cabecera.style.transitionDuration = '5s';
    mensaje.style.opacity = '0';
    mensaje.style.transitionDuration = '5s';

    //Creo otra funcion llamada por un timeout para eliminar los elementos creados anteriormente
    setTimeout(eliminarMensaje, 1000, mensaje, parrafo);

}

//Funcion que elimina definitivamente los elementos creados con anterioridad
function eliminarMensaje(mensaje, parrafo){
    mensaje.removeChild(parrafo);
    documento.removeChild(mensaje);
}

//Como los botones de agregar estan seleccionados todos con un querySelector, que da como resultado un arbol de nodos
//tengo que crear una función que a cda botón le agregue un evenlistener, que llama a la funcion de agregar al carrito
//cuando pueda creo en el html y el css el elemento carrito
botonesAgregar.forEach(botonesAgregar=>{
    botonesAgregar.addEventListener('click',agregarCarro);
    botonesAgregar.addEventListener('mouseover', cambiosDeColores);
    botonesAgregar.addEventListener('mouseout', retornoColores);
});

//Función cambios de colores en los botones
function cambiosDeColores(event){
    event.target.style.backgroundColor = "red";
    event.target.style.boxShadow = "2px 2px 2px darkred"
    event.target.innerHTML = "+";
    event.target.style.borderRadius = "50%";
}

function retornoColores(event){
    event.target.style.backgroundColor = "lightgray";
    event.target.style.boxShadow = "none";
    event.target.innerHTML = 'AGREGAR';
    event.target.style.borderRadius = "5px";
}


//Esta va a ser la primera función para agregar al carrito, estoy probando cosas
function agregarCarro(event){
    leerDatosHamburguesa (event.target.parentElement);
    //console.log(leerDatosHamburguesa);
}

//Función para manejar los datos de la hamburguesa que queremos agregar al carrito
function leerDatosHamburguesa(hamburguesa){
    //Creo un objeto con lo que me hace falta de cada hamburguesa
    let infoBurguer = {
        foto : hamburguesa.querySelector("img").src,
        precio : parseFloat(hamburguesa.querySelector("span").textContent),
        cantidad : 1,
        id: hamburguesa.querySelector("button").getAttribute("id")
    }
    //console.log(infoBurguer);
    almacenarObjetosIntermedios(infoBurguer);
    carritoBoton.style.visibility = "visible";
}

//Función para agregar objetos almacenados a nuestro arreglo
function almacenarObjetosIntermedios(infoBurguer){
    objetosAlmacenados.push(infoBurguer);
    //console.log(objetosAlmacenados);
}

//Funcion del botón del carro de la compra que checkea su estado
function revisarEstadoCarro(event){
    event.preventDefault();
    if(objetosAlmacenados.length > 0){
        crearListadoCompra();
        event.target.style.backgroundColor = "lightgreen";
    }
}

//Funcion que crea elementos en el carrito de la compra
function crearListadoCompra(){
    carritoHtml.innerHTML = "";
    carritoHtml.style.opacity = "100%";
    carritoHtml.style.transitionDuration = "1.5s";
    carritoHtml.style.width = "fit-content";
    carritoHtml.style.height = "fit-content";
    totalCompra.innerHTML = "";
    let precioTotal = 0;
    objetosAlmacenados.forEach(objeto => {
        const espacioHamburguesa = document.createElement("div");
        const foto = document.createElement("img");
        foto.src = objeto.foto;
        foto.style.width = "100px";
        foto.style.height = "100px";
        espacioHamburguesa.appendChild(foto);
        const precio = document.createElement("span");
        precio.innerHTML = objeto.precio;
        precioTotal += objeto.precio;
        espacioHamburguesa.appendChild(precio);
        totalCompra.innerHTML = precioTotal;
        const cantidad = document.createElement("span");
        cantidad.innerHTML = objeto.cantidad;
        espacioHamburguesa.appendChild(cantidad);
        carritoHtml.appendChild(espacioHamburguesa);
        total.style.visibility = 'visible';
        totalCompra.style.visibility = 'visible';
        setTimeout(cerrarVentanaCarrito, 3000);
    })
}

//Con el timeout me aseguro de que la ventana del carrito desaparezca para que no se vea, ya que no me arreglo con el botón
function cerrarVentanaCarrito(){
    carritoHtml.style.opacity = "0%";
    carritoHtml.style.transitionDuration = "1.5s";
    carritoHtml.style.width = "0%";
    carritoHtml.style.height = "0%";
    carritoBoton.style.backgroundColor = "lightgray";
    total.style.visibility = "hidden";
    totalCompra.style.visibility = "hidden";
    //Me aseguro de que todo el contenido del div desaparezca para que cada elemento html vuelva a su sitio original
    carritoHtml.innerHTML="";
}

//Esta es la función para cambiar entre modo oscuro y modo claro. Lo que va haciendo es agregar una clase adicional a cada elemento
//de html y luego se codifica el css para esa clase en modo oscuro y modo claro. Así que cada elemento va a tener dos bloques
//en css, uno para oscuro y otro para claro. Por ahora no he añadido todos los elementos, van pocos simplemente para probar
bottonVisualizacion.addEventListener("click", ()=>{
    documento.classList.toggle("modo_claro");
    cabecera.classList.toggle('modo_claro');
    cambiaColores(cabecera);
    body.classList.toggle('modo_claro');
    cambiaColores(body);
    carritoHtml.classList.toggle('modo_claro');
    cambiaColores(carritoHtml);
    totalCompra.classList.toggle('modo_claro');
    total.classList.toggle('modo_claro');
    cambiaColores(totalCompra);
    cambiaColores(total);

    //En el caso de los elementos dentro del gridcontainer tengo que hacerlo de otra manera
    tarjetas.forEach(element => {
        element.classList.toggle('modo_claro');
        //Cada elemento es enviado como parámetro a la funcion cambiaColores
        cambiaColores(element);
        // if (element.classList.contains('modo_claro')) {
            // element.style.backgroundColor = estiloClaro.colorFondo;
            // element.style.color = estiloClaro.color;
            // element.style.boxShadow = estiloClaro.sombras;
            // element.style.border = estiloClaro.bordes;
        // } else {
            // element.style.backgroundColor = estiloOscuro.colorFondo;
            // element.style.color = estiloOscuro.color;
            // element.style.boxShadow = estiloOscuro.sombras;
            // element.style.border = estiloOscuro.bordes;
        // }
    })

});

//Como al final entiendo que todo el cambio de estilos debe de hacerse por js tengo que cambiar lo que tenia aquí.
//Creo un objeto aquí
let estiloClaro = {
    colorFondo : 'white',
    bordes : '2px solid blue',
    sombras : '2px 2px 2px darkblue',
    color : 'black',
}

//Objeto con los colores originales
let estiloOscuro = {
    colorFondo : 'black',
    bordes : '2px solid red',
    sombras : '2px 2px 2px lightgray',
    color : 'white',
}

//Metodo para cambiar colores
function cambiaColores(elemento){
    if(elemento.classList.contains("modo_claro")){
        elemento.style.backgroundColor = estiloClaro.colorFondo;
        elemento.style.color = estiloClaro.color;
        elemento.style.boxShadow = estiloClaro.sombras;
        elemento.style.border = estiloClaro.bordes;
    }else{
        elemento.style.backgroundColor = estiloOscuro.colorFondo;
        elemento.style.color = estiloOscuro.color;
        elemento.style.boxShadow = estiloOscuro.sombras;
        elemento.style.border = estiloOscuro.bordes;
    }
}

//Sección de cambiar colores a los párrafos, de nuevo hay que tener en cuenta el modoOscuro y el modoClaro
//Defino constante que seleccione todos los elementos con la clase parrafor_modificar
const parrafosModificables = document.querySelectorAll('.parrafos_modificar');
console.log(parrafosModificables);

//con un foreach voy a dar una serie de addevents a esos elementos
parrafosModificables.forEach(element => {
    element.addEventListener('mouseover', resaltarElemento);
    //console.log('funcion Agregada');
    element.addEventListener('mouseout', restablecerElemento);
    //console.log('funcion Agregada');
})

//Defino funciones
function resaltarElemento(event){
    if(documento.classList.contains("modo_claro")){
        event.target.style.color = "darkorange";
        event.target.style.textDecoration = "underline";
        event.target.style.textShadow = "2px 2px 10px black";
        event.target.style.transitionDuration = "1.5s";
    }else{
        event.target.style.color = "darkred";
        event.target.style.textDecoration = "underline";
        event.target.style.textShadow = "2px 2px 10px black";
        event.target.style.transitionDuration = "1.5s";
    }
}

function restablecerElemento(event){
    if(documento.classList.contains("modo_claro")){
        event.target.style.color = "black";
        event.target.style.textDecoration = "none";
        event.target.style.textShadow = "none";
        event.target.style.transitionDuration = "1.5s";
    }else{
        event.target.style.color = "white";
        event.target.style.textDecoration = "none";
        event.target.style.textShadow = "none";
        event.target.style.transitionDuration = "1.5s";
    }
}

//Sección para el timer de un elemento
let fechaLimite = new Date(2025,3,5,14,0,0);



setInterval(cuentaRegresiva,1000,fechaLimite);

function cuentaRegresiva(flimite){

    let diferenciaFechas = flimite - Date.now();

    let segundosTotales = parseInt(diferenciaFechas / 1000);

    let segundos = parseInt(segundosTotales % 60);
    let minutosTotales = parseInt(segundosTotales / 60);

    let minutos = parseInt(minutosTotales % 60);
    let horasTotales = parseInt(minutosTotales / 60);

    let horas = parseInt(horasTotales % 24);
    let diasTotales = parseInt(horasTotales / 24);

    let cuentaRegresiva = diasTotales + " Dias " + horas + "Horas " + ": " + minutos + "Minutos " + ": " + segundos + "Segundos";

    mostrarCuentaAtras(cuentaRegresiva);
}

//Creo una constante para el párrafo de la cuenta atrás

let cuentaAtras = document.getElementById("cuenta_regresiva");

//Defino la función necesaria
function mostrarCuentaAtras(cuentaRegresiva){
    cuentaAtras.innerHTML = cuentaRegresiva;
}

//Layout formularios
// const formularios = document.querySelector(".form-container");
// const reserva = document.querySelector("#reservar");
// formularios.addEventListener("submit", enviardatos);
// reserva.addEventListener("click", mostrarFormulario);

// formularios.style.visibility = "hidden";
// function enviardatos(){

// }

// function mostrarFormulario(){
//     formularios.style.visibility = "visible";
//     formularios.style.margin = "0 auto";
// }