package com.unamedgroup.placeholder.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Essa classe lê a imagem q é passada para ela pelo construtor e armazena a imagem na
 * variável sheet. Também tem um método para buscar um sprite dentro da sheet
 */
public class SpriteSheet {
	private BufferedImage spriteSheet;
		
	public SpriteSheet(String path) {
		try {
			spriteSheet = ImageIO.read(getClass().getResource(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x , int y , int width , int height) {
		return spriteSheet.getSubimage(x, y, width, height);
	}
}