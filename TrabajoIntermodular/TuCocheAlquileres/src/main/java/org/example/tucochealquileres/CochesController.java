package org.example.tucochealquileres;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CochesController implements Initializable {

    @FXML
    private Pane ventana_registroParking;

    @FXML
    private VBox cajaVertical_registroParkings;

    @FXML
    private HBox hbox_nombreParking;

    @FXML
    private Label lbl_matricula_registroCoche;

    @FXML
    private TextField input_matricula_registroCoche;

    @FXML
    private Label lbl_validacionNombre;

    @FXML
    private HBox hbox_direccionParking;

    @FXML
    private Label lbl_marca_registroCoche;

    @FXML
    private TextField input_marca_registroCoche;

    @FXML
    private Label lbl_validacionDireccion;

    @FXML
    private HBox hbox_localidadParking;

    @FXML
    private Label lbl_modelo_registroCoche;

    @FXML
    private TextField input_modelo_registroCoche;

    @FXML
    private Label lbl_validacionLocalidad;

    @FXML
    private HBox hbox_cpParking;

    @FXML
    private Label lbl_color_registroCoche;

    @FXML
    private TextField input_color_registroCoche;

    @FXML
    private Label lbl_validacionCP;

    @FXML
    private HBox hbox_capacidadParking;

    @FXML
    private Label lbl_estado_registroCoche;

    @FXML
    private TextField input_estado_registroCoche;

    @FXML
    private Label lbl_validacionCapacidad;

    @FXML
    private HBox hbox_horarios_RegistroParking;

    @FXML
    private Label lbl_horarioPicker_RegistroCoche;

    @FXML
    private HBox registroHorarioApertura_RegistroParking;

    @FXML
    private ComboBox<String> cmb_HorasDesde_RegistroCoche;

    @FXML
    private ComboBox<String> cmb_MinDesde_RegistroCoche;

    @FXML
    private HBox registroHorarioCierre_RegistroParking;

    @FXML
    private ComboBox<String> cmb_HorasHasta_RegistroCoche;

    @FXML
    private ComboBox<String> cmb_MinHasta_RegistroCoche;

    @FXML
    private HBox botonera_RegistroParkings;

    @FXML
    private Button botonGuardar_RegistroParking;

    @FXML
    private Button botonAtras_RegistroParkings;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializamos los ComboBox
        ObservableList<String> minutos = cargarMinutos();
        ObservableList<String> horas = cargarHoras();

        // Cargamos los comboBox con los datos de horas y minutos
        cmb_HorasDesde_RegistroCoche.setItems(horas);
        cmb_HorasHasta_RegistroCoche.setItems(horas);
        cmb_MinDesde_RegistroCoche.setItems(minutos);
        cmb_MinHasta_RegistroCoche.setItems(minutos);

        // Configuración de la validación de matrícula
        input_matricula_registroCoche.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!validarMatricula(input_matricula_registroCoche.getText())) {
                    input_matricula_registroCoche.setText("");
                    input_matricula_registroCoche.setPromptText("Error en el formato de la matrícula");
                    lbl_validacionNombre.setText("❌");
                } else {
                    lbl_validacionNombre.setText("✓");
                }
            }
        });

        // Configuración de la validación de marca
        input_marca_registroCoche.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (input_marca_registroCoche.getText().isEmpty()) {
                    input_marca_registroCoche.setPromptText("La marca no puede estar vacía");
                    lbl_validacionDireccion.setText("❌");
                } else {
                    lbl_validacionDireccion.setText("✓");
                }
            }
        });

        // Configuración de la validación de modelo
        input_modelo_registroCoche.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (input_modelo_registroCoche.getText().isEmpty()) {
                    input_modelo_registroCoche.setPromptText("El modelo no puede estar vacío");
                    lbl_validacionLocalidad.setText("❌");
                } else {
                    lbl_validacionLocalidad.setText("✓");
                }
            }
        });

        // Configuración de la validación de color
        input_color_registroCoche.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (input_color_registroCoche.getText().isEmpty()) {
                    input_color_registroCoche.setPromptText("El color no puede estar vacío");
                    lbl_validacionCP.setText("❌");
                } else {
                    lbl_validacionCP.setText("✓");
                }
            }
        });

        // Configuración de la validación de estado
        input_estado_registroCoche.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (input_estado_registroCoche.getText().isEmpty()) {
                    input_estado_registroCoche.setPromptText("El estado no puede estar vacío");
                    lbl_validacionCapacidad.setText("❌");
                } else {
                    lbl_validacionCapacidad.setText("✓");
                }
            }
        });

        // Configuración de los botones
        botonGuardar_RegistroParking.setOnAction(event -> registrarCoche());
        botonAtras_RegistroParkings.setOnAction(event -> volverAtras());
    }

    /**
     * Método para registrar un nuevo coche con los datos ingresados
     */
    private void registrarCoche() {
        // Verificar que todos los campos estén completos
        if (validarCamposCompletos()) {
            // Recogemos los datos de los campos
            String matricula = input_matricula_registroCoche.getText();
            String marca = input_marca_registroCoche.getText();
            String modelo = input_modelo_registroCoche.getText();
            String color = input_color_registroCoche.getText();
            String estado = input_estado_registroCoche.getText();

            // Verificamos que se hayan seleccionado horarios
            String horaDesde = cmb_HorasDesde_RegistroCoche.getValue();
            String minDesde = cmb_MinDesde_RegistroCoche.getValue();
            String horaHasta = cmb_HorasHasta_RegistroCoche.getValue();
            String minHasta = cmb_MinHasta_RegistroCoche.getValue();

            if (horaDesde == null || minDesde == null || horaHasta == null || minHasta == null) {
                // Mostrar un mensaje de error al usuario
                System.out.println("Por favor, selecciona los horarios");
                return;
            }

            // Crear un nuevo objeto Coche (deberías tener esta clase)
            // Coche nuevoCoche = new Coche(matricula, marca, modelo, color, estado, horaDesde + ":" + minDesde, horaHasta + ":" + minHasta);

            // Aquí iría el código para guardar el coche en la base de datos

            // Mensaje de éxito y limpieza de campos
            System.out.println("¡Coche registrado correctamente!");
            limpiarCampos();
        } else {
            // Mostrar mensaje de error
            System.out.println("Por favor, completa todos los campos correctamente");
        }
    }

    /**
     * Método para verificar que todos los campos estén completos y sean válidos
     * @return true si todos los campos son válidos, false en caso contrario
     */
    private boolean validarCamposCompletos() {
        boolean matriculaValida = validarMatricula(input_matricula_registroCoche.getText());
        boolean marcaValida = !input_marca_registroCoche.getText().isEmpty();
        boolean modeloValido = !input_modelo_registroCoche.getText().isEmpty();
        boolean colorValido = !input_color_registroCoche.getText().isEmpty();
        boolean estadoValido = !input_estado_registroCoche.getText().isEmpty();

        return matriculaValida && marcaValida && modeloValido && colorValido && estadoValido;
    }

    /**
     * Método para validar el formato de la matrícula (4 números seguidos de 3 letras mayúsculas)
     * @param matricula La matrícula a validar
     * @return true si la matrícula es válida, false en caso contrario
     */
    private boolean validarMatricula(String matricula) {
        if (matricula == null || matricula.isEmpty()) {
            return false;
        }

        // Patrón para matrícula española: 4 números y 3 letras (1234ABC)
        return matricula.matches("\\d{4}[A-Z]{3}");
    }

    /**
     * Método para limpiar todos los campos después de un registro exitoso
     */
    private void limpiarCampos() {
        input_matricula_registroCoche.clear();
        input_marca_registroCoche.clear();
        input_modelo_registroCoche.clear();
        input_color_registroCoche.clear();
        input_estado_registroCoche.clear();

        cmb_HorasDesde_RegistroCoche.setValue(null);
        cmb_MinDesde_RegistroCoche.setValue(null);
        cmb_HorasHasta_RegistroCoche.setValue(null);
        cmb_MinHasta_RegistroCoche.setValue(null);

        lbl_validacionNombre.setText("");
        lbl_validacionDireccion.setText("");
        lbl_validacionLocalidad.setText("");
        lbl_validacionCP.setText("");
        lbl_validacionCapacidad.setText("");
    }

    /**
     * Método para volver a la pantalla anterior
     */
    private void volverAtras() {
        // Aquí implementarías la lógica para volver a la pantalla anterior
        Stage stage = (Stage) botonAtras_RegistroParkings.getScene().getWindow();
        // Puedes cargar otra escena aquí o cerrar la ventana
        // stage.setScene(nuevaEscena);
        // O simplemente cerrar la ventana:
        // stage.close();

        System.out.println("Volviendo a la pantalla anterior...");
    }

    /**
     * Este método carga las horas para los comboBox (00-23)
     * @return Una ObservableList con las horas formateadas
     */
    private ObservableList<String> cargarHoras() {
        DecimalFormat df = new DecimalFormat("00");
        String numeroFormateado;
        ObservableList<String> horas = FXCollections.observableArrayList();

        for (int i = 0; i < 24; i++) {
            numeroFormateado = df.format(i);
            horas.add(numeroFormateado);
        }

        return horas;
    }

    /**
     * Este método carga los minutos para los comboBox (00-59)
     * @return Una ObservableList con los minutos formateados
     */
    private ObservableList<String> cargarMinutos() {
        DecimalFormat df = new DecimalFormat("00");
        String numeroFormateado;
        ObservableList<String> minutos = FXCollections.observableArrayList();

        for (int i = 0; i < 60; i++) {
            numeroFormateado = df.format(i);
            minutos.add(numeroFormateado);
        }

        return minutos;
    }
}