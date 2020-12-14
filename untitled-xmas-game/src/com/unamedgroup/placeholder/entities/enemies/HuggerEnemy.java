package com.unamedgroup.placeholder.entities.enemies;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Room;

public class HuggerEnemy extends Enemy implements GravityEffected, Hittable {

	private double vspd;
	private int idleDelay, movingTime;
	private boolean chasing;
	private int direction;
	private int explosionDelay;
	private boolean exploding;
	private int hp = 2;
	
	public HuggerEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed,
			int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
		
		super.status = ((Game.rand.nextInt(2)%2 == 0) ? "left" : "right");
	}

	@Override
	public void tick() {
		super.tick();
		this.animation();
		movingTime++;
		searchForPlayer();
		if(!chasing && !exploding) {
			if(status == "right" && handler.getGame().getRoom().isFree((int)(super.getX() + super.getMaskX() + speed) , super.getY(), super.getMaskW(), super.getMaskH())) {
				x+=speed;
				this.direction = 1;
				if(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() + 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() + (int)speed , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
					status = "left";
					super.getAnimation().setSpriteX(0);
				}
			}else if(status == "left" && handler.getGame().getRoom().isFree((int)(super.getX() + super.getMaskX() - speed) , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
				x-=speed;
				this.direction = -1;
				if(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() - 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().getRoom().isFree(super.getX() - (int)speed , super.getY(), super.getMaskW(), super.getMaskH())) {
					status = "right";
					super.getAnimation().setSpriteX(0);
				}
			}
			
			if(movingTime > 70 + Game.rand.nextInt(50)) {
				status = "idle";
				idleDelay++;
				if (idleDelay > 60 + Game.rand.nextInt(40)) {
					idleDelay = 0;
					movingTime = 0;
					if (Game.rand.nextInt(100) < 50) {
						super.getAnimation().setSpriteX(0);
						status = "left";
					} else {
						super.getAnimation().setSpriteX(0);
						status = "right";
					}
				}
			}
		}
		
		if(status == "explode") {
			exploding = true;
			this.destroyEnemy();
		}
		
		this.fall();
	}
	
	private void animation() {
		if (status != "explode") {
			super.getAnimation().offSet(-8, 0);
			int facing = ((this.direction == 1) ? 0 : 2);
			super.setHeight(32);
			super.setWidth(32);
			super.getAnimation().setWidth(32);
			super.getAnimation().setHeight(32);
			switch (status) {
			case "idle":
				super.getAnimation().setNumSpritesX(2);
				super.getAnimation().setSpriteY(facing);
				super.getAnimation().setSpriteVeloticy(3);
				break;
			default:
				super.getAnimation().setNumSpritesX(4);
				super.getAnimation().setSpriteY(facing + 1);
				super.getAnimation().setSpriteVeloticy(5);
				break;
			}
		}
	}
	
	public void searchForPlayer() {
		chasing = true;
		super.setSpeed(1.56);
		if(super.calculateDistance(this.getX(), handler.getGame().getPlayer().getX(), this.getY(), handler.getGame().getPlayer().getY()) < 96 && Math.abs((this.getY() - handler.getGame().getPlayer().getY())) < 16 && !exploding) {
			if(handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskX() - 18 > super.getX() && handler.getGame().getRoom().isFree((int)(super.getX() + super.getMaskX() + speed) , super.getY(), super.getMaskW(), super.getMaskH())) {
				x+=speed;
				this.direction = 1;
				status = "right";
				if(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() + 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() + (int)speed , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
					chasing = false;
					status = "idle";
					super.getAnimation().setSpriteX(0);
				}
			}else if(handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskX() + 10 < super.getX() && handler.getGame().getRoom().isFree((int)(super.getX() + super.getMaskX() - speed) , super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH()) && !exploding) {
				x-=speed;
				this.direction = -1;
				status = "left";
				if(handler.getGame().getRoom().isFree(super.getX() + super.getMaskX() - 16 , super.getY() + super.getMaskY() + 1, super.getMaskW(), super.getMaskH()) || !handler.getGame().getRoom().isFree(super.getX() - (int)speed , super.getY(), super.getMaskW(), super.getMaskH())) {
					chasing = false;
					status = "idle";
					super.getAnimation().setSpriteX(0);
				}
			} else {
				status = "explode";
				super.getAnimation().setSpriteX(0);
			}
		}else {
			super.setSpeed(1);
			chasing = false;
		}
	}
	
	public void destroyEnemy() {
		// animação explosão;
		int facingExplosion = ((this.direction == 1) ? 2 : 3);
		super.setHeight(64);
		super.setWidth(64);
		super.getAnimation().offSet(-24, -32);
		super.getAnimation().setWidth(64);
		super.getAnimation().setHeight(64);
		super.getAnimation().setSpriteY(facingExplosion);
		explosionDelay++;
		if(explosionDelay < 9) {
			super.getAnimation().setSpriteX(0);
		}else if(explosionDelay < 22){
			super.getAnimation().setSpriteX(1);
		}else if(explosionDelay < 30){
			super.getAnimation().setSpriteX(2);
		}else if(explosionDelay < 40) {
			super.getAnimation().setSpriteX(3);
		}else if(explosionDelay < 45) {
			if(super.calculateDistance(this.getX(), handler.getGame().getPlayer().getX(), this.getY(), handler.getGame().getPlayer().getY()) < 88){
				if (!handler.getGame().getPlayer().isDamaged()){
					handler.getGame().getPlayer().hitPlayer(2);
				}
			} 
			super.getAnimation().setSpriteX(4);
		}else if(explosionDelay < 50) {
			super.getAnimation().setSpriteX(5);
		}else if(explosionDelay < 60) {
			super.getAnimation().setSpriteX(6);
		}else {
			Room.entities.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		if(explosionDelay > 41) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(217, 226, 205, 180));
			g.fillOval(super.getX() + super.getMaskX() + super.getMaskW() / 2- 80 - handler.getCamera().getX(), super.getY() + super.getMaskY() + super.getMaskH() / 2 - 80 - handler.getCamera().getY(), 160, 160);
			if(explosionDelay%5 < 2) {
				g2.setColor(new Color(235, 140, 0, 150));
				g.fillOval(super.getX() + super.getMaskX() + super.getMaskW() / 2 - 68 - handler.getCamera().getX(), super.getY() + super.getMaskY() + super.getMaskH() / 2 - 87 - handler.getCamera().getY(), 50, 50);
				g2.setColor(new Color(255, 240, 60, 170));
				g.fillOval(super.getX() + super.getMaskX() + super.getMaskW() / 2 - 75 - handler.getCamera().getX(), super.getY() + super.getMaskY() + super.getMaskH() / 2 - 70 - handler.getCamera().getY(), 150, 150);
			}else {
				g2.setColor(new Color(235, 140, 0, 165));
				g.fillOval(super.getX() + super.getMaskX() + super.getMaskW() / 2 - 72 - handler.getCamera().getX(), super.getY() + super.getMaskY() + super.getMaskH() / 2 - 78 - handler.getCamera().getY(), 150, 150);
				g2.setColor(new Color(255, 240, 60, 190));
				g.fillOval(super.getX() + super.getMaskX() + super.getMaskW() / 2 + 10 - handler.getCamera().getX(), super.getY() + super.getMaskY() + super.getMaskH() / 2 + 12 - handler.getCamera().getY(), 60, 60);
			}
			if(explosionDelay < 45)
				super.render(g);
		}else
			super.render(g);
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
		}catch(ArrayIndexOutOfBoundsException aioobe) {
			Room.entities.remove(this);
			return;
		}
		
		super.setY(super.getY() + (int)vspd);
	}

	@Override
	public void getHit() {
		this.hp--;
		if(this.hp < 1) {
			Room.entities.remove(this);
			return;
		}
	}

}
