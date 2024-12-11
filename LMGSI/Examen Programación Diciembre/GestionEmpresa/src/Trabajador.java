import java.time.LocalDate;

public class Trabajador {
    private String nombre;
    private LocalDate fechaNacimiento;
    private String dni;
    private String direccion;
    private String numeroSegSoc;

    public static Trabajador[] personas = new Trabajador[50];
    public static int contadorTrabajadores = 0;
    public static int segSoc = 1;

    public Trabajador(){

    }

    public Trabajador(String nombre, String fechaNacimiento, String dni, String direccion) {
        this.nombre = nombre.toUpperCase();
        this.fechaNacimiento = MyUtils.transformarFecha(fechaNacimiento);
        this.dni = dni;
        this.direccion = direccion.toUpperCase();

        personas[contadorTrabajadores] = this;
        contadorTrabajadores++;
    }
    public String getNombre() {
        return nombre;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public String getDni() {
        return dni;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getNumeroSegSoc() {
        return numeroSegSoc;
    }

    public void setNumeroSS(String numeroSS){
        this.numeroSegSoc = numeroSS;
    }

    public String mostrarInfoPersonas(){
        return "DNI: " + this.dni + " NOMBRE: " + this.getNombre() + "\n" +
                "FECHA NACIMIENTO: " + MyUtils.formatearFecha(this.fechaNacimiento) + " DIRECCION: " + this.direccion;
    }

    public boolean validarDniExistente(String dni){
        boolean existe = false;
        for(int i = 0; i < contadorTrabajadores; i++){
            if(dni.equals(personas[i].getDni())){
                existe = true;
            }
        }
        return existe;
    }

    public String listadoTrabajadores(){
        String listado = "";
        for(int i = 0; i < contadorTrabajadores; i++){
            if(personas[i].getNumeroSegSoc() == null) {
                listado += (i + 1) + " .- " + personas[i].mostrarInfoPersonas() +"\n";
            }
        }
        return listado;
    }

    public void modificarTrabajadorEmpresa(Trabajador t){
        for (int i = 0; i < contadorTrabajadores; i++){
            if(t.getDni().equals(personas[i].getDni())){
                personas[i].setNumeroSS(t.getNumeroSegSoc());
            }
        }
    }



}
