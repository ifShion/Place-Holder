package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.World;
import com.unamedgroup.placeholder.entities.Player;

/**
 * 
 * Teremos um papel importante com as classes states nesse projeto:
 *  Temos que cuidar de cada classe dessa como se fosse um jogo único,
 * então todas terão seu próprio mapa, Lista de entidades e jogador
 * @author Nathan
 */

 @SuppressWarnings("unused")
public class State_00 implements State {
    private int ID;
    //Trocar o Player para o protagonista específico
    public static Player alpha;
	private int id2;
	private Handler handler;

    public State_00(int id, Handler handler){
        id2 = id;
		this.ID = id;
		this.handler = handler;
        alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 16, Game.spriteTeste, 4, 2, 5, 4, 1, 2, 0, handler);
    }

    @Override
    public void init() {
        alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 16, Game.spriteTeste, 4, 2, 5, 4, 1, 2, 0, handler);
    	// Temos um problema aqui: se a gente colocar a speed do personagem um número quebrado, ele anda muito rápido pra um lado
    	// e muito devagar pro outro ;-;. Ainda bem q esse sistema de movimentação é só um protótipo.
        alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 24, Game.spriteTeste, 4, 2, 1, 4, 2, 16, 16, handler);
        alpha.setMask(3, 12, 10, 12);
        Game.currentMapID = 1001;
        Game.player = alpha;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        /**/
		//Desenho pixelado
		//Essa linha embaixo organiza as entidades na lista em ordem crescente de profundidade,
		//logo, o par�metro depth de toda entidade ir� ditar se ele � renderizado em cima de outra entidade.
        
    }
    
}
