package org.example.vetdawexam;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ventana4Controller implements Initializable {

    @FXML
    private Button btnAtras, btnBuscar;

    @FXML
    private TextField tfPasaporte;

    @FXML
    private ListView<Mascota> listMascotas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfPasaporte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                if(!validarFormatoPasaporte(tfPasaporte.getText())){
                    tfPasaporte.setText("");
                    tfPasaporte.setPromptText("Inserte el formato de pasaporte correcto");
                    tfPasaporte.setStyle("-fx-border-color: red;");
                }
            }
        });

    }

    private boolean validarFormatoPasaporte(String text) {
        return text.matches("[P][0-9]{8}");
    }

    PrincipalController controller;

    private BorderPane panelPrincipal;
    private VBox botoneraPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setVboxPrincipal(VBox botoneraPrincipal) {
        this.botoneraPrincipal = botoneraPrincipal;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
        BorderPane bp = loader.load();
        panelPrincipal.setCenter(botoneraPrincipal);
    }


    public Mascota buscarMascota(){
        String pasaporte = tfPasaporte.getText();

        Mascota m;

        DBOperations db = new DBOperations();

        m = db.obtenerMascotaPorPasaporte(pasaporte);

        if(m != null){
            listMascotas.getItems().add(m);
        }else{
            listMascotas.setItems(db.cargaDatosMascotasInicio());
        }

        return m;
    }

}
