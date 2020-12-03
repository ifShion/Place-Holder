package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.main.Handler;

public class Alpha extends Player implements GravityEffected {

	private double vspd;
	private boolean jump;
	private boolean pressedDown;
	private boolean inTheAir;


	public Alpha(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed,
			int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
		status = "";
	}

	public void tick() {
		super.tick();
		if (animated && !inTheAir) {
			super.getAnimation().setPlay(true);
			int facingIdle = ((super.direction == 1) ? 0 : 2);
			super.setHeight(24);
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

		this.fall();
	}

	@Override
	public void fall() {
		vspd += GravityEffected.GRAVITY;
		int facingJump = ((super.direction == 1) ? 3 : 5);

		// sitema de pulo do personagem. Ele não pode pular duas vezes nem segurar o
		// botão para pular assim q alcançar o chão
		if (handler.getInputHandler().prime.down && !pressedDown
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
			if (vspd > 4.7)
				vspd = 4.7;

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
				super.getAnimation().setNumSpritesX(3);
				super.getAnimation().setSpriteY(facingJump);
				super.getAnimation().setSpriteVeloticy(8);
			} else {
				// descendo
				super.getAnimation().setHeight(32);
				super.setHeight(32);
				super.getAnimation().setNumSpritesX(3);
				super.getAnimation().setSpriteY(facingJump + 1);
				super.getAnimation().setSpriteVeloticy(6);
			}
		}

		y = y + vspd;
	}


}
