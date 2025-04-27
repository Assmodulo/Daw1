package com.videoDBD;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class DBoperations {

    public int insertarNuevoVideoClub(Videoclub videoClub){
        int rowsAffected=-1;

        String instruccion = "INSERT INTO videoclubs VALUES(?,?,?,?)";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, videoClub.getCif());
            statement.setString(2, videoClub.getNombre());
            statement.setString(3, videoClub.getDireccion());
            statement.setString(4, videoClub.getTelefono());

            rowsAffected = statement.executeUpdate();

        }catch (Exception e) {
            System.out.println("Error al ejecutar el statement. " + e.getMessage());
        }

        return rowsAffected;
    }

    public LinkedList<Videoclub> getListadoVideoclubs() {
        LinkedList<Videoclub> listadoVideoclubs = new LinkedList<>();

        String instruccion = "SELECT * FROM videoclubs";

        try(Connection con = SQLSingleConnection.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet rs = statement.executeQuery(instruccion);

            while (rs.next()) {
                Videoclub videoClub;

                String cif = rs.getString("cod_videoclub");
                String nombre = rs.getString("nombre_videoclub");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");

                videoClub = new Videoclub(cif, nombre, direccion, telefono);

                listadoVideoclubs.add(videoClub);
            }
        } catch (SQLException e) {

            System.out.println("Error al ejecutar el statement. " + e.getMessage());
        }

        return listadoVideoclubs;
    }


    public int eliminarVideoClub(String cif){
        int rowsAffected=-1;
        String instruccion = "DELETE FROM videoclubs WHERE cod_videoclub = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, cif);

            rowsAffected = statement.executeUpdate();

        }catch(SQLException e){
            System.out.println("Error al ejecutar el statement. " + e.getMessage());
        }

        return rowsAffected;
    }

    public Videoclub seleccionarVideoClub(String cif){
        Videoclub videoclub = null;
        VideoclubBuilder videoclubBuilder = new VideoclubBuilder();

        String instruccion = "SELECT * FROM videoclubs WHERE cod_videoclub = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, cif);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                videoclub = videoclubBuilder
                        .cif(rs.getString("cod_videoclub"))
                        .nombre(rs.getString("nombre_videoclub"))
                        .direccion(rs.getString("direccion"))
                        .telefono(rs.getString("telefono"))
                        .build();
            }

        }catch (SQLException e) {
            System.out.println("Error al ejecutar el statement. " + e.getMessage());
        }
        return videoclub;
    }

    public LinkedList<Cliente> obtenerClientesVideoclub(Videoclub v){
        LinkedList<Cliente> listado = new LinkedList<>();

        String instruccion = "select cod_cliente, dni, nombre, apellidos," +
                "f_nacimiento, f_alta, telefono from clientes where cod_videoclub = ?";

        Cliente c;

        ClienteBuilder cliente = new ClienteBuilder();

        try(Connection con = SQLSingleConnection.crearConexion();
        PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, v.getCif());

            generarListadoClientes(listado, statement, cliente);

        }catch (SQLException e){
            System.out.println("Error al ejecutar el statement. " + e.getMessage());
        }

        return listado;
    }

    public int insertarNuevoCliente(Cliente c, Videoclub v){
        int rowsafected = -1;

        String instruccion = "insert into clientes (cod_cliente, dni, nombre, apellidos," +
                "f_nacimiento, f_alta, telefono, cod_videoclub) values(?,?,?,?,?,?,?,?)";

        try(Connection con = SQLSingleConnection.crearConexion();
        PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, c.getCod_cliente());
            statement.setString(2, c.getDni());
            statement.setString(3, c.getNombre());
            statement.setString(4, c.getApellidos());
            statement.setString(5, MyUtils.formatearFechaSQL(c.getF_nacimiento()));
            statement.setString(6, MyUtils.formatearFechaSQL(c.getF_alta()));
            statement.setString(7, c.getTelefono());
            statement.setString(8,v.getCif());

            rowsafected = statement.executeUpdate();

            Cliente.numeroTotalSociosFranquicia++;

        }catch (SQLException e){
            System.out.println("Error al ejecutar instrucción SQL. " + e.getMessage());
        }

        return rowsafected;
    }

    public int insertarNuevoArticulo(Articulo a, Videoclub v) {

        int rowsAfected = -1;

        String instruccion = "insert into articulos (cod_articulo, tipo, titulo, genero, f_alta, cod_videoclub)" +
                " values (?,?,?,?,?,?)";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1,a.getCod_articulo());
            statement.setString(2,a.getTipo());
            statement.setString(3,a.getNombre());
            statement.setString(4,a.getGenero());
            statement.setString(5,MyUtils.formatearFechaSQL(a.getF_alta()));
            statement.setString(6,v.getCif());

            rowsAfected = statement.executeUpdate();

            Articulo.cantidadTotalArticulos++;
            
        }catch(SQLException e){
            System.out.println("Problema al ejecutar el statement SQL. " + e.getMessage());
        }

        return rowsAfected;
    }

    public LinkedList<Articulo> obtenerArticulosDeUnVideoclub(String cif) {

        LinkedList<Articulo> articulos = new LinkedList<>();

        Articulo a;

        ArticuloBuilder articulo = new ArticuloBuilder();

        String instruccion = "select cod_articulo, tipo, titulo, genero, f_alta, alquilada from articulos" +
                " where cod_videoclub = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            generarListadoArticulos(cif, articulos, articulo, statement);

        }catch (SQLException e){
            System.out.println("Error al ejecutar la consulta sobre Articulos. " +e.getMessage());
        }

        return articulos;
    }

    public int eliminarArticulo(Articulo a) {
        int rowsAfected = -1;

        String instruccion = "delete from articulos where cod_articulo = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, a.getCod_articulo());

            rowsAfected = statement.executeUpdate();

        }catch(SQLException e){
            System.out.println("No se ha podido eliminar el artículo por alguna razon. " + e.getMessage());
        }


        return rowsAfected;
    }

    public LinkedList<Cliente> obtenerClientesVideoclubActivos(String cif) {
        LinkedList<Cliente> clientes = new LinkedList<>();

        String instruccion = "select cod_cliente, dni, nombre, apellidos, f_nacimiento," +
                "f_alta, telefono from clientes where cod_videoclub = ? and f_baja is null";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            Cliente c;

            ClienteBuilder cliente = new ClienteBuilder();

            statement.setString(1, cif);

            generarListadoClientes(clientes, statement, cliente);

        }catch(SQLException e){
            System.out.println("No se han encontrado registros coincidentes. " +e.getMessage());
        }


        return clientes;
    }

    private void generarListadoClientes(LinkedList<Cliente> clientes, PreparedStatement statement, ClienteBuilder cliente) throws SQLException {
        Cliente c;
        ResultSet rs = statement.executeQuery();

        while(rs.next()){

            String codigo = rs.getString(1);
            String dni = rs.getString(2);
            String nombre = rs.getString(3);
            String apellidos = rs.getString(4);
            LocalDate f_nac = LocalDate.parse(rs.getString(5), MyUtils.formatoFechaSQL);
            LocalDate f_alta = LocalDate.parse(rs.getString(6), MyUtils.formatoFechaSQL);
            String telefono = rs.getString(7);

            c = cliente
                    .cod_cliente(codigo)
                    .dni(dni)
                    .nombre(nombre)
                    .apellidos(apellidos)
                    .f_nacim(f_nac)
                    .f_alta(f_alta)
                    .telefono(telefono)
                    .buildIntermedia();

            clientes.add(c);
        }
    }

    public LinkedList<Articulo> obtenerArticulosParaAlquiler(String cif) {

        LinkedList<Articulo> articulos = new LinkedList<>();

        Articulo a;

        ArticuloBuilder articulo = new ArticuloBuilder();

        String instruccion = "select cod_articulo, tipo, titulo, genero, f_alta, alquilada " +
                "from articulos where cod_videoclub = ? and f_baja is null";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            generarListadoArticulos(cif, articulos, articulo, statement);

        }catch(SQLException e){
            System.out.println("No se ha podido ejecutar la query seleccionada. " + e.getMessage());
        }

        return articulos;
    }

    private void generarListadoArticulos(String cif, LinkedList<Articulo> articulos, ArticuloBuilder articulo, PreparedStatement statement) throws SQLException {
        Articulo a;
        statement.setString(1, cif);

        ResultSet rs = statement.executeQuery();

        while(rs.next()){

            String cod_articulo = rs.getString(1);
            String tipo = rs.getString(2);
            String titulo = rs.getString(3);
            String genero = rs.getString(4);
            LocalDate f_alta = LocalDate.parse(rs.getString(5), MyUtils.formatoFechaSQL);
            boolean alquilada = rs.getBoolean(6);

            a = articulo
                    .cod_articulo(cod_articulo)
                    .tipo(tipo)
                    .nombre(titulo)
                    .genero(genero)
                    .f_alta(f_alta)
                    .alquilada(alquilada)
                    .buildIntermedia();

            articulos.add(a);
        }
    }

    public int insertarNuevoAlquiler(String cif, String codArticulo, String codCliente) {

        int rowsAffected = -1;

        String instruccion = "insert into alquileres (cod_videoclub, cod_articulo, cod_socio, f_alquiler) values (?,?,?,?)";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1,cif);
            statement.setString(2,codArticulo);
            statement.setString(3,codCliente);
            statement.setString(4,MyUtils.formatearFechaHoraSQL(LocalDateTime.now()));

            rowsAffected = statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("No se ha podido realizar el insert en alquileres. " +e.getMessage());
        }

        actualizarEstadoAlquiladoArticulo(codArticulo);

        return rowsAffected;
    }

    private void actualizarEstadoAlquiladoArticulo(String codArticulo){

        String instruccion = "update articulos set alquilada = true where cod_articulo = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, codArticulo);

            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("No se ha podido actualizar el estado alquilado del artículo. " + e.getMessage());
        }
    }

    public ArrayList<Alquiler> obtenerListadoAlquileres(String cif){
        ArrayList<Alquiler> alquileres = new ArrayList<>();

        String instruccion = "select cod_articulo, cod_socio, f_alquiler from alquileres" +
                " where cod_videoclub = ? and f_devolucion is null";

        Alquiler a;

        AlquilerBuilder alquiler = new AlquilerBuilder();

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){



            statement.setString(1, cif);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){

                String codArticulo = rs.getString(1);
                String codSocio = rs.getString(2);
                LocalDateTime f_alquiler = LocalDateTime.parse(rs.getString(3), MyUtils.formatoFechaHoraSQL);

                a = alquiler
                        .codArticulo(codArticulo)
                        .codSocio(codSocio)
                        .f_alquiler(f_alquiler)
                        .build();

                alquileres.add(a);
            }

        }catch (SQLException e){
            System.out.println("No se ha podido obtener ningún registro de alquileres. " +e.getMessage());
        }

        return alquileres;
    }

    public String obtenerDatosCompletosAlquiler(String codArticulo, String codSocio, LocalDateTime f_alquiler) {

        String datos = "";

        String instruccion = "select v.cod_videoclub, v.nombre_videoclub, c.cod_cliente, c.nombre, c.apellidos, " +
                "a.cod_articulo, a.titulo, al.f_alquiler " +
                "from videoclubs v " +
                "join clientes c on v.cod_videoclub = c.cod_videoclub " +
                "join articulos a on v.cod_videoclub = a.cod_videoclub " +
                "join alquileres al on v.cod_videoclub = al.cod_videoclub " +
                "where al.cod_articulo = ? and al.cod_socio = ? and al.f_alquiler = ? and f_devolucion is null";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, codArticulo);
            statement.setString(2, codSocio);
            statement.setString(3, MyUtils.formatearFechaHoraSQL(f_alquiler));

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String codVideoclub = rs.getString(1);
                String nombreV = rs.getString(2);
                String codS = rs.getString(3);
                String nombre = rs.getString(4);
                String apellidos = rs.getString(5);
                String codA = rs.getString(6);
                String titulo = rs.getString(7);
                LocalDateTime f_alq = LocalDateTime.parse(rs.getString(8), MyUtils.formatoFechaHoraSQL);
                String fAlquiler = MyUtils.formatearFechaHora(f_alq);

                datos = String.format(" | %-10S | %-50S |\n" +
                        " | %-10S | %-25S | %-30S | \n" +
                        " | %-10S | %-50S |\n" +
                        " | %-25S|", codVideoclub, nombreV, codS, nombre, apellidos, codA, titulo, fAlquiler);
            }

        }catch (SQLException e){
            System.out.println("No se han podido obtener los datos buscados. " + e.getMessage());
        }

        return datos;
    }

    public int realizarDevolucionArticulo(String codArticulo, String codSocio, LocalDateTime fAlquiler, LocalDateTime fechaHora) {

        int rowsAffected = -1;

        String instruccion = "update alquileres set f_devolucion = ? where cod_articulo = ? and cod_socio = ? and f_alquiler = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, MyUtils.formatearFechaHoraSQL(fechaHora));
            statement.setString(2, codArticulo);
            statement.setString(3, codSocio);
            statement.setString(4, MyUtils.formatearFechaHoraSQL(fAlquiler));

            rowsAffected = statement.executeUpdate();

        }catch(SQLException e){
            System.out.println("No se ha podido realizar el update del registro. " + e.getMessage());
        }

        actualizarEstadoAlquiladoFalseArticulo(codArticulo);

        return rowsAffected;
    }

    private void actualizarEstadoAlquiladoFalseArticulo(String codArticulo){

        String instruccion = "update articulos set alquilada = false where cod_articulo = ?";

        try(Connection con = SQLSingleConnection.crearConexion();
            PreparedStatement statement = con.prepareStatement(instruccion)){

            statement.setString(1, codArticulo);

            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("No se ha podido actualizar el estado alquilado del artículo. " + e.getMessage());
        }
    }
}
