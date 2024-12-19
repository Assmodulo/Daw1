public abstract class Astros {
    //Definimos la clase abstracta

    //Defino sus atributos
    protected double radioEcuatorial;
    protected double masa;
    protected double rotacionSobreEje;
    protected double tempMedia;
    protected double gravedad;
    protected String nombre;

    public double getMasa() {
        return masa;
    }

    public double getRadioEcuatorial() {
        return radioEcuatorial;
    }

    public double getRotacionSobreEje() {
        return rotacionSobreEje;
    }

    public double getTempMedia() {
        return tempMedia;
    }

    public double getGravedad() {
        return gravedad;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Astro{" +
                "RadioEcuatorial=" + radioEcuatorial +
                ", Masa=" + masa +
                ", Rotacion sobre su propio eje=" + rotacionSobreEje +
                ", TempMedia=" + tempMedia +
                ", Gravedad=" + gravedad +
                ", Nombre='" + nombre + '\'' +
                '}';
    }
}