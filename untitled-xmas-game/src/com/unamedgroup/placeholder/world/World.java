package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.unamedgroup.placeholder.entities.enemies.CannonEnemy;
import com.unamedgroup.placeholder.entities.enemies.HuggerEnemy;
import com.unamedgroup.placeholder.entities.enemies.WalkerEnemy;
import com.unamedgroup.placeholder.entities.enemies.TrackerEnemy;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.tiles.FreeTile;
import com.unamedgroup.placeholder.world.tiles.SolidTile;
import com.unamedgroup.placeholder.world.tiles.Tile;

/**
 * Essa classe traduz uma imagem e converte suas representações em pixels em
 * tiles no mapa. Esse sistema interpreta cada cor da imagem e atribui a ela um
 * valor em Tile
 * 
 * Já vejo um problema com essa classe, pq vamos precisar de diferentes files
 * para os diferentes jogos Por mais q ela funcione para determinar colisões, os
 * sprites e comportamentos de tiles e entidades especiais não conseguem
 * ser gerenciadas por aqui.
 * 
 * @author Daniel Neves
 */
public abstract class World {
	/*
.	 * Alterações: Mudei os atributos státicos, vai ser mais demorado chamá-los
	 * agora pq vai ter que chamar Game, mas agora todos eles têm tamanhos e tiles
	 * diferentes e podemos cuidar de cada um separadamente, em vez de instanciar um
	 * novo cada vez q mudar de sala
	 */
	public Tile[] tiles;
	public int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	public String path;
	private Handler handler;
	
	/**
	 * Recebe um caminho até um arquivo de imagem q contem os dados do mapa a ser
	 * construído;
	 * 
	 * @param path
	 * @param handler
	 */
	public World(String path, Handler handler) {
		this.handler = handler;
		this.path = path;
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			int pixelAtual;
			for (int xx = 0; xx < WIDTH; xx++) {
				for (int yy = 0; yy < HEIGHT; yy++) {
					pixelAtual = pixels[xx + (yy * WIDTH)];
					switch(pixelAtual) {
					case 0xFF000000://preto
					tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
						handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);
						break;
						
					case 0xFFFFFFFF://branco
						new WallBuilder(xx, yy, pixels, tiles, WIDTH, HEIGHT, handler);
						break;
					case 0xFF0000FF://azul
						handler.getGame().getPlayer().setX(xx*TILE_SIZE);
						handler.getGame().getPlayer().setY(yy*TILE_SIZE);
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);						
						break;
					case 0xFFFF0000: //vermelho
						WalkerEnemy e = new WalkerEnemy(xx * World.TILE_SIZE, yy * World.TILE_SIZE, 16, 16, Game.spriteTeste, 1, 1, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
						Game.entities.add(e);
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);
						break;
					case 0xFFFF8000: //laranja
						TrackerEnemy te = new TrackerEnemy(xx * World.TILE_SIZE, yy * World.TILE_SIZE, 16, 16, Game.spriteTeste, 1, 2, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
						te.setMask(3, 4, 10, 8);
						Game.entities.add(te);
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);
						break;
					case 0xFF00FF00: //verde
						CannonEnemy cl = new CannonEnemy(xx * World.TILE_SIZE, yy * World.TILE_SIZE, 24, 32, -2, Game.nutCrackerTest, 1, 2, 4, 8, 2, 0 , 0, handler);
						Game.entities.add(cl);
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);
						break;
					case 0xFF00FFFF: //ciano
						CannonEnemy cr = new CannonEnemy(xx * World.TILE_SIZE, yy * World.TILE_SIZE, 24, 32, 2, Game.nutCrackerTest, 1, 2, 4, 8, 2, 0 , 0, handler);
						Game.entities.add(cr);
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);
						break;
					case 0xFFFF00FF: //magenta
						HuggerEnemy he = new HuggerEnemy(xx * World.TILE_SIZE, yy * World.TILE_SIZE, 16, 16, Game.spriteTeste, 1, 1, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
						Game.entities.add(he);
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);
						break;
					default:
						tiles[xx + (yy * WIDTH)] = new FreeTile(xx * TILE_SIZE, yy * TILE_SIZE,
								handler.getGame().currentMap.getSprite(4 * TILE_SIZE, 1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), handler);						
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
	public static double getDistance(double x1, double x2) {
		return Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2));
	}

	/**
	 * Método de colisão utilizado se a largura ou altura do objeto for diferente do
	 * tamanho padrão do tile
	 * 
	 * @param xNext
	 * @param yNext
	 * @param width
	 * @param height
	 * @return se o espaço que se pretende visitar é acessível
	 */
	
	public boolean isFree( int xNext , int yNext , int width , int height) {
		
		int x1 = xNext / (TILE_SIZE);
		int y1 = yNext / (TILE_SIZE);

		int x2 = (xNext + width - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;

		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + height - 1) / TILE_SIZE;

		int x4 = (xNext + width - 1) / TILE_SIZE;
		int y4 = (yNext + height - 1) / TILE_SIZE;

		return !((tiles[x1 + y1 * WIDTH] instanceof SolidTile) || (tiles[x2 + y2 * WIDTH] instanceof SolidTile)
				|| (tiles[x3 + y3 * WIDTH] instanceof SolidTile) || (tiles[x4 + y4 * WIDTH] instanceof SolidTile));
	}

	/**
	 * Método de colisão usado para objetos que tem largura e altura iguais ao
	 * tamanho padrão do tile
	 * 
	 * @param xNext
	 * @param yNext
	 * @return se o espaço que se pretende visitar está livre
	 */
	/*public boolean isFree( int xNext , int yNext) {
		
		int x1 = xNext / (TILE_SIZE);
		int y1 = yNext / (TILE_SIZE);
		
		int x2 = (xNext + TILE_SIZE - 1)/ TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		return !((tiles[x1 + y1 * WIDTH] instanceof SolidTile) ||
				 (tiles[x2 + y2 * WIDTH] instanceof SolidTile) ||
				 (tiles[x3 + y3 * WIDTH] instanceof SolidTile) ||
				 (tiles[x4 + y4 * WIDTH] instanceof SolidTile)); 
	}*/
	/**
	 * Método renderiza apenas os tiles que estão adjacentes e internos aos limites
	 * da tela do jogo
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		
		int xStart = handler.getCamera().getX() >> 4;
		int yStart = handler.getCamera().getY() >> 4;

		int xFinal = xStart + (Game.WIDTH >> 3);
		int yFinal = yStart + (Game.HEIGHT >> 3);

		for (int xx = xStart; xx <= xFinal; xx++) {
			for (int yy = yStart; yy <= yFinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
//				g.drawImage(Game.room.map.getSpriteSheet(), 0 - Game.camera.getX(), 0 - Game.camera.getY(), Game.room.map.getSpriteSheet().getWidth(), Game.room.map.getSpriteSheet().getHeight(), null);
			}
		}
	}
}
