package org.example.tucochealquileres;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ventanaPrincipalController implements Initializable {

    @FXML
    private BorderPane menu_principal;

    @FXML
    private HBox barra_superior;

    @FXML
    private Button btn_parkings;

    @FXML
    private TilePane menu_parkings;

    @FXML
    private Button btn_registroParking;


    public BorderPane getMenu_principal() {
        return menu_principal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }



    @FXML
    public void mostrarPanelLateralParkings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panelLateralParkings.fxml"));
        TilePane panelIzquierdo = loader.load();
        menu_principal.setLeft(panelIzquierdo);
        PanelLateralIzquierdoParkingsController controladorIzquierdo = loader.getController();
        controladorIzquierdo.setVentanaPrincipalController(this);
    }

}