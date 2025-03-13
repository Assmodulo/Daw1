import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Empresa {

    private String nombre;
    private String cif;
    private LocalDate fFundacion;
    private LinkedList<Persona> empleados;
    private int contadorTrabajadores;
    private int director, gerenteDireccion;

    public Empresa(String nombre, String cif, LocalDate fFundacion) {
        this.nombre = nombre;
        this.cif = cif;
        this.fFundacion = fFundacion;
        this.empleados = new LinkedList<>();
        this.contadorTrabajadores = 0;
        this.director = 0;
        this.gerenteDireccion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public LocalDate getfFundacion() {
        return fFundacion;
    }

    public int getDirector() {
        return director;
    }

    public int getGerenteDireccion() {
        return gerenteDireccion;
    }

    //Quizá no me haga falta pero en caso de que necesite recuperar la linkedlist por algún motivo
    public LinkedList getEmpleados() {
        return empleados;
    }

    public void registrarEmpleado(Persona empleado) throws DniYaExistenteException{
        boolean encontrado = false;
        GerenteDep gerente = null;
        Trabajador t;
        for(Persona persona : empleados) {
            if(persona.getDni().equals(empleado.getDni())) {
                encontrado = true;
            }
        }
        if(encontrado) {
            throw new DniYaExistenteException();
        }else{
            if(empleado instanceof Trabajador){
                Trabajador trabajador = (Trabajador) empleado;
                trabajador.setNumeroSS(String.valueOf(contadorTrabajadores + 1));
                if(empleado instanceof Director){
                    this.director++;
                }else{
                    this.gerenteDireccion++;
                }
            }
            t = (Trabajador)empleado;
            for(Persona persona : empleados) {
                if(((Trabajador)persona) instanceof GerenteDep) {
                    gerente = (GerenteDep) persona;
                    if(t.getDepartamento().equals(gerente.getDepartamento())){
                        gerente.addTrabajador(t);
                    }
                }
            }
            empleados.add(empleado);
            contadorTrabajadores++;
        }
    }

    public String listadoDeTrabajadores(){
        String listado = "";
        for(Persona persona : empleados) {
            listado = listado + persona.toString() + "\n";
        }
        return listado;
    }

    public boolean EliminarTrabajador(String dni){

        boolean encontradoParaEliminar = false;

        Persona p = null;

        for(Persona persona : empleados) {
            if(persona.getDni().equals(dni)) {
                p = persona;
                encontradoParaEliminar = true;
            }
        }

        if(encontradoParaEliminar) {
            if(p instanceof Director) {
                this.director--;
            }else if(p instanceof GerenteDep){
                this.gerenteDireccion--;
            }

            empleados.remove(p);
        }

        return encontradoParaEliminar;
    }

    public String mostrarJerarquiaDeLaEmpresa(){
        String listadoOrganizado = "";

        listadoOrganizado += this.toString() + "\n";

        if(this.director == 0){
            listadoOrganizado += "Director : No Asignado\n";
        }else{
            for(Persona persona : empleados) {
                if(persona instanceof Director) {
                    listadoOrganizado += "Director:\n" + ((Director)persona).toString() + "\n";
                }
            }
        }

        if(this.gerenteDireccion == 0){
            listadoOrganizado += "Gerentes de Dirección : No Asignados\n";
        }else{
            for(Persona persona : empleados) {
                if(persona instanceof GerenteDep) {
                    listadoOrganizado += "GerenteDep:\n" + ((GerenteDep)persona).toString() + "\n";
                    listadoOrganizado += ((GerenteDep) persona).listarTrabajadoresDep();
                }
            }
        }


        return listadoOrganizado;
    }

    public String mostrarInforDepartamentoConcreto(Departamentos d){
        String listadoDepartamento = d.toString() + ":\n";
        for(Persona persona : empleados) {
            if(persona instanceof GerenteDep){
                listadoDepartamento += ((GerenteDep) persona).toString() + "\n";
                listadoDepartamento += ((GerenteDep) persona).listarTrabajadoresDep();
            }
        }
        return listadoDepartamento;
    }

    public String reunirEmpresa(){

        Director d = null;
        GerenteDep ge;
        Trabajador t;
        boolean posibleReunion= false;
        String listadoDisponibles = "";

        for(Persona persona : empleados) {
            if(persona instanceof Director) {
                d = (Director) persona;
                if(!d.isEsReunido() && !d.isFueraOficina()) {
                    posibleReunion = true;
                }
            }
        }

        if(posibleReunion){
            listadoDisponibles = "Hora de la Reunión: " + MyUtils.formatearFechaHora(LocalDateTime.now()) +
                    d.toString() + "\n";
            for(Persona persona : empleados) {
                if (((Trabajador)persona).isEnLaOficina()) {
                    if(persona instanceof GerenteDep) {
                        ge = (GerenteDep) persona;
                        listadoDisponibles += ge.toString() + "\n";
                    }else{
                        t = (Trabajador) persona;
                        listadoDisponibles += t.toString() + "\n";
                    }
                }

            }
        }

        return listadoDisponibles;
    }

    public Director retornarDirector(){
        Director d = null;

        for(Persona persona : empleados) {
            if(persona instanceof Director) {
                d = (Director) persona;
            }
        }
        return d;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", cif='" + cif + '\'' +
                ", fFundacion=" + MyUtils.formatearFecha(fFundacion) +
                '}';
    }
}
