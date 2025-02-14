import java.time.LocalDate;

public class Cliente extends Persona{
    private String nTelefono;
    private String email;
    private String direccion;

    public Cliente(String nombre, String dni, LocalDate fechaNacimiento, String nTelefono, String email, String direccion) {
        super(nombre, dni, fechaNacimiento);
        this.nTelefono = nTelefono;
        this.email = email;
        this.direccion = direccion;
    }

    public String getnTelefono() {
        return nTelefono;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getDni() {
        return super.getDni();
    }

    @Override
    public String getFechaNacimiento() {
        return super.getFechaNacimiento();
    }

    public void setnTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return super.toString() + "Número Tlfno: " + nTelefono + ", Email: " + email + ", Direccion: " + direccion;
    }
}
