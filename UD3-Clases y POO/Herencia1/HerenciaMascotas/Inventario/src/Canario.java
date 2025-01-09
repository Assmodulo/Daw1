import java.time.LocalDate;

public class Canario extends Aves{
    private String color;
    private boolean canta;

    public Canario(String nombre, LocalDate fechaNacim, String pico, String color) {
        super(nombre, fechaNacim, pico);
        this.color = color;
        this.canta = true;
    }

    public String getColor() {
        return color;
    }

    public boolean isCanta() {
        return canta;
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
        return super.toString() + "\nColor: " + color + "Canta: " + canta;
    }

    @Override
    public String volar() {
        return super.volar();
    }

    @Override
    public void habla() {
        System.out.println("PIO PIO PI");
    }
}
