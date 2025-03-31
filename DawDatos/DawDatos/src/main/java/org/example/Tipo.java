package org.example;

public class Tipo {

    private int tip0Id;
    private Tipos tipo;

    public Tipo(int tip0Id, Tipos tipo) {
        this.tip0Id = tip0Id;
        this.tipo = tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }

    public int getTip0Id() {
        return tip0Id;
    }

    public Tipos getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "ID TIPO: " + this.getTip0Id() + " TIPO: " + this.getTipo();
    }
}
