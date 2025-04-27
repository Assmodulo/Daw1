package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage){

        Scene scene = new Scene(crearContenido());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label crearContenido(){
        return new Label("Hello World");
    }

    public static void main(String[] args) {
        launch();
    }
}