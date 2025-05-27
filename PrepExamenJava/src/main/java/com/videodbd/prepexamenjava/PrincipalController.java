package com.videodbd.prepexamenjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PrincipalController {


    PrincipalController controller;
    //Declaro una variable del tipo BorderPane. Esta va a ser la ventana principal. Ahora voy a crear un par de funciones
    //para cambiar de una ventana a otra de forma fácil.
    @FXML
    private BorderPane panelPrincipal;


    //Un getter para poder obtener este panel principal para poder cambiar de clases de forma rápida. Voy a crear aparte
    // un par de views más para tenerlo preparado por si acaso mañana. En una yo creo que voy a hacer una botonera, dentro
    // De este menú principal y luego varias distintas con un botón atrás para volver al menú principal.

    public BorderPane getPanelPrincipal() {
        return panelPrincipal;
    }

    //Linkeamos la botonera principal
    @FXML
    private VBox botoneraPrincipal;

    //Creamos un método para obtener la botonera principal
    public void getBotoneraPrincipal() {
        panelPrincipal.setCenter(botoneraPrincipal);
    }

    //Vamos a linkear los botones, todos ellos

    @FXML
    private Button btnVentana1, btnVentana2, btnVentana3, btnVentana4, btnSalirPrincipal;


    public void salirPrograma(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void mostrarVentana1(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaVentana1.fxml"));
        Pane paneVentana1 = loader.load();

        Ventana1Controller controller = loader.getController();
        controller.setVboxPrincipal(botoneraPrincipal);
        controller.setPanelPrincipal(panelPrincipal);

        panelPrincipal.setCenter(paneVentana1);
    }
}