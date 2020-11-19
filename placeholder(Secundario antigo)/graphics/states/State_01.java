package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.main.StateManager;

public class State_01 implements State {
    private int ID;
    private Handler handler;

    public State_01(int id, Handler handler){
        this.handler = handler;
        this.ID = id;
    }


    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void tick() {
        if(handler.getInputHandler().prime.clicked)
            StateManager.setState(0);
        handler.getGame().entities.forEach(entity -> entity.tick());
        

    }

    @Override
    public void render(Graphics g) {
        /**/
		//Desenho pixelado
		//Essa linha embaixo organiza as entidades na lista em ordem crescente de profundidade,
		//logo, o par�metro depth de toda entidade ir� ditar se ele � renderizado em cima de outra entidade.
		handler.getGame().entities.sort((e1, e2) -> Integer.compare(e1.depth, e2.depth));
		for (Entity entity : handler.getGame().entities) entity.render(g);

        //Essa linha desenha uma imagem, nesse caso, foi um recorte da Sprite Sheet
		g.drawImage(handler.getGame().spriteTeste.getSprite(80, 0, 48, 16), 50, 20, null);
        
    }
    
}
