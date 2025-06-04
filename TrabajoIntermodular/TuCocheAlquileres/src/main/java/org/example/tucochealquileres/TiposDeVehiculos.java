package org.example.tucochealquileres;


/**
 * Clase que utilizaremos para mapear los datos de la tabla TiposVehiculos de nuestro modelo
 */
public class TiposDeVehiculos {

    private int idTipoVehiculo;
    private String tipo;
    private String descripcion;
    private Double tarifaAdicional;

    /**
     * Constructor que nos sirve para recuperar los datos de la tabla TiposVehiculos
     * @param idTipoVehiculo int con la id del tipo
     * @param tipo String que identifica el tipo
     * @param descripcion String que es una pequeña descripción del tipo de vehículo
     * @param tarifaAdicional Double que nos sirve para marcar el precio de alquiler del vehículo
     */
    public TiposDeVehiculos(int idTipoVehiculo, String tipo, String descripcion, Double tarifaAdicional) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.tarifaAdicional = tarifaAdicional;
    }

    /**
     * Constructor que nos sirve para hacer los inserts sin el atributo id, ya que en la tabla del modelo es
     * autoincrement
     * @param tipo String con el tipo indicado que coincidira con el enum definido en la tabla
     * @param descripcion String con una breve descripción del tipo
     * @param tarifaAdicional Double con el que se indica el precio diario de alquiler de un vehículo
     */
    public TiposDeVehiculos(String tipo, String descripcion, Double tarifaAdicional) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.tarifaAdicional = tarifaAdicional;
    }

    /**
     * Getter de la id del tipo de vehículo
     * @return un int con el valor de la id del tipo
     */
    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    /**
     * Getter del tipo de vehículo
     * @return un String que nos indica el tipo de vehículo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Getter que nos devuelve la descripción del tipo de vehículo
     * @return un String que nos indica la descripcion del tipo
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Getter del precio de alquiler diario de un vehículo
     * @return un double que es el precio de alquiler diario de un vehículo
     */
    public Double getTarifaAdicional() {
        return tarifaAdicional;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

//Creo que para esta clase sólo tengo que definir un setter, el cual corresponde al precio de alquiler diario

    /**
     * Setter del precio diario de alquiler de un vehículo
     * @param tarifaAdicional recibe como parámetro un double que es el nuevo valor del precio de alquiler diario
     */
    public void setTarifaAdicional(Double tarifaAdicional) {
        this.tarifaAdicional = tarifaAdicional;
    }
}
