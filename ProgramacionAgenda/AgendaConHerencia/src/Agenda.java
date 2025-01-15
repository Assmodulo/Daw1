public class Agenda {
    private Contacto [] agenda;
    private int contadorContactos;

    public Agenda() {
        this.agenda = new Contacto[10];
        this.contadorContactos = 0;
    }

    public int getContadorContactos(){
        return this.contadorContactos;
    }

    public int getLongitudArray(){
        return this.agenda.length;
    }
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

    //Para el metodo de listar contactos yo prefiero un metodo String antes que un void
    public String listarContactos(){
        String lista = "";
        for (int i = 0; i < this.contadorContactos; i++) {
            lista += (i+1) + " " + this.agenda[i].toString() + "\n";
        }
        return lista;
    }

    //Un listado simple para que se muestren los nombres de los datos antes de borrar los datos
    public String listadoSimple(){
        String lista = "";
        for (int i = 0; i < this.contadorContactos; i++) {
            lista += (i+1) + " " + this.agenda[i].getNombre() + "\n";
        }
        return lista;
    }

    public int posicionContacto(String nombre) {
        int posicion = -1;
        for (int i = 0; i < this.contadorContactos; i++) {
            if (this.agenda[i].getNombre().equals(nombre.toUpperCase())) {
                posicion = i;
            }
        }
        return posicion;
    }

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
