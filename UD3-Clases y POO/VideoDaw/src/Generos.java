public enum Generos {
    ACCION("ACCION"), TERROR("TERROR"), DRAMA("DRAMA"), SUSPENSE("SUSPENSE"),
    ROMANTICA("ROMANTICA"), COMEDIA("COMEDIA"), AVENTURAS("AVENTURAS"), INFANTIL("INFANTIL");

    private String genero;

    private Generos(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
