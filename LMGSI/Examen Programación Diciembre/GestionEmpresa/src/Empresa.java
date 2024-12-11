import java.time.LocalDate;
import java.util.Arrays;

public class Empresa {
    private String nombreEmpresa;
    private String cif;
    private LocalDate fechaFundacion;
    private int maximoTrabajadores;

    private int numeroTrabajadoresActurales = 0;

    private Trabajador[] trabajadores;



    public Empresa(String nombre, String cif, String fechaFundacion, int maximoTrabajadores) {
        this.nombreEmpresa = nombre.toUpperCase();
        this.cif = cif;
        this.fechaFundacion = MyUtils.transformarFecha(fechaFundacion);
        this.maximoTrabajadores = maximoTrabajadores;
        trabajadores = new Trabajador[maximoTrabajadores];
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getCif() {
        return cif;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public int getMaximoTrabajadores() {
        return maximoTrabajadores;
    }

    public int getNumeroTrabajadoresActurales(){
        return numeroTrabajadoresActurales;
    }

    public String mostrarInfoEmpresa(){
        return "CIF: " + this.cif + " NOMBRE: " + this.nombreEmpresa + " FECHA FUNDACIÓN: " + this.fechaFundacion;
    }

    public String mostrarInformacionTrabajadores(){
        Trabajador t;
        String listadoTrabajadores = "";
        for(int i = 0; i < numeroTrabajadoresActurales; i++){
            t = trabajadores[i];
            listadoTrabajadores += t.mostrarInfoPersonas() + " NUM. SS: " + t.getNumeroSegSoc() + "\n";
        }
        return listadoTrabajadores;
    }

    public boolean incluirTrabajadorEmpresa(Trabajador trabajador){
        boolean correcto = false;
        if (numeroTrabajadoresActurales < this.trabajadores.length) {
            trabajador.setNumeroSS(MyUtils.formatoNumSegSocial(Trabajador.segSoc));
            trabajador.modificarTrabajadorEmpresa(trabajador);
            this.trabajadores[numeroTrabajadoresActurales] = trabajador;
            numeroTrabajadoresActurales++;
            Trabajador.segSoc++;
            correcto = true;
        }

        return correcto;
    }

    public boolean eliminarTrabajadorEmpresa(String dni){
        boolean correcto = false;

            for(int i = 0; i < numeroTrabajadoresActurales; i++){
               if(trabajadores[i].getDni().equals(dni)){
                   trabajadores[i].setNumeroSS(null);
                   trabajadores[i].modificarTrabajadorEmpresa(trabajadores[i]);
                   trabajadores[i] = null;
                   numeroTrabajadoresActurales--;
                   correcto = true;
              }

            }
        return correcto;
    }

    public void reasignarArray(){
        Trabajador[] auxiliar = new Trabajador[numeroTrabajadoresActurales];
        int contadorAuxiliar = 0;
        for(int i = 0; i < numeroTrabajadoresActurales; i++){
            if(this.trabajadores[i] != null){
                auxiliar[contadorAuxiliar] = this.trabajadores[i];
            }
        }
        Arrays.fill(this.trabajadores, null);
        for(int j = 0; j < auxiliar.length; j++){
            this.trabajadores[j] = auxiliar[j];
        }

    }


}
