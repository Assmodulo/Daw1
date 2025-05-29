package org.example.vetdawexam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
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
    private Button btnVentana1, btnVentana2, btnVentana3, btnVentana4, btnSalirPrincipal, btnExportar;


    public void salirPrograma(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void mostrarVentana1(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana1.fxml"));
        Pane paneVentana1 = loader.load();

       Ventana1Controller controller = loader.getController();
       controller.setVboxPrincipal(botoneraPrincipal);
       controller.setPanelPrincipal(panelPrincipal);

        panelPrincipal.setCenter(paneVentana1);
    }

    public void mostrarVentana2(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana2.fxml"));
        Pane paneVentana2 = loader.load();

        Ventana2Controller controller = loader.getController();
        controller.setVboxPrincipal(botoneraPrincipal);
        controller.setPanelPrincipal(panelPrincipal);

        panelPrincipal.setCenter(paneVentana2);
    }

    public void mostrarVentana3(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana3.fxml"));
        Pane paneVentana3 = loader.load();

        Ventana3Controller controller = loader.getController();
        controller.setVboxPrincipal(botoneraPrincipal);
        controller.setPanelPrincipal(panelPrincipal);

        panelPrincipal.setCenter(paneVentana3);
    }

    public void mostrarVentana4(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ventana4.fxml"));
        Pane paneVentana4 = loader.load();

        Ventana4Controller controller = loader.getController();
        controller.setVboxPrincipal(botoneraPrincipal);
        controller.setPanelPrincipal(panelPrincipal);

        panelPrincipal.setCenter(paneVentana4);
    }

    public void exportarDatos(){

        DBOperations db = new DBOperations();

        LinkedList<Mascota> mascotas = new LinkedList<>();

        mascotas.addAll(db.cargaDatosMascotasInicio());

        try (FileOutputStream file = new FileOutputStream("src\\main\\resources\\mascotas.dat", false);
             ObjectOutputStream buffer = new ObjectOutputStream(file)){
            //buffer.writeObject(personas);
            for (Mascota m : mascotas) {
                buffer.writeObject(mascotas);
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error: "+e.getMessage());
        }

    }
}