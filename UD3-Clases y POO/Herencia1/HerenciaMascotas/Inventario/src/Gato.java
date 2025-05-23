import java.time.LocalDate;

public class Gato extends Mascotas{
    private String color;
    private boolean peloLargo;

    public Gato(String nombre, LocalDate fechaNacim, String color, boolean peloLargo) {
        super(nombre, fechaNacim);
        this.color = color;
        this.peloLargo = peloLargo;
    }

    public String getColor() {
        return color;
    }

    public boolean isPeloLargo() {
        return peloLargo;
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
        return super.toString() + "\nColor: " + color + "Pelo Largo: " + peloLargo;
    }

    @Override
    public void habla() {
        System.out.println("Miau");
    }
}
