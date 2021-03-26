package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Room;

public class Rattles extends Entity {

	public Rattles(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed,
			int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX,
				initPosY, handler);
	}

	@Override
	public void tick() {
		super.tick();
		if (super.isColliding(this, handler.getGame().getPlayer())) {
			handler.getSounds().play("Pick_up_Stick2", handler.getGameVolume());
			handler.getGame().getPlayer().inventario.add(this);
	        Room.entities.remove(this); 
	        }
	    }

	    @Override
	    public void render(Graphics g) {
			super.render(g);
//	        g.setColoxcl(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
	    }
	
}
