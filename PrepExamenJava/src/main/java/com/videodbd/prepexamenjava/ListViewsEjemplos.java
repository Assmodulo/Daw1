package com.videodbd.prepexamenjava;

public class ListViewsEjemplos {

    //Creo una observable
    ObservableList<Persona> personas = FXCollections.observableArrayList();
    //Añado elementos a la observable. Se pueden añadir al obtener registros de la BD
    personas.add();
    personas.add();
    //Se añade el contenido de la observable a la listview.
    listView.setItems(personas);

    //Ejemplo que hizo Anuar
    List<Persona> people = loadFilePeople("export_Personas.dat");
        personas.addAll(people);
        personListView.setItems(personas);

    //Podemos hacer que una variable de una clase creada por mi obtenga el valor de un elemento seleccionado en la listview
    private void eliminarSeleccionado(ActionEvent event) {
        MiClase seleccionado = listView.getSelectionModel().getSelectedItem();

    }

    //Puedo bindear las opcienes de disable y de visibility de un botón para eliminar o añadir con la propiedad de que
    //haya un elemento seleccionado en la listview
    eliminarButton.disableProperty().bind(
            listView.getSelectionModel().selectedItemProperty().isNull()
);
}
