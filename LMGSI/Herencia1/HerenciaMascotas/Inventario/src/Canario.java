public class Canario extends Aves{
    private String color;
    private boolean canta;

    public Canario(String nombre, String edad, String estado, String fechaNacim, boolean vuela, String pico, String color, boolean canta) {
        super(nombre, edad, estado, fechaNacim, vuela, pico);
        this.color = color;
        this.canta = canta;
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
    public String getFechaNacim() {
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
