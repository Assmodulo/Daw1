//Bueno, como no tengo mucha idea de js, voy a tirar de morro y copiare algo del ejemplo que tu has pasado
//Aqui añado la primera linea, según el ejercicio de ejemplo y los apuntes, para leer la página completa
//Menos mal que tengo el ide para autocompletar

document.addEventListener("DOMContentLoaded", function (){
    //Voy a empezar con algo sencillo, por ejemplo, cambiar algo al pasar el texto por encima del párrafo

    //Para ello lo primero es declarar una variable y hacer que sea igual a los parametros del elementeo con id parrafo

    
    function cambiarTexto() {
        let parrafo = document.getElementById("parrafo");
        parrafo.innerHTML = "Aprendiendo nuevas cosas";
    }

    //voy a declarar una escucha de evento en el párrafo
    parrafo.addEventListener("click", cambiarTexto);
});