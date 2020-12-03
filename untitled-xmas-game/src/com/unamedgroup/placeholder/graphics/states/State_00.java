package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Alpha;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Hud;
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
    private Hud hud;

    public State_00(int id, Handler handler){
		super(id, handler);
        alpha = new Alpha(0, 0, 16, 24, Game.alphaTeste, 4, 3, 1, 4, 18, 0, 0, handler);
        alpha.setMask(4, 7, 8, 17);
        alpha.setHp(5);
        handler.getGame().setPlayer(alpha);
        hud = new Hud(handler);
    }

    @Override
    public void init() {
    	handler.getGame().setPlayer(alpha);
        handler.getGame().updateEntities();
        handler.getGame().setCurrentMapID(1001);
    }
    @Override
    public void tick() {
        hud.tick();
    }

    @Override
    public void render(Graphics g) {
        hud.render(g);
    }
    
}
