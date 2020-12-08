package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class Door extends Entity {
    public int destiny;
    public int tpx;
    public int tpy;

    //Mudar DoorTile para entidade, não fiz isso agora pq não sei como Animation funciona
    public Door(int x, int y, int width, int height, SpriteSheet spriteSheet, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, int destiny , int tpx , int tpy, Handler handler) {
        super(x, y, width, height, spriteSheet, 0, 0, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
        
        this.destiny = destiny;
        this.tpx = tpx;
        this.tpy = tpy;
        
    }

    public void movePlayer() {
        if(super.isColliding(this, handler.getGame().getPlayer())) {
            handler.getGame().updateEntities();
            handler.getGame().changeCurrentMapID(destiny);
            handler.getGame().getPlayer().setX(tpx);
            handler.getGame().getPlayer().setY(tpy);
           return;
        }
	}

    @Override
    public void tick() {
        super.tick();
        movePlayer();
    }
    
    @Override
    public void render(Graphics g) {
    	super.render(g);
	}
}