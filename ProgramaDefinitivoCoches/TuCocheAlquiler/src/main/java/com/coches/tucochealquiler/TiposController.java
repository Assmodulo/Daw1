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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TiposController implements Initializable {

    PrincipalController principalController;

    private BorderPane panelPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    @FXML
    private TextField tf_descripcion, tf_tarifa, tf_tipo;

    @FXML
    private ComboBox<String> cmb_tipos;

    @FXML
    private Label lbl_errordescripcion, lbl_errortarifa, lbl_notificacion, lbl_errortipo, lbl_idtipo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        inicializar();

        tf_tipo.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!ValidadoresGenericos.validarVarchar30(tf_tipo.getText())){
                    tf_tipoInicializar();
                    incorrecto(tf_tipo, lbl_errortipo);
                }else{
                    correcto(lbl_errortipo);
                }
            }
        });

        tf_descripcion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_descripcion.getText())){
                    tf_descripcionInicializar();
                    incorrecto(tf_descripcion, lbl_errordescripcion);
                }else{
                    correcto(lbl_errordescripcion);
                }
            }
        });

        tf_tarifa.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoTarifa(tf_tarifa.getText())){
                    tf_tarifaInicializar();
                    incorrecto(tf_tarifa, lbl_errortarifa);
                }else{
                    correcto(lbl_errortarifa);
                }
            }
        });
    }

    private boolean validarFormatoTarifa(String text) {
        return text.matches("[0-9]{1,4}[.][0-9]{2}");
    }

    private void correcto(Label lblErrortipo) {
        lbl_errortipo.setText("CORRECTO");
        lbl_errortipo.setStyle("-fx-text-fill: green;");
    }

    private void incorrecto(TextField tf, Label lbl) {
        tf.setStyle("-fx-border-color: red;");
        lbl.setText("Formato del dato incorrecto");
        lbl.setStyle("-fx-text-fill: red;");
    }

    public void inicializar() {
        tf_descripcionInicializar();
        tf_tipoInicializar();
        tf_tarifaInicializar();
        lbl_idtipoInicializar();
    }

    private void tf_tarifaInicializar() {
        tf_tarifa.setText("");
        tf_tarifa.setPromptText("Inserte la tarifa. Máximo 4 caracteres y dos decimales. Ej: 1111.11");
    }

    private void tf_tipoInicializar() {
        tf_tipo.setPromptText("Inserte el tipo de vehículo. Máximo 30 caracteres.");
        tf_tipo.setText("");
    }

    private void tf_descripcionInicializar() {
        tf_descripcion.setText("");
        tf_descripcion.setPromptText("Inserte la descripción. Máximo 50 caracteres");
    }

    private void lbl_idtipoInicializar() {
        int idtipo = 0;

        SQLAccess sqlAccess = new SQLAccess();

        idtipo = sqlAccess.obtenerIdTipo();

        lbl_idtipo.setText(String.valueOf(idtipo));
    }

    public void registrarTipo(){
        int resultado;

        SQLAccess sqlAccess = new SQLAccess();

        TiposVehiculos tp = new TiposVehiculos(tf_tipo.getText(), tf_descripcion.getText(), Double.parseDouble(tf_tarifa.getText()));

        resultado = sqlAccess.insertarNuevoTipo(tp);

        if(resultado >= 1){
            lbl_notificacion.setText("Tipo registrado con exito");
            lbl_notificacion.setStyle("-fx-text-fill: green;");
        }else{
            lbl_notificacion.setText("Tipo no registrado");
            lbl_notificacion.setStyle("-fx-text-fill: red;");
        }
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panel-principal.fxml"));
        BorderPane borderPane = loader.load();
        panelPrincipal.setCenter(borderPane);
    }
}
