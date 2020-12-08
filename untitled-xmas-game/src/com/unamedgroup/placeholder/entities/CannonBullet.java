package com.unamedgroup.placeholder.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

/**
 * Um dos projéteis criados no jogo
 * @author Daniel Neves
 *
 */
public class CannonBullet extends Projectile {
	private int direction;
	private int range, maxRange = 80;
	
	/**
	 * @param x						posição do eixo x do inimigo
	 * @param y						posição do eixo x do inimigo
	 * @param width					largura do inimigo para render
	 * @param height				altura do inimigo para render
	 * @param direction				direção na qual o projétil se move
	 * @param spriteSheet			folha de sprites de onde serão tirados os desenhos do inimigo
	 * @param depth					profundidade do inimigo na lista
	 * @param speed					velocidade do inimigo
	 * @param animationSpeed		velocidade de animação do inimigo
	 * @param numSpritesX			número de sprites da animação em x
	 * @param numSpritesY			número de sprites da animação em y
	 * @param initPosX				posição inicial para captura dos sprites em x
	 * @param initPosY				posição inicial para captura dos sprites em y
	 * @param handler				variável de controle
	 */
	public CannonBullet(int x, int y, int width, int height, int direction, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		this.direction = direction;
	}
	
	@Override
	public void tick() {
		super.tick();		
		super.getAnimation().setPlay(true);

		range++;
		x = x + (super.getSpeed() * direction);
		if(range > maxRange || !handler.getGame().room.isFree(super.getX() + (int)(speed * direction), super.getY(), super.getMaskW(), super.getMaskH())) {
			super.destroyProjectile();
		}

		if (getHitBox().intersects(new Rectangle((int)(handler.getGame().getPlayer().getX()+1), (int) (handler.getGame().getPlayer().getY()+1), handler.getGame().getPlayer().getWidth()+2, handler.getGame().getPlayer().getHeight()+2))) {
		//if(super.calculateDistance(this.getX(), handler.getGame().getPlayer().getX(), this.getY(), handler.getGame().getPlayer().getY()) <= 8) {
			handler.getGame().getPlayer().setHp(handler.getGame().getPlayer().getHp() - 1);
			super.destroyProjectile();
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.BLUE);
		//g.fillRect(getHitBox().x+super.getMaskX()-handler.getCamera().getX(), getHitBox().y+super.getMaskY()-handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX()+1, super.getY() + super.getMaskY() - handler.getCamera().getY()+1, super.getMaskW()-2, super.getMaskH()-2);
	}

}
