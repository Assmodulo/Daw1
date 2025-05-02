package com.videodbd.pruebasescenasjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorPrincipal {

    @FXML
    private void mostrarSeccionVideoclub() throws IOException {
        Stage stage = new Stage();
        FXMLLoader cargadorSeccionVideoclub = new FXMLLoader(VentanaPrincipal.class.getResource("escenavideoclub.fxml"));
        Scene scene = new Scene(cargadorSeccionVideoclub.getRoot());
        stage.setScene(scene);
        stage.show();
    }

}