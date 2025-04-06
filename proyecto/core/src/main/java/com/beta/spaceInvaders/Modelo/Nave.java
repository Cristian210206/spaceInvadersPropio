package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Nave extends ObjetoVolador {
    public Nave(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
    }

    public boolean comprobarColisiones(List<Disparo> disparosEnemigos) {
        for (Disparo disparo: disparosEnemigos) {
            if(disparo.getPosicionX() >= this.getPosicionX() && disparo.getPosicionX() <= this.getPosicionX()+getTamanioX() && disparo.getPosicionY() >= this.getPosicionY() && disparo.getPosicionY() <= this.getPosicionY()+getTamanioY()) {
                disparosEnemigos.remove(disparo);
                return true;
            }
        }
        return false;
    }
}
