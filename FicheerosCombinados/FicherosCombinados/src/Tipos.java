public enum Tipos {

    ALIMENTACION("Alimentación"), OFICINA("Oficina"), HOGAR("Hogar"), CONSTRUCCION("Construccion");


    private String tipo;

    private Tipos(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
