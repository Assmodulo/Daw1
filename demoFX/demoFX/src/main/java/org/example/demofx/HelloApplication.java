package org.example.demofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Java FX Wellcome");

        primaryStage.show();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Label scenetitle = new Label("Welcome");
        scenetitle.setStyle("-fx-font-weight: bold");
        scenetitle.setMaxSize(150,150);
        grid.add(scenetitle, 0, 0);



        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);


    }

    public static void main(String[] args) {
        launch();
    }
}