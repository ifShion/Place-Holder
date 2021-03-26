package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.CannonBullet;
import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Room;

/**
 * Inimigo estacionário que atira projéteis a cada intervalo de tempo
 * @author Daniel Neves
 *
 */
public class CannonEnemy extends Enemy implements Hittable {

	private int direction;
	private boolean isShooting;
	private int directionalRender;
	private boolean damaged;
	private int damageCooldown;

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
		
		super.setHp(3);
		this.directionalRender = ((this.direction < 0) ? 1 : 0);
	}

	@Override
	public void tick() {
		super.tick();
		
		damageCooldown--;
		if(damageCooldown < 0) {
			damaged = false;
			damageCooldown = 0;
		}
		
		super.getAnimation().setPlay(true);
		if(!damaged) {
			switch (status) {
				case "idle":
					super.getAnimation().setSpriteY(directionalRender);
					super.getAnimation().setNumSpritesX(2);
					super.getAnimation().setSpriteVeloticy(4);
					break;
				case "shooting":
					super.getAnimation().setSpriteY(directionalRender);
					super.getAnimation().setNumSpritesX(8);
					super.getAnimation().setSpriteVeloticy(5);
					break;
				default:
					break;
			}	
		} else {
			super.getAnimation().setSpriteY(directionalRender + 2);
			super.getAnimation().setNumSpritesX(5);
			super.getAnimation().setSpriteVeloticy(10);
		}
		
		// o jogador entra em alcance do canhão e ele atira a cada 2 segundos
		if(super.calculateDistance(super.getX() , handler.getGame().getPlayer().getX() , super.getY() , handler.getGame().getPlayer().getY()) < 128) {
			status = "shooting";
			if(super.getAnimation().getSpriteX() == 4 * super.width && isShooting) {
				isShooting = false;
				int dir = ((this.direction < 0) ? -1 : 1);
				Room.entities.add(new CannonBullet(super.getX() + 12 + (5 * dir), super.getY() + 16 , 5 , 4, dir, Game.nutCracker, 3, 2, 5, 4, 1, 0, 128, handler));
				
			}else if(super.getAnimation().getSpriteX() != 4 * super.width) {
				isShooting = true;
			}
		}else {
			status = "idle";
		}
	}
	
	@Override
	public void destroyEnemy() {
		Room.entities.remove(this);
		return;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
//		g.setColor(Color.RED);
//		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
	}
	
	@Override
	public void getHit(){
		boolean weakPoint = ((direction < 0) ? handler.getGame().getPlayer().getX() > super.getX() + super.getMaskW() 
		/ 2 : handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskW() < super.getX());
		if(weakPoint && damageCooldown == 0 && !damaged) {
			super.getAnimation().setSpriteX(0);
			damageCooldown = 30;
			super.setHp(super.getHp() - 1);
			this.damaged = true;
			if(super.getHp() < 1)
				this.destroyEnemy();
		}
	}
}
