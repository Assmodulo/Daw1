package com.coches.tucochealquiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class SQLAccess {
    public ObservableList<Clientes> getListaClientes() {
        ObservableList<Clientes> lista = FXCollections.observableArrayList();

        String getClientes = "SELECT * FROM Clientes";

        try (Connection connection = SingletonConection.crearConexion(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getClientes);) {
            while (resultSet.next()) {
                int id = resultSet.getInt("cliente_id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellidos");
                String dni = resultSet.getString("dni");
                LocalDate fnacim = LocalDate.parse(resultSet.getString("f_nacimiento"), MyFechas.formatoFechaSQL);
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String ciudad = resultSet.getString("ciudad");
                String cp = resultSet.getString("codigo_postal");
                boolean permiso = resultSet.getBoolean("permiso_valido");
                boolean activo = resultSet.getBoolean("activo");

                Clientes c = new Clientes(id, nombre, apellido, dni,fnacim, telefono, direccion, ciudad, cp,email, activo, permiso );
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
        }

        return lista;
    }

    public List buscarParkingId(String ListaParkingId) {
        List lista = new LinkedList<>();

        String getParkingPorId = "SELECT * FROM Parkings WHERE parking_id = ?";

        try (Connection connection = SingletonConection.crearConexion();
             PreparedStatement statement = connection.prepareStatement(getParkingPorId);) {
            statement.setString(1, ListaParkingId);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {

                int parking_id = resultSet.getInt("parking_id");
                String nombre = resultSet.getNString("nombre");
                String direccion = resultSet.getNString("direccion");
                String ciudad = resultSet.getNString("ciudad");
                String codigoPostal = resultSet.getNString("codigo_postal");
                int capacidad = resultSet.getInt("capacidad_total");
                int plazasDisponibles = resultSet.getInt("plazas_disponibles");
                Object FormateadoresDeFechas;
                //LocalTime horaApertura = LocalTime.parse(resultSet.getNString("hora_apertura"),FormateadoresDeFechas.formatoHoraJavaSQL);
                //LocalTime horaCierre = LocalTime.parse(resultSet.getNString("hora_cierre"),FormateadoresDeFechas.formatoHoraJavaSQL);
                Boolean activo = resultSet.getBoolean("activo");

                //Parkings p1 =  new Parkings(parking_id, nombre, direccion, ciudad, codigoPostal, capacidad, plazasDisponibles, horaApertura, horaCierre, activo);
                //lista.add(p1);

            }

        } catch (Exception e) {
            System.out.println("Error al obtener la lista de parkings: " + e.getMessage());
        }
        return lista;

    }

    public ObservableList<Parkings> getListaParkings() {
        ObservableList<Parkings> lista = FXCollections.observableArrayList();


        String getParkings = "SELECT * FROM Parkings";

        try (Connection connection = SingletonConection.crearConexion(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getParkings);) {
            while (resultSet.next()) {

                int parking_id = resultSet.getInt("parking_id");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String ciudad = resultSet.getString("ciudad");
                String codigoPostal = resultSet.getString("codigo_postal");
                int capacidad = resultSet.getInt("capacidad_total");
                int plazasDisponibles = resultSet.getInt("plazas_disponibles");
                LocalTime horaApertura = resultSet.getTime("hora_apertura").toLocalTime();
                LocalTime horaCierre = resultSet.getTime("hora_cierre").toLocalTime();
                Boolean activo = resultSet.getBoolean("activo");

                Parkings p1 =  new Parkings(parking_id, nombre, direccion, ciudad, codigoPostal, capacidad, plazasDisponibles, horaApertura, horaCierre, activo);
                lista.add(p1);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de Parkings: " + e.getMessage());
        }

        return lista;
    }


    public int registrarParking(Parkings parkings) {

        int rowsAffected = 0;

        String myStatement = "insert into Parkings (nombre, direccion, ciudad, codigo_postal, capacidad_total, " +
                "plazas_disponibles, hora_apertura, hora_cierre) values (?,?,?,?,?,?,?,?)";

        try(Connection con = SingletonConection.crearConexion();
            PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setString(1, parkings.getNombre());
            statement.setString(2, parkings.getDireccion());
            statement.setString(3, parkings.getCiudad());
            statement.setString(4, parkings.getCodigoPostal());
            statement.setInt(5, parkings.getCapacidad());
            statement.setInt(6, parkings.getPlazasDisponibles());
            statement.setTime(7, Time.valueOf(parkings.getHoraApertura()));
            statement.setTime(8, Time.valueOf(parkings.getHoraCierre()));
            statement.executeUpdate();

            rowsAffected = statement.getUpdateCount();
        }catch (SQLException e){
            System.out.println("Error al registrar el parking: " + e.getMessage());
        }
        return rowsAffected;
    }

    public int getVistaParkingCuenta(){
        int resultado = -1;

        String myStatement = "select * from cuentaparkings";

        try(Connection con = SingletonConection.crearConexion();
        Statement statement = con.createStatement()){
            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("numparkings");

                resultado = id;
            }

        }catch (SQLException e){
            System.out.println("Error al obtener el resultado de la vista parking: " + e.getMessage());
        }

        return resultado;
    }

    public int obtenerIdTipo() {
        int resultado = -1;

        String myStatement = "select * from vistaidtipo";

        try(Connection con = SingletonConection.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("ids");
                resultado = id + 1;
            }

        }catch(SQLException e){
            System.out.println("Error al obtener el resultado de la vista tipo: " + e.getMessage());
        }

        return resultado;
    }

    public int insertarNuevoTipo(TiposVehiculos tipo) {
        int resultado = 0;

        String myStatment = "insert into TiposVehiculos (tipo, descripcion, tarifa) values (?,?,?)";

        try(Connection con = SingletonConection.crearConexion();
        PreparedStatement statement = con.prepareStatement(myStatment)){

            statement.setString(1, tipo.getTipo());
            statement.setString(2, tipo.getDescripcion());
            statement.setDouble(3, tipo.getTarifaAdicional());

            resultado = statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al insertar el tipo: " + e.getMessage());
        }

        return resultado;
    }

    public int obtenerCuentaVehiculos() {
        int resultado = 0;

        String myStatement = "select * from cuentavehiculos";

        try(Connection con = SingletonConection.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("numvehiculos");
                resultado = id + 1;
            }

        }catch(SQLException e){
            System.out.println("Error al obtener el resultado de la vista tipo: " + e.getMessage());
        }

        return resultado;
    }

    public ObservableList<TiposVehiculos> obtenerListadoTipos() {

        ObservableList<TiposVehiculos> listado = FXCollections.observableArrayList();

        String myStatement = "select * from TiposVehiculos";

        try(Connection con = SingletonConection.crearConexion();
            Statement statement = con.createStatement()){
            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("tipo_id");
                String tipo = resultSet.getString("tipo");

                TiposVehiculos tv = new TiposVehiculos(id, tipo);

                listado.add(tv);

            }

        }catch (SQLException e){
            System.out.println("Error al obtener el resultado de la vista tipo: " + e.getMessage());
        }
        return listado;
    }

    public int insertarNuevoVehiculo(Vehiculos v) {
        int rowsAffected = -1;

        String myStatement ="insert into vehiculos (matricula, marca, modelo, color, tipo_id, estado, parking_id) " +
                "values (?,?,?,?,?,?,?)";

        try(Connection con = SingletonConection.crearConexion();
            PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setString(1, v.getMatricula());
            statement.setString(2, v.getMarca());
            statement.setString(3, v.getModelo());
            statement.setString(4, v.getColor());
            statement.setInt(5, v.getTipoId());
            statement.setString(6, v.getEstado());
            statement.setInt(7, v.getParkingId());
            rowsAffected = statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error al insertar el vehiculo: " + e.getMessage());
        }

        return rowsAffected;
    }

    public int getVistaClientesCuenta() {
        int resultado = 0;

        String myStatement = "select * from cuentaclientes";

        try(Connection con = SingletonConection.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("clientes");
                resultado = id + 1;
            }

        }catch(SQLException e){
            System.out.println("Error al obtener el resultado de la vista tipo: " + e.getMessage());
        }

        return resultado;
    }

    public int registrarCliente(Clientes c) {
        int rowsAffected = -1;

        String myStatement = "insert into clientes (nombre, apellidos, dni, f_nacimiento, email, telefono, direccion," +
                "ciudad, codigo_postal) values (?,?,?,?,?,?,?,?,?)";

        try(Connection con = SingletonConection.crearConexion();
            PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setString(1, c.getNombre());
            statement.setString(2, c.getApellido());
            statement.setString(3, c.getDni());
            statement.setString(4, MyFechas.formatearFechaSQL(c.getFechaNacimiento()));
            statement.setString(5, c.getEmail());
            statement.setString(6, c.getTelefono());
            statement.setString(7, c.getDireccion());
            statement.setString(8, c.getCiudad());
            statement.setString(9, c.getCodigoPostal());
            rowsAffected = statement.executeUpdate();

        }catch(SQLException e){
            System.out.println("Error al registrar el cliente: " + e.getMessage());
        }

        return rowsAffected;
    }

    public int obtenerCuentaReservas() {
        int resultado = 0;

        String myStatement = "select * from cuentareservas";

        try(Connection con = SingletonConection.crearConexion();
            Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                resultado = resultSet.getInt("reservas");
            }

        }catch (SQLException e){
            System.out.println("Error al obtener el resultado de la vista tipo: " + e.getMessage());
        }

        return resultado + 1;
    }

    public ObservableList<Vehiculos> getListaVehiculos() {
        ObservableList<Vehiculos> vehiculos = FXCollections.observableArrayList();
        String myStatement = "select * from Vehiculos";

        try(Connection con = SingletonConection.crearConexion();
        Statement statement = con.createStatement()){

            ResultSet resultSet = statement.executeQuery(myStatement);
            while (resultSet.next()) {
                int id = resultSet.getInt("vehiculo_id");
                String matricula = resultSet.getString("matricula");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String color = resultSet.getString("color");
                int tipoId = resultSet.getInt("tipo_id");
                String estado = resultSet.getString("estado");
                LocalDate fecha = LocalDate.parse(resultSet.getString("fecha_adquisicion"), MyFechas.formatoFechaSQL);
                int parkingId = resultSet.getInt("parking_id");

                Vehiculos v = new Vehiculos(id, matricula, marca, modelo, color,  estado, fecha, tipoId, parkingId);

                vehiculos.add(v);
            }

        }catch(SQLException e){
            System.out.println("Error al obtener el resultado de la vista tipo: " + e.getMessage());
        }

        return vehiculos;
    }

    public int insertarReserva(Reserva r) {
        int rowsAffected = -1;

        String myStatement = "insert into Reservas (cliente_id, vehiculo_id, parking_id, fecha_inicio, fecha_fin, costo_total) values (?,?,?,?,?,?)";

        try(Connection con = SingletonConection.crearConexion();
            PreparedStatement statement = con.prepareStatement(myStatement)){

            statement.setInt(1,r.getIdcliente());
            statement.setInt(2,r.getIdvehiculo());
            statement.setInt(3,r.getIdparking());
            statement.setString(4, MyFechas.formatearFechaSQL(r.getInicio()));
            statement.setString(5, MyFechas.formatearFechaSQL(r.getFin()));
            statement.setDouble(6, r.getCosto());

            rowsAffected = statement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Error al insertar el reserva tipo: " + e.getMessage());
        }

        return rowsAffected;
    }
}
