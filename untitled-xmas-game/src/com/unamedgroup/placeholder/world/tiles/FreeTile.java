package com.unamedgroup.placeholder.world.tiles;

import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.main.Handler;
/**
 * Classe de um dos tiles que constrói o mundo que determina que entidades com sistema de colisão implementado podem transitar por ele
 * Pode ser abstrato e derivar muitos outros tiles. É usaddo principalmente pelo polimorfismo.
 * @author Daniel Neves
 */
public class FreeTile extends Tile {
	/**
	 * Constrói um novo Tile livre na coordenada x, y do mapa.
	 * Se pretende criar um novo tile invísivel, basta apenas passar o valor sprite como null para o super
	 * e exigir apenas o x, y
	 * @param x
	 * @param y
	 * @param sprite
	 * @param handler
	 */
    public FreeTile(int x, int y, BufferedImage sprite, Handler handler) {
		super(x, y, sprite, handler);
	}
}
