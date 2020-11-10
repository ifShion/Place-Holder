package com.unamedgroup.placeholder.world.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.entities.Animation;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.world.World;
/**
 * Classe abstrata mãe de todos Tiles do mapa
 * @author Daniel Neves
 */
public abstract class Tile {
    public boolean animated = false;
	
	//Sprites dos tiles do mapa
    public static BufferedImage FREE_TILE = Game.spriteTeste.getSprite(World.TILE_SIZE * 3 , 0, World.TILE_SIZE , World.TILE_SIZE);
	public static BufferedImage SOLID_TILE = Game.spriteTeste.getSprite(World.TILE_SIZE * 2 , 0, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage DOOR_TILE = Game.spriteTeste.getSprite(World.TILE_SIZE * 4 , 0, World.TILE_SIZE, World.TILE_SIZE);
	
	private BufferedImage sprite;
	private SpriteSheet spriteSheet;
	private Animation animation;
	protected int x , y;
	/**
	 * * Constrói um novo Tile na coordenada x, y do mapa.
	 * Se pretende criar um novo tile invísivel, basta apenas passar o valor sprite como null para o super
	 * e exigir apenas o x, y
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		
		this.sprite = sprite;
	}
	/**
	 * Fiz esse construtor caso precisemos animar algum Tile
	 * @param x
	 * @param y
	 * @param spriteSheet
	 * @param animationSpeed
	 * @param numSpritesX
	 * @param numSpritesY
	 * @param initPosX
	 * @param initPosY
	 */
	public Tile(int x , int y , SpriteSheet spriteSheet, int animationSpeed, int numSpritesX, int numSpritesY, int initPosX, int initPosY) {
		animation = new Animation(animationSpeed, World.TILE_SIZE, World.TILE_SIZE, numSpritesX, numSpritesY, initPosX, initPosY);
		
		this.x = x;
		this.y = y;
		animated = true;
		this.spriteSheet = spriteSheet;
	}
	
	public double calculateDistance(int x1 , int x2 , int y1 , int y2) {
		double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2 , 2));
		return distance;
	}

	public void tick() {
		if(animated)
			animation.tick();
	}

	public void render(Graphics g) {
		if(animated){
			g.drawImage(spriteSheet.getSpriteSheet(),
			this.x - Game.camera.getX(),					// Coordenada X na tela
			this.y - Game.camera.getY(),					// Coordenada Y na tela
			(int) (this.y - Game.camera.getX()+World.TILE_SIZE),		// Largura do Sprite
			(int) (this.y - Game.camera.getY()+World.TILE_SIZE),	// Altura do sprite
			(int) animation.getSpriteX() + animation.getInitPosX(),	// Coordenada X1 na imagem
			(int) animation.getSpriteY() + animation.getInitPosY(),	// Coordenada Y1 na imagem
			(int) (animation.getSpriteX() + animation.getInitPosX()+World.TILE_SIZE),	// Coordenada X2 na imagem
			(int) (animation.getSpriteY() + animation.getInitPosY()+World.TILE_SIZE),	// Coordenada Y2 na imagem
			null);
		}else {
			g.drawImage(sprite , x - Game.camera.getX() , y - Game.camera.getY() , null);
		}
	}
}
