public abstract class Mascotas {
    private String nombre, edad, estado, fechaNacim;
    public Mascotas(String nombre, String edad, String estado, String fechaNacim) {
        this.nombre = nombre;
        this.edad = edad;
        this.estado = estado;
        this.fechaNacim = fechaNacim;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaNacim() {
        return fechaNacim;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mascota:\n" +
                "Nombre: " + this.nombre +
                ", Edad: " + this.edad + "\n" +
                "Estado: " + this.estado +
                ", Fecha de Nacimiento: " + this.fechaNacim;
    }

    //Creo los métodos que indica el ejercicio
    //Supongo que el método muestra es el to string

    public void cumpleanios(){
        //Supongo que este método es para mostrar la fecha de cumpleaños, siendo para mostrar mes y día
        //Por ahora vacio
    }

    public void morir(){
        //Al morir la mascota supongo que lo que se quiere es cambiar el etado de la misma
        this.setEstado("Pasó a mejor vida");
    }

    public void habla(){
        //Me imagino que este método está creado en la clase abstracta pero que su implementación depende de sus clases hijas
        //así que supongo que su contenido cambiará dentro de cada clase hija
    }
}
