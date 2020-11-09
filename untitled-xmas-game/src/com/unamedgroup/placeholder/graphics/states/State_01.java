package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.unamedgroup.placeholder.entities.Animation;
import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.StateManager;

public class State_01 implements State {
    private int ID;
    public static List<Entity> entities;
    private Animation patinho;

    public State_01(int id){
        this.ID = id;
        init();
    }


    @Override
    public void init() {
        entities = new ArrayList<>();
        patinho = new Animation(50, 50, 44, 50, "/patinho.png", 1, 1, 10);
        entities.add(patinho);

    }

    @Override
    public void tick() {
        if(Game.input.prime.clicked)
            StateManager.setState(0);
        entities.forEach(entity -> entity.tick());
        

    }

    @Override
    public void render(Graphics g) {
        /**/
		//Desenho pixelado
		//Essa linha embaixo organiza as entidades na lista em ordem crescente de profundidade,
		//logo, o parâmetro depth de toda entidade irá ditar se ele é renderizado em cima de outra entidade.
		entities.sort((e1, e2) -> Integer.compare(e1.depth, e2.depth));
		for (Entity entity : entities) entity.render(g);

        //Essa linha desenha uma imagem, nesse caso, foi um recorte da Sprite Sheet
		g.drawImage(Game.spriteTeste.getSprite(80, 0, 48, 16), 50, 20, null);
        
    }
    
}
