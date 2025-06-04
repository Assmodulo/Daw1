package com.coches.tucochealquiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarProductoEstrella();
        cargarTiposVehiculos();
        cargarMetricas();

        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
        colParking.setCellValueFactory(new PropertyValueFactory<>("parking"));
        colInicio.setCellValueFactory(new PropertyValueFactory<>("inicio"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("fin"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));


    }

    @FXML private Label lblTotalParkings, lblTotalVehiculos, lblTotalClientes, lblReservasActivas;

    @FXML
    private Label lblMatricula, lblMarcaModelo, lblVecesAlquilado, lblIngresos;

    private void cargarProductoEstrella() {
        try (Connection conn = SingletonConection.crearConexion()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT v.matricula, v.marca, v.modelo, COUNT(*) AS veces_alquilado, SUM(r.costo_total) AS ingresos " +
                            "FROM Reservas r JOIN Vehiculos v ON r.vehiculo_id = v.vehiculo_id " +
                            "GROUP BY v.vehiculo_id, v.matricula, v.marca, v.modelo " +
                            "ORDER BY veces_alquilado DESC LIMIT 1"
            );
            if (rs.next()) {
                lblMatricula.setText("Matrícula: " + rs.getString("matricula"));
                lblMarcaModelo.setText("Marca/Modelo: " + rs.getString("marca") + " " + rs.getString("modelo"));
                lblVecesAlquilado.setText("Veces alquilado: " + rs.getInt("veces_alquilado"));
                lblIngresos.setText("Ingresos generados: " + rs.getDouble("ingresos") + " €");
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los productos estrella");
        }
    }

    private void cargarTiposVehiculos() {
        pieTiposVehiculos.getData().clear();
        try (Connection conn = SingletonConection.crearConexion()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT tv.tipo, COUNT(*) AS total " +
                            "FROM Reservas r " +
                            "JOIN Vehiculos v ON r.vehiculo_id = v.vehiculo_id " +
                            "JOIN TiposVehiculos tv ON v.tipo_id = tv.tipo_id " +
                            "GROUP BY tv.tipo"
            );
            while (rs.next()) {
                pieTiposVehiculos.getData().add(
                        new PieChart.Data(rs.getString("tipo"), rs.getInt("total"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los tipos vehiculos");
        }
    }

    private void cargarMetricas() {
        try (Connection conn = SingletonConection.crearConexion()) {
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Parkings");
            if (rs.next()) lblTotalParkings.setText(rs.getString(1));

            rs = st.executeQuery("SELECT COUNT(*) FROM Vehiculos");
            if (rs.next()) lblTotalVehiculos.setText(rs.getString(1));

            rs = st.executeQuery("SELECT COUNT(*) FROM Clientes");
            if (rs.next()) lblTotalClientes.setText(rs.getString(1));

            rs = st.executeQuery("SELECT COUNT(*) FROM Reservas WHERE NOW() BETWEEN fecha_inicio AND fecha_fin");
            if (rs.next()) lblReservasActivas.setText(rs.getString(1));

        } catch (SQLException e) {
            System.out.println("Error al cargar los metricas");
        }
    }


    @FXML
    private PieChart pieTiposVehiculos;

    @FXML private TableView<Reserva> tablaUltimasReservas;

    @FXML private TableColumn<Reserva, String> colCliente, colVehiculo, colParking, colInicio, colFin;
    @FXML private TableColumn<Reserva, Double> colCosto;

}
