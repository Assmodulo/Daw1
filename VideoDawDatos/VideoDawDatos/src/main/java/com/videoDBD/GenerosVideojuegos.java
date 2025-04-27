package com.videoDBD;

public enum GenerosVideojuegos {

    FPS("Fps"), RPG("Rpg"), ARPG("Arpg"), ESTRATEGIA("Estrategia"), DEPORTES("Deportes"), LUCHA("Lucha"), RETRO("Retro");

    private String generoV;

    private GenerosVideojuegos(String generoV){
        this.generoV = generoV;
    }

    public String getGeneroV(){
        return this.generoV;
    }
}
