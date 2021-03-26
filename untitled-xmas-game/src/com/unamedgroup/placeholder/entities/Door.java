package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class Door extends Entity {
    public Boolean locked;
    public int destiny;
    public int tpx;
    public int tpy;

    //Mudar DoorTile para entidade, não fiz isso agora pq não sei como Animation funciona
    public Door(int x, int y, int width, int height, SpriteSheet spriteSheet, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, int destiny , int tpx , int tpy, boolean locked, Handler handler) {
        super(x, y, width, height, spriteSheet, 0, 0, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
        
        this.locked = locked;
        this.destiny = destiny;
        this.tpx = tpx;
        this.tpy = tpy;
        
    }

    public void movePlayer() {
        if(super.isColliding(this, handler.getGame().getPlayer())) {
            if(handler.getInputHandler().up.clicked){
                if(locked){
                    for(int i=0; i<handler.getGame().getPlayer().getInventario().size();i++){
                        if(handler.getGame().getPlayer().getInventario().get(i) instanceof Key){
                            locked = false;
                            handler.getGame().getPlayer().getInventario().remove(i);
                        }             
                    }
                    if (locked){
                        handler.getSounds().play("Door_locked", handler.getGameVolume());
                    } 
                    
                }else{
                    handler.getGame().updateEntities();
                    handler.getSounds().play("close_door", handler.getGameVolume());
                    handler.getGame().changeCurrentMapID(destiny);
                    handler.getGame().getPlayer().setX(tpx);
                    handler.getGame().getPlayer().setY(tpy);
                    
                }
            }
           return;
        }
	}

    @Override
    public void tick() {
    	if(locked) {
            super.getAnimation().setSpriteX(1);
    	}else {
    		super.getAnimation().setPlay(false);
            super.getAnimation().setSpriteX(0);
    	}
        super.tick();
        movePlayer();
    }
    
    @Override
    public void render(Graphics g) {
    	super.render(g);
	}
}