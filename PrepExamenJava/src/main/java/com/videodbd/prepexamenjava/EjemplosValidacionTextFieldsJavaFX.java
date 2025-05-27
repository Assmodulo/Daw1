package com.videodbd.prepexamenjava;

public class EjemplosValidacionTextFieldsJavaFX {

    //Con estos ejemplos sacados del trabajo en grupo creo que me vale para saber como funciona
    //Si los metodos que validan los patrones lanzan excepción creo recordar que el setPromptText se puede actualizar
    //en el catch, de la misma forma que el setText.

    input_nombre_registroParkings.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if(!newValue){
            if(!validacionesGenerales.validarVarchar50(input_nombre_registroParkings.getText())){
                input_nombre_registroParkings.setText("");
                input_nombre_registroParkings.setPromptText("Longitud del nombre incorrecta");
            }else{
                setNombre(input_nombre_registroParkings.getText());
            }
        }
    });

        input_direccion_registroParkings.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if(!newValue){
            if(!validacionesGenerales.validarVarchar100(input_direccion_registroParkings.getText())){
                input_direccion_registroParkings.setText("");
                input_direccion_registroParkings.setPromptText("Longitud del direccion incorrecta");
            }else{
                setDireccion(input_direccion_registroParkings.getText());
            }
        }
    });

}
