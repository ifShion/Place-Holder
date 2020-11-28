package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.entities.players.Alpha;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

@SuppressWarnings("unused")
public class State_01 extends State {
    private Alpha alpha;

    public State_01(int id, Handler handler){
        super(id, handler);
        alpha = new Alpha(Game.WIDTH/2, Game.HEIGHT/2, 64, 64, new SpriteSheet("/red-teste-spritesheet.png"), 4, 4, 5, 4, 4, 0, 0, handler);
        alpha.setMask(24, 24, 16, 34);
        handler.getGame().setPlayer(alpha);
    }


    @Override
    public void init() {
        handler.getGame().setCurrentMapID(1000);
        handler.getGame().setPlayer(alpha);
        handler.getGame().updateEntities();
    }

    @Override
    public void tick() {
         if(handler.getInputHandler().prime.clicked){
             handler.getStateManager().setState(0);
             handler.getGame().changeCurrentMapID(handler.getGame().getCurrentMapID());
         }
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
