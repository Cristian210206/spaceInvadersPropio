package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Batallion {
    private Squadron[] squadrones;

    public Batallion() {
        this.squadrones = new Squadron[3];
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

    public boolean moverSquadron(ObjetoVolador.Direccion direccion, int limite_X) {
        if (squadrones != null) {
            for (Squadron squadron: squadrones) {
                if(!squadron.moverEnemigos(direccion, limite_X)) {
                    return false;
                }
            }
            return true;
        } else {
            throw new Error("No se ha creado el batallon");
        }
    }
}
