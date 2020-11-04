package com.unamedgroup.placeholder.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.InputHandler;
/**
 * É bom essa classe ser abstrata tbm já q vai ter 5 personagens
 * Classe de teste
 * @author Daniel Neves
 *
 */
public class Player extends Entity {
	Game game;
	InputHandler input;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite, int depth, int speed, Game game, InputHandler input) {
		super(x, y, width, height, sprite, depth, speed);
		
		this.game = game;
		this.input = input;
	}
	
	public void tick() {
		if(input.up.clicked) {
			setY(getY() - speed);
		}else if(input.right.clicked) {
			setX(getX() + speed);
		}else if(input.down.clicked) {
			setY(getY() + speed);
		}else if(input.left.clicked) {
			setX(getX() - speed);
		}
	
		
		// Utilizar esse código para centralizar a câmera no centralizado quando existir um mapa
//		Game.camera.setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , World.WIDTH * World.TILE_SIZE - Game.WIDTH));
//		Game.camera.setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , World.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(super.getX() - Game.camera.getX() , super.getY() - Game.camera.getY(), super.getWidth() , super.getHeight());
	}

}
