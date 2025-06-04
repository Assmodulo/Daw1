package com.coches.tucochealquiler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


import java.net.URL;
import java.util.ResourceBundle;

public class ViewPController implements Initializable {

    @FXML
    private ListView<Parkings> listview_parkings;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Parkings> parkings = FXCollections.observableArrayList();

        SQLAccess sqlAccess = new SQLAccess();

        parkings = sqlAccess.getListaParkings();

        listview_parkings.setItems(parkings);

    }

    PrincipalController principalController;

    private BorderPane panelPrincipal;

    public void setPanelPrincipal(BorderPane panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }


}
