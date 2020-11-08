package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.StateManager;

public class State_01 implements State {
    private int ID;

    public State_01(int id){
        this.ID = id;
    }


    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void tick() {
        if(Game.input.prime.clicked)
            StateManager.setState(0);
        Game.entities.forEach(entity -> entity.tick());
        

    }

    @Override
    public void render(Graphics g) {
        /**/
		//Desenho pixelado
		//Essa linha embaixo organiza as entidades na lista em ordem crescente de profundidade,
		//logo, o parâmetro depth de toda entidade irá ditar se ele é renderizado em cima de outra entidade.
		Game.entities.sort((e1, e2) -> Integer.compare(e1.depth, e2.depth));
		for (Entity entity : Game.entities) entity.render(g);

        //Essa linha desenha uma imagem, nesse caso, foi um recorte da Sprite Sheet
		g.drawImage(Game.spriteTeste.getSprite(80, 0, 48, 16), 50, 20, null);
        
    }
    
}
