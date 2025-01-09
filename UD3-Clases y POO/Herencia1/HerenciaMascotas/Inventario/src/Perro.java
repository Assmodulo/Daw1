import java.time.LocalDate;

public class Perro extends Mascotas{
    private String raza;
    private boolean pulgas;

    public Perro(){}

    public Perro(String nombre, LocalDate fechaNacim, String raza) {
        super(nombre, fechaNacim);
        this.raza = raza;
        this.pulgas = false;
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String getEdad() {
        return super.getEdad();
    }

    @Override
    public String getEstado() {
        return super.getEstado();
    }

    @Override
    public LocalDate getFechaNacim() {
        return super.getFechaNacim();
    }

    @Override
    public String toString() {
        return super.toString() + "\nRaza: " + raza + " Pulgas: " + pulgas;
    }

    @Override
    public void cumpleanios() {
        super.cumpleanios();
    }

    @Override
    public void habla() {
        System.out.println("Guau");
    }
}
