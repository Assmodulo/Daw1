import java.time.LocalDate;

public class Trabajador extends Persona{

    private String numeroSS;
    private String email;
    private double salario;
    private Departamentos departamento;
    private boolean enLaOficina;


    public Trabajador(String nombre, LocalDate fNacimiento, String dni, String direccion,
                      String email, double salario, Departamentos departamento) {
        super(nombre, fNacimiento, dni, direccion);
        this.email = email;
        this.salario = salario;
        this.departamento = departamento;
        this.enLaOficina = true;
    }

    public void setNumeroSS(String numeroSS) {
        this.numeroSS = numeroSS;
    }

    public String getNumeroSS() {
        return numeroSS;
    }

    public String getEmail() {
        return email;
    }

    public double getSalario() {
        return salario;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public boolean isEnLaOficina() {
        return enLaOficina;
    }

    public void setEnLaOficina(boolean enLaOficina) {
        this.enLaOficina = enLaOficina;
    }

    @Override
    public String toString() {
        return super.toString() + "Trabajador{" + "numeroSS=" + numeroSS + ", email=" + email + ", salario=" + salario +
                ", departamento=" + departamento + '}';
    }
}
