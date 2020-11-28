package com.unamedgroup.placeholder.entities.enemies;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.main.Handler;

public class TrackerEnemy extends Enemy implements GravityEffected {

	private double vspd;
	
	public TrackerEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
	}

	@Override
	public void fall() {
		vspd+=GravityEffected.GRAVITY;
		if(!handler.getGame().room.isFree(super.getX() + super.getMaskX(),(int)(super.getY() + super.getMaskY() + vspd), super.getMaskW(), super.getMaskH())) {
			int signVsp = 0;
			if(vspd >= 0) {
				signVsp = 1;
			}else {
				signVsp = -1;
			}
			while(handler.getGame().room.isFree(super.getX() + super.getMaskX() ,(int)(super.getY() + super.getMaskY() + signVsp), super.getMaskW(), super.getMaskH())) {
				super.setY(super.getY() + (int)signVsp);
			}
			vspd = 0;
		}
		super.setY(super.getY() + (int)vspd);
	}

}
