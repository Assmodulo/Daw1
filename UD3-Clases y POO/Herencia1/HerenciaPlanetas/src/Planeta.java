public class Planeta extends Astros{
    //Defino los atributos propios de esta clase
    private double distanciaSol;
    private double orbitaAlrededorSol;
    private boolean tieneSatelites;

    Satelites [] satelites;
    private int contadorSatelites = 0;


    //Defino constructor, con los datos incluidos de la clase padre
    public Planeta(String nombre, double masa, double rotacionSobreEje,
                   double tempMedia, double gravedad, double radioEcuatorial,double distanciaSol,
                   double orbitaAlrededorSol, boolean tieneSatelites) {
        this.nombre = nombre;
        this.masa = masa;
        this.rotacionSobreEje = rotacionSobreEje;
        this.tempMedia = tempMedia;
        this.gravedad = gravedad;
        this.radioEcuatorial = radioEcuatorial;
        this.distanciaSol = distanciaSol;
        this.orbitaAlrededorSol = orbitaAlrededorSol;
        this.tieneSatelites = tieneSatelites;
        if(this.tieneSatelites){
            this.satelites = new Satelites[5];
        }
    }

    //Defino los getters propios de esta clase

    public double getDistanciaSol() {
        return distanciaSol;
    }

    public double getOrbitaAlrededorSol() {
        return orbitaAlrededorSol;
    }

    public boolean isTieneSatelites() {
        return tieneSatelites;
    }

    //Creo un setter simplemente para saber si se ha almacenado algún satélite
    public int getContadorSatelites() {
        return contadorSatelites;
    }

    //Importo los métodos de la clase Padre

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
        return super.toString() + "Distancia al Sol: " + this.distanciaSol + ", Orbita alrededor del Sol: " +
                this.orbitaAlrededorSol + ", Tiene Satelites: " + this.tieneSatelites;
    }

    //Creo un método para almacenar un satélite en este planeta
    public void almacenarSatelites(Satelites satelite) {
        this.satelites[contadorSatelites] = satelite;
        contadorSatelites++;
    }
}
