package org.example.vetdawexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Ventana1Controller implements Initializable {
    PrincipalController controller;

    private BorderPane panelPrincipal;
    private VBox botoneraPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setVboxPrincipal(VBox botoneraPrincipal) {
        this.botoneraPrincipal = botoneraPrincipal;
    }


    @FXML
    private Pane ventana1;

    @FXML
    private Label lblResultado;

    @FXML
    private VBox vboxRegistroMascota;

    @FXML
    private ListView<Propietario> listviewPropietarios;

    @FXML
    private Button btnAtras, btnRegistrar;

    @FXML
    private TextField tfPasaporte, tfNombre, tfPeso, tfFnacimiento;

    @FXML
    private ComboBox<TipoMascota> cmbTipoMascota;



    public void setVentana1Controller(PrincipalController controller) {
        this.controller = controller;
    }

    public void volverPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
        BorderPane bp = loader.load();
        panelPrincipal.setCenter(botoneraPrincipal);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Propietario> propietarios = FXCollections.observableArrayList();
        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();

        ObservableList<TipoMascota> tiposMascotas = FXCollections.observableArrayList();

        DBOperations db = new DBOperations();

        try {
            propietarios = db.cargaDatosPropietarioInicio();
            mascotas = db.cargaDatosMascotasInicio();
            tiposMascotas = db.cargaDatosTiposMascotas();
            cmbTipoMascota.setItems(tiposMascotas);
        } catch (SQLException e) {
            System.out.println("No se ha podido cargar datos de propietarios. " + e.getMessage());
        }

        listviewPropietarios.setItems(propietarios);

        ObservableList<Mascota> finalMascotas = mascotas;
        ObservableList<Mascota> finalMascotas1 = mascotas;
        tfPasaporte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarPasaporteMascota(tfPasaporte.getText())) {
                    tfPasaporte.setText("");
                    tfPasaporte.setPromptText("Inserte ej: \"P00000000\" sustituya ceros por número");
                    tfPasaporte.setStyle("-fx-border-color: red;");
                }else{
                    LinkedList<Mascota> mascota = new LinkedList<>();
                    mascota.addAll(finalMascotas1);
                    if(validarPasaporteExistente(tfPasaporte.getText(),mascota)){
                        tfPasaporte.setText("");
                        tfPasaporte.setPromptText("El pasaporte ha sido encontrado en la base de datos");
                        tfPasaporte.setStyle("-fx-border-color: red;");
                    }
                }
            }
        });

        tfFnacimiento.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarFNacimiento(tfFnacimiento.getText())) {
                    tfFnacimiento.setText("");
                    tfFnacimiento.setPromptText("Inserte Fecha Nacimiento ej: 12/12/2000");
                    tfFnacimiento.setStyle("-fx-border-color: red;");
                }else{
                    try {
                        if(!MyFechas.validarFechaAnterior(tfFnacimiento.getText())){
                            tfFnacimiento.setText("");
                            tfFnacimiento.setPromptText("Inserte Fecha Anterior ej: 12/12/2000");
                            tfFnacimiento.setStyle("-fx-border-color: red;");
                        }
                    } catch (FechaIncorrectaException e) {
                        tfFnacimiento.setPromptText(e.getMessage());
                    }
                }
            }
        });

        tfNombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if(!validarName(tfNombre.getText())) {
                    tfNombre.setText("");
                    tfNombre.setPromptText("Inserte Nombre ej: Máximo 45 caracteres, minimo 3");
                    tfNombre.setStyle("-fx-border-color: red;");
                }
            }
        });

        tfPeso.focusedProperty().addListener((observable, oldValue, newValue) -> {
           if(!newValue) {
               if(!validarPeso(tfPeso.getText())) {
                   tfPeso.setText("");
                   tfPeso.setPromptText("Inserte Peso con 1 decimal ej: 10.4");
                   tfPeso.setStyle("-fx-border-color: red;");
               }
           }
        });



        //Hay que tocar las opciones del botón para que no se muestre hasta que todo esté completo

        btnRegistrar.disableProperty().bind(tfPasaporte.textProperty().isEmpty().and(tfFnacimiento.textProperty().isEmpty()).
                and(tfNombre.textProperty().isEmpty().and(tfPeso.textProperty().isEmpty().and(listviewPropietarios.getSelectionModel().selectedItemProperty().isNull()).
                        and(cmbTipoMascota.getSelectionModel().selectedItemProperty().isNull()))));
    }

    private boolean validarPasaporteExistente(String text, LinkedList<Mascota> mascotas) {
        boolean encontrado = false;

        for(Mascota m : mascotas) {
            if(m.getNombre().equals(text)) {
                encontrado = true;
            }
        }
        return encontrado;
    }


    private boolean validarPasaporteMascota(String pasaporte){
        return pasaporte.matches("[P]{1}[0-9]{8}");
    }

    private boolean validarName(String name){
        return (name.length() >= 3 && name.matches("[A-Z]{1}[a-z]{3,45}"));
    }

    private boolean validarFNacimiento(String fnacimiento){
        return fnacimiento.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}");
    }

    private boolean validarPeso(String peso){
        return peso.matches("[0-9]{1,3}[.][0-9]{1}");
    }

    public void registrarMascotaBaseDeDatos(){
        TipoMascota t = cmbTipoMascota.getSelectionModel().getSelectedItem();
        Propietario p = listviewPropietarios.getSelectionModel().getSelectedItem();

        Mascota m = new Mascota(tfPasaporte.getText(), tfNombre.getText(), Double.parseDouble(tfPeso.getText()),
                LocalDate.parse(tfFnacimiento.getText(),MyFechas.formatoFecha),p.getDni(), t.getIdTipo());

        DBOperations db = new DBOperations();

        lblResultado.setText(db.insertarMascota(m));
        lblResultado.setStyle("-fx-text-fill:green;");
    }


}
