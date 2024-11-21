public enum TipoMovimiento {
    INGRESO ("Ingreso"), RETIRADA ("Reintegro");
    private String tipo;

    private TipoMovimiento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
