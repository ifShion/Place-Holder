package com.unamedgroup.placeholder.world.tiles;

import java.awt.image.BufferedImage;
/**
 * Classe de um dos tiles que constrói o mundo que determina que entidades com sistema de colisão implementado não podem transitar por ele
 * Pode ser abstrato e derivar muitos outros tiles. É usaddo principalmente pelo polimorfismo.
 * @author Daniel Neves
 */
public class SolidTile extends Tile {
    /**
     * Constrói um novo Tile sólido na coordenada x, y do mapa.
	 * Se pretende criar um novo tile invísivel, basta apenas passar o valor sprite como null para o super
	 * e exigir apenas o x, y
     * @param x
     * @param y
     * @param sprite
     */
    public SolidTile(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }
}