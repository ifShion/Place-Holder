package com.unamedgroup.placeholder.world.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.world.World;
/**
 * Classe abstrata mãe de todos Tiles do mapa
 * @author Daniel Neves
 */
public abstract class Tile {
    //public boolean show = false;
	
	//Sprites dos tiles do mapa
    public static BufferedImage FREE_TILE = Game.spriteTeste.getSprite(World.TILE_SIZE * 3 , 0, World.TILE_SIZE , World.TILE_SIZE);
	public static BufferedImage SOLID_TILE = Game.spriteTeste.getSprite(World.TILE_SIZE * 2 , 0, World.TILE_SIZE, World.TILE_SIZE);
	
	private BufferedImage sprite;
	protected int x , y;
	/**
	 * * Constrói um novo Tile na coordenada x, y do mapa.
	 * Se pretende criar um novo tile invísivel, basta apenas passar o valor sprite como null para o super
	 * e exigir apenas o x, y
	 * @param x
	 * @param y
	 * @param sprite
	 */
	public Tile(int x , int y , BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	

	public void render(Graphics g) {
		//if(show)
			g.drawImage(sprite , x - Game.camera.getX() , y - Game.camera.getY() , null);
	}
}
