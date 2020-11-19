package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.main.StateManager;
import com.unamedgroup.placeholder.entities.Entity;

/**
 * 
 * Teremos um papel importante com as classes states nesse projeto:
 *  Temos que cuidar de cada classe dessa como se fosse um jogo único,
 * então todas terão seu próprio mapa, Lista de entidades e jogador
 * @author Nathan
 */

public class State_00 implements State {
    private int ID;
    private Handler handler;

    public State_00(int id, Handler handler){
        this.handler = handler;
        this.ID = id;
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {
        if(handler.getInputHandler().prime.clicked)
            StateManager.setState(1);
        handler.getGame().entities.forEach(entity -> entity.tick());
    }

    @Override
    public void render(Graphics g) {
        // Eu poderia ter colocado essa linha em Game, mas coloquei aqui para lembrar de implementar vários mapas no
        //jogo
        handler.getGame().worldTeste.render(g);
        /**/
		//Desenho pixelado
		//Essa linha embaixo organiza as entidades na lista em ordem crescente de profundidade,
		//logo, o par�metro depth de toda entidade ir� ditar se ele � renderizado em cima de outra entidade.
		handler.getGame().entities.sort((e1, e2) -> Integer.compare(e1.depth, e2.depth));
		for (Entity entity : handler.getGame().entities) entity.render(g);

        //Essa linha desenha uma imagem, nesse caso, foi um recorte da Sprite Sheet
		g.drawImage(handler.getGame().spriteTeste.getSprite(80, 0, 48, 16), 30, 10, null);
        
    }
    
}
