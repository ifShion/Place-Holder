package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class Alpha extends Player implements GravityEffected {

	private double vspd;
	private boolean jump;
	private boolean pressedDown, pressedAttack;
	private boolean inTheAir;
	private boolean attacking;
	private int attackingDekay;
	private boolean hit;

	public Alpha(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed,
			int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
		status = "";
	}

	public void tick() {
		if (animated && !inTheAir && !attacking) {
			super.getAnimation().resetOffSet();
			super.getAnimation().setPlay(true);
			int facingIdle = ((super.direction == 1) ? 0 : 2);
			super.setHeight(24);
			super.setWidth(16);
			switch (status) {
			case "idle":
				super.getAnimation().setNumSpritesX(4);
				super.getAnimation().setWidth(16);
				super.getAnimation().setHeight(24);
				super.getAnimation().setSpriteY(facingIdle);
				super.getAnimation().setSpriteVeloticy(4);
				break;
			case "right":
				super.getAnimation().setNumSpritesX(4);
				super.getAnimation().setWidth(16);
				super.getAnimation().setHeight(24);
				super.getAnimation().setSpriteY(1);
				super.getAnimation().setSpriteVeloticy(6);
				break;
			case "left":
				super.getAnimation().setNumSpritesX(4);
				super.getAnimation().setWidth(16);
				super.getAnimation().setHeight(24);
				super.getAnimation().setSpriteY(3);
				super.getAnimation().setSpriteVeloticy(6);
				break;
			default:
				break;
			}
		}
		
		this.isDead(2, 67.5);
		this.playerAttack();
		this.fall();
		super.tick();
	}
	
	private void playerAttack() {
		if (handler.getInputHandler().secondary.down && !pressedAttack && !handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + 1) + super.getMaskY(), super.getMaskW(), super.getMaskH())) {
			this.attacking = true;
			super.getAnimation().setSpriteX(0);
			pressedAttack = true;
		} else if (!handler.getInputHandler().secondary.down) {
			pressedAttack = false;
		}
		
		if(attacking) {
			super.moveable = false;
			this.bbAttack();
		}else {
			super.moveable = true;
		}
	}
 
	private void bbAttack() {
		int facingAttack = ((super.direction == 1) ? 7 : 8);
		int facingOffSet = ((super.direction == 1) ? -3 : -11);
		super.setHeight(32);
		super.setWidth(32);
		super.getAnimation().offSet(facingOffSet, -8);
		super.getAnimation().setWidth(32);
		super.getAnimation().setHeight(32);
		super.getAnimation().setSpriteY(facingAttack);
		attackingDekay++;
		if(attackingDekay < 10) {
			super.getAnimation().setSpriteX(0);
		}else if(attackingDekay < 18){
			super.getAnimation().setSpriteX(1);
		}else if(attackingDekay < 23){
			super.getAnimation().setSpriteX(2);
		}else if(attackingDekay < 30) {
			super.getAnimation().setSpriteX(3);
			attackHitBox();
		}else {
			hit = false;
			attackingDekay = 0;
			super.getAnimation().setSpriteX(0);
			this.attacking = !attacking;
		}
	}
	
	private void attackHitBox() {
		int facingAttack = ((super.direction == 1) ? 5 : -25);
		Rectangle batHitBox = new Rectangle(super.getX() + super.getMaskX() + super.getMaskW()/2 + facingAttack - handler.getCamera().getX(), super.getY() - 8 - handler.getCamera().getY(), 20, 32);
		for(int i = 0 ; i < Game.entities.size(); i++) {
			Rectangle enemy = new Rectangle(Game.entities.get(i).getX() + Game.entities.get(i).getMaskX() - handler.getCamera().getX() , Game.entities.get(i).getY() + Game.entities.get(i).getMaskY() - handler.getCamera().getY() , Game.entities.get(i).getMaskW() , Game.entities.get(i).getMaskH());

			if(batHitBox.intersects(enemy) && Game.entities.get(i) instanceof Hittable && !hit) {
				Hittable h = (Hittable)Game.entities.get(i);
				h.getHit();
				
				this.hit = true;
			}
		}
		
		for(int i = 0 ; i < Game.projectiles.size(); i++) {
			Rectangle enemy = new Rectangle(Game.projectiles.get(i).getX() + Game.projectiles.get(i).getMaskX() - handler.getCamera().getX() , Game.projectiles.get(i).getY() + Game.projectiles.get(i).getMaskY() - handler.getCamera().getY() , Game.projectiles.get(i).getMaskW() , Game.projectiles.get(i).getMaskH());

			if(batHitBox.intersects(enemy) && Game.projectiles.get(i) instanceof Hittable && !hit) {
				Hittable h = (Hittable)Game.projectiles.get(i);
				h.getHit();
				
				this.hit = true;
			}
		}
	}
	
	@Override
	public void fall() {
		vspd += GravityEffected.GRAVITY;
		int facingJump = ((super.direction == 1) ? 3 : 5);

		// sitema de pulo do personagem. Ele não pode pular duas vezes nem segurar o
		// botão para pular assim q alcançar o chão
		if (handler.getInputHandler().prime.down && !pressedDown && moveable
				&& !handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + 1) + super.getMaskY(),
						super.getMaskW(), super.getMaskH())) {
			this.jump = true;
			pressedDown = true;
		} else if (!handler.getInputHandler().prime.down) {
			pressedDown = false;
		}

		if(vspd != GravityEffected.GRAVITY && !inTheAir) {
			inTheAir = true;
			super.getAnimation().setSpriteX(0);
		}
		
		try {
			// altura que o jogador pula
			if (!handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + 1) + super.getMaskY(),
					super.getMaskW(), super.getMaskH()) && jump) {
				// impulso
				vspd = -7.5;
				super.setSpeed(2.5);
				jump = false;
			}

			// verifica se o local para onde o jogador está subindo ou caindo para está
			// disponível
			if (!handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + vspd) + super.getMaskY(),
					super.getMaskW(), super.getMaskH())) {
				int signVsp = 0;
				if (vspd >= 0) {
					signVsp = 1;
				} else {
					signVsp = -1;
				}
	
				// impossibilita o jogador atingir velocidades de queda muito altas
				if (vspd > 3.45) vspd = 3.45;
	
				// impossibilita o jogador se enterrar no chão
				while (handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + signVsp) + super.getMaskY(),
						super.getMaskW(), super.getMaskH())) {
					y += signVsp;
				}
				// cai no chão
				vspd = 0;
				super.setSpeed(3);
				this.inTheAir = false;
			}else {
				if (vspd <= 0) {
					// subindo
					super.getAnimation().setHeight(32);
					super.setHeight(32);
					super.setWidth(16);
					super.getAnimation().setNumSpritesX(3);
					super.getAnimation().setSpriteY(facingJump);
					super.getAnimation().setSpriteVeloticy(8);
				} else {
					// descendo
					super.getAnimation().setHeight(32);
					super.setHeight(32);
					super.setWidth(16);
					super.getAnimation().setNumSpritesX(3);
					super.getAnimation().setSpriteY(facingJump + 1);
					super.getAnimation().setSpriteVeloticy(6);
				}
			}
			
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			this.fallVoid(2, 67.5);
			vspd = -1.0;
		}

		y = y + vspd;
	}

	@Override
	public void render(Graphics g) {
//		int facingAttack = ((super.direction == 1) ? 5 : -25);
//		if(attacking) {
//			g.setColor(Color.YELLOW);
//			g.fillRect(super.getX() + super.getMaskX() + super.getMaskW()/2 + facingAttack - handler.getCamera().getX(), super.getY() - 8 - handler.getCamera().getY(), 20, 32);
//		}

		super.render(g);
	}
	
	
}
