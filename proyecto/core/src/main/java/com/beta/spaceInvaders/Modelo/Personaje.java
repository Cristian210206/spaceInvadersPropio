package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Personaje extends Nave {
    private int vidas;

    public Personaje(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen, int vidas) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
        this.vidas = vidas;
    }

    public void perderVida(List<Disparo> disparosEnemigos) {
        if (this.vidas > 0) {
            if (comprobarColisiones(disparosEnemigos)) {
                this.vidas--;
            }
        }
    }

    public int getVidas() {
        return vidas;
    }
}
