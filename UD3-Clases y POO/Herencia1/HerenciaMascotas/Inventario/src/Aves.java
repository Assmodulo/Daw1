import java.time.LocalDate;

public abstract class Aves extends Mascotas {

    private boolean vuela;
    private String pico;

    public Aves(String nombre, LocalDate fechaNacim, String pico) {
        super(nombre, fechaNacim);
        this.vuela = true;
        this.pico = pico;
    }

    public boolean isVuela() {
        return vuela;
    }

    public String getPico() {
        return pico;
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
        return super.toString() + "\nPico: " + this.pico + " Vuela: " + this.vuela;
    }

    public String volar(){
        return super.getNombre() + "Se va volando";
    }
}
