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

    //Metodo para recorrer el catálogo de películas disponibles para alquiler de este videoclub en concreto
    public String peliculasAlquiler(){
        String listadoPeliculasDisponibles = "";
        Pelicula p;
        for(int i = 0; i < this.peliculasTotales; i++){
            if(!this.peliculassRegistradas[i].isAlquilada()){
                p = this.peliculassRegistradas[i];
                listadoPeliculasDisponibles = listadoPeliculasDisponibles+ "\n" + p.getTitulo();
            }
        }
        return listadoPeliculasDisponibles;
    }

    //Metodo para recorrer el catálogo de cliente dados de alta de este videoclub en concreto
    public String clientesDadosAlta(){
        String clientesAlta = "";
        Cliente c;
        for(int i = 0; i < this.clientesTotales; i++){
            c = this.clientesRegistrados[i];
            clientesAlta = clientesAlta + "\n" + c.getNumSocio() + "- -" + c.getNombre();
        }
        return clientesAlta;
    }

    //Metodo para devolver el cliente al seleccionarlo de entre todos los posibles
    public Cliente devolverCliente(String codigo){
        Cliente c = null;
        for(int i = 0; i < this.clientesTotales; i++){
            if(this.clientesRegistrados[i].getNumSocio().equals(codigo)){
                c = this.clientesRegistrados[i];
            }
        }
        return c;
    }

    //Metodo para devolver la película al seleccionarla de entre todas las posibles
    public Pelicula devolverPelicula(String codigo){
        Pelicula p = null;
        for(int i = 0; i < this.peliculasTotales; i++){
            if(this.peliculassRegistradas[i].getCod().equals(codigo)){
                p = this.peliculassRegistradas[i];
            }
        }
        return p;
    }
}
