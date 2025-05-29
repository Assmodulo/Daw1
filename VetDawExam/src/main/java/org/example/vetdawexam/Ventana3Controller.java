package org.example.vetdawexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Ventana3Controller implements Initializable {

    PrincipalController controller;

    private BorderPane panelPrincipal;
    private VBox botoneraPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setVboxPrincipal(VBox botoneraPrincipal) {
        this.botoneraPrincipal = botoneraPrincipal;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
        BorderPane bp = loader.load();
        panelPrincipal.setCenter(botoneraPrincipal);
    }

    @FXML
    private ListView<Mascota> listMascotas;

    @FXML
    private Label lblDniProp, lblNombreProp, lblApellidosProp, lblResultado;

    @FXML
    private TextField tfDuracion, tfComentario;

    @FXML
    private Button btnRegistrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();

        DBOperations dbOperations = new DBOperations();

        mascotas = dbOperations.cargaDatosMascotasInicio();

        listMascotas.setItems(mascotas);

        listMascotas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Mascota mascota = listMascotas.getSelectionModel().getSelectedItem();
            obtenerDatosPropietarioMascota(mascota.getPropietarioDni());
        });

        tfDuracion.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                if(!validarFormatoDuración(tfDuracion.getText())){
                    tfDuracion.setText("");
                    tfDuracion.setPromptText("Inserte solo valores numéricos");
                    tfDuracion.setStyle("-fx-border-color: red;");
                }
            }
        });

        btnRegistrar.disableProperty().bind(listMascotas.getSelectionModel().selectedItemProperty().isNull().
                                            and(tfDuracion.textProperty().isEmpty().
                                            and(tfComentario.textProperty().isEmpty())));
    }

    private boolean validarFormatoDuración(String text) {
        return text.matches("[0-9]{1,3}");
    }

    private void obtenerDatosPropietarioMascota(String propietarioDni) {
        Propietario p;

        DBOperations dbOperations = new DBOperations();

        //Por alguna razón tengo un error en mi sql, por ahora se queda así, y no hace nada más.

        p = dbOperations.obtenerPropietarioDni(propietarioDni);

        if(p != null) {
            lblDniProp.setText(p.getDni());
            lblNombreProp.setText(p.getNombre());
            lblApellidosProp.setText(p.getApellido());
        }
    }

    public void registrarConsulta(){
        Mascota mascota = listMascotas.getSelectionModel().getSelectedItem();
        DBOperations dbOperations = new DBOperations();

        String fecha = MyFechas.formatearFechaHoraSQL(LocalDateTime.now());

        int duracion = Integer.parseInt(tfDuracion.getText());

        String comentario = tfComentario.getText();

        int rowsAfected = -1;

        rowsAfected = dbOperations.registrarConsulta(mascota, fecha, duracion, comentario);

        if(rowsAfected >= 1){
            lblResultado.setText("Registro exitosos");
            lblResultado.setStyle("-fx-border-color: green;");
            lblDniProp.setStyle("-fx-text-fill: green;");
        }else{
            lblResultado.setText("Registro no realizado");
            lblResultado.setStyle("-fx-border-color: red;");
            lblDniProp.setStyle("-fx-text-fill: red;");
        }
    }
}
