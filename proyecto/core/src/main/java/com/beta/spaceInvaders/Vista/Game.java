package com.beta.spaceInvaders.Vista;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.beta.spaceInvaders.Modelo.Batallion;
import com.beta.spaceInvaders.Modelo.Disparo;
import com.beta.spaceInvaders.Modelo.Enemigo;
import com.beta.spaceInvaders.Modelo.ObjetoVolador;
import com.beta.spaceInvaders.Modelo.Player;
import com.beta.spaceInvaders.Modelo.Squadron;

import org.w3c.dom.Text;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture imgJugador;
    private Texture imgEnemigo;
    private Texture imgDisparoJugador;
    private Texture imgDisparoEnemigo;
    private Texture imgVictoria;
    private Texture imgDerrota;
    private Texture imgInicio;
    private Texture imgFondo;
    private Texture imgVida;
    private Player jugador;
    private Batallion batallon;
    private static final int TAMANIO_NAVE_X = 50;
    private static final int TAMANIO_NAVE_Y = 50;
    private static final int VELOCIDAD_JUGADOR = 2;
    private static final int VELOCIDAD_ENEMIGOS = 1;
    private static int TAMANIO_PANTALLA_X;
    private static int TAMANIO_PANTALLA_Y;
    private static final int TAMANIO_VIDA = 40;
    private static final int MARGENES_VIDAS = 10;
    private static Enemigo enemigo_inicial;
    private static Enemigo[] enemigosSegundoYTercerSquadron;
    private ObjetoVolador.Direccion direccion_Batallon = ObjetoVolador.Direccion.DERECHA;
    private ObjetoVolador.Direccion direccion_Jugador = ObjetoVolador.Direccion.QUIETO;
    private enum EstadoPartida {INICIO, JUGANDO, FINALMALO,FINALBUENO}
    private EstadoPartida estado = EstadoPartida.INICIO;
    public static int NUMERO_ENEMIGOS = Batallion.NUMERO_ESCUADRONES*Squadron.NUMERO_ENEMIGOS;

    @Override
    public void create() {
        batch = new SpriteBatch();
        imgJugador = new Texture("Player.png");
        imgEnemigo = new Texture("Enemigo.png");
        imgDisparoJugador = new Texture("disparoAliado.jpg");
        imgDisparoEnemigo = new Texture("disparoEnemigo.jpg");
        imgVictoria = new Texture("Victoria.jpg");
        imgDerrota = new Texture("GameOver.jpg");
        imgInicio = new Texture("Inicio.png");
        imgFondo = new Texture("fondo.jpeg");
        imgVida = new Texture("vida.png");
        TAMANIO_PANTALLA_X = Gdx.graphics.getWidth();
        TAMANIO_PANTALLA_Y = Gdx.graphics.getHeight();
        enemigosSegundoYTercerSquadron = new Enemigo[2];
        enemigo_inicial = new Enemigo(TAMANIO_NAVE_X, TAMANIO_PANTALLA_Y-(TAMANIO_NAVE_Y+50),TAMANIO_NAVE_X,TAMANIO_NAVE_Y,VELOCIDAD_ENEMIGOS,0,true,imgEnemigo);
        enemigosSegundoYTercerSquadron[0] = new Enemigo(enemigo_inicial.getPosicionX(),enemigo_inicial.getPosicionY()-60,enemigo_inicial.getTamanioX(),enemigo_inicial.getTamanioY(),enemigo_inicial.getVelocidadX(),enemigo_inicial.getVelocidadY(),enemigo_inicial.getActivo(),imgEnemigo);
        enemigosSegundoYTercerSquadron[1] = new Enemigo(enemigo_inicial.getPosicionX(),enemigosSegundoYTercerSquadron[0].getPosicionY()-60,enemigo_inicial.getTamanioX(),enemigo_inicial.getTamanioY(),enemigo_inicial.getVelocidadX(),enemigo_inicial.getVelocidadY(),enemigo_inicial.getActivo(),imgEnemigo);
        batallon = new Batallion();
        batallon.crearBattallon(new Squadron(enemigo_inicial),enemigosSegundoYTercerSquadron);
        jugador = new Player((TAMANIO_PANTALLA_X/2)-(TAMANIO_NAVE_X-2),0,TAMANIO_NAVE_X,TAMANIO_NAVE_Y,VELOCIDAD_JUGADOR,0,true,imgJugador,3,"Equipo Beta");
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        switch (estado) {
            case INICIO -> {
                batch.draw(imgInicio,0,0,TAMANIO_PANTALLA_X,TAMANIO_PANTALLA_Y);
                if(Gdx.input.justTouched()) estado = EstadoPartida.JUGANDO;
            }
            case JUGANDO -> {
                batch.draw(imgFondo,0,0,TAMANIO_PANTALLA_X,TAMANIO_PANTALLA_Y);
                batallon.pintarBatallon(batch);
                jugador.pintarse(batch);
                jugador.pintarVidas(batch,imgVida,MARGENES_VIDAS,TAMANIO_PANTALLA_Y-(TAMANIO_VIDA+MARGENES_VIDAS),TAMANIO_VIDA,TAMANIO_VIDA,MARGENES_VIDAS);
                if(!batallon.moverSquadron(direccion_Batallon,TAMANIO_PANTALLA_X)) { //Solo entra si no puede moverse, y invierte la direccion
                    if(direccion_Batallon == ObjetoVolador.Direccion.DERECHA) {
                        direccion_Batallon = ObjetoVolador.Direccion.IZQUIERDA;
                    } else if(direccion_Batallon == ObjetoVolador.Direccion.IZQUIERDA) {
                        direccion_Batallon = ObjetoVolador.Direccion.DERECHA;
                    }
                }
                if (Gdx.input.isKeyJustPressed(Input.Keys.DPAD_RIGHT)) {
                    direccion_Jugador = ObjetoVolador.Direccion.DERECHA;
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_LEFT)) {
                    direccion_Jugador = ObjetoVolador.Direccion.IZQUIERDA;
                }
                if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    jugador.disparar(imgDisparoJugador);
                }
                jugador.moverse(jugador.getVelocidadX(), direccion_Jugador,TAMANIO_PANTALLA_X);
                for(Disparo disparo :Player.disparos) {
                    disparo.moverse(disparo.getVelocidadY(),ObjetoVolador.Direccion.ARRIBA,TAMANIO_PANTALLA_X);
                    disparo.pintarse(batch);
                }
                batallon.dispararSquadron(imgDisparoEnemigo);
                for(Disparo disparo: Batallion.disparos) {
                    disparo.moverse(disparo.getVelocidadY(), ObjetoVolador.Direccion.ABAJO,TAMANIO_PANTALLA_X);
                    disparo.pintarse(batch);
                }
                batallon.colisionesEnemigos();
                jugador.comprobarPerderVida(Batallion.disparos);
                if (NUMERO_ENEMIGOS == 0) estado = EstadoPartida.FINALBUENO;
                if (jugador.getVidas() == 0) estado = EstadoPartida.FINALMALO;
            }
            case FINALBUENO -> {
                batch.draw(imgVictoria,0,0,TAMANIO_PANTALLA_X,TAMANIO_PANTALLA_Y);
            }
            case FINALMALO -> {
                batch.draw(imgDerrota,0,0,TAMANIO_PANTALLA_X,TAMANIO_PANTALLA_Y);
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
