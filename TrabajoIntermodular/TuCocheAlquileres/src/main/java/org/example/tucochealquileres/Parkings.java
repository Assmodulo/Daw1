package org.example.tucochealquileres;


import java.time.LocalDateTime;
import java.time.LocalTime;


public class Parkings {
    private int parking_id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private int capacidad;
    private int plazasDisponibles;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private Boolean activo;


    public Parkings(){

    };

    /**
     * Constructor de un parking. Nos sirve lo mismo para pasar los objetos al modelo o para obtenerlos a partir de el
     * @param nombre String con el nombre del parking
     * @param direccion String con la direccion del parking
     * @param ciudad String con la ciudad en la que se encuentra el parking
     * @param codigoPostal String con el código postal de la ciudad
     * @param capacidad Int con la capacidad total de coches que tiene el parking
     * @param plazasDisponibles int con la plazas que todavia tiene disponible el parking
     * @param horaApertura LocalTime que nos indica la hora de apertura del parking
     * @param horaCierre LocalTime que nos indica la hora de cierre del parking
     * @param activo Boolean que nos indica si el parking está activo o si por alguna razón se ha cerrado
     */
    public Parkings(int parking_id,String nombre, String direccion, String ciudad, String codigoPostal, int capacidad, int plazasDisponibles,
                    LocalTime horaApertura, LocalTime horaCierre, Boolean activo) {
        this.parking_id = parking_id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.capacidad = capacidad;
        this.plazasDisponibles = plazasDisponibles;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.activo = activo;
    }

    /*
    Creo los setters de los únicos atributos que creo que se podrían cambiar
     */

    /**
     * Setter de la hora de apertura, por si se quiere cambiar el horario del mismo
     * @param horaApertura Un LocalTime que indica el horario de apertura
     */
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    /**
     * Setter de la hora de cierre, por si se quiere cambiar el horario del mismo
     * @param horaCierre Un LocalTime que indica el horario de apertura
     */
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    /**
     * Setter de la actividad o no de un parking. Es un valor que podría cambiar
     * @param activo Un Boolean que nos indica si el parking está activo o no
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /*
    A partir de aquí defino los getters
     */

    /**
     * Getter del nombre de un parking
     * @return Un String que es el nombre de un parking
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de la direccion de un parking
     * @return Un String que es la dirección de un parking
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Getter de la ciudad de un parking
     * @return Un String con el nombre de la ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Getter del codigo postal de un parking
     * @return Un String con el codigo postal de un parking
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Getter de la capacidad de un parking
     * @return un Int con la capacidad de un parking
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Getter de las plazas disponibles de un parking
     * @return un int con las plazas restantes disponibles de un parking
     */
    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    /**
     * Getter de la hora de apertura del parking en cuestion
     * @return Un objeto LocalTime con el horario de apertura
     */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    /**
     * Getter de la hora de cierre del parking en cuestión
     * @return Un objeto LocalTime con el horario de cierre
     */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    /**
     * Getter del estado de actividad de un parking
     * @return un Boolean que nos indica el estado de actividad de un parking
     */
    public Boolean getActivo() {
        return activo;
    }

    public void setPlazasDisponibles(int plazasDisponibles) {
        this.plazasDisponibles = plazasDisponibles;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setParking_id(int parking_id) {
        this.parking_id = parking_id;
    }

    public int getParking_id() {
        return parking_id;
    }
}
