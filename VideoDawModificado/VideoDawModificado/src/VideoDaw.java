import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class VideoDaw {

    private String cif;
    private String direccion;
    private LocalDate fechaAlta;
    LinkedList<Cliente> socios;
    LinkedList<Articulo> inventarioProductos;
    LinkedList<Alquiler> listadoAlquileres;
    private int contadorArticulos;
    private int contadorClientes;

    public VideoDaw(String cif, String direccion){
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();
        this.socios = new LinkedList<>();
        this.inventarioProductos = new LinkedList<>();
        this.listadoAlquileres = new LinkedList<>();
        this.contadorArticulos = 0;
        this.contadorClientes = 0;
    }

    /**
     * Este constructor solo tiene la función de volver a crear el objeto después de cargar el listado de videoclubs
     * @param cif Un string con el valor del CIF
     * @param direccion Un String con el valor de la dirección
     * @param fechaAlta Un objeto LocalDate con la fecha de Alta del videoclub
     */
    public VideoDaw(String cif, String direccion, LocalDate fechaAlta) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.socios = new LinkedList<>();
        this.inventarioProductos = new LinkedList<>();
        this.listadoAlquileres = new LinkedList<>();
        this.contadorArticulos = 0;
        this.contadorClientes = 0;
    }


    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public int getContadorArticulos() {
        return contadorArticulos;
    }

    public int getContadorClientes() {
        return contadorClientes;
    }

    /**
     * Supongo que es el método mostrarInfoVideoclub que indica el enunciado
     * @return String con la información del videoclub
     */
    @Override
    public String toString() {
        return "Establecimiento: \n" +
                "CIF: " + this.cif + ", Direccion: " + this.direccion + "\nFecha de Alta: " + MyUtils.formatearFecha(this.fechaAlta);
    }

    /**
     * Metodo que recibe un String igual a una opción, y según esa opción, completa un listado u otro.
     * Listado solo de películas, videojuegos o de todos los productos
     * @param opcion parámetro que nos servirá para elegir el resultado del listado
     * @return String que muestra un listado de elementos
     */
    public String mostrarProductosRegistrados(int opcion){
        String listado="";

        switch(opcion){
            case 1:
                for(Articulo peliculas : this.inventarioProductos){
                    if(peliculas.getClass() == Pelicula.class){
                        if (peliculas.getFechaBaja() == null) {
                            listado += peliculas.toString() + "\n";
                        }
                    }
                }
                break;
            case 2:
                for(Articulo videojuegos : this.inventarioProductos){
                    if(videojuegos.getClass() == Videojuegos.class){
                        if (videojuegos.getFechaBaja() == null) {
                            listado += videojuegos.toString() + "\n";
                        }
                    }
                }
                break;
            case 3:
                for(Articulo articulo : this.inventarioProductos){
                    if (articulo.getFechaBaja() == null) {
                        listado += articulo.toString() + "\n";
                    }
                }
                break;
            case 4:
                for(Articulo articulo : this.inventarioProductos){
                        listado += articulo.toString() + "\n";
                }
                break;
        }
        return listado;
    }

    /**
     * Metodo para obtener el listado de clientes del videoclub. Solo obtiene la info de los clientes que están activos,
     * es decir, no obtiene los datos de aquellos que están dados de baja
     * @return String: Listado de Clientes activos
     */
    public String mostrarSociosVideoclub(){
        String listado="";
        for(Cliente cliente : this.socios){
            if (cliente.getFechaBaja() == null) {
                listado += cliente.toString() + "\n";
            }
        }
        return listado;
    }

    /**
     * Metodo que muestra los datos de todos los clientes incluidos aquellos que se han dado de baja
     * @return Listado completo de los clientes del videoclub
     */
    public String mostrarSociosVideoclubHistorico(){
        String listado="";
        for(Cliente cliente : this.socios){
            listado += cliente.toString() + "\n";
        }
        return listado;
    }

    /**
     * Metodo que valida si el dni de un cliente nuevo ya existe en los archivos del videoclub
     * @param dni String con un formato válido de un dni
     * @throws ClienteExistenteException Si el dni ya existe se genera una Exception de este tipo
     */
    public void validarDniNuevoCliente(String dni) throws ClienteExistenteException{
        for(Cliente cliente : this.socios){
            if(cliente.getDni().equals(dni)){
                throw new ClienteExistenteException();
            }
        }
    }

    /**
     * Metodo para almacenar un Objeto Cliente en la colección correspondiente
     * @param cliente Un objeto de la clase cliente que se va a almacenar
     */
    public void addCliente(Cliente cliente){
        this.socios.add(cliente);
        this.contadorClientes++;
    }

    /**
     * Metodo para almacenar un Objeto Pelicula
     * @param pelicula Un objeto de la clase Pelicula
     */
    public void addArticuloP(Pelicula pelicula){
        this.inventarioProductos.add(pelicula);
        this.contadorArticulos++;
    }

    /**
     * Metodo para almacenar un Objeto Videojuego
     * @param videojuego un objeto de la clase Videojuego
     */
    public void addArticuloV(Videojuegos videojuego) throws IOException {
        this.inventarioProductos.add(videojuego);
        this.contadorArticulos++;
        guardadoDatosVideojuegoFichero(videojuego);
    }
    /**
     * Método para crear los ficheros de almacenamiento de datos de forma automática al crear un objeto de la clase Videodaw
     * @param cif String con el valor del cif del objeto para poder identificar correctamente a que videoclub pertenece
     */
    private void creacionDeFicherosRelacionado(String cif) throws IOException {
        String ficheroArticulos = cif + "_articulos.csv";
        String ficheroClientes = cif + "_clientes.csv";
        String ficheroAlquileres = cif + "_alquileres.csv";

        File fichArticulos = new File("./resources",ficheroArticulos);
        File fichClientes = new File("./resources",ficheroClientes);
        File fichAlquileres = new File("./resources", ficheroAlquileres);

        fichArticulos.createNewFile();
        fichClientes.createNewFile();
        fichAlquileres.createNewFile();
    }

    /**
     * Metodo que nos va a guardar los datos de un cliente en el fichero del videoclub correspondiente
     * @param cliente Un objeto de tipo cliente que se acaba de crear
     * @throws IOException Una Exception que deriva del manejo de ficheros
     */
    private void guardadoDatosClienteFichero(Cliente cliente) throws IOException {
        String ficheroClientes = this.cif + "_clientes.csv";
        String cadenaDatos;
        cadenaDatos = clienteToFile(cliente);

        try(FileWriter fichero = new FileWriter("./resources/" + ficheroClientes,true);
            BufferedWriter escritorClientes = new BufferedWriter(fichero)){
            escritorClientes.write(cadenaDatos);
            escritorClientes.newLine();
        }
    }

    /**
     * Metodo que nos prepara los datos de un cliente para pasarlos a un fichero de almacenamiento
     * @param cliente Objeto de tipo cliente que es el que vamos a almacenar
     * @return String con los datos preparados para almacenarlo en un fichero
     * @throws IOException Lanza Exception en caso de problemas al manejar ficheros
     */
    private String clienteToFile(Cliente cliente) throws IOException {
        String fechaBaja = "0";
        if(cliente.getFechaBaja() != null){
            fechaBaja = MyUtils.formatearFecha(cliente.getFechaBaja());
        }

        return cliente.getNombre() + "," + cliente.getDni() + "," + cliente.getDireccion() + "," +
                MyUtils.formatearFecha(cliente.fechaNacimiento) + "," + cliente.getCodSocio() + "," + fechaBaja;
    }

    /**
     * Metodo para guardar los datos de una película en un fichero al asignar la película a un videoclub
     * @param pelicula Un objeto de la clase Pelicula
     * @throws IOException Lanza esta exception si hay problemas al manejar ficheros
     */
    private void guardadoDatosPeliculaFichero(Pelicula pelicula) throws IOException {
        String ficheroArticulos = this.cif + "_articulos.csv";
        String cadenaDatos;
        cadenaDatos = peliculaToFile(pelicula);

        try(FileWriter fichero = new FileWriter("./resources/" + ficheroArticulos,true);
            BufferedWriter escritorArticulos = new BufferedWriter(fichero)){
            escritorArticulos.write(cadenaDatos);
            escritorArticulos.newLine();
        }
    }


    /**
     * Metodo para guardar los datos de un Videojuego en un fichero al asignar el videojuego a un videoclub
     * @param videojuego Un objeto de la clase videojuego
     * @throws IOException Lanza esta exception si hay problemas al manejar ficheros
     */
    private void guardadoDatosVideojuegoFichero(Videojuegos videojuego) throws IOException {
        String ficheroArticulos = this.cif + "_articulos.csv";
        String cadenaDatos;
        cadenaDatos = videojuegoToFile(videojuego);

        try(FileWriter fichero = new FileWriter("./resources/" + ficheroArticulos,true);
            BufferedWriter escritorArticulos = new BufferedWriter(fichero)){
            escritorArticulos.write(cadenaDatos);
            escritorArticulos.newLine();
        }
    }

    /**
     * Metodo que sirve para preparar todos los datos de una Película para almacenarlos en fichero
     * @param pelicula recibe un objeto de tipo Pelicula como parámetro
     * @return String con los datos preparados para escribir
     */
    private String peliculaToFile(Pelicula pelicula){
        String fechaBaja;
        String fechaAlquiler;
        if(pelicula.getFechaBaja() == null){
            fechaBaja = "0";
        }else{
            fechaBaja = MyUtils.formatearFecha(pelicula.getFechaBaja());
        }

        if(pelicula.getFechaAlquiler() == null){
            fechaAlquiler = "0";
        }else{
            fechaAlquiler = MyUtils.formatearFechaHora(pelicula.getFechaAlquiler());
        }
        return pelicula.getCodigo() + "," + pelicula.getTitulo() + "," + pelicula.getGenero().toString() + "," +
                MyUtils.formatearFecha(pelicula.getFechaAlta()) + "," + fechaBaja + "," + fechaAlquiler + "," + pelicula.isAlquilada();
    }


    /**
     * Metodo que sirve para preparar todos los datos de un Videojuego para almacenarlos en fichero
     * @param videojuego recibe un objeto de tipo Videojuego como parámetro
     * @return String con los datos preparados para escribir
     */
    private String videojuegoToFile(Videojuegos videojuego){
        String fechaBaja;
        String fechaAlquiler;
        if(videojuego.getFechaBaja() == null){
            fechaBaja = "0";
        }else{
            fechaBaja = MyUtils.formatearFecha(videojuego.getFechaBaja());
        }

        if(videojuego.getFechaAlquiler() == null){
            fechaAlquiler = "0";
        }else{
            fechaAlquiler = MyUtils.formatearFechaHora(videojuego.getFechaAlquiler());
        }
        return videojuego.getCodigo() + "," + videojuego.getTitulo() + "," + videojuego.getGenero().toString() + "," +
                MyUtils.formatearFecha(videojuego.getFechaAlta()) + "," + fechaBaja + "," + fechaAlquiler + "," + videojuego.isAlquilada();
    }


    /**
     * Método que busca un cliente por el código y si lo encuentra Actualiza su fecha de baja. Podría haber creado una Exception
     * personalizada, pero como el código ya queda claro así no lo he visto necesario
     * @param codigo Un dato de tipo String que es el elemento comparador para buscar el cliente
     * @return Un valor booleano el cual será false si no se ha encontrado, o true si se ha actualizado la fecha de baja
     */
    public boolean eliminarCliente(String codigo) {
        boolean actualizado = false;
        for(Cliente c : this.socios){
            if(c.getCodSocio().equals(codigo)){
                c.setFechaBaja(LocalDate.now());
                actualizado = true;
            }
        }

        return actualizado;
    }



    /**
     * Método que da de baja un artículo de nuestro inventario
     * @param codigoEliminar El código del artículo que se va a dar de baja
     * @return Un valor booleano que nos indicará si la operación se ha podido realizar o no
     */
    public boolean eliminarArticuloInventario(String codigoEliminar){
        Pelicula p = null;
        Videojuegos v = null;
        boolean actualizado = false;

        for(Articulo a : this.inventarioProductos){
            if (a.getCodigo().equals(codigoEliminar)) {
               a.setFechaBaja(LocalDate.now());
               actualizado = true;
            }
        }
        return actualizado;
    }


    /**
     * Metodo que nos devuelve un cliente valido para alquilar un articulo
     * @param codigo El codigo que nos servirá para comparar y elegir un cliente
     * @return Un objeto de tipo Cliente o null en caso de no encontrar el cliente
     */
    public Cliente validacionClienteAlquiler(String codigo){

        Cliente c = null;
        for(Cliente cliente : this.socios){
            if(cliente.getCodSocio().equals(codigo) && cliente.getFechaBaja() == null){
                c = cliente;
            }
        }
        return c;
    }

    public Cliente retornarClienteAlquiler(String codigo){
        Cliente c = null;
        String codCliente = "";
        for(Alquiler alquiler : this.listadoAlquileres){
            if(alquiler.getCodProducto().equals(codigo)){
                codCliente = alquiler.getCodSocio();
            }
        }
        for(Cliente cliente : this.socios){
            if(cliente.getCodSocio().equals(codCliente)){
                c = cliente;
            }
        }
        return c;
    }

    /**
     * Método para validar que un articulo pelicula es apto para alquiler
     * @param codigo el código de pelicula que se intenta alquilar
     * @return objeto tipo Pelicula para proceder al alquiler
     */
    public Pelicula validacionPeliculaAlquiler(String codigo){
        Pelicula p = null;
        for(Articulo pelicula : this.inventarioProductos){
            if(pelicula.getCodigo().equals(codigo) && pelicula.getFechaBaja() == null){
                if(((Pelicula)pelicula).getFechaAlquiler() == null){
                    p = (Pelicula)pelicula;
                }
            }
        }
        return p;
    }

    /**
     * Metodo que sirve para validar un objeto de tipo videojuegos para su alquiler
     * @param codigo String que identifica el código de videojuego
     * @return un objeto de tipo videojuego
     */
    public Videojuegos validacionVideojuegoAlquiler(String codigo){
        Videojuegos v = null;
        for(Articulo videojuego : this.inventarioProductos){
            if(videojuego.getCodigo().equals(codigo) && videojuego.getFechaBaja() == null){
                if(((Videojuegos)videojuego).getFechaAlquiler() == null){
                    v = (Videojuegos)videojuego ;
                }
            }
        }
        return v;
    }

    /**
     * Metodo que almacena los alquileres en una colección
     * @param alquiler un objeto de tipo alquiler
     */
    public void almacenarAlquiler(Alquiler alquiler){
        this.listadoAlquileres.add(alquiler);
    }

    private void almacenarNuevoAlquiler(Alquiler alquiler){
        String alquilerAFichero = alquilerToFile(alquiler);

        try(FileWriter escritor = new FileWriter("./resources/" + this.cif + "_alquileres.csv", true);
        BufferedWriter escritorBuff = new BufferedWriter(escritor)){
                escritorBuff.write(alquilerAFichero);
                escritorBuff.newLine();
        }catch (IOException e){
            System.out.println("Error al almacenar el alquiler");
        }
    }

    private String alquilerToFile(Alquiler alquiler){
        String alquilerToFile = alquiler.getCodProducto() + "," +alquiler.getCodSocio() + "," +
                MyUtils.formatearFechaHora(alquiler.getFechaAlquiler()) +
                "," + MyUtils.formatearFechaHora(alquiler.getFechaDevolucion());
        return alquilerToFile;
    }

    public String  mostrarListadoAlquiler(){
        String listadoAlquileres = "";
        for(Alquiler alquiler : this.listadoAlquileres){
            if(alquiler.getFechaDevolucion() == null){
                listadoAlquileres += alquiler;
            }
        }
        return listadoAlquileres;
    }

    public void seleccionArticuloDevolver(String codigo){
        for(Alquiler alquiler : this.listadoAlquileres){
            if(codigo.equals(alquiler.getCodProducto())){
                alquiler.setFechaDevolucion();
                recuperarObjetoArticuloAlquilada(alquiler.getCodProducto());
            }
        }
    }

    public void recuperarObjetoArticuloAlquilada(String codigo){
        for(Articulo articulo : this.inventarioProductos){
            if(articulo.getCodigo().equals(codigo)){
                if(articulo instanceof Videojuegos){
                    ((Videojuegos)articulo).setIsAlquilada(false);
                    ((Videojuegos)articulo).setFechaAlquiler(null);
                }else{
                    ((Pelicula)articulo).setIsAlquilada(false);
                    ((Pelicula)articulo).setFechaAlquiler(null);
                }
            }
        }
    }

}
