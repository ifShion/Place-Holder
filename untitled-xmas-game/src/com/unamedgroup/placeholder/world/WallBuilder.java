package com.unamedgroup.placeholder.world;

import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.tiles.SolidTile;
import com.unamedgroup.placeholder.world.tiles.Tile;

public class WallBuilder {

	public WallBuilder(int xx, int yy, int[] pixels, Tile[] tiles, int WIDTH, int HEIGHT, Handler handler) {
		if(xx == 0 || yy == 0 || xx == WIDTH -1 || yy == HEIGHT -1) {
			tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
					handler.getGame().currentMap.getSprite(1 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
		}else {
			//essa linha abaixo é para testes
			tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
					handler.getGame().currentMap.getSprite(10 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			// bloco interno
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFFFFFFFF && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(1 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// pilar
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(10 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// quinas internas
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(0 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(2 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(0 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFFFFFFFF && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(2 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// quinas externas
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(9 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(11 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(11 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(9 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// laterais
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(2 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(1 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(1 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(0 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// t juntor 
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(10 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(9 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFFFFFFFF && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(10 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFFFFFFFF && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(11 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// Laterias finas
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(3 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(4 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// Quinas finas
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(3 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(3 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(5 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(5 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// Cruz
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(7 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// beiras
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(7 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(8 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(7 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(6 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000 && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(10 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(9 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFFFFFFFF && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(10 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFFFFFFFF && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFFFFFFFF) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(11 * World.TILE_SIZE, 1 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			// T juntor fino
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFF000000 && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(6 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFF000000 && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(8 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFF000000 && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFFFFFFFF && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(8 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
			if(pixels[xx - 1 + (yy * WIDTH)] == 0xFFFFFFFF && // esquerda
					pixels[xx - 1 + ((yy - 1)* WIDTH)] == 0xFF000000 && // esquerda, cima
					pixels[xx + ((yy - 1) * WIDTH)] == 0xFFFFFFFF && // cima
					pixels[xx + 1 + ((yy - 1) * WIDTH)] == 0xFF000000 && // direita, cima
					pixels[xx + 1 + (yy * WIDTH)] == 0xFF000000 && // direita
					pixels[xx + 1 + ((yy + 1) * WIDTH)] == 0xFF000000 && //direita, baixo
					pixels[xx + ((yy + 1) * WIDTH)] == 0xFFFFFFFF && // baixo
					pixels[xx - 1 + ((yy + 1) * WIDTH)] == 0xFF000000) { // baixo, esquerda
				tiles[xx + (yy * WIDTH)] = new SolidTile(xx * World.TILE_SIZE, yy * World.TILE_SIZE,
						handler.getGame().currentMap.getSprite(6 * World.TILE_SIZE, 2 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), handler);
			}
		}
	}
}
