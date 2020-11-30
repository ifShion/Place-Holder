package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.main.Handler;

public class Alpha extends Player implements GravityEffected {

	private double vspd;
	private boolean jump;
	private boolean pressedDown;

	public Alpha(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed,
			int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY,
				handler);
		status = "";
	}

	public void tick() {
		super.tick();
		if (animated) {
			super.getAnimation().setPlay(true);
			switch (status) {
			case "idle":
				super.getAnimation().setSpriteY(0);
				super.getAnimation().setSpriteVeloticy(4);
				break;
			case "right":
				super.getAnimation().setSpriteY(1);
				super.getAnimation().setSpriteVeloticy(6);
				break;
			default:
				super.getAnimation().setSpriteY(0);
				super.getAnimation().setSpriteVeloticy(4);
				break;
			}
		}

		this.fall();
	}

	@Override
	public void fall() {
		vspd += GravityEffected.GRAVITY;

		// sitema de pulo do personagem. Ele não pode pular duas vezes nem segurar o
		// botão para pular assim q alcançar o chão
		if (handler.getInputHandler().prime.down && !pressedDown
				&& !handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + 1) + super.getMaskY(),
						super.getMaskW(), super.getMaskH())) {
			jump = true;
			pressedDown = true;
		} else if (!handler.getInputHandler().prime.down) {
			pressedDown = false;
		}

		// altura que o jogador pula
		if (!handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + 1) + super.getMaskY(),
				super.getMaskW(), super.getMaskH()) && jump) {
			super.status = "jump upward";
			vspd = -7.5;
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
			if (vspd > 6.2)
				vspd = 6.2;

			// impossibilita o jogador se enterrar no chão
			while (handler.getGame().room.isFree((int) x + super.getMaskX(), (int) (y + signVsp) + super.getMaskY(),
					super.getMaskW(), super.getMaskH())) {
				y += signVsp;
			}
			vspd = 0;
		}

		y = y + vspd;
	}

}
