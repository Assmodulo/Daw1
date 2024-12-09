import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public String mostrarInfoVideoclub(){
        return "CIF: " + this.getCif() + " Dirección: " + this.getDireccion() + " Fecha de alta: " + MyUtils.formatearFecha(this.fechaAlta);
    }

    //Metodo para almacenar un nuevo cliente y modificar el valor de su contador
    public void guardarDatosCliente(Cliente cliente) {
        clientesRegistrados[clientesTotales] = cliente;
        clientesTotales++;
    }
    //Metodo para almacenar una nueva pelicula y modificar el valor de su contador
    public void guardarDatosPelicula(Pelicula pelicula) {
        peliculassRegistradas[peliculasTotales] = pelicula;
        peliculasTotales++;
    }

    //Método para comprobar si un cliente ya existe
    public boolean comprobarClientes(String dni){
        boolean existe = false;
        Cliente c;
        //Evaluo que clientesTotales sea distinto de cero, porque si es igual a 0 quiere decir que no has insertado
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
                if(p.getFechaBaja() == null){
                    listadoPeliculasDisponibles += "\n" + p.getCod() + " " + p.getTitulo();
                }
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
            if(c.getFechaBaja() == null){
                clientesAlta = clientesAlta + "\n" + c.getNumSocio() + "- -" + c.getNombre();
            }
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

    //Metodo para devolver la película al seleccionarla de entre todas las posibles. Es para devolver el objeto
    //película no para el método de devolver una película al videoclub. Devuelve películas que no estén alquiladas.
    public Pelicula devolverPelicula(String codigo){
        Pelicula p = null;
        for(int i = 0; i < this.peliculasTotales; i++){
            if(!this.peliculassRegistradas[i].isAlquilada()) {
                if (this.peliculassRegistradas[i].getCod().equals(codigo)) {
                    p = this.peliculassRegistradas[i];
                }
            }
        }
        return p;
    }

    //Metodo que realiza las acciones necesarias al completar un alquiler
    public void generarAlquiler(Cliente c, Pelicula p){
        p.setAlquilada(true);
        p.setFechaAlquiler(LocalDateTime.now());
        c.crearAlquiler(p);
    }

    public String listadoPeliculasAlquiladas(){
        String listadoPeliculasAlquiladas = "";
        Pelicula p;
        for(int i = 0; i < this.peliculasTotales; i++){
            p = this.peliculassRegistradas[i];
            if(p.isAlquilada()){
                listadoPeliculasAlquiladas += "\n" + p.getCod() + " " + p.getTitulo() + " " + MyUtils.formatearFHora(p.getFechaAlquiler());
            }
        }
        return listadoPeliculasAlquiladas;
    }

    //Este metodo nos permite recuperar la información de una pelicula de entre todas aquellas que están
    //alquiladas, para devolverlas despues
    public Pelicula seleccionarPeliculaAlquilada(String codigo){
        Pelicula p = null;
        for(int i = 0; i < this.peliculasTotales; i++){
            if(this.peliculassRegistradas[i].isAlquilada()){
                if(this.peliculassRegistradas[i].getCod().equals(codigo)){
                    p = this.peliculassRegistradas[i];
                }
            }
        }
        return p;
    }

    //Metodo para llevar a cabo las acciones necesarias cuando se devuelve una película
    //No se me ocurrió otra forma de actualizar el dato en el array del cliente correspondiente, así que usé más fors
    //anidados
    public void devolverPeliculaAlquilada(Pelicula p){
        Cliente c;
        Pelicula p2 = p; //La declaro para después de cambiar la fecha baja e isalquilada en el videoclub
                            //tener todavia los datos para compararla con los clientes.
        for(int i = 0; i < this.peliculasTotales; i++){
            if(this.peliculassRegistradas[i].equals(p)){
                p.setAlquilada(false);
                p.setFechaAlquiler(null);
                //Hago este print para comprobar las cosas al hacer pruebas
                System.out.println(p.mostrarInforPelicula());
            }
        }
        for(int i = 0; i < this.clientesTotales; i++){
            c = this.clientesRegistrados[i];
            for(int j = 0; j < c.getCantPeliculas(); j++){
                if(p2.equals(c.devolverDatosPeliculaAlquilada(j))){
                    c.devolverDatosPeliculaAlquilada(j).setAlquilada(false);
                    c.devolverDatosPeliculaAlquilada(j).setFechaAlquiler(null);
                    //Hago este print para comprobar las cosas al hacer pruebas
                    System.out.println(c.devolverDatosPeliculaAlquilada(j).mostrarInforPelicula());
                }
            }
        }
    }
}
