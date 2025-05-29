package org.example.vetdawexam;

public class TipoMascota {

    private int idTipo;
    private String tipo;

    public TipoMascota(int idTipo, String tipo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
    }


    public int getIdTipo() {
        return idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return String.format("%-2d | %-20S", this.idTipo, this.tipo);
    }
}
