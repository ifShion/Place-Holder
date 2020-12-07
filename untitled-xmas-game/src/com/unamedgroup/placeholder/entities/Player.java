package com.unamedgroup.placeholder.entities;

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
	protected boolean animated;
	protected int direction;
	protected boolean moveable;
	private int damageCooldown = 120;

	protected int hp;
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
		
		this.animated = true;
		this.direction = 1;
		this.moveable = true;
	}
	
	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void hitPlayer(int damage) {
		if(damageCooldown == 0) {
			damageCooldown = 30;
			this.hp-=damage;
		}
	}
	//Verifica se o player está morto, e se estiver reinicia
	//o jogo na posição de spawn. - Euler Lima
	public void isDead(int x, int y) {
		if(this.hp == 0) {
			handler.getGame().getPlayer().setX(x);
			handler.getGame().getPlayer().setY(y);
			this.hp = 5;
		}
	}
	//Sistema inicial de cair no vazio - Euler Lima
	public void fallVoid(int x, int y) {
		if(handler.getGame().getPlayer().getY() >= 1112) {
			this.hp--;
			handler.getGame().getPlayer().setX(x);
			handler.getGame().getPlayer().setY(y);
		}
	}

	public void tick() {
		super.tick();
		
		//Alteração: mudei a condição para o personagem poder se mover. Implementando um sistema de colisão simples

		boolean right = handler.getInputHandler().right.down && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() + speed), super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH());
		boolean left = handler.getInputHandler().left.down && handler.getGame().room.isFree((int)(super.getX() + super.getMaskX() - speed), super.getY() + super.getMaskY(), super.getMaskW(), super.getMaskH());;
		
		// Eu resolvi o problema de caminhar na diagonal (ainda tem alguns bugs) - Daniel Nogueira 
		boolean flag=false;  //variável para usar caso dois botôes estarem sendo apertados ao mesmo tempo

		if (flag) {
			handler.getCamera().setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , handler.getGame().room.WIDTH * World.TILE_SIZE - Game.WIDTH));
			handler.getCamera().setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , handler.getGame().room.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
			return;  //caso os dois botões tenham sido apertados, o método acaba
		}

		if(moveable) {
			if(left) {
				setX(getX() - speed);
				status = "left";
				direction = -1;
			}else if(right) {
				setX(getX() + speed);
				status = "right";
				direction = 1;
			}else {
				status = "idle";
			}
		}
		
		damageCooldown--;
		if(damageCooldown < 0) damageCooldown = 0;
		
		// Utilizar esse código para centralizar a câmera no centralizado quando existir um mapa
		handler.getCamera().setX(Camera.clamp(super.getX() - Game.WIDTH/2 , 0 , handler.getGame().room.WIDTH * World.TILE_SIZE - Game.WIDTH));
		handler.getCamera().setY(Camera.clamp(super.getY() - Game.HEIGHT/2 , 0 , handler.getGame().room.HEIGHT * World.TILE_SIZE - Game.HEIGHT));
	}

	public void render(Graphics g) {
		super.render(g);
//		g.setColor(Color.red);
//		g.fillRect((int)(x-handler.getCamera().getX()+super.getMaskX()),(int) (y-handler.getCamera().getY()+super.getMaskY()), super.getMaskW(), super.getMaskH());
	}

}
