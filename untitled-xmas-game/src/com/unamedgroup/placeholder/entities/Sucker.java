package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Color;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class Sucker extends Entity {

    public Sucker(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed,
            int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
        super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX,
                initPosY, handler);
    }

    @Override
    public void tick() {
        super.tick();
        if(super.isColliding(this, handler.getGame().getPlayer()) && !(handler.getGame().getPlayer().hp==handler.getGame().getPlayer().MAX_LIFE)){
            handler.getGame().getPlayer().setHp(handler.getGame().getPlayer().MAX_LIFE);
            handler.getGame().entities.remove(this); 
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.MAGENTA);
		g.fillOval(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
    }

    
    
}
