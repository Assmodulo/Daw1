package org.example.tucochealquileres;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TviewParkingsController implements Initializable {

    @FXML
    private TableView<Parkings> tview_Parkings = new TableView<>();

    @FXML
    private TableColumn<Parkings, String> columnNombre, columnDireccion, columnCiudad, columnCP;

    @FXML
    private TableColumn<Parkings, Integer> columnId, columnCapacidad, columnDisponibles;

    @FXML
    private TableColumn<Parkings, LocalTime> columnApertura, columnCierre;

    @FXML
    private TableColumn<Parkings, Boolean> columnActivo;

    @FXML
    ObservableList<Parkings> parkings = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.columnId.setCellValueFactory(new PropertyValueFactory<>("parking_id"));
        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.columnCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        this.columnCP.setCellValueFactory(new PropertyValueFactory<>("codigoPostal"));
        this.columnCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        this.columnDisponibles.setCellValueFactory(new PropertyValueFactory<>("plazasDisponibles"));
        this.columnApertura.setCellValueFactory(new PropertyValueFactory<>("horaApertura"));
        this.columnCierre.setCellValueFactory(new PropertyValueFactory<>("horaCierre"));
        this.columnActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

        SQLAccces sql = new SQLAccces();

        parkings.addAll(sql.getListaParkings());

        this.tview_Parkings.setItems(parkings);

    }
}
