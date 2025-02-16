document.addEventListener('DOMContentLoaded', function() {
    // Obtener referencias a elementos del DOM
    const taskForm = document.getElementById('taskForm');  // Formulario para agregar nuevas tareas
    const taskInput = document.getElementById('taskInput');  // Entrada de texto para la nueva tarea
    const taskList = document.getElementById('taskList');  // Lista de tareas
    const messages = document.getElementById('messages');  // Div para mensajes de éxito o error

    // Inicializar el array de tareas desde el almacenamiento local o un array vacío si no hay datos almacenados
    let tasks = JSON.parse(localStorage.getItem('tasks')) || [];

    // Función para renderizar las tareas en la lista
    function renderTasks() {
        taskList.innerHTML = '';  // Limpiar la lista antes de volver a renderizar
        tasks.forEach((task, index) => {
            const li = document.createElement('li');
            li.innerHTML = `
              <span>${task}</span>
              <button class="delete-button" onclick="deleteTask(${index})">Eliminar</button>
          `;
            taskList.appendChild(li);  // Agregar elemento de tarea a la lista
        });
    }

    // Función para eliminar una tarea
    function deleteTask(index) {
        tasks.splice(index, 1);  // Eliminar la tarea del array
        localStorage.setItem('tasks', JSON.stringify(tasks));  // Actualizar el almacenamiento local
        renderTasks();  // Volver a renderizar la lista de tareas
        showMessage('Tarea eliminada correctamente', 'success');  // Mostrar mensaje de éxito
    }

    // Función para mostrar mensajes de éxito o error
    function showMessage(message, type) {
        messages.innerHTML = `<div class="${type}">${message}</div>`;  // Crear elemento div con mensaje
        setTimeout(() => {
            messages.innerHTML = '';  // Limpiar el mensaje después de 3 segundos
        }, 3000);
    }

    // Escuchador de eventos para el formulario de tareas
    taskForm.addEventListener('submit', function(event) {
        event.preventDefault();  // Evitar el comportamiento predeterminado del formulario
        const newTask = taskInput.value.trim();  // Obtener el valor de la nueva tarea
        if (newTask !== '') {
            tasks.push(newTask);  // Agregar la nueva tarea al array
            localStorage.setItem('tasks', JSON.stringify(tasks));  // Actualizar el almacenamiento local
            renderTasks();  // Volver a renderizar la lista de tareas
            taskInput.value = '';  // Limpiar el campo de entrada
            showMessage('Tarea agregada correctamente', 'success');  // Mostrar mensaje de éxito
        } else {
            showMessage('Por favor, ingrese una tarea válida', 'error');  // Mostrar mensaje de error
        }
    });

    // Renderizar las tareas al cargar la página
    renderTasks();
});
