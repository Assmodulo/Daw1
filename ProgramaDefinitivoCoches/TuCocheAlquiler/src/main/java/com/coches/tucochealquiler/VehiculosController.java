package com.coches.tucochealquiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VehiculosController implements Initializable {


    PrincipalController principalController;

    private BorderPane panelPrincipal;

    @FXML
    private TextField tf_matricula, tf_marca, tf_modelo, tf_color ;

    @FXML
    private Label lbl_idvehiculo, lbl_errormatricula, lbl_errormarca, lbl_errormodelo, lbl_errorcolor, lbl_errortipov, lbl_errorparking, lbl_errorestado, lbl_notificacion;

    @FXML
    private ComboBox<String> cmb_estadov;
    @FXML
    private ComboBox<TiposVehiculos> cmb_tipov;
    @FXML
    private ComboBox<Parkings> cmb_parking;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /*public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource());
        BorderPane bp = loader.load();
        panelPrincipal.setCenter();
    }*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        inicializar();
    }

    private void inicializar() {
        lbl_idvehiculoInitialize();

        tf_matriculaInitialize();

        tf_marcaInitialize();

        tf_modeloInitialize();

        tf_colorInitialize();

        cmb_tipovInitialize();

        cmb_estadovInitialize();

        cmb_parkingInitialize();

        tf_matricula.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!ValidacionesYPatronesConcretos.validarPatronMatricula(tf_matricula.getText())) {
                    tf_matriculaInitialize();
                    incorrecto(lbl_errormatricula);
                }else{
                    correcto(lbl_errormatricula);
                }
            }
        });

        tf_modelo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_modelo.getText())) {
                    tf_modeloInitialize();
                    incorrecto(lbl_errormodelo);
                }else{
                    correcto(lbl_errormodelo);
                }
            }
        });

        tf_marca.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_marca.getText())) {
                    tf_marcaInitialize();
                    incorrecto(lbl_errormarca);
                }else{
                    correcto(lbl_errormarca);
                }
            }
        });

        tf_color.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar30(tf_color.getText())) {
                    tf_colorInitialize();
                    incorrecto(lbl_errorcolor);
                }else{
                    correcto(lbl_errorcolor);
                }
            }
        });

        cmb_parking.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                incorrecto(lbl_errorparking);
            }else{
                correcto(lbl_errorparking);
            }
        });

        cmb_tipov.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                incorrecto(lbl_errortipov);
            }else{
                correcto(lbl_errortipov);
            }
        });

        cmb_estadov.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                incorrecto(lbl_errorestado);
            }else{
                correcto(lbl_errorestado);
            }
        });
    }

    private void correcto(Label lbl) {
        lbl.setText("CORRECTO");
        lbl.setStyle("-fx-text-fill: green;");
    }

    private void incorrecto( Label lbl) {
        lbl.setText("Formato del dato Incorrecto");
        lbl.setStyle("-fx-text-fill: red;");
    }

    private void cmb_parkingInitialize() {
        ObservableList<Parkings> items = FXCollections.observableArrayList();
        SQLAccess access = new SQLAccess();

        items = access.getListaParkings();

        cmb_parking.getItems().setAll(items);

        cmb_parking.setConverter(new StringConverter<Parkings>() {

            @Override
            public String toString(Parkings parkings) {
                if(parkings == null) return "";
                return parkings.getParking_id() + " - " + parkings.getNombre();
            }

            @Override
            public Parkings fromString(String s) {
                return null;
            }
        });
    }

    private void cmb_estadovInitialize() {
        cmb_estadov.getItems().add("Disponible");
        cmb_estadov.getItems().add("Alquilado");
        cmb_estadov.getItems().add("Mantenimiento");
        cmb_estadov.getItems().add("Baja");
    }

    private void cmb_tipovInitialize() {
        SQLAccess sqlAccess = new SQLAccess();

        ObservableList<TiposVehiculos> listTiposVehiculos;

        listTiposVehiculos = sqlAccess.obtenerListadoTipos();

        cmb_tipov.setItems(listTiposVehiculos);

        cmb_tipov.setConverter(new StringConverter<TiposVehiculos>() {
            @Override
            public String toString(TiposVehiculos object) {
                if(object == null) return "";
                return object.getIdTipoVehiculo() + " - " + object.getTipo();
            }

            @Override
            public TiposVehiculos fromString(String s) {
                return null;
            }
        });

    }

    private void tf_colorInitialize() {
        tf_color.setText("");
        tf_color.setPromptText("Introduzca el color del vehículo. Máximo 30 caracteres");
    }

    private void tf_modeloInitialize() {
        tf_modelo.setText("");
        tf_modelo.setPromptText("Introduzca el modelo. Máximo 50 caracteres");
    }

    private void tf_marcaInitialize() {
        tf_marca.setText("");
        tf_marca.setPromptText("Introduzca la marca. Máximo 50 caracteres");
    }

    private void tf_matriculaInitialize() {
        tf_matricula.setText("");
        tf_matricula.setPromptText("Inserte una matrícula valida. 4 dígitos y 3 letras. Ej: 1111AAA");
    }

    private void lbl_idvehiculoInitialize() {
        SQLAccess sqlAccess = new SQLAccess();

        int resultado = sqlAccess.obtenerCuentaVehiculos();

        lbl_idvehiculo.setText(String.valueOf(resultado));
    }

    public void registrarVehiculo(ActionEvent actionEvent) {
        int resultado;

        SQLAccess sqlAccess = new SQLAccess();

        Parkings parkings = cmb_parking.getSelectionModel().getSelectedItem();

        TiposVehiculos tipoVehiculo = cmb_tipov.getSelectionModel().getSelectedItem();

        String estado = cmb_estadov.getSelectionModel().getSelectedItem();

        Vehiculos v = new Vehiculos(tf_matricula.getText(), tf_marca.getText(), tf_modelo.getText(), tf_color.getText(), estado,tipoVehiculo.getIdTipoVehiculo(), parkings.getParking_id());

        resultado = sqlAccess.insertarNuevoVehiculo(v);

        if(resultado == 0){
            lbl_notificacion.setText("No se ha registrado el vehiculo");
            lbl_notificacion.setStyle("-fx-text-fill: red;");
        }else{
            lbl_notificacion.setText("El vehiculo se ha registrado");
            lbl_notificacion.setStyle("-fx-text-fill: green;");
        }
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panel-principal.fxml"));
        BorderPane borderPane = loader.load();
        panelPrincipal.setCenter(borderPane);
    }
}
