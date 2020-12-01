package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Alpha;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

import font.CustomFont;

/**
 * 
 * Teremos um papel importante com as classes states nesse projeto:
 *  Temos que cuidar de cada classe dessa como se fosse um jogo único,
 * então todas terão seu próprio mapa, Lista de entidades e jogador
 * @author Nathan
 */

 @SuppressWarnings("unused")
public class State_00 extends State {
    private Alpha alpha;
    private Toast missao, local, local2;

    public State_00(int id, Handler handler){
		super(id, handler);
        //alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 16, Game.spriteTeste, 4, 2, 5, 4, 1, 2, 0, handler);
        alpha = new Alpha(Game.WIDTH/2, Game.HEIGHT/2, 16, 24, Game.alphaTeste, 4, 3, 1, 4, 10, 0, 0, handler);
        alpha.setMask(4, 7, 8, 17);
        handler.getGame().setPlayer(alpha);

		missao = new Toast("Alpha",Game.WIDTH/2,30, 10, CustomFont.getFont("rumbletumble.TTF", 45));
		local = new Toast("No armazém dos", Game.WIDTH/2, 120,13, CustomFont.getFont("K26ToyBlocks123.ttf", 20));
		local2 = new Toast("Brinquedos", Game.WIDTH/2, 140,13, CustomFont.getFont("K26ToyBlocks123.ttf", 20));
    }

    @Override
    public void init() {
    	handler.getGame().setPlayer(alpha);
        handler.getGame().updateEntities();
        handler.getGame().setCurrentMapID(1001);
        missao.doit();
        local.doit();
        local2.doit();
    }

    @Override
    public void render(Graphics g) {
        missao.draw((Graphics2D)g);
        local.draw((Graphics2D)g);
        local2.draw((Graphics2D)g);
    }
    
}
