package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.world.Camera;
import com.unamedgroup.placeholder.world.World;

/**
 * É bom essa classe ser abstrata tbm já q vai ter 5 personagens Classe de teste
 * 
 * O objetivo dessa classe agora é adaptarmo-na para se tornar uma classe
 * abstrata, já que teremos 6 jogadores físicos no jogo (incluindo tower
 * defense)
 * 
 * @author Daniel Neves
 *
 */
public class Player extends Entity {
	/**
	 * Mudei a questão da animação. Toda a área ( int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY) do
	 * construtor é decidada a ela. Mais detalhes em Entity:
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param sprite
	 * @param depth
	 * @param speed
	 * @param animationSpeed
	 * @param numSpritesX
	 * @param numSpritesY
	 * @param initPosX
	 * @param initPosY
	 */
	public Player(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY) {
		super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY);
		
	}
	
	public void tick() {
		super.tick();
		//Alteração: mudei a condição para o personagem poder se mover. Implementando um sistema de colisão simples

		boolean right = Game.input.right.down && Game.room.isFree((int)(super.getX() + speed), super.getY());
		boolean left = Game.input.left.down && Game.room.isFree((int)(super.getX() - speed), super.getY());
		boolean down = Game.input.down.down && Game.room.isFree(super.getX(), (int)(super.getY() + speed));
		boolean up = Game.input.up.down && Game.room.isFree(super.getX(), (int)(super.getY() - speed));

		if(up) {
			setY(getY() - speed);
		}else if(down) {
			setY(getY() + speed);
		}
		if(left) {
			setX(getX() - speed);
		}else if(right) {
			setX(getX() + speed);
		}
		
		// Utilizar esse código para centralizar a câmera no centralizado quando existir um mapa
		Game.camera.setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , Game.room.WIDTH * World.TILE_SIZE - Game.WIDTH));
		Game.camera.setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , Game.room.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

}
