console.log("¡El archivo javascritp está funcionando!");

const boton = document.getElementById("boton");

console.log(boton);

const mensaje = document.getElementById("mensaje");
console.log(mensaje);

boton.addEventListener("click", () => {
    mensaje.textContent = "¡Probando a generar un mensaje distinto al que se proporciona en el ejercicio!";
})

const cuerpo = document.getElementById("cuerpo");

console.log(cuerpo);

const botonStilo = document.getElementById("boton2");

botonStilo.addEventListener("click", () => {
    console.log('Botón de estilo pulsado');
    cuerpo.style.backgroundColor = "skyblue";
})