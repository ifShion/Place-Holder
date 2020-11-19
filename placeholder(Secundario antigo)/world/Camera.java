package com.unamedgroup.placeholder.world;

public class Camera {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public static int clamp(int atual , int min , int max) {
		if(atual < min) {
			atual = min;
		}
		
		if(atual > max) {
			atual = max;
		}
		
		return atual;
	}
}
