import java.time.LocalDate;

public class Loro extends Aves{
    private String origen;
    private boolean hable;

    public Loro(String nombre, LocalDate fechaNacim, String pico, String origen) {
        super(nombre, fechaNacim, pico);
        this.origen = origen;
        this.hable = true;
    }

    public String getOrigen() {
        return origen;
    }

    public boolean isHable() {
        return hable;
    }

    @Override
    public boolean isVuela() {
        return super.isVuela();
    }

    @Override
    public String getPico() {
        return super.getPico();
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
        return super.toString() + "\nOrigen: " + origen + " Hable: " + hable;
    }

    @Override
    public String volar() {
        return super.volar();
    }

    public String saludar(){
        return "El loro " + this.getNombre() + " dice !!!Hola a todos!!!";
    }
}
