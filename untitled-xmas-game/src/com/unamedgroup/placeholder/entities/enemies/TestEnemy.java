package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.main.Handler;
/**
 * Esse inimigo caminha de um lado para outro de uma plataforma sem cair dela.
 * @author Daniel Neves
 *
 */
public class TestEnemy extends Enemy implements GravityEffected {

	private double vspd;
	private boolean left = true, right;
	
	public TestEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		super.tick();
		// sempre que inimigo alcança o limite de uma plataforma, ele segue o caminho inverso
		if(right && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() + speed) , super.getY(), super.getMaskW(), super.getMaskH())) {
			x+=speed;
			if(handler.getGame().room.isFree(super.getX() + super.getMaskX() + 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().room.isFree(super.getX() + super.getMaskX() + (int)speed , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
				right = false;
				left = true;
			}
		}else if(left && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() - speed) , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
			x-=speed;
			if(handler.getGame().room.isFree(super.getX() + super.getMaskX() - 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().room.isFree(super.getX() - (int)speed , super.getY(), super.getMaskW(), super.getMaskH())) {
				right = true;
				left = false;
			}
		}
		
		this.fall();
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.GREEN);
		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
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
