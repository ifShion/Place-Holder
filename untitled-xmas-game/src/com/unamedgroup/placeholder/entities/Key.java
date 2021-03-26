package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Room;

public class Key extends Entity {
    public Key(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler){
        super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		
    }

    public void tick(){
        super.tick();
        if(super.isColliding(this, handler.getGame().getPlayer())){
            handler.getGame().getPlayer().getInventario().add(this);
            handler.getSounds().play("pCoin4", handler.getGameVolume());
            Room.entities.remove(this);
        }
    }
}