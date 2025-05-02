package com.videodbd.videodawinterfaz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HelloController {

    @FXML
    private TabPane tabPanePrincipal;

    @FXML
    private Button btnAltaVideoClub;

    @FXML
    private VBox menuLateralVideoclub;

    @FXML
    private AnchorPane paneAltaVideoClub;

    @FXML
    private Pane paneLogin;

    @FXML
    private TextField tfCodVideoCAlta;

    @FXML
    private TextField tfNombreVideoClubAlta;

    @FXML
    private TextField tfDireccionVideoClubAlta;

    @FXML
    private TextField tfTelefonoVideoClubAlta;

    @FXML
    private Button btnGuardarVC;

    @FXML
    protected void onAltaVideoClubAction(ActionEvent event){
        paneLogin.setVisible(true);
        tfCodVideoCAlta.requestFocus();
    }

    @FXML
    protected void onGuardarDatosVCAction(){
        String cif = tfCodVideoCAlta.getText();
        String nombre = tfNombreVideoClubAlta.getText();
        String direccion = tfDireccionVideoClubAlta.getText();
        String telefono = tfTelefonoVideoClubAlta.getText();

        try {
            MyUtils.validarFormatoCif(cif);
            MyUtils.validarFormatosNombDirVC(nombre);
            MyUtils.validarFormatosNombDirVC(direccion);
            MyUtils.validarFormatoTelefonoVC(telefono);
            MyUtils.pasarDatosParaGuardarBD(cif, nombre, direccion,telefono);

        } catch (ErrorFormatoCifException e) {
            tfCodVideoCAlta.setText("");
            tfCodVideoCAlta.setPromptText(e.getMessage());
        }catch(ErrorFormatoTelefonoException e){
            tfTelefonoVideoClubAlta.setText("");
            tfTelefonoVideoClubAlta.setPromptText(e.getMessage());
        }catch (ErrorFormatoNomDirException e){
            tfDireccionVideoClubAlta.setText("");
            tfNombreVideoClubAlta.setText("");
            tfNombreVideoClubAlta.setPromptText(e.getMessage());
            tfDireccionVideoClubAlta.setPromptText(e.getMessage());
        }

    }

}