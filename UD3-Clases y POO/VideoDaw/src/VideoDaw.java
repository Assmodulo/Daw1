import java.time.LocalDate;

public class VideoDaw {
    private String cif;
    private String direccion;
    private LocalDate fechaAlta;
    private Pelicula[] peliculassRegistradas;
    private Cliente[] clientesRegistrados;

    //Variables contadores para manejar los arrays y para crear los códigos
    private int peliculasTotales = 0, clientesTotales = 0;

    public VideoDaw() {
    }

    public VideoDaw(String cif, String direccion) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();
        peliculassRegistradas = new Pelicula[10];
        clientesRegistrados = new Cliente[10];
    }

    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFechaAlta() {
        return MyUtils.formatearFecha(this.fechaAlta);
    }

    public int getPeliculasTotales() {
        return peliculasTotales;
    }

    public int getClientesTotales() {
        return clientesTotales;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void guardarDatosCliente(Cliente cliente) {
        clientesRegistrados[clientesTotales] = cliente;
        clientesTotales++;
    }

    public void guardarDatosPelicula(Pelicula pelicula) {
        peliculassRegistradas[peliculasTotales] = pelicula;
        peliculasTotales++;
    }

    //Método para comprobar si un cliente ya existe
    public boolean comprobarClientes(String dni){
        boolean existe = false;
        Cliente c = new Cliente();
        //Evaluo que clientesTotales sea distinto de cero, poque si es igual a 0 quiere decir que no has insertado
        //ningún cliente, si es distinto de cero, recorro el array
        if(clientesTotales != 0){
            for(int i = 0; i < clientesTotales; i++){
                c = clientesRegistrados[i];
                if(dni.equals(c.getDni())){
                    existe = true;
                    System.out.println("EL CLIENTE YA HA SIDO CREADO CON ANTERIORIDAD");
                }
            }
        }
        return existe;
    }
}
