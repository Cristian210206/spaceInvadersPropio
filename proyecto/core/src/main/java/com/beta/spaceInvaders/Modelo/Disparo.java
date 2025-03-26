package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

public class Disparo extends ObjetoVolador {
    public Disparo(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
    }
}
