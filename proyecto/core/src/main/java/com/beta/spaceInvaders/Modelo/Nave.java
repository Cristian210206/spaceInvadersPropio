package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Nave extends ObjetoVolador {
    public Nave(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
    }

    public boolean comprobarColisiones(List<Disparo> disparosEnemigos) {
        for (Disparo disparo: disparosEnemigos) {
            if(disparo.getPosicionX() == this.getPosicionX() && disparo.getPosicionY() == this.getPosicionY()) {
                disparosEnemigos.remove(disparo);
                return true;
            }
        }
        return false;
    }

    public void disparar(List<Disparo> listaDisparos, Texture imagenDisparo) {
        Disparo disparo = new Disparo(super.getPosicionX(),super.getPosicionY(),5,25,0,5,true,imagenDisparo);
        listaDisparos.add(disparo);
    }
}
