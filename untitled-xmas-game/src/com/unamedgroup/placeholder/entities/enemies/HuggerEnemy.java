package com.unamedgroup.placeholder.entities.enemies;
import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class HuggerEnemy extends Enemy implements GravityEffected {

	private double vspd;
	private String moveDecision = "left";
	private int idleDelay, movingTime;
	private boolean chasing;
	
	public HuggerEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed,
			int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
	}

	@Override
	public void tick() {
		super.tick();
		movingTime++;
		searchForPlayer();
		if(!chasing) {
			if(moveDecision == "right" && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() + speed) , super.getY(), super.getMaskW(), super.getMaskH())) {
				x+=speed;
				if(handler.getGame().room.isFree(super.getX() + super.getMaskX() + 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().room.isFree(super.getX() + super.getMaskX() + (int)speed , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
					moveDecision = "left";
				}
			}else if(moveDecision == "left" && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() - speed) , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
				x-=speed;
				if(handler.getGame().room.isFree(super.getX() + super.getMaskX() - 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().room.isFree(super.getX() - (int)speed , super.getY(), super.getMaskW(), super.getMaskH())) {
					moveDecision = "right";
				}
			}
			
			if(movingTime > 50 + Game.rand.nextInt(50)) {
				moveDecision = "idle";
				idleDelay++;
				if (idleDelay > 40 + Game.rand.nextInt(40)) {
					idleDelay = 0;
					movingTime = 0;
					if (Game.rand.nextInt(100) < 50) moveDecision = "left";
					else moveDecision = "right";
				}
			}
		}
		
		if(moveDecision == "explode") {
			this.destroyEnemy();
		}
		
		this.fall();
	}
	
	public void destroyEnemy() {
		// animação explosão;
		if(super.calculateDistance(this.getX(), handler.getGame().getPlayer().getX(), this.getY(), handler.getGame().getPlayer().getY()) < 50) {
			// damo player;
		}
		
		// destruir inimigo;
	}

	public void searchForPlayer() {
		chasing = true;
		if(super.calculateDistance(this.getX(), handler.getGame().getPlayer().getX(), this.getY(), handler.getGame().getPlayer().getY()) < 120 && Math.abs((this.getY() - handler.getGame().getPlayer().getY())) < 16) {
			if(handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskX() - 18 > super.getX() && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() + speed) , super.getY(), super.getMaskW(), super.getMaskH())) {
				x+=speed;
				if(handler.getGame().room.isFree(super.getX() + super.getMaskX() + 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().room.isFree(super.getX() + super.getMaskX() + (int)speed , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
					chasing = false;
					moveDecision = "idle";
				}
			}else if(handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskX() + 10 < super.getX() && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() - speed) , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
				x-=speed;
				if(handler.getGame().room.isFree(super.getX() + super.getMaskX() - 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().room.isFree(super.getX() - (int)speed , super.getY(), super.getMaskW(), super.getMaskH())) {
					chasing = false;
					moveDecision = "idle";
				}
			} else {
				moveDecision = "explode";
			}
		}else {
			chasing = false;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.MAGENTA);
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
