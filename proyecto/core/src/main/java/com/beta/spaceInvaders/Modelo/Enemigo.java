package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Enemigo extends Nave {
    List<Disparo> disparos;
    public Enemigo(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
        disparos = new ArrayList<>();
    }
}