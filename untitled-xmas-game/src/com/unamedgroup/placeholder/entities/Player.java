package com.unamedgroup.placeholder.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Camera;
import com.unamedgroup.placeholder.world.World;
/**
 * É bom essa classe ser abstrata tbm já q vai ter 5 personagens
 * Classe de teste
 * 
 * O objetivo dessa classe agora é adaptarmo-na para se tornar uma classe abstrata, já que 
 * teremos 6 jogadores físicos no jogo (incluindo tower defense)
 * @author Daniel Neves
 *
 */
public class Player extends Entity {
	
	private Handler handler;

	public Player(int x, int y, int width, int height, BufferedImage sprite, int depth, int speed, Handler handler) {
		super(x, y, width, height, sprite, depth, speed);
		this.handler = handler;
		
	}
	
	public void tick() {
		//Alteração: mudei a condição para o personagem poder se mover. Implementando um sistema de colisão simples

		boolean right = handler.getInputHandler().right.down && World.isFree((int)(super.getX() + speed), super.getY());
		boolean left = handler.getInputHandler().left.down && World.isFree((int)(super.getX() - speed), super.getY());
		boolean down = handler.getInputHandler().down.down && World.isFree(super.getX(), (int)(super.getY() + speed));
		boolean up = handler.getInputHandler().up.down && World.isFree(super.getX(), (int)(super.getY() - speed));

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
		handler.getCamera().setX(Camera.clamp(super.getX() - handler.getGame().WIDTH/2 , 0 , World.WIDTH * World.TILE_SIZE - handler.getGame().WIDTH));
		handler.getCamera().setY(Camera.clamp(super.getY() - handler.getGame().HEIGHT/2 , 0 , World.HEIGHT * World.TILE_SIZE - handler.getGame().HEIGHT));
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

}
