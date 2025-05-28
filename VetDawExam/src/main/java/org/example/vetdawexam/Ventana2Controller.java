package org.example.vetdawexam;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Ventana2Controller implements Initializable {

    PrincipalController controller;

    private BorderPane panelPrincipal;
    private VBox botoneraPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setVboxPrincipal(VBox botoneraPrincipal) {
        this.botoneraPrincipal = botoneraPrincipal;
    }

    @FXML
    private TextField tfDni, tfNombre, tfApellidos, tfEmail, tfTelefono, tfDireccion;

    @FXML
    private Button btnAtras, btnRegistrar;

    @FXML
    private Label lblResultado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LinkedList<Propietario> propietarios = new LinkedList<>();

        DBOperations dbOperations = new DBOperations();

        try {
            propietarios.addAll(dbOperations.cargaDatosPropietarioInicio());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btnRegistrar.disableProperty().bind(tfDni.textProperty().isNull().
                                            and(tfNombre.textProperty().isNull().
                                            and(tfApellidos.textProperty().isNull().
                                            and(tfDireccion.textProperty().isNull().
                                            and(tfTelefono.textProperty().isNull().
                                            and(tfEmail.textProperty().isNull()))))));


        tfDni.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoDni(tfDni.getText())) {
                    tfDni.setText("");
                    tfDni.setPromptText("Inserte ej: 11111111A");
                    tfDni.setStyle("-fx-border-color: red;");
                }else{
                    if(validarDniExistente(tfDni.getText(),propietarios)){
                        tfDni.setText("");
                        tfDni.setPromptText("El pasaporte ha sido encontrado en la base de datos");
                        tfDni.setStyle("-fx-border-color: red;");
                    }
                }
            }
        });

        tfNombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoNombre(tfNombre.getText())){
                    tfNombre.setText(null);
                    tfNombre.setPromptText("Inserte ej: Minimo 3 caracterer, máximo 45");
                    tfNombre.setStyle("-fx-border-color: red;");
                }

            }
        });

        tfApellidos.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoNombre(tfApellidos.getText())){
                    tfApellidos.setText(null);
                    tfApellidos.setPromptText("Inserte ej: Minimo 3 caracterer, máximo 45");
                    tfApellidos.setStyle("-fx-border-color: red;");
                }
            }
        });

        tfDireccion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoDireccion(tfDireccion.getText())){
                    tfDireccion.setText(null);
                    tfDireccion.setPromptText("Inserter caracterer alfanuméricos hasta un máximo de 100");
                    tfDireccion.setStyle("-fx-border-color: red;");
                }
            }
        });

        tfTelefono.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoTelefono(tfTelefono.getText())){
                    tfTelefono.setText(null);
                    tfTelefono.setPromptText("Inserte telefono ej: 666666666");
                    tfTelefono.setStyle("-fx-border-color: red;");
                }
            }
        });

        tfEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFormatoEmail(tfEmail.getText())){
                    tfEmail.setText(null);
                    tfEmail.setPromptText("Inserte ej: MiDireccion@midominio.es");
                    tfEmail.setStyle("-fx-border-color: red;");
                }
            }
        });

    }

    private boolean validarFormatoEmail(String text) {
        return text.matches("[A-Z_a-z-0-9]{5,30}[@][a-z]{3,10}[.][a-z]{2,3}");
    }

    private boolean validarFormatoTelefono(String text) {
        return text.matches("[0-9]{9}");
    }

    private boolean validarFormatoDireccion(String text) {
        return text.matches("[a-z A-Z0-9]{1,100}");
    }

    private boolean validarFormatoNombre(String text) {
        return text.matches("[a-z A-Z]{3,45}");
    }

    private boolean validarDniExistente(String text, LinkedList<Propietario> propietarios) {
        boolean encontrado = false;

        for(Propietario propietario : propietarios) {
            if(propietario.getDni().equals(text)) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    private boolean validarFormatoDni(String text) {
        return text.matches("[0-9]{8}[A-Za-z]{1}");
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
        BorderPane bp = loader.load();
        panelPrincipal.setCenter(botoneraPrincipal);
    }

    public void registrarPropietario(){

        DBOperations db = new DBOperations();

        Propietario p;

        int rowsAffected = -1;

        p = new Propietario(tfDni.getText(),tfNombre.getText(),tfApellidos.getText(),
                tfTelefono.getText(),tfDireccion.getText(),tfEmail.getText());

        rowsAffected = db.insertarPropietario(p);

        if(rowsAffected < 1){
            lblResultado.setText("Error al insertar propietario");
            lblResultado.setStyle("-fx-border-color: red;");
        }else{
            lblResultado.setText("Propietario registrado exitosamente");
            lblResultado.setStyle("-fx-border-color: green;");
        }
    }
}
