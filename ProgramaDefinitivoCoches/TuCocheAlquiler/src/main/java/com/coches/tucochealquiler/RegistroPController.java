package com.coches.tucochealquiler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class RegistroPController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SQLAccess access = new SQLAccess();

        lbl_idparking.setText(String.valueOf(access.getVistaParkingCuenta() + 1));

        inicializar();

        tf_nombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_nombre.getText())) {
                    tf_nombreInicializar();
                    tf_nombre.setStyle("-fx-border-color: red;");
                    nombreerror();
                }else{
                    correctoCompleto(tf_nombre, lbl_errornombre);
                }
            }
        });

        tf_direccion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_direccion.getText())) {
                    tf_direccionInicializar();
                    tf_direccion.setStyle("-fx-border-color: red;");
                    direccionerror();
                }else{
                    correctoCompleto(tf_direccion, lbl_errordireccion);
                }
            }
        });

        tf_ciudad.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_ciudad.getText())) {
                    tf_ciudadInicializar();
                    tf_ciudad.setStyle("-fx-border-color: red;");
                    ciudaderror();
                }else{
                    correctoCompleto(tf_ciudad, lbl_errorciudad);
                }
            }
        });

        tf_codP.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidacionesYPatronesConcretos.validarCodigoPostal(tf_codP.getText())) {
                    tf_codPInicializar();
                    tf_codP.setStyle("-fx-border-color: red;");
                    errorcp();
                }else{
                    correctoCompleto(tf_codP, lbl_errorcp);
                }
            }
        });

        tf_capacidad.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidacionesYPatronesConcretos.validarCapacidad(tf_capacidad.getText())) {
                    tf_capacidadInicializar();
                    tf_capacidad.setStyle("-fx-border-color: red;");
                    errorcapacidad();
                }else{
                    tf_disponibilidad.setText(tf_capacidad.getText());
                    correctoCompleto(tf_disponibilidad, lbl_errordisponibilidad);
                }
            }
        });

        tf_horaap.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!validarFormatoHora(tf_horaap.getText()) || !validarValorHora(tf_horaap.getText())){
                    tf_horaapInicializar();
                    tf_horaap.setStyle("-fx-border-color: red;");
                    errorhoraap();
                }else{
                    correcto(tf_horaap);
                }
            }
        });

        tf_horaci.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!validarFormatoHora(tf_horaci.getText()) || !validarValorHora(tf_horaci.getText())){
                    tf_horaapInicializar();
                    tf_horaci.setStyle("-fx-border-color: red;");
                    errorhoraci();
                }else{
                    correcto(tf_horaci);
                }
            }
        });

        tf_minap.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!validarFormatoHora(tf_minap.getText()) || !validarValorMin(tf_minap.getText())){
                    tf_minapInicializar();
                    tf_minap.setStyle("-fx-border-color: red;");
                    errorhoraap();
                }else{
                    correcto(tf_minap);
                }
            }
        });

        tf_minci.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!validarFormatoHora(tf_minci.getText()) || !validarValorMin(tf_minci.getText())){
                    tf_minciInicializar();
                    tf_minci.setStyle("-fx-border-color: red;");
                    errorhoraci();
                }else{
                    correcto(tf_minci);
                }
            }
        });
    }

    private void correctoCompleto(TextField tfield, Label lbl) {
        tfield.setStyle("-fx-border-color: green;");
        lbl.setText("Correcto");
        lbl.setStyle("-fx-text-fill: green;");
    }

    private void correcto(TextField tfield) {
        tfield.setStyle("-fx-border-color: green;");
    }

    private void errorhoraci() {
        lbl_errorhoraci.setText("Existen errores al introducir el horario");
        lbl_errorhoraci.setStyle("-fx-text-fill: red;");
    }

    private boolean validarValorMin(String text) {
        return (Integer.parseInt(text) < 0 && Integer.parseInt(text) <= 59);
    }

    private void errorhoraap() {
        lbl_errorhoraap.setText("Existen errores al introducir el horario");
        lbl_errorhoraap.setStyle("-fx-text-fill: red;");
    }

    private boolean validarValorHora(String text) {
        return (Integer.parseInt(text) > 0 && Integer.parseInt(text) <= 23);
    }

    private boolean validarFormatoHora(String text) {
        return text.matches("[0-9]{2}");
    }

    private void errorcapacidad() {
        lbl_errorcapacidad.setText("Capacidad incorrecta. Inserte dato otra vez");
        lbl_errorcapacidad.setStyle("-fx-text-fill: red;");
    }

    private void errorcp() {
        lbl_errorcp.setText("Error en el formato del CP. Deben de ser 5 dígitos");
        lbl_errorcp.setStyle("-fx-text-fill: red;");
    }

    private void ciudaderror() {
        lbl_errorciudad.setText("Formato de la ciudad erroneo, pruebe otra vez");
        lbl_errorciudad.setStyle("-fx-text-fill: red;");
    }

    private void direccionerror() {
        lbl_errordireccion.setStyle("-fx-text-fill: red;");
        lbl_errordireccion.setText("Formato de la direccion incorrecto, pruebe otra vez");
    }

    private void nombreerror() {
        lbl_errornombre.setText("Formato del nombre incorrecto, pruebe otra vez");
        lbl_errornombre.setStyle("-fx-text-fill: red;");
    }

    PrincipalController principalController;

    private BorderPane panelPrincipal;

    @FXML
    private TextField tf_nombre, tf_direccion, tf_ciudad, tf_codP, tf_capacidad, tf_disponibilidad, tf_horaap, tf_minap, tf_horaci, tf_minci;

    @FXML
    private Label lbl_errornombre, lbl_errordireccion, lbl_errorciudad, lbl_errorcp, lbl_errorcapacidad, lbl_errordisponibilidad,
                    lbl_errorhoraap, lbl_errorhoraci, lbl_notificacion, lbl_idparking;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panel-principal.fxml"));
        BorderPane borderPane = loader.load();
        panelPrincipal.setCenter(borderPane);
    }

    public void inicializar() {
        tf_nombreInicializar();
        tf_direccionInicializar();
        tf_ciudadInicializar();
        tf_codPInicializar();
        tf_capacidadInicializar();
        tf_disponibilidadInicializar();
        tf_horaapInicializar();
        tf_horaciInicializar();
        tf_minapInicializar();
        tf_minciInicializar();
    }

    private void tf_minciInicializar() {
        tf_minci.setText("");
        tf_minci.setPromptText("ej 00. Min 00 Max 59");
    }

    private void tf_minapInicializar() {
        tf_minap.setText("");
        tf_minap.setPromptText("ej 00. Min 00 Max 59");
    }

    private void tf_horaciInicializar() {
        tf_horaci.setText("");
        tf_horaci.setPromptText("ej 00. Min 00 Max 23");
    }

    private void tf_horaapInicializar() {
        tf_horaap.setText("");
        tf_horaap.setPromptText("ej 00. Min 00 Max 23");
    }

    private void tf_disponibilidadInicializar() {
        tf_disponibilidad.setText("");
        tf_disponibilidad.setPromptText("Cuando indique la capacidad este campo se autorrellenará");
    }

    private void tf_capacidadInicializar() {
        tf_capacidad.setText("");
        tf_capacidad.setPromptText("Indique la capacidad. Inserte dígitos. ej: 100");
    }

    private void tf_codPInicializar() {
        tf_codP.setText("");
        tf_codP.setPromptText("Introduzca el CP. 5 dígitos. ej 00000");
    }

    private void tf_ciudadInicializar() {
        tf_ciudad.setText("");
        tf_ciudad.setPromptText("Introduzca el nombre de la ciudad. Max 50 caracteres");
    }

    private void tf_direccionInicializar() {
        tf_direccion.setText("");
        tf_direccion.setPromptText("Introduzca la direccion. Max 50 caracteres");
    }

    private void tf_nombreInicializar() {
        tf_nombre.setPromptText("Introduzca el nombre. Max 50 caracteres");
        tf_nombre.setText("");
    }

    public void registrarParking(){
        String horaap = tf_horaap.getText().concat(":").concat(tf_minap.getText());
        LocalTime horaAp = LocalTime.parse(horaap, MyFechas.formatoHorario);

        String horaci = tf_horaci.getText().concat(":").concat(tf_minci.getText());
        LocalTime horaCi = LocalTime.parse(horaci, MyFechas.formatoHorario);

        int capacidad = Integer.parseInt(tf_capacidad.getText());

        Parkings p = new Parkings(tf_nombre.getText(), tf_direccion.getText(), tf_ciudad.getText(), tf_codP.getText(),
                capacidad, horaAp, horaCi);

        SQLAccess sql = new SQLAccess();

        int resultado;

        resultado = sql.registrarParking(p);

        if(resultado > 0){
            lbl_notificacion.setText("Parking registrado");
            lbl_notificacion.setStyle("-fx-text-fill: green;");
        }else{
            lbl_notificacion.setText("Parking no registrado");
            lbl_notificacion.setStyle("-fx-text-fill: red;");
        }
    }

}
