package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.AStar;
import com.unamedgroup.placeholder.world.Room;
import com.unamedgroup.placeholder.world.Vector2i;
import com.unamedgroup.placeholder.world.World;

/**
 * Esse inimigo usa o algoritmo de busca de caminho para perseguir o jogador no mapa
 * @author Daniel Neves
 *
 */
public class TrackerEnemy extends Enemy implements Hittable {

	private int followDelay;
	private int attackDelay;
	
	/**
	 * Inicializa um inimigo que persegue o jogador
	 * @param x						posição do eixo x do inimigo
	 * @param y						posição do eixo x do inimigo
	 * @param width					largura do inimigo para render
	 * @param height				altura do inimigo para render
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
	public TrackerEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		
		this.status = "";
	}
	
	@Override
	public void tick() {
		super.tick();
		this.animation();
		// O inimigo irá parar de seguir o personagem depois que chegar nessa distância
		if(calculateDistance(super.getX(), handler.getGame().getPlayer().getX(), super.getY(), handler.getGame().getPlayer().getY()) < 32) {
			super.followPath(null);
		}
		// Assim q o jogador entra em certa distância do inimigo, ele começa a perseguí-lo
		if(super.calculateDistance(super.getX() , handler.getGame().getPlayer().getX() , super.getY() , handler.getGame().getPlayer().getY()) < 144) {
			followDelay++;
			if(super.isCollidingWithPlayer() == false) {
				status = "flying";
				if(path == null || path.size() == 0 && followDelay > 60) {
					followDelay = 0;
					//cria os vetores que vão guiar o inimigo para o jogador
					Vector2i start = new Vector2i((int) x/World.TILE_SIZE , (int) y/World.TILE_SIZE);
					Vector2i end = new Vector2i((int) (handler.getGame().getPlayer().getX() + 8)/World.TILE_SIZE , (int) (handler.getGame().getPlayer().getY() + 8)/World.TILE_SIZE);
					path = new AStar(handler).findPath(handler.getGame().getRoom() , start , end);
				}
				// faz com q o inimigo não se mova a todo tick e fique muito rápido
				if(Game.rand.nextInt(100) < 90)	
					super.followPath(path);
			}else {
				this.damagePlayer();
				status = "attacking";
			}
			
			if(followDelay > 1000) followDelay = 800;
		}
	}
	
	public void damagePlayer() {
		//status attacking;
		attackDelay++;
		super.getAnimation().offSet(-5, -12);
		super.setHeight(32);
		super.setWidth(24);
		super.getAnimation().setWidth(24);
		super.getAnimation().setHeight(32);
		super.getAnimation().setSpriteX(0);
		super.getAnimation().setSpriteY(1);
		
		if (attackDelay < 10) {
			super.getAnimation().setSpriteX(0);
		}else if(attackDelay < 45) {
			super.getAnimation().setSpriteX(1);
		}else if (attackDelay < 52) {
			super.getAnimation().setSpriteX(2);
		}else if (attackDelay < 60) {
			attackDelay = 0;
			super.getAnimation().setSpriteX(3);
			handler.getGame().getPlayer().hitPlayer(1);
		}
	}
	
	private void animation() {
		super.getAnimation().offSet(-5, -8);
		super.setHeight(24);
		super.setWidth(24);
		super.getAnimation().setWidth(24);
		super.getAnimation().setHeight(24);
		if(status != "attacking") {
			switch (super.status) {
			case "flying": 
				super.getAnimation().setNumSpritesX(4);
				super.getAnimation().setSpriteY(0);
				super.getAnimation().setSpriteVeloticy(5);
				break;
			default:
				super.getAnimation().setNumSpritesX(4);
				super.getAnimation().setSpriteY(0);
				super.getAnimation().setSpriteVeloticy(5);
				break;
			}
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
//		g.setColor(Color.ORANGE);
//		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
		
	}

	@Override
	public void getHit() {
		destroyEnemy();
	}

}
