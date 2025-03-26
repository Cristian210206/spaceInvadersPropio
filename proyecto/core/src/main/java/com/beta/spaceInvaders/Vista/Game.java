package com.beta.spaceInvaders.Vista;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.beta.spaceInvaders.Modelo.Batallion;
import com.beta.spaceInvaders.Modelo.Enemigo;
import com.beta.spaceInvaders.Modelo.ObjetoVolador;
import com.beta.spaceInvaders.Modelo.Player;
import com.beta.spaceInvaders.Modelo.Squadron;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture imgJugador;
    private Texture imgEnemigo;
    private Texture imgDisparo;
    private Player jugador;
    private Batallion batallon;
    private static final int TAMANIO_NAVE_X = 50;
    private static final int TAMANIO_NAVE_Y = 50;
    private static final int VELOCIDAD = 1;
    private static int TAMANIO_PANTALLA_X;
    private static int TAMANIO_PANTALLA_Y;
    private static Enemigo enemigo_inicial;
    private static Enemigo[] enemigosSegundoYTercerSquadron;
    private ObjetoVolador.Direccion direccion_Batallon = ObjetoVolador.Direccion.DERECHA;

    @Override
    public void create() {
        batch = new SpriteBatch();
        imgJugador = new Texture("Player.png");
        imgEnemigo = new Texture("Enemigo.png");
        imgDisparo = new Texture("Disparo.png");
        TAMANIO_PANTALLA_X = Gdx.graphics.getWidth();
        TAMANIO_PANTALLA_Y = Gdx.graphics.getHeight();
        enemigosSegundoYTercerSquadron = new Enemigo[2];
        enemigo_inicial = new Enemigo(TAMANIO_NAVE_X, TAMANIO_PANTALLA_Y-(TAMANIO_NAVE_Y+50),TAMANIO_NAVE_X,TAMANIO_NAVE_Y,VELOCIDAD,0,true,imgEnemigo);
        enemigosSegundoYTercerSquadron[0] = new Enemigo(enemigo_inicial.getPosicionX(),enemigo_inicial.getPosicionY()-60,enemigo_inicial.getTamanioX(),enemigo_inicial.getTamanioY(),enemigo_inicial.getVelocidadX(),enemigo_inicial.getVelocidadY(),enemigo_inicial.getActivo(),imgEnemigo);
        enemigosSegundoYTercerSquadron[1] = new Enemigo(enemigo_inicial.getPosicionX(),enemigosSegundoYTercerSquadron[0].getPosicionY()-60,enemigo_inicial.getTamanioX(),enemigo_inicial.getTamanioY(),enemigo_inicial.getVelocidadX(),enemigo_inicial.getVelocidadY(),enemigo_inicial.getActivo(),imgEnemigo);
        batallon = new Batallion();
        batallon.crearBattallon(new Squadron(enemigo_inicial),enemigosSegundoYTercerSquadron);
        jugador = new Player((TAMANIO_PANTALLA_X/2)-(TAMANIO_NAVE_X-2),0,TAMANIO_NAVE_X,TAMANIO_NAVE_Y,VELOCIDAD,0,true,imgJugador,3,"Equipo Beta");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batallon.pintarBatallon(batch);
        jugador.pintarse(batch);
        if(batallon.moverSquadron(direccion_Batallon,TAMANIO_PANTALLA_X)) {
            direccion_Batallon = ObjetoVolador.Direccion.IZQUIERDA;
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
