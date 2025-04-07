package org.example;

public class Tipo {

    private int tipoId;
    private String tipo;

    public Tipo(int tip0Id, String tipo) {
        this.tipoId = tip0Id;
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTipoId() {
        return tipoId;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "ID TIPO: " + this.getTipoId() + " TIPO: " + this.getTipo();
    }
}
