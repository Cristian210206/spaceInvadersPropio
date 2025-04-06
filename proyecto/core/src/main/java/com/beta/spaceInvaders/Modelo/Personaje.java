package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Personaje extends Nave {
    private List<Vida> vidas;

    public Personaje(int posicionX, int posicionY, int tamanioX, int tamanioY, int velocidadX, int velocidadY, boolean activo, Texture imagen, int vidas) {
        super(posicionX, posicionY, tamanioX, tamanioY, velocidadX, velocidadY, activo, imagen);
        this.vidas = new ArrayList<>();
        for (int i = 0; i<vidas;i++) {
            this.vidas.add(new Vida());
        }
    }

    public void comprobarPerderVida(List<Disparo> disparosEnemigos) {
        if (comprobarColisiones(disparosEnemigos)) {
            vidas.remove(vidas.getLast());
        }
    }

    public int getVidas() {
        int contador=0;
        for (Vida v : vidas) contador++;
        return contador;
    }

    public void pintarVidas(SpriteBatch batch,Texture img, int posX, int posY, int tamanioX, int tamanioY, int margen) {
        int margenes = 0;
        for (Vida vida : vidas) {
            vida.pintarse(batch,img,posX+margenes,posY,tamanioX,tamanioY);
            margenes += tamanioX+margen; //Calcula constantemente donde se coloca la siguiente vida
        }
    }
}
