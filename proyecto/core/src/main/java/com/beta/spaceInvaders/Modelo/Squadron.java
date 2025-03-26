package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Squadron {
    private Enemigo[] enemigos;
    private static final int MARGENES_ENEMIGOS = 70;

    public Squadron(Enemigo enemigo) {
        enemigos = new Enemigo[8];
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
                enemigo.pintarse(batch);
            }
        } else {
            throw new Error("No se ha creado el escuadron");
        }
    }

    public boolean moverEnemigos(ObjetoVolador.Direccion direccion, int limite_X) {
        if (enemigos != null) {
            if (direccion == ObjetoVolador.Direccion.IZQUIERDA) {
                for(Enemigo enemigo: enemigos) {
                    if(!enemigo.moverse(enemigo.getVelocidadX(),direccion,limite_X) && enemigo.getActivo()) {
                        return false;
                    };
                }
            } else if (direccion == ObjetoVolador.Direccion.DERECHA) {
                for (int i= enemigos.length-1; i >= 0; i--) {
                    if(!enemigos[i].moverse(enemigos[i].getVelocidadX(),direccion,limite_X) && enemigos[i].getActivo()) {
                        return false;
                    }
                }
            }
        } else {
            throw new Error("No se ha creado el escuadron");
        }
        return false;
    }

    public Enemigo[] getSquadron() {
        Enemigo[] copiaSquadron = new Enemigo[enemigos.length];
        for(int i = 0; i< copiaSquadron.length; i++) {
            copiaSquadron[i] = enemigos[i];
        }
        return copiaSquadron;
    }
}
