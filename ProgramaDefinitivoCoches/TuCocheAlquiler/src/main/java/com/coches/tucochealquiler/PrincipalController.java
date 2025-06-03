package com.coches.tucochealquiler;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            cargarDashBoard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void cargarDashBoard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("panel-principal.fxml"));
        BorderPane borderPane = loader.load();
        panel_principal.setCenter(borderPane);
    }

    @FXML
    private BorderPane panel_principal;

    @FXML
    private VBox panel_lateral;

    @FXML
    private Accordion menu_izquierdo;

    @FXML
    private TitledPane sec_1, sec_2;

    @FXML
    private Label menu_insertarParking;

    public void cambiarVentanaRegistroP() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro-parkings.fxml"));
        Pane paneRegP = loader.load();

        RegistroPController controller = loader.getController();
        controller.setPanelPrincipal(panel_principal);

        panel_principal.setCenter(paneRegP);
    }

    public void cambiarVentanaViewParkings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tview-parkings.fxml"));
        Pane paneViewParkings = loader.load();

        ViewPController controller = loader.getController();
        controller.setPanelPrincipal(panel_principal);

        panel_principal.setCenter(paneViewParkings);
    }

    public void cambiarVentanaInsertarTipo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tipovehiculo.fxml"));
        Pane paneTipoVehiculo = loader.load();

        TiposController controller = loader.getController();
        controller.setPanelPrincipal(panel_principal);

        panel_principal.setCenter(paneTipoVehiculo);
    }

    public void cambiarVentanaRegistroV() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro-vehiculos.fxml"));
        Pane paneRegistroV = loader.load();

        VehiculosController controller = loader.getController();
        controller.setPanelPrincipal(panel_principal);

        panel_principal.setCenter(paneRegistroV);
    }

    public void cambiarVentanaInsertarClientes() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro-clientes.fxml"));
        Pane paneRegistroC = loader.load();

        RegistroCController controller = loader.getController();
        controller.setPanelPrincipal(panel_principal);

        panel_principal.setCenter(paneRegistroC);
    }

    public void cambiarVentanaInsertarReservas() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro-reservas.fxml"));
        Pane paneRegistroR = loader.load();

        ReservaController controller = loader.getController();
        controller.setPanelPrincipal(panel_principal);

        panel_principal.setCenter(paneRegistroR);
    }


}