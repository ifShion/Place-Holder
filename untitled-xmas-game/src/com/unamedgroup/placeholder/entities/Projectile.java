package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public abstract class Projectile extends Entity {

	public Projectile(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed,
			int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
	}
	
	public Projectile(double x, double y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void destroyProjectile() {
		Game.projectiles.remove(this);
		return;
	}

}
