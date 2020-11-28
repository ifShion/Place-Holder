package com.unamedgroup.placeholder.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
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
	protected String status;		// Ainda estou testando esse argumento, utilizo para definir a animação em uma classe filha
	protected boolean walking;
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
	 * @param handler
	 */

	public Player(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler){
		super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
	}

	public void tick() {
		super.tick();
		//Alteração: mudei a condição para o personagem poder se mover. Implementando um sistema de colisão simples

		boolean right = handler.getInputHandler().right.down && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() + speed), super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH());
		boolean left = handler.getInputHandler().left.down && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() - speed), super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH());;
		boolean down = handler.getInputHandler().down.down && handler.getGame().room.isFree(super.getX() + super.getMaskX(), (int)(super.getY() + super.getMaskY() + speed), super.getMaskW(), super.getMaskH());;
		boolean up = handler.getInputHandler().up.down && handler.getGame().room.isFree(super.getX() + super.getMaskX(), (int)(super.getY() + super.getMaskY() - speed), super.getMaskW(), super.getMaskH());;

		if(up||down||left||right){
			walking = true;
		}else{
			walking = false;
		}

		// Eu resolvi o problema de caminhar na diagonal (ainda tem alguns bugs) - Daniel Nogueira 
		boolean flag=false;  //variável para usar caso dois botôes estarem sendo apertados ao mesmo tempo

		if (up && left && !right && !down){
			setY(getY() - (speed-(speed*0.45)));
			setX(getX() - (speed-(speed*0.45)));
			status = "left";
			flag=true;
		}
		if (up && right && !left && !down){
			setY(getY() - (speed-(speed*0.45)));
			setX(getX() + (speed-(speed*0.2)));
			status = "right";
			flag=true;
		}
		if (down && left && !right && !up){
			setY(getY() + (speed-(speed*0.2)));
			setX(getX() - (speed-(speed*0.45)));
			status = "left";
			flag=true;
		}
		if (down && right && !left && !up){
			setY(getY() + (speed-(speed*0.2)));
			setX(getX() + (speed-(speed*0.2)));
			status = "right";
			flag=true;
		}

		if (flag) {
			handler.getCamera().setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , handler.getGame().room.WIDTH * World.TILE_SIZE - Game.WIDTH));
			handler.getCamera().setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , handler.getGame().room.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
			return;  //caso os dois botões tenham sido apertados, o método acaba
		}

		if(up) {
			setY(getY() - speed);
			status = "up";
		}else if(down) {
			setY(getY() + speed);
			status = "down";
		}
		if(left) {
			setX(getX() - speed);
			status = "left";
		}else if(right) {
			setX(getX() + speed);
			status = "right";
		}
		
		// Utilizar esse código para centralizar a câmera no centralizado quando existir um mapa
		handler.getCamera().setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , handler.getGame().room.WIDTH * World.TILE_SIZE - Game.WIDTH));
		handler.getCamera().setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , handler.getGame().room.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.red);
		//g.fillRect((int)(x-handler.getCamera().getX()+super.getMaskX()),(int) (y-handler.getCamera().getY()+super.getMaskY()), super.getMaskW(), super.getMaskH());
	}

}
