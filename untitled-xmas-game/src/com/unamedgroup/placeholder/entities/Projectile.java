package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;
import java.awt.Rectangle;

public abstract class Projectile extends Entity {

	private Rectangle hitBox;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param spriteSheet
	 * @param depth
	 * @param speed
	 * @param animationSpeed
	 * @param numSpritesX
	 * @param numSpritesY
	 * @param initPosX
	 * @param initPosY
	 * @param handler
	 */
	public Projectile(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed,
			int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
		hitBox = new Rectangle(x,y,width,height);
	}
	
	public Projectile(double x, double y, int width, int height, Handler handler) {
		super(x, y, width, height, handler);
	}
	
	public void destroyProjectile() {
		Room.projectiles.remove(this);
		return;
	}

	public Rectangle getHitBox(){
		return this.hitBox;
	}

	public void setHitBox(Rectangle r){
		this.hitBox = r;
	}

}
