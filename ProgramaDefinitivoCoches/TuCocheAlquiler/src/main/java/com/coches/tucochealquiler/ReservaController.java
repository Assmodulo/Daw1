package com.coches.tucochealquiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ReservaController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SQLAccess sqlAccess = new SQLAccess();

        lbl_idreserva.setText(String.valueOf(sqlAccess.obtenerCuentaReservas()));

        ObservableList<Parkings> parkings = FXCollections.observableArrayList();
        ObservableList<Vehiculos> vehiculos = FXCollections.observableArrayList();
        ObservableList<Clientes> clientes = FXCollections.observableArrayList();

        parkings = sqlAccess.getListaParkings();
        vehiculos = sqlAccess.getListaVehiculos();
        clientes = sqlAccess.getListaClientes();

        cmb_cliente.setConverter(new StringConverter<Clientes>() {
            @Override
            public String toString(Clientes object) {
                if(object == null) return "";
                return object.getClienteId() + " - " + object.getNombre()+" " +object.getApellido();
            }

            @Override
            public Clientes fromString(String s) {
                return null;
            }
        });

        cmb_cliente.setItems(clientes);

        cmb_vehiculo.setConverter(new StringConverter<Vehiculos>() {
            @Override
            public String toString(Vehiculos object) {
                if(object == null) return "";
                return object.getMatricula() + " - " + object.getMarca() + " " + object.getModelo();
            }

            @Override
            public Vehiculos fromString(String s) {
                return null;
            }
        });

        cmb_vehiculo.setItems(vehiculos);

        cmb_parking.setConverter(new StringConverter<Parkings>() {
            @Override
            public String toString(Parkings object) {
                if(object == null) return "";
                return object.getParking_id() + " - " + object.getNombre();
            }

            @Override
            public Parkings fromString(String s) {
                return null;
            }
        });

        cmb_parking.setItems(parkings);

        dpfechaInicio.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(dpfechaInicio.getValue().isBefore(LocalDate.now())){
                    dpfechaInicio.setValue(null);
                    lbl_errorfecha1.setText("Fecha Anterior a la actual");
                }else{
                    evaluarFechasYCostes();
                }
            }
        });

        dpfechaFin.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(dpfechaFin.getValue().isBefore(LocalDate.now())){
                    dpfechaFin.setValue(null);
                    lbl_errorfecha2.setText("Fecha Anterior a la actual");
                }else{
                    evaluarFechasYCostes();
                }
            }
        });

    }

    private void evaluarFechasYCostes() {
        if (dpfechaInicio.getValue() != null && dpfechaFin.getValue() != null) {
            if(dpfechaInicio.getValue().isAfter(dpfechaFin.getValue())){
                dpfechaInicio.setValue(null);
                dpfechaFin.setValue(null);
                lbl_errorfecha1.setText("La fecha inicial no puede ser posterior a la final");
                lbl_errorfecha2.setText("La fecha inicial no puede ser posterior a la final");
                lbl_errorfecha1.setStyle("-fx-text-fill: red;");
                lbl_errorfecha2.setStyle("-fx-text-fill: red;");
            }else{
                if (cmb_vehiculo.getValue() != null) {
                    long dias = ChronoUnit.DAYS.between(dpfechaInicio.getValue(), dpfechaFin.getValue());
                    dias++;
                    double costoDiario = obtenerTarifa(cmb_vehiculo.getValue());
                    lbl_costototal.setText(String.valueOf(dias * costoDiario));
                }
            }
        }
    }

    private double obtenerTarifa(Vehiculos v) {
        double tarifa = 0;

        String myStatement = "select tarifa from TiposVehiculos where tipo_id = ?";

        try(Connection con = SingletonConection.crearConexion();
            PreparedStatement ps = con.prepareStatement(myStatement)) {

            ps.setInt(1, v.getTipoId());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                tarifa = rs.getDouble(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return tarifa;
    }

    PrincipalController principalController;

    private BorderPane panelPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panel-principal.fxml"));
        BorderPane borderPane = loader.load();
        panelPrincipal.setCenter(borderPane);
    }

    @FXML
    private Label lbl_idreserva, lbl_notificacion, lbl_errorfecha1, lbl_errorfecha2, lbl_errorcliente, lbl_errorparking,
        lbl_errovehiculo, lbl_costototal;

    @FXML
    private ComboBox<Clientes> cmb_cliente;

    @FXML
    private ComboBox<Vehiculos> cmb_vehiculo;

    @FXML
    private ComboBox<Parkings>cmb_parking;

    @FXML
    private DatePicker dpfechaInicio, dpfechaFin;

    public void insertarReserva() throws IOException {
        SQLAccess sqlAccess = new SQLAccess();

        Reserva r = new Reserva(cmb_cliente.getValue().getClienteId(), cmb_vehiculo.getValue().getVehiculoId(),cmb_parking.getValue().getParking_id(), dpfechaInicio.getValue(),
                dpfechaFin.getValue(), Double.parseDouble(lbl_costototal.getText()));

        int resultado = sqlAccess.insertarReserva(r);

        if(resultado > 0){
            lbl_notificacion.setText("Reserva agregada");
            lbl_notificacion.setStyle("-fx-text-fill: green;");
        }else{
            lbl_notificacion.setText("Reserva no agregada");
            lbl_notificacion.setStyle("-fx-text-fill: red;");
        }
    }

}
