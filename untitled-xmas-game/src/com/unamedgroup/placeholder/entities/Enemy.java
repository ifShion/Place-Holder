package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

/**
 * Classe mãe de todos os inimigos
 * @author Daniel Neves
 *
 */
public abstract class Enemy extends Entity {
	protected String status;
	private boolean damaged;

	public Enemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		
		this.status = "";
	}
	
	public boolean isDamaged() {
		return damaged;
	}
	
	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
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
	
	public void tick(){
		super.tick();
	}
	
	public abstract void destroyEnemy();
	
	public void render(Graphics g) {
		super.render(g);
	}
	
}
