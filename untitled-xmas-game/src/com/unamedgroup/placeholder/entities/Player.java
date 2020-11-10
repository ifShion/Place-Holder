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

	public Player(int x, int y, int width, int height, SpriteSheet sprite, int numSpritesX, int numSpritesY, int depth, int speed) {
		super(x, y, width, height, sprite, numSpritesX, numSpritesY, depth, speed);
		
	}
	
	public void tick() {
		//Alteração: mudei a condição para o personagem poder se mover. Implementando um sistema de colisão simples

		boolean right = Game.input.right.down && World.isFree((int)(super.getX() + speed), super.getY());
		boolean left = Game.input.left.down && World.isFree((int)(super.getX() - speed), super.getY());
		boolean down = Game.input.down.down && World.isFree(super.getX(), (int)(super.getY() + speed));
		boolean up = Game.input.up.down && World.isFree(super.getX(), (int)(super.getY() - speed));

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
		Game.camera.setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , World.WIDTH * World.TILE_SIZE - Game.WIDTH));
		Game.camera.setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , World.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

}
