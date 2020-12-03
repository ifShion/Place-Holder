package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.Hittable;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.AStar;
import com.unamedgroup.placeholder.world.Vector2i;
import com.unamedgroup.placeholder.world.World;

/**
 * Esse inimigo usa o algoritmo de busca de caminho para perseguir o jogador no mapa
 * @author Daniel Neves
 *
 */
public class TrackerEnemy extends Enemy implements Hittable {

	private int followDelay;
	
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
	}
	
	@Override
	public void tick() {
		super.tick();
		// O inimigo irá parar de seguir o personagem depois que chegar nessa distância
		if(calculateDistance(super.getX(), handler.getGame().getPlayer().getX(), super.getY(), handler.getGame().getPlayer().getY()) < 32) {
			super.followPath(null);
		}
		// Assim q o jogador entra em certa distância do inimigo, ele começa a perseguí-lo
		if(super.calculateDistance(super.getX() , handler.getGame().getPlayer().getX() , super.getY() , handler.getGame().getPlayer().getY()) < 144) {
			followDelay++;
			if(super.isCollidingWithPlayer() == false) {
				if(path == null || path.size() == 0 && followDelay > 60) {
					followDelay = 0;
					//cria os vetores que vão guiar o inimigo para o jogador
					Vector2i start = new Vector2i((int) x/World.TILE_SIZE , (int) y/World.TILE_SIZE);
					Vector2i end = new Vector2i((int) (handler.getGame().getPlayer().getX() + 8)/World.TILE_SIZE , (int) (handler.getGame().getPlayer().getY() + 8)/World.TILE_SIZE);
					path = new AStar(handler).findPath(handler.getGame().room , start , end);
				}
				// faz com q o inimigo não se mova a todo tick e fique muito rápido
				if(Game.rand.nextInt(100) < 90)	
					super.followPath(path);
			}
			
			if(followDelay > 1000) followDelay = 800;
		}
	}
	
	@Override
	public void destroyEnemy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.ORANGE);
		g.fillRect(super.getX() + super.getMaskX() - handler.getCamera().getX(), super.getY() + super.getMaskY() - handler.getCamera().getY(), super.getMaskW(), super.getMaskH());
	}

	@Override
	public void getHit() {
		Game.entities.remove(this);
		Game.enemies.remove(this);
		return;
	}

}
