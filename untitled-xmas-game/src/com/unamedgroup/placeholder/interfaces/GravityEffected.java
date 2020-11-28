package com.unamedgroup.placeholder.interfaces;

/**
 * Todas entidades, tiles ou outros objetos do jogo que implementarem essa interface,
 * deverão deixar explícito como a queda e a gravidade os afetam. A gravidade nos mundos
 * é constante e deverá ser levada em consideração para as ações em fall.
 * @author Daniel Neves
 *
 */
public interface GravityEffected {
	
	public static final double GRAVITY = 0.4022;
	
	public void fall();
}
