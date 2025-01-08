public class Perro extends Mascotas{
    private String raza;
    private boolean pulgas;

    public Perro(String nombre, String edad, String estado, String fechaNacim, String raza, boolean pulgas) {
        super(nombre, edad, estado, fechaNacim);
        this.raza = raza;
        this.pulgas = pulgas;
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
        return super.toString() + "\n Raza: " + raza + " Pulgas: " + pulgas;
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
