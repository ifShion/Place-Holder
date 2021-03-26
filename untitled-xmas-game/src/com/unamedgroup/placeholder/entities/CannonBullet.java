package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Handler;

/**
 * Um dos projéteis criados no jogo
 * @author Daniel Neves
 *
 */
public class CannonBullet extends Projectile implements Hittable {
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
		handler.getSounds().play("Cannon", handler.getGameVolume());
	}
	
	@Override
	public void tick() {
		super.tick();		
		super.getAnimation().setPlay(true);

		range++;
		x = x + (super.getSpeed() * direction);
		if(range > maxRange || !handler.getGame().getRoom().isFree(super.getX() + (int)(speed * direction), super.getY(), super.getMaskW(), super.getMaskH())) {
			super.destroyProjectile();
		}
		
		if(isCollidingWithPlayer()) {
			if (!handler.getGame().getPlayer().isDamaged()){
				super.destroyProjectile();
				handler.getGame().getPlayer().hitPlayer(1);
			}
		}
	}
	
	/**
	 * Método básico para calcular colisão entre jogador e inimigos
	 * @return se o jogador intersecta esse inimigo
	 */
	public boolean isCollidingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + super.getMaskX() , this.getY() + super.getMaskY() , super.getMaskW() , super.getMaskH());
		Rectangle player = new Rectangle(handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskX() , handler.getGame().getPlayer().getY() + handler.getGame().getPlayer().getMaskY() , handler.getGame().getPlayer().getMaskW() , handler.getGame().getPlayer().getMaskH());
		
		return (enemyCurrent.intersects(player));	
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		//g.setColor(Color.BLUE);
		//g.fillRect(getHitBox().x+super.getMaskX()-handler.getCamera().getX(), getHitBox().y+super.getMaskY()-handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
		//g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX()+1, super.getY() + super.getMaskY() - handler.getCamera().getY()+1, super.getMaskW()-2, super.getMaskH()-2);
	}

	@Override
	public void getHit() {
		super.destroyProjectile();
	}
	
}
