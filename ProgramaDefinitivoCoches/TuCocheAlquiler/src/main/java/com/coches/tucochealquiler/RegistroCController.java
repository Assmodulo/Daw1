package com.coches.tucochealquiler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistroCController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializar();
    }

    private void inicializar() {

        SQLAccess access = new SQLAccess();

        int id_parking = access.getVistaParkingCuenta();

        lbl_idcliente.setText(String.valueOf(id_parking));

            tf_nombreInicializar();
            tf_apellidosInicializar();
            tf_direccionInicializar();
            tf_ciudadInicializar();
            tf_codPInicializar();
            tf_telefonoInicializar();
            tf_emailInicializar();
            tf_dniInicializar();


        tf_nombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_nombre.getText())) {
                    tf_nombreInicializar();
                    incorrecto(lbl_errornombre);
                }else{
                    correcto(lbl_errornombre);
                }
            }
        });

        tf_direccion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_direccion.getText())) {
                    tf_direccionInicializar();
                    incorrecto(lbl_errordireccion);
                }else{
                    correcto(lbl_errordireccion);
                }
            }
        });

        tf_ciudad.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar50(tf_ciudad.getText())) {
                    tf_ciudadInicializar();
                    incorrecto(lbl_errordireccion);
                }else{
                    correcto(lbl_errordireccion);
                }
            }
        });

        tf_cp.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidacionesYPatronesConcretos.validarCodigoPostal(tf_cp.getText())) {
                    tf_codPInicializar();
                    incorrecto(lbl_errorcp);
                }else{
                    correcto(lbl_errorcp);
                }
            }
        });

        tf_apellidos.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar100(tf_apellidos.getText())) {
                    tf_apellidosInicializar();
                    incorrecto(lbl_errorapellidos);
                }else{
                    correcto(lbl_errorapellidos);
                }
            }
        });

        tf_email.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarEmail(tf_email.getText())) {
                    tf_emailInicializar();
                    incorrecto(lbl_erroremail);
                }else{
                    correcto(lbl_erroremail);
                }
            }
        });

        tf_telefono.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidacionesYPatronesConcretos.validarPatronTelefono(tf_telefono.getText())) {
                    tf_telefonoInicializar();
                    incorrecto(lbl_errortelefono);
                }else{
                    correcto(lbl_errortelefono);
                }
            }
        });

        tf_dni.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidacionesYPatronesConcretos.validarFormatoNie(tf_dni.getText())) {
                    tf_dniInicializar();
                    incorrecto(lbl_errordni);
                }else{
                    correcto(lbl_errordni);
                    tf_dni.setText(tf_dni.getText().concat(ValidacionesYPatronesConcretos.obtenerLetraDni(tf_dni.getText())));
                }
            }
        });

        tf_direccion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(!ValidadoresGenericos.validarVarchar100(tf_direccion.getText())) {
                    tf_direccionInicializar();
                    incorrecto(lbl_errordireccion);
                }else{
                    correcto(lbl_errordireccion);
                }
            }
        });

        dp_fnacimiento.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue) {
                if(MyFechas.validarFechaCorrecta(dp_fnacimiento.getValue())){
                    lbl_errorfnacim.setText("Introduzca una fecha valida");
                    lbl_errorfnacim.setStyle("-fx-text-fill: red;");
                    dp_fnacimiento.setValue(null);
                }else if(!MyFechas.validarMayorEdad(dp_fnacimiento.getValue())){
                    lbl_errorfnacim.setText("El usuario es menor de edad.");
                    lbl_errorfnacim.setStyle("-fx-text-fill: red;");
                    dp_fnacimiento.setValue(null);
                }
            }
        });

    }

    private void tf_dniInicializar() {
        tf_dni.setText("");
        tf_dni.setPromptText("Introduzca el dni. 8 caracteres, sin letra.");
    }

    private void tf_emailInicializar() {
        tf_email.setText("");
        tf_email.setPromptText("Introduzca el email. Max 100 caracteres. Ej: miemail@email.es");
    }

    private void tf_telefonoInicializar() {
        tf_telefono.setText("");
        tf_telefono.setPromptText("Introduzca el teléfono. 9 dígitos. Ej: 666666666");
    }

    private void tf_codPInicializar() {
        tf_cp.setText("");
        tf_cp.setPromptText("Introduzca el CP. 5 dígitos obligatorios. Ej:11111");
    }

    private void tf_ciudadInicializar() {
        tf_ciudad.setText("");
        tf_ciudad.setPromptText("Introduzca la localidad. Max 100 caracteres");
    }

    private void tf_direccionInicializar() {
        tf_direccion.setText("");
        tf_direccion.setPromptText("Introduzca la direccion. Max 100 caracteres");
    }

    private void tf_apellidosInicializar() {
        tf_apellidos.setText("");
        tf_apellidos.setPromptText("Introduzca los apellidos. Max 100 caracteres");
    }

    private void tf_nombreInicializar() {
        tf_nombre.setText("");
        tf_nombre.setPromptText("Introduzca el nombre. Max 50 caracteres");
    }

    private void correcto(Label lbl) {
        lbl.setText("CORRECTO");
        lbl.setStyle("-fx-text-fill: green;");
    }

    private void incorrecto( Label lbl) {
        lbl.setText("Formato del dato Incorrecto");
        lbl.setStyle("-fx-text-fill: red;");
    }

    PrincipalController principalController;

    private BorderPane panelPrincipal;

    @FXML
    private TextField tf_nombre, tf_direccion, tf_ciudad, tf_cp, tf_telefono, tf_email, tf_dni, tf_apellidos;

    @FXML
    private DatePicker dp_fnacimiento;

    @FXML
    private Label lbl_errornombre, lbl_errordireccion, lbl_errorciudad, lbl_errorcp, lbl_errortelefono, lbl_erroremail,
        lbl_errordni, lbl_errorapellidos, lbl_errorfnacim, lbl_notificacion, lbl_idcliente;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panel-principal.fxml"));
        BorderPane borderPane = loader.load();
        panelPrincipal.setCenter(borderPane);
    }

    public void registrarCliente(){
        int resultado = 0;

        if(dp_fnacimiento.getValue() != null){
            LocalDate fecha = dp_fnacimiento.getValue();

            SQLAccess access = new SQLAccess();

            Clientes c = new Clientes(tf_nombre.getText(), tf_apellidos.getText(), tf_dni.getText(), fecha,
                    tf_email.getText(), tf_telefono.getText(),tf_direccion.getText(),tf_ciudad.getText(), tf_cp.getText());

            resultado = access.registrarCliente(c);
        }

        if(resultado == 1){
            lbl_notificacion.setText("REGISTRO CLIENTE CORRECTO");
            lbl_notificacion.setStyle("-fx-text-fill: green;");
        }else{
            lbl_notificacion.setText("REGISTRO CLIENTE INCORRECTO");
            lbl_notificacion.setStyle("-fx-text-fill: red;");
        }

    }
}
