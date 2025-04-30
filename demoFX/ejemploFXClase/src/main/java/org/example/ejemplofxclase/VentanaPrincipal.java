package org.example.ejemplofxclase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VentanaPrincipal.class.getResource("ventana-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800 , 800);
        stage.setTitle("Ventana Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}