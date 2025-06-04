package org.example.tucochealquileres;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PanelLateralIzquierdoParkingsController {


    private ventanaPrincipalController ventanaPrincipalController;

    @FXML
    public void mostrarVentanaRegistro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ventanaRegistroParkings.fxml"));
        Pane ventanaCentral = loader.load();
        ventanaPrincipalController.getMenu_principal().setCenter(ventanaCentral);

    }

    public void mostrarVentanaTViewParkings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listViewParkings.fxml"));
        TableView<Parkings> tablaParkings = loader.load();
        ventanaPrincipalController.getMenu_principal().setCenter(tablaParkings);
    }

    public void setVentanaPrincipalController(ventanaPrincipalController ventanaPrincipalController) {
        this.ventanaPrincipalController = ventanaPrincipalController;
    }
}
