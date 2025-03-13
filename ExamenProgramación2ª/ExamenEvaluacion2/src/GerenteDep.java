import java.time.LocalDate;
import java.util.LinkedList;

public class GerenteDep extends Trabajador {
    private int numTrabajadoresDep;
    private String numTelefono;
    private LinkedList<Trabajador> trabajadoresDep;

    public GerenteDep(String nombre, LocalDate fNacimiento, String dni, String direccion,
                      String email, double salario, Departamentos departamento,
                      String numTelefono  ) {
        super(nombre, fNacimiento,dni, direccion,email,salario,departamento);
        this.trabajadoresDep = new LinkedList<>();
        this.numTelefono = numTelefono;
        this.numTrabajadoresDep = 0;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void addTrabajador(Trabajador t) {
        if(this instanceof Trabajador) {
            trabajadoresDep.add(t);
        }
    }

    public String listarTrabajadoresDep(){
        String listado = "";

        if(this.trabajadoresDep.isEmpty()){
            listado = "Sin Trabajadores asignados a este Departamento";
        }else{
            for(Trabajador t: this.trabajadoresDep){
                listado += t.toString() + "\n";
            }
        }
        return listado;
    }

    @Override
    public String toString() {
        return super.toString() + ", Telefono: " + this.numTelefono + "\n";
    }
}
