package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Room;
/**
 * Esse inimigo caminha de um lado para outro de uma plataforma sem cair dela.
 * @author Daniel Neves
 *
 */
public class WalkerEnemy extends Enemy implements GravityEffected, Hittable {

	private double vspd;
	
	public WalkerEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		// TODO Auto-generated constructor stub
		
		super.status = ((Game.rand.nextInt(100) < 50) ? "left" : "right");
	}

	public void tick() {
		super.tick();
		
		super.getAnimation().setPlay(true);
		switch(status) {
		case "left":
			super.getAnimation().setNumSpritesX(4);
			super.getAnimation().setWidth(16);
			super.getAnimation().setHeight(16);
			super.getAnimation().setSpriteY(1);
			super.getAnimation().setSpriteVeloticy(6);
			break;
		case "right":
			super.getAnimation().setNumSpritesX(4);
			super.getAnimation().setWidth(16);
			super.getAnimation().setHeight(16);
			super.getAnimation().setSpriteY(0);
			super.getAnimation().setSpriteVeloticy(6);
			break;
		}
		
		// sempre que inimigo alcança o limite de uma plataforma, ele segue o caminho inverso
		if(super.status == "right" && handler.getGame().getRoom().isFree((int)(super.getX() + super.getMaskX() + speed) , super.getY(), super.getMaskW(), super.getMaskH())) {
			x+=speed;
			if(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() + 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() + (int)speed , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
				super.status = "left";
			}
		}else if(super.status == "left" && handler.getGame().getRoom().isFree((int)(super.getX() + super.getMaskX() - speed) , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
			x-=speed;
			if(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() - 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().getRoom().isFree(super.getX() - (int)speed , super.getY(), super.getMaskW(), super.getMaskH())) {
				super.status = "right";
			}
		}
		
		this.fall();
		if(isCollidingWithPlayer()) {
			if (!handler.getGame().getPlayer().isDamaged()){
				handler.getGame().getPlayer().hitPlayer(1);
			}
		}
	}
	
	@Override
	public void destroyEnemy() {
		Room.entities.remove(this);
		return;
	}
	
	public void render(Graphics g) {
		super.render(g);
//		g.setColor(Color.GREEN);
//		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
	}

	@Override
	public void fall() {
		vspd+=GravityEffected.GRAVITY;
		try {
			if(!handler.getGame().getRoom().isFree(super.getX() + super.getMaskX(),(int)(super.getY() + super.getMaskY() + vspd), super.getMaskW(), super.getMaskH())) {
				int signVsp = 0;
				if(vspd >= 0) {
					signVsp = 1;
				}else {
					signVsp = -1;
				}
				while(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() ,(int)(super.getY() + super.getMaskY() + signVsp), super.getMaskW(), super.getMaskH())) {
					super.setY(super.getY() + (int)signVsp);
				}
				vspd = 0;
			}
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			this.destroyEnemy();
		}
		super.setY(super.getY() + (int)vspd);
	}

	@Override
	public void getHit() {
		this.destroyEnemy();
	}
}
