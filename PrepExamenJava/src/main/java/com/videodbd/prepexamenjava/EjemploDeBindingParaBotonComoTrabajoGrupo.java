package com.videodbd.prepexamenjava;

public class EjemploDeBindingParaBotonComoTrabajoGrupo {

    //Con este comando, es por poner un ejemplo, se puede bindear la visibilidad de un botón con las propiedades de tener
    //valor los otros campo o de que algo haya sido seleccionado en los combobox

    botonGuardar_RegistroParking.visibleProperty().bind(input_nombre_registroParkings.textProperty().isNotEmpty().
    and(input_direccion_registroParkings.textProperty().isNotEmpty().
    and(input_localidad_registroParkings.textProperty().isNotEmpty().
    and(input_cp_registroParkings.textProperty().isNotEmpty().
    and(input_capacidad_registroParkings.textProperty().isNotEmpty().
    and(cmb_HorasDesde_RegistroParking.valueProperty().isNotNull()).
    and(cmb_MinDesde_RegistroParking.valueProperty().isNotNull()).
    and(cmb_HorasHasta_RegistroParking.valueProperty().isNotNull())).
    and(cmb_MinHasta_RegistroParking.valueProperty().isNotNull())))));
}
