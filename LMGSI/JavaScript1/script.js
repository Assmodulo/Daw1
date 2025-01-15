//Bueno, como no tengo mucha idea de js, voy a tirar de morro y copiare algo del ejemplo que tu has pasado
//Aqui añado la primera linea, según el ejercicio de ejemplo y los apuntes, para leer la página completa
//Menos mal que tengo el ide para autocompletar

document.addEventListener("DOMContentLoaded", function (){

    //Voy creando constantes para linkear estas constantes con los elementos correspondientes e interactivos de nuestra
    //web.
    const parrafo = document.getElementById('parrafo');
    const imagen = document.getElementById('imagen');
    const seccion = document.getElementById("seccion_principal");
    const boton = document.getElementById("restaurar");


    //Declaro una constante que recoge los valores iniciales de los elementos de la página, para despues usarlos al restaurar
    const valoresIniciales = {
        colorParrafo : 'white',
        textParrafo : "¿Que vamos a hacer hoy?",
        tamanioTexto: '16px',
        anchoImagen:'90%',
        altoImagen: '100%',
        colorFondoSeccion : '#5508a3',
        anchoSeccion : '90%'
    };

    //Funcion para cambiar el texto del parrafo, cuando algun triger llame a la funcion
    function cambiarTexto() {
        parrafo.innerHTML = "Aprendiendo nuevas cosas";
    }

    //Funcion para cambiar el tamaño y el color del texto del párrafo de la página
    function cambiarTamanioTexto(){
        parrafo.style.fontSize = '35px';
        parrafo.style.color = 'red';
    }

    //Con esta función cambio parametro de la imagen de fondo de la seccion
    function cambiarImagenYFondo(){
        //Cambio varias cosas para que se note
        imagen.style.width = '50%';
        imagen.style.height = '50%';
        seccion.style.backgroundColor = 'yellow';
        seccion.style.width = '60%';
    }

    //Quizá no lo he hecho
    function restaurarValoresPorDefecto(){
        parrafo.innerHTML = valoresIniciales.textParrafo;
        parrafo.style.fontSize = valoresIniciales.tamanioTexto;
        parrafo.style.color = valoresIniciales.colorParrafo;
        imagen.style.width = valoresIniciales.anchoImagen;
        imagen.style.height = valoresIniciales.altoImagen;
        seccion.style.backgroundColor = valoresIniciales.colorFondoSeccion;
        seccion.style.width = valoresIniciales.anchoSeccion;
    }

    //voy a declarar una escucha de evento en el párrafo
    parrafo.addEventListener("click", cambiarTexto);
    linkb.addEventListener("click", cambiarTamanioTexto);
    linka.addEventListener("click", cambiarImagenYFondo);
    boton.addEventListener("click", restaurarValoresPorDefecto);
});