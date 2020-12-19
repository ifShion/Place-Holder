package com.unamedgroup.placeholder.graphics.states.cutscene;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.graphics.states.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

import font.CustomFont;

/**
 * Cutscene de introdução à missão do alpha
 */
public class Cutscene_A01 extends State {
    private Toast missao,local,local2;
    private Entity alpha;
    private int screenTime;

    /**
     * @param id
     * @param handler
     */
    public Cutscene_A01(int id, Handler handler) {
        super(id, handler);   
		missao = new Toast("Alpha",Game.WIDTH/2,30,10,CustomFont.getFont("AlaskanNights.ttf",  45));
		local = new Toast("No armazém dos", Game.WIDTH/2, 120,10, CustomFont.getFont("K26ToyBlocks123.ttf", 20));
        local2 = new Toast("Brinquedos", Game.WIDTH/2, 140,10, CustomFont.getFont("K26ToyBlocks123.ttf", 20));
        alpha = new Entity(-24, (Game.HEIGHT/2)+12, 16, 24, Game.alpha, 4, 3, 4, 4, 10, 0, 0, handler);
        alpha.getAnimation().setSpriteY(1);
        alpha.getAnimation().setPlay(true);
        
    }

    
    @Override
    public void init() {
        handler.getGame().statesUseMaps = false;
        screenTime=0;
        missao.doit();
        local.doit();
        local2.doit();
    }

    @Override
    public void tick() {
        if(handler.getInputHandler().secondary.clicked)
            handler.getStateManager().setState(0);

        alpha.tick();

        if(alpha.getX()<(Game.WIDTH/2))
            alpha.setX(alpha.getX()+1);
        else
            alpha.getAnimation().setSpriteY(0);
        if(screenTime>=230)
            handler.getStateManager().setState(0);
        screenTime++;
    }
    
    @Override
    public void render(Graphics g) {
        local.render((Graphics2D)g);
        local2.render((Graphics2D)g);
        missao.render((Graphics2D)g);
        alpha.render(g);
    }
}
