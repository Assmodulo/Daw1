package org.example.tucochealquileres;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TviewEmpleadosController {

    @FXML
    private TableView<Parkings> tview_Parkings;

    @FXML
    private TableColumn<Parkings, String> columnNombre, columnDireccion, columnCiudad, columnCP;

    @FXML
    private TableColumn<Parkings, Integer> columnId, columnCapacidad, columnDisponibles;

    @FXML
    private TableColumn<Parkings, LocalTime> columnApertura, columnCierre;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        SQLAccces sqlAccces = new SQLAccces();

        tview_Parkings = new TableView<Parkings>();

        ObservableList<Parkings> parkings = FXCollections.observableArrayList();

        columnId = new TableColumn("parking_id");

        columnId.setCellValueFactory(new PropertyValueFactory<>("parking_id"));





        parkings.addAll(sqlAccces.getListaParkings());

        tview_Parkings.setItems(parkings);
        tview_Parkings.getColumns().addAll();



    }
}



