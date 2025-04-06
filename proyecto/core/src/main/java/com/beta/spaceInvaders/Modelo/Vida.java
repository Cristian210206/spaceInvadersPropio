package com.beta.spaceInvaders.Modelo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.nio.channels.spi.SelectorProvider;

public class Vida {
    public void pintarse(SpriteBatch batch, Texture img, int posX, int posY, int tamanioX, int tamanioY) {
        batch.draw(img,posX,posY,tamanioX,tamanioY);
    }
}
