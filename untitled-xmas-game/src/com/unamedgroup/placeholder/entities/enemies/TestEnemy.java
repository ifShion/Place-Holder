package com.unamedgroup.placeholder.entities.enemies;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class TestEnemy extends Enemy {

	public TestEnemy(int x, int y, int width, int height, SpriteSheet spriteSheet, int depth, int speed, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
		super(x, y, width, height, spriteSheet, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
}
