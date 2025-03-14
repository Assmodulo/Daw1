const cambioModo = document.getElementById('toggle_dark_mode');
const documento = document.querySelector('html');
const tarjetas = document.querySelectorAll(".product");
const contenedor = document.querySelector(".product-container");
const carrito = document.querySelector(".cart-content");

const botonAdd = document.querySelectorAll(".add-to-cart");
botonAdd.forEach(element => {
    element.addEventListener("click", (e) => {
        e.preventDefault();
        leerDatosPlato(e.target.parentElement);
    })
})

//Aqui empiezo a modificar las cosas para el modo oscuro en el botón de abajo
cambioModo.addEventListener('click', e => {
        documento.classList.toggle('dark-mode');
        modificarEstilos();
})

//Vamos a añadir otro addevent para las tarjetas, pero para los botones de añadir


//cambio de colores
function modificarEstilos() {

        tarjetas.forEach(tarjeta => {
            if(documento.classList.contains('dark-mode')){
                tarjeta.style.backgroundColor = 'rgba(0,0,0,0.5)';
                contenedor.style.backgroundColor = 'rgba(0, 0, 0, 0.5)';
                tarjeta.style.color = 'white';
            }else{
                tarjeta.style.backgroundColor = 'rgba(255,255,255,0.6)';
                contenedor.style.backgroundColor = 'rgba(255,255,255,0.6)';
                tarjeta.style.color = 'black';
            }
        })

}

const platos = [];

function leerDatosPlato(e) {
    let infoPlato = {
        foto : e.querySelector("img").src,
        precio : e.querySelector("p").textContent,
    }

    almacenarDatos(infoPlato);
    mostrarDatosCarrito(infoPlato);
}

function almacenarDatos(infoPlato) {
    platos.push(infoPlato);
}

function mostrarDatosCarrito(infoPlato) {
    carrito.innerHTML = '';
    platos.forEach(objeto =>{

        const espacioPlato = document.createElement("div");
        const foto = document.createElement("img");
        foto.src = objeto.foto;
        foto.style.width = '100px';
        foto.style.height = '100px';
        espacioPlato.appendChild(foto);
        const precio = document.createElement("p");
        precio.textContent = objeto.precio;
        espacioPlato.appendChild(precio);
        carrito.appendChild(espacioPlato);
    })
}