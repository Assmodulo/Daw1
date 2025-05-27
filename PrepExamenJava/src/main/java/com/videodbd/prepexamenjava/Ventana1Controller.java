package com.videodbd.prepexamenjava;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Ventana1Controller {

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
    private Pane ventana1;

    @FXML
    private Button btnAtras;

    public void setVentana1Controller(PrincipalController controller) {
        this.controller = controller;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
        BorderPane bp = loader.load();
        panelPrincipal.setCenter(botoneraPrincipal);
    }
}
