import java.time.LocalDate;

public class Director extends Trabajador{
    private String numTelefono;
    private boolean esReunido;
    private boolean fueraOficina;

    public Director(String nombre, LocalDate fNacimiento, String dni, String direccion,
                    String email, double salario, Departamentos departamento,
                    String numTelefono){
        super(nombre, fNacimiento,dni, direccion,email,salario,departamento);
        this.numTelefono = numTelefono;
        this.esReunido = false;
        this.fueraOficina = false;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public boolean isEsReunido() {
        return esReunido;
    }

    public boolean isFueraOficina() {
        return fueraOficina;
    }

    public void setEsReunido(boolean esReunido) {
        this.esReunido = esReunido;
    }

    public void setFueraOficina(boolean fueraOficina) {
        this.fueraOficina = fueraOficina;
    }

    @Override
    public String toString() {
        return super.toString() + ", Teléfono : " + this.numTelefono;
    }
}
