public class Satelites extends Astros{
    //Defino los atributos
    private double distanciaPlaneta;
    private double orbitaPlanetaria;
    private String nombrePlaneta;

    //Defino el constructor

    public Satelites(String nombre, double masa, double rotacionSobreEje,
                     double tempMedia, double gravedad, double radioEcuatorial,
                     double distanciaPlaneta, double orbitaPlanetaria, String nombrePlaneta) {
        super();
        this.distanciaPlaneta = distanciaPlaneta;
        this.orbitaPlanetaria = orbitaPlanetaria;
        this.nombrePlaneta = nombrePlaneta;
    }

    public double getDistanciaPlaneta() {
        return distanciaPlaneta;
    }

    public double getOrbitaPlanetaria() {
        return orbitaPlanetaria;
    }

    public String getNombrePlaneta() {
        return nombrePlaneta;
    }

    @Override
    public double getMasa() {
        return super.getMasa();
    }

    @Override
    public double getRadioEcuatorial() {
        return super.getRadioEcuatorial();
    }

    @Override
    public double getRotacionSobreEje() {
        return super.getRotacionSobreEje();
    }

    @Override
    public double getTempMedia() {
        return super.getTempMedia();
    }

    @Override
    public double getGravedad() {
        return super.getGravedad();
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public String toString() {
        return super.toString() + "Distancia al planeta origen: " + this.distanciaPlaneta +
                ", Orbita planetaria: " + this.orbitaPlanetaria + ", Planeta Origne: " + this.nombrePlaneta;
    }
}
