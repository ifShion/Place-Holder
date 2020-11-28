package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
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
public class State_00 extends State {
    private Player alpha;

    public State_00(int id, Handler handler){
		super(id, handler);
        //alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 16, Game.spriteTeste, 4, 2, 5, 4, 1, 2, 0, handler);
        alpha = new Player(Game.WIDTH/2, Game.HEIGHT/2, 16, 24, new SpriteSheet("/testSpriteSheet1.png"), 4, 2, 1, 4, 2, 16, 16, handler);
        alpha.setMask(3, 12, 10, 12);
        handler.getGame().setPlayer(alpha);
    }

    @Override
    public void init() {
        handler.getGame().setCurrentMapID(1001);
        handler.getGame().setPlayer(alpha);
        handler.getGame().updateEntities();
    }

    @Override
    public void tick() {
        if(handler.getInputHandler().prime.clicked){
            handler.getStateManager().setState(1);
            handler.getGame().changeCurrentMapID(handler.getGame().getCurrentMapID());
        }
    }

    @Override
    public void render(Graphics g) {
       
        
    }
    
}
