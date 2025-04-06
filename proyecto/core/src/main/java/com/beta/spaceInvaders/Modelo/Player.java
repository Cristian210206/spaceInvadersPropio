package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Player extends Personaje {
    private String nombre;
    private int puntuacion;
    public static List<Disparo> disparos;

    public Player(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen, int vidas, String nombre) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen, vidas);
        this.nombre = nombre;
        this.puntuacion = 0;
        this.disparos = new ArrayList<>();
    }

    public void aumentarPuntuacion(int cantidad) {
        this.puntuacion+=cantidad;
    }

    public void disparar(Texture imagenDisparo) {
        Disparo disparo = new Disparo(super.getPosicionX(),super.getPosicionY()+super.getTamanioY(),5,10,0,1,true,imagenDisparo);
        disparos.add(disparo);
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}
