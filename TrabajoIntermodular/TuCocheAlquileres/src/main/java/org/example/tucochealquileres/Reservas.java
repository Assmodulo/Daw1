package org.example.tucochealquileres;

import java.time.LocalDate;

public class Reservas {
    private int idREserva;
    private int idCliente;
    private int idVehiculo;
    private int idParking;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Double costeTotal;

    /**
     * Este es elconstructor completo, que nos servirá para poder recuperar todos los datos de esta tabla y almacenarlos
     * en una colección
     * @param idREserva Int que indica el código de la reserva. Es un autoincrement en SQL. No siempre lo necesitaremos en
     *                  el constructor
     * @param idCliente Int que coincide con el id de un cliente
     * @param idVehiculo Int que coincide con el id de un vehículo
     * @param idParking Int que coincide con el id del parking donde está ese vehículo
     * @param fechaInicio LocalDate que nos marca la fecha de inicio de una reserva
     * @param fechaFin LocalDate que nos marca la fecha de fin de una reserva
     * @param estado String que seleccionaremos de un enum que nos indica el estado de una reserva
     * @param costeTotal Double que nos indica el importe total de una reserva
     */

    public Reservas(int idREserva, int idCliente, int idVehiculo, int idParking, LocalDate fechaInicio, LocalDate fechaFin, String estado, Double costeTotal) {
        this.idREserva = idREserva;
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.idParking = idParking;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.costeTotal = costeTotal;
    }

    /**
     * Este es un constructor parcial, para realizar inserts en la tabla, pero para una reserva en la que no se indica la fecha de fin de
     * la misma y, por lo tanto, tampoco se podrá calcular el importe de la propia reserva
     * @param idCliente Int que es la id del cliente
     * @param idVehiculo Int que es la id del vehículo
     * @param idParking Int que es la id del parking
     * @param fechaInicio LocalDate que nos indica la fecha de inicio de una reserva
     * @param estado String que nos indica el estado de la propia reserva
     */
    public Reservas(int idCliente, int idVehiculo, int idParking, LocalDate fechaInicio, String estado) {
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.idParking = idParking;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
    }


    /**
     * Este es otro constructor parcial, en el se indica todo menos el coste, que creo que tendremos que buscar una forma de
     * calcularlo automaticamente
     * @param idCliente Int que es la id del cliente
     * @param idVehiculo Int que es la id del vehículo
     * @param idParking Int que es la id del parking
     * @param fechaInicio LocalDate que nos marca la fecha de inicio de la reserva
     * @param fechaFin LocalDate que nos marca la fecha de fin de la reserva
     * @param estado String que nos indica el estado de la propia reserva
     */
    public Reservas(int idCliente, int idVehiculo, int idParking, LocalDate fechaInicio, LocalDate fechaFin, String estado) {
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.idParking = idParking;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    /**
     * Método para obtener la id de una reserva
     * @return un int que es la id de la reserva
     */
    public int getIdREserva() {
        return idREserva;
    }

    /**
     * Método para obtener la id de el cliente que hace la reserva
     * @return un int que coincide con la id del cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Método para obtener la id del vehículo reservado
     * @return int que es la id de el vehículo
     */
    public int getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * Método con el que obtenemos la id del parking donde estaba el coche que se ha reservado
     * @return int que coincide con la id de un parking
     */
    public int getIdParking() {
        return idParking;
    }

    /**
     * Método para obtener la fecha de inicio de una reserva
     * @return Un LocalDate que nos indica la fecha de comienzo de una reserva
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Método para obtener la fecha de fin de una reserva
     * @return Un LocalDate que nos indica la fecha de fin de una reserva
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Método con el que podemos obtener el estado de una reserva
     * @return String que nos indica el estado de la reserva
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método para obtener el precio total de una reserva
     * @return Double que nos indica el precio total de una reserva. Habrá que estar atentos por si hay que formatearlo
     * para que solo tenga dos decimales siempre
     */
    public Double getCosteTotal() {
        return costeTotal;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCosteTotal(Double costeTotal) {
        this.costeTotal = costeTotal;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public void setIdREserva(int idREserva) {
        this.idREserva = idREserva;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    /**
     * En principio este método nos devuleve un toString de la clase Reservas pero formateado para que salga de una forma
     * determinada y sea algo más adecuado para una list view. También evalua el valor de fechaFin para variar su valor en
     * función de si fechaFin es null o no.
     * @return Un String con un formato determinado
     */





    @Override
    public String toString() {

        String fFinReserva = "";
        if(this.fechaFin == null){
            fFinReserva = "Sin Fecha";
        }else{
            fFinReserva = FormateadoresDeFechas.formatearFecha(this.fechaFin);
        }

        return String.format(" %05d,%05d,%05d,%05d,%-10S,%-10S,%-20S,%-10.2f",this.idREserva, this.idCliente, this.idVehiculo,
        this.idParking, FormateadoresDeFechas.formatearFecha(this.fechaInicio),fFinReserva,
                this.estado, this.costeTotal);
    }

}
