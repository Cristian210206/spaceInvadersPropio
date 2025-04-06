package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Enemigo extends Nave {
    public Enemigo(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
    }

    public void disparar(Texture imagenDisparo, List<Disparo> disparos) {
        Disparo disparo = new Disparo(super.getPosicionX(),super.getPosicionY(),5,10,0,1,true,imagenDisparo);
        disparos.add(disparo);
    }
}