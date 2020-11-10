package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;

public abstract class Entity {
	/* Inicializar os sprites iniciais de todas entidades aqui */

	protected double x; // Coordenada X na tela
	protected double y; // Coordenada Y na tela
	protected double width; // Largura do sprite
	protected double height; // Altura do Sprite
	protected double spriteX = 0; // Coordenada X dentro do arquivo de Imagem
	protected double spriteY = 0; // Coordenada Y dentro do arquivo de Imagem

	public double speed;

	private SpriteSheet sprite;
	private Animation animation;

	private int maskX;
	private int maskY;
	private int maskW;
	private int maskH;

	public int depth;

	// Utilizei a sobrecarga para poder mandar s� o caminho em vez de criar um objeto de imagem onde for usar a entidade - @natescom
	public Entity(int x, int y, int width, int height, SpriteSheet spriteSheet, int numSpritesX, int numSpritesY, int depth, int speed) {
		init(x, y, width, height, spriteSheet, depth, speed);
		animation = new Animation(this, 20, numSpritesX, numSpritesY);
	}

	// Fiz esse init para poder usar uma sobrecarga no construtor - @natescom //
	private void init(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;	

		this.sprite = spriteSheet;

		this.maskX = 0;
		this.maskY = 0;
		this.maskW = width;
		this.maskH = height;
		
		this.depth = depth;
		this.speed = speed;
	}
	

	/*Getters e Setters*/

	public SpriteSheet getSprite() {
		return sprite;
	}

	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public int getWidth() {
		return (int)width;
	}
	
	public int getHeight() {
		return (int)height;
	}
	
	public int getMaskX() {
		return maskX;
	}
	
	public int getMaskY() {
		return maskY;
	}
	
	public int getMaskW() {
		return maskW;
	}
	
	public int getMaskH() {
		return maskH;
	}
	 
	 public void setMask(int maskX , int maskY , int maskW , int maskH) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskW = maskW;
		this.maskH = maskH;
	 }
	 
	 public double calculateDistance(int x1 , int x2 , int y1 , int y2) {
		 double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2 , 2));
		 return distance;
	 }
	 
	public static boolean isColliding(Entity e1,Entity e2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.getMaskX() , e1.getY() + e1.getMaskY() , e1.getMaskW() , e1.getMaskH());
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.getMaskX() , e2.getY() + e2.getMaskY() , e2.getMaskW() , e2.getMaskH());
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void tick() {
		animation.tick();
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite.getSpriteSheet(),
			this.getX() - Game.camera.getX(),
			this.getY() - Game.camera.getY(),
			(int) (this.getX() - Game.camera.getX()+width),
			(int) (this.getY() - Game.camera.getY()+height),
			(int) spriteX,
			(int) spriteY,
			(int) (spriteX+width),
			(int) (spriteY+height),
			null);
	}
	
}

