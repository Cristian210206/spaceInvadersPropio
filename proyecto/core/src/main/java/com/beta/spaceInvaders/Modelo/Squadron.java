package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.beta.spaceInvaders.Vista.Game;

import java.util.List;

public class Squadron {
    private Enemigo[] enemigos;
    public static final int NUMERO_ENEMIGOS = 8;
    private static final int MARGENES_ENEMIGOS = 70;

    public Squadron(Enemigo enemigo) {
        enemigos = new Enemigo[NUMERO_ENEMIGOS];
        crearSquadron(enemigo);
    }

    private void crearSquadron(Enemigo enemigo) {
        enemigos[0] = enemigo;
        for (int i = 1;i<enemigos.length;i++) {
            enemigos[i] = new Enemigo(enemigos[i-1].getPosicionX()+MARGENES_ENEMIGOS,enemigos[i-1].getPosicionY(),enemigos[i-1].getTamanioX(), enemigos[i-1].getTamanioY(),enemigos[i-1].getVelocidadX(),enemigos[i-1].getVelocidadY(),true,enemigos[i-1].getImagen());
        }
    }

    public void pintarSquadron(SpriteBatch batch) {
        if (enemigos != null) {
            for(Enemigo enemigo: enemigos) {
                if(enemigo.getActivo()) {
                    enemigo.pintarse(batch);
                }
            }
        } else {
            throw new Error("No se ha creado el escuadron");
        }
    }

    public boolean moverEnemigos(ObjetoVolador.Direccion direccion, int limite_X) {
        if (direccion == ObjetoVolador.Direccion.IZQUIERDA) {
            for(Enemigo enemigo: enemigos) {
                if(!enemigo.moverse(enemigo.getVelocidadX(),direccion,limite_X)) {
                    return false; // Corta el bucle en caso de que el de la izquierda del todo no pueda moverse
                };
            }
        } else if (direccion == ObjetoVolador.Direccion.DERECHA) {
            for (int i= enemigos.length-1; i >= 0; i--) {
                if(!enemigos[i].moverse(enemigos[i].getVelocidadX(),direccion,limite_X)) {
                    return false; // Corta el bucle en caso de que el de la derecha del todo no pueda moverse
                }
            }
        }
        return true;
    }

    public void dispararEnemigos(Texture imagenDisparo, List<Disparo> disparos) {
        for(Enemigo enemigo: enemigos) {
            if (enemigo.getActivo() && Math.random() > 0.9995) {
                enemigo.disparar(imagenDisparo, disparos);
            }
        }
    }

    public void morirEnemigo() {
        for (Enemigo enemigo: enemigos) {
            if (enemigo.getActivo() && enemigo.comprobarColisiones(Player.disparos)) {
                enemigo.setActivo(false);
                Game.NUMERO_ENEMIGOS--;
            }
        }
    }
}
