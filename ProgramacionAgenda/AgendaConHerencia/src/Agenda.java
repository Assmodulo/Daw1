public class Agenda {
    private Contacto [] agenda;
    private int contadorContactos;

    //El constructor no necesita recibir parámetros
    public Agenda() {
        //inicializo la variable y el string al crear un objeto de tipo agenda
        this.agenda = new Contacto[10];
        this.contadorContactos = 0;
    }

    //Los getters, solo necesito el valor del contador y la longitud del array para alguna evaluación o comparación
    public int getContadorContactos(){
        return this.contadorContactos;
    }

    public int getLongitudArray(){
        return this.agenda.length;
    }

    //Metodo para añadir un contacto que recibe un objeto por parametro y devuelve un boolean para saber si la operación
    //ha sido correcta
    public boolean addContacto(Contacto contacto) {
        boolean add = false;
        boolean encontrado = false;

        if (contadorContactos < agenda.length) {
            for (int i = 0; i < contadorContactos; i++) {
                if (agenda[i].getNombre().equals(contacto.getNombre().toUpperCase())) {
                    encontrado = true;
                }
            }
            if(!encontrado) {
                agenda[contadorContactos] = contacto;
                contadorContactos++;
                add = true;
            }
        }
        return add;
    }

    /*Metodo para eliminar un contacto. Cuando se elimina uno, llama a otro método para reorganizar el array*/
    public boolean eliminarContacto(String nombre) {
        boolean eliminado = false;
        if (this.contadorContactos > 0) {
            for (int i = 0; i < this.contadorContactos; i++) {
                if (agenda[i].getNombre().equals(nombre.toUpperCase())) {
                    agenda[i] = null;
                    reorganizarArray(i);
                    contadorContactos--;
                    eliminado = true;
                }
            }
        }
        return eliminado;
    }

    //Un método que busca si un nombre determinado existe dentro de la agenda, devulve un boolean que lo indica
    public boolean existeContacto(String nombre) {
        boolean existe = false;
        if (this.contadorContactos > 0) {
            for (int i = 0; i < this.contadorContactos; i++) {
                if (this.agenda[i].getNombre().equals(nombre.toUpperCase())) {
                    existe = true;
                }
            }
        }
        return existe;
    }

    //Para el metodo de listar contactos yo prefiero un metodo String antes que un void, devuelve un string con los contactos
    //de la agenda
    public String listarContactos(){
        String lista = "";
        for (int i = 0; i < this.contadorContactos; i++) {
            lista += (i+1) + " " + this.agenda[i].toString() + "\n";
        }
        return lista;
    }

    //Un listado simple para que se muestren solo los nombres de los contactos antes de elegir cual borrar
    public String listadoSimple(){
        String lista = "";
        for (int i = 0; i < this.contadorContactos; i++) {
            lista += (i+1) + " " + this.agenda[i].getNombre() + "\n";
        }
        return lista;
    }

    /*Metodo que nos devuelve un int, que es la posición de un contacto, cuyo nombre pasamos como parámetro*/
    public int posicionContacto(String nombre) {
        int posicion = -1;
        for (int i = 0; i < this.contadorContactos; i++) {
            if (this.agenda[i].getNombre().equals(nombre.toUpperCase())) {
                posicion = i;
            }
        }
        return posicion;
    }

    /*Metodo que recibe un int para indicar una posición del array y nos devuelve los datos completos de ese contacto*/
    public String devolverDatosPosicion(int posicion){
        return this.agenda[posicion].toString();
    }

    private void reorganizarArray(int posicionInicial){
        for(int i = posicionInicial; i < agenda.length; i++) {
            if (i < this.agenda.length -1) {
                this.agenda[i] = this.agenda[i+1];
            }
        }
        agenda[agenda.length - 1] = null;
    }
}
