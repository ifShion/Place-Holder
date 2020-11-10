package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Game;
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
    public static Player alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 16, Game.spriteTeste, 4, 1, 1, 2);

    public State_00(int id){
        this.ID = id;
        init();
    }

    @Override
    public void init() {

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

        //Essa linha desenha uma imagem, nesse caso, foi um recorte da Sprite Sheet
		g.drawImage(Game.spriteTeste.getSprite(80, 0, 48, 16), 30, 10, null);
        
    }
    
}
