package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;

public class Entity extends Animation {
	/* Inicializar os sprites iniciais de todas entidades aqui */
    protected double x;           // Coordenada X na tela
	protected double y;           // Coordenada Y na tela
	public double speed;

	//private Animation animation;
	private SpriteSheet sprite;

	private int maskX;
	private int maskY;
	private int maskW;
	private int maskH;

	public int depth;

	public Entity(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed){
		super(animationSpeed, width, height, spriteSheet.getSpriteSheet().getWidth()/width, spriteSheet.getSpriteSheet().getHeight()/height);
		this.x = x;
		this.y = y;
		
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
	
	public void render(Graphics g) {
		g.drawImage(sprite.getSpriteSheet(),
			this.getX() - Game.camera.getX(),					// Coordenada X na tela
			this.getY() - Game.camera.getY(),					// Coordenada Y na tela
			(int) (this.getX() - Game.camera.getX()+width),		// Largura do Sprite
			(int) (this.getY() - Game.camera.getY()+height),	// Altura do sprite
			(int) spriteX,										// Coordenada X1 na imagem
			(int) spriteY,										// Coordenada Y1 na imagem
			(int) (spriteX+width),								// Coordenada X2 na imagem
			(int) (spriteY+height),								// Coordenada Y2 na imagem
			null);
	}
	
}

