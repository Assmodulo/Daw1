package org.example.tucochealquileres;

/**
 * Enum que nos va a almacenar las diferentes opciones en las que un vehículo puede encontrarse dentro de nuestro modelo
 * de gestión y los valores que puede tomar en los registros dentro de la base de datos.
 * Este enum en principio no se va a usar al dar de alta, ya que existe un estado por defecto, pero si al modificar el estado
 * de un vehículo
 */
public enum EstadoVehiculos {

    DISPONIBLE("Disponible"), ALQUILADO("Alquilado"), MANTENIMIENTO("Mantenimiento"), BAJA("Baja");

    /**
     * Variable privada que se va a usar para poder seleccionar el String asociado a cada elemento del enum y que se
     * corresponde con los estados posibles definidos en nuestro modelo
     */
    private String estadoVehiculo;

    /**
     * Constructor privado de este tipo de enum
     * @param estadoVehiculo Recibe un String que luego es el que se va a utilizar y pasar a nuestro modelo
     */
    private EstadoVehiculos(String estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    /**
     * Método getter para poder recuperar el valor asociado a cada elemento del enum
     * @return Un String que luego puede ser almacenado en las variables de clase con las que hemos mapeado el modelo dentro
     * de la lógica de la aplicación.
     */
    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }
}
