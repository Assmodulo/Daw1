package org.example.tucochealquileres;

/**
 * Se crea esta clase para seleccionar los tipos de coches, ya que también se ha definido como un enum en el modelo
 * de la base de datos. Simplemente hay que asegurarse de que el texto de los mismos sea igual.
 */
public enum TiposCoches {

    COMPACTO("Compacto"),BERLINA("Berlina"),SUV("SUV"),TODOTERRENO("Todoterreno"), CAMIONETA("Camioneta");

    /**
     * Variable del tipo String para almacenar el valor asociado a cada tipo de coche
     */
    private String tipoCoche;

    /**
     * Constructor privado de este enum.
     * @param tipoCoche String que describe el tipo de coche asociado a cada elemento del enum
     */
    private TiposCoches(String tipoCoche) {
        this.tipoCoche = tipoCoche;
    }

    /**
     * Metodo getter para obtener el valor de cada elemento del tipo de coche. Luego ese String se puede pasar a la base
     * de datos, al campo correspondiente a tipo de coche.
     * @return Un String con el valor almacenado de cada elemento del enum.
     */
    public String getTipoCoche() {

        return tipoCoche;
    }
}
