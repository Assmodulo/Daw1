package org.example.ejemplofxclase;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.ejemplofxmodel.Persona;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPrincipal implements Initializable {

    //Creacion de un objeto de la clase persona

    private Persona persona;


    //Ventana principal
    @FXML
    private VBox principal;


    //Cajas de texto del formulario. Todas llamadas como tf+Campo
    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfTelefono;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfEmail;






    @FXML
    protected void onSaveButtonAction(ActionEvent event) {
        persona = new Persona();


        try {
            persona.setNombre(tfNombre.getText());
            persona.setApellidos(tfApellidos.getText());
            persona.setTelefono(tfTelefono.getText());
            persona.setEdad(Integer.parseInt(tfEdad.getText()));
            persona.setEmail(tfEmail.getText());
        } catch (NumberFormatException e) {
            tfEdad.setText("");
            tfEdad.setPromptText("Introduzca solo valores numéricos");
        }
    }

    @FXML
    protected void onCloseButtonAction(ActionEvent event) {
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Persona persona = new Persona();
    }
}