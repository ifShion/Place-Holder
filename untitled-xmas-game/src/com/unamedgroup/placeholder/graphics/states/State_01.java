package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.entities.players.Alpha;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Game;

@SuppressWarnings("unused")
public class State_01 implements State {
    private int ID;
    public static Alpha alpha;

    public State_01(int id){
        this.ID = id;
        alpha = new Alpha(Game.WIDTH/2, Game.HEIGHT/2, 64, 64, new SpriteSheet("/red-teste-spritesheet.png"), 4, 4, 5, 4, 4, 0, 0);
    }


    @Override
    public void init() {
        alpha = new Alpha(Game.WIDTH/2, Game.HEIGHT/2, 64, 64, new SpriteSheet("/red-teste-spritesheet.png"), 4, 4, 5, 4, 4, 0, 0);
        Game.currentMapID = 1000;
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

        //Essa linha desenha uma imagem, nesse caso, foi um recorte da Sprite Sheet
		g.drawImage(Game.spriteTeste.getSprite(80, 0, 48, 16), 50, 20, null);
        
    }
    
}
