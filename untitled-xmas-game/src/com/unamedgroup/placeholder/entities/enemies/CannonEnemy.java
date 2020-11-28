package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.CannonBullet;
import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

/**
 * Inimigo estacionário que atira projéteis a cada intervalo de tempo
 * @author Daniel Neves
 *
 */
public class CannonEnemy extends Enemy {

	private int direction;
	private int shotDelay;
	/**
	 * 
	 * @param x						posição do eixo x do inimigo
	 * @param y						posição do eixo x do inimigo
	 * @param width					largura do inimigo para render
	 * @param height				altura do inimigo para render
	 * @param direction				direção na qual o inimigo lança seus projéteis
	 * @param spriteSheet			folha de sprites de onde serão tirados os desenhos do inimigo
	 * @param depth					profundidade do inimigo na lista
	 * @param speed					velocidade do inimigo
	 * @param animationSpeed		velocidade de animação do inimigo
	 * @param numSpritesX			número de sprites da animação em x
	 * @param numSpritesY			número de sprites da animação em y
	 * @param initPosX				posição inicial para captura dos sprites em x
	 * @param initPosY				posição inicial para captura dos sprites em y
	 * @param handler				variável de controle
	 * 
	 * @see CannonBullet
	 */
	public CannonEnemy(int x, int y, int width, int height, int direction, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		this.direction = direction;
	}

	@Override
	public void tick() {
		super.tick();
		// o jogador entra em alcance do canhão e ele atira a cada 2 segundos
		if(super.calculateDistance(super.getX() , handler.getGame().getPlayer().getX() , super.getY() , handler.getGame().getPlayer().getY()) < 128) {
			shotDelay++;
			if(shotDelay > 120) {
				shotDelay = 0;
				int dir = ((this.direction < 0) ? -1 : 1);
				Game.projectiles.add(new CannonBullet(super.getX() + 12 + (5 * dir), super.getY() + 16 , 5 , 4, dir, Game.spriteTeste, 3, 2, 5, 3, 1, 4, 0, handler));
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.RED);
		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
	}
	
}
