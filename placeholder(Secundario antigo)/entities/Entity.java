package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.unamedgroup.placeholder.main.Game;

public abstract class Entity {
	/*Inicializar os sprites iniciais de todas entidades aqui*/
	
	protected double x;
	protected double y;
	private double width;
	private double height;
	
	public double speed;
	
	private BufferedImage sprite;
	
	 private int maskX;
	 private int maskY;
	 private int maskW;
	 private int maskH;
	 
	 public int depth;
	
	public Entity(int x , int y , int width , int height , BufferedImage sprite , int depth , int speed){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskX = 0;
		this.maskY = 0;
		this.maskW = width;
		this.maskH = height;
		
		this.depth = depth;
		this.speed = speed;
	}
	
	/*Getters e Setters*/
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
	 
	 public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
			
			@Override
			public int compare(Entity n0,Entity n1) {
				if(n1.depth < n0.depth)
					return +1;
				if(n1.depth > n0.depth)
					return -1;
				return 0;
			}
			
		};
	 
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
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite , this.getX() - Game.camera.getX() , this.getY() - Game.camera.getY() , null);

	}
	
}

