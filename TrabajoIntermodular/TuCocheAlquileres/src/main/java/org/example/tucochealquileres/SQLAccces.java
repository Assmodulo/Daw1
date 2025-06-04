package org.example.tucochealquileres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class SQLAccces {
    public List getListaClientes() {
        List lista = new LinkedList<>();

        String getClientes = "SELECT * FROM Clientes";

        try (Connection connection = SingletonConnectionSQL.crearConexion(); Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getClientes);) {
            while (resultSet.next()) {
                lista.add(resultSet.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
        }

        return lista;
    }

    public List buscarParkingId(String ListaParkingId) {
        List lista = new LinkedList<>();

        String getParkingPorId = "SELECT * FROM Parkings WHERE parking_id = ?";

        try (Connection connection = SingletonConnectionSQL.crearConexion();
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
                LocalTime horaApertura = LocalTime.parse(resultSet.getNString("hora_apertura"),FormateadoresDeFechas.formatoHoraJavaSQL);
                LocalTime horaCierre = LocalTime.parse(resultSet.getNString("hora_cierre"),FormateadoresDeFechas.formatoHoraJavaSQL);
                Boolean activo = resultSet.getBoolean("activo");

                Parkings p1 =  new Parkings(parking_id, nombre, direccion, ciudad, codigoPostal, capacidad, plazasDisponibles, horaApertura, horaCierre, activo);
                lista.add(p1);

            }

        } catch (Exception e) {
            System.out.println("Error al obtener la lista de parkings: " + e.getMessage());
        }
        return lista;

    }

    public LinkedList<Parkings> getListaParkings() {
        LinkedList<Parkings> lista = new LinkedList<>();


        String getParkings = "SELECT * FROM Parkings";

        try (Connection connection = SingletonConnectionSQL.crearConexion(); Statement statement = connection.createStatement();
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


}
