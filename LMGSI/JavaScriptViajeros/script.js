document.addEventListener("DOMContentLoaded", function() {
    const formulario = document.getElementById("formulario");
    const tarea = document.getElementById("tarea");
    const lista = document.getElementById("lista");

    let task = JSON.parse(localStorage.getItem("task")) || [];
    visualizarTareas();

    formulario.addEventListener("submit", (e) => {
        e.preventDefault();
        const tareaNueva = tarea.value.trim();
        tarea.push(tareaNueva);
        localStorage.setItem("task", JSON.stringify(tarea));
        visualizarTareas();
    })

    function visualizarTareas() {
        lista.innerHTML = "";
        task.forEach(tareaAlmacenada, index =>{
            const elemento = document.createElement("li");
            elemento.appendChild(<span></span>);
            lista.appendChild(elemento);
        })

    }
})