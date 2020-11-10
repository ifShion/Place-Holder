package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.world.tiles.*;

/**
 * Essa classe traduz uma imagem e converte suas representações em pixels em tiles no mmapa. Esse
 * sistema interpreta cada cor da imagem e atribui a ela um valor em Tile
 * 
 * Já vejo um problema com essa classe, pq vamos precisar de diferentes files para os diferentes jogos
 * Por mais q ela funcione para determinar colisões, os sprites e comportamentos de tiles e entidades
 * especiais não conseguem sergerenciadas por aqui.
 * TODO: Resolver esse problema. Talevz fazer essa classe abstrata ??????
 * @author Daniel Neves
 */
public abstract class World {
    public static Tile[] tiles;
	public static int WIDTH , HEIGHT;
	public static final int TILE_SIZE = 16;
	public String path;
	
	/**
	 * Recebe um caminho até um arquivo de imagem q contem os dados do mapa a ser construído;
	 * @param path
	 */
	public World(String path) {
		this.path = path;
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0 , 0 , WIDTH , HEIGHT , pixels , 0 , WIDTH);
			int pixelAtual;
			for(int xx = 0 ; xx < WIDTH ; xx++) {
				for(int yy = 0 ; yy < HEIGHT ; yy++) {
					pixelAtual = pixels[xx + (yy * WIDTH)];
					switch(pixelAtual) {
					case 0xFF000000://preto
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE , yy * TILE_SIZE , Tile.FREE_TILE);
						break;
					case 0xFFFFFFFF://branco
						tiles[xx + (yy * WIDTH)] = new SolidTile(xx * TILE_SIZE , yy * TILE_SIZE , Tile.SOLID_TILE);
						break;
					case 0xFFFFFF00://AMARELO
                        tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE , yy * TILE_SIZE , Tile.DOOR_TILE);
						break;
					default:
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE , yy * TILE_SIZE , Tile.FREE_TILE);
						break;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param x1
	 * @param x2
	 * @return a distância entre dois pontos no mapa
	 */
	public static double getDistance(double x1 , double x2) {
		return Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
	}

	/**
	 * Método de colisão utilizado se a largura ou altura do objeto for diferente do tamanho padrão do tile
	 * @param xNext
	 * @param yNext
	 * @param width
	 * @param height
	 * @return se o espaço que se pretende visitar é acessível
	 */
	
	public static boolean isFreeDynamic( int xNext , int yNext , int width , int height) {
		
		int x1 = xNext / (TILE_SIZE);
		int y1 = yNext / (TILE_SIZE);
		
		int x2 = (xNext + width - 1)/ TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + height - 1) / TILE_SIZE;
		
		int x4 = (xNext + width - 1) / TILE_SIZE;
		int y4 = (yNext + height - 1) / TILE_SIZE;
		
		return !((tiles[x1 + y1 * World.WIDTH] instanceof SolidTile) ||
				 (tiles[x2 + y2 * World.WIDTH] instanceof SolidTile) ||
				 (tiles[x3 + y3 * World.WIDTH] instanceof SolidTile) ||
				 (tiles[x4 + y4 * World.WIDTH] instanceof SolidTile));
	}
	
	/**
	 * Método de colisão usado para objetos que tem largura e altura iguais ao tamanho padrão do tile
	 * @param xNext
	 * @param yNext
	 * @return se o espaço que se pretende visitar está livre
	 */
	public static boolean isFree( int xNext , int yNext) {
		
		int x1 = xNext / (TILE_SIZE);
		int y1 = yNext / (TILE_SIZE);
		
		int x2 = (xNext + TILE_SIZE - 1)/ TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		return !((tiles[x1 + y1 * World.WIDTH] instanceof SolidTile) ||
				 (tiles[x2 + y2 * World.WIDTH] instanceof SolidTile) ||
				 (tiles[x3 + y3 * World.WIDTH] instanceof SolidTile) ||
				 (tiles[x4 + y4 * World.WIDTH] instanceof SolidTile)); 
	}
	/**
	 * Método renderiza apenas os tiles que estão adjacentes e internos aos limites da tela do jogo
	 * @param g
	 */
	public void render(Graphics g) {
		
		int xStart = Game.camera.getX() >> 4;
		int yStart = Game.camera.getY() >> 4;

		int xFinal = xStart + (Game.WIDTH >> 3);
		int yFinal = yStart + (Game.HEIGHT >> 4);
		
		for(int xx = xStart ; xx <= xFinal ; xx++) {
			for(int yy = yStart ; yy <= yFinal ; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
