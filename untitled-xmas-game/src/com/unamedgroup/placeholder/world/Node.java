package com.unamedgroup.placeholder.world;

/**
 * Administra os vértices no algoritmo de caminho mínimo do A*
 * @author Daniel Neves
 *
 */
public class Node {

	public Vector2i tile;
	public Node parent;
	public double fCost,gCost,hCost;
	
	/**
	 * Cria um vértice para ser analisado
	 * @param tile
	 * @param parent
	 * @param gCost
	 * @param hCost
	 */
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = gCost + hCost;
	}
	
	
}
