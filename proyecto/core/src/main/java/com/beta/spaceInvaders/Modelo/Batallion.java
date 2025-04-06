package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Batallion {
    private Squadron[] squadrones;
    public static List<Disparo> disparos;
    public static final int NUMERO_ESCUADRONES = 3;

    public Batallion() {
        this.squadrones = new Squadron[NUMERO_ESCUADRONES];
        disparos = new ArrayList<>();
    }

    public void crearBattallon(Squadron squadron, Enemigo[] enemigos_Iniciales) {
        squadrones[0] = squadron;
        for (int i = 1;i<squadrones.length;i++) {
            squadrones[i] = new Squadron(enemigos_Iniciales[i-1]);
        }
    }

    public void pintarBatallon(SpriteBatch batch) {
        if (squadrones != null) {
            for (Squadron squadron: squadrones) {
                squadron.pintarSquadron(batch);
            }
        } else {
            throw new Error("No se ha creado el batallon");
        }
    }

    public void dispararSquadron(Texture imgDisparo) {
        for(Squadron squadron: squadrones) {
            squadron.dispararEnemigos(imgDisparo, disparos);
        }
    }

    public boolean moverSquadron(ObjetoVolador.Direccion direccion, int limite_X) {
        boolean puedeMoverse = true;
        for (Squadron squadron: squadrones) {
            puedeMoverse = squadron.moverEnemigos(direccion, limite_X);
        }
        return puedeMoverse;
    }

    public void colisionesEnemigos() {
        for(Squadron squadron: squadrones) {
            squadron.morirEnemigo();
        }
    }
}
