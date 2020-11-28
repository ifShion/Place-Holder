package com.unamedgroup.placeholder.graphics.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.graphics.screen_components.Label;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

import font.CustomFont;

/**
 * Cutscene de introdução à missão do alpha
 */
public class Cutscene_A01 extends State {
    private Toast missao;
    private Label local;
    private Label local2;
    public Cutscene_A01(int id, Handler handler) {
        super(id, handler);   
		missao = new Toast("Alpha",Game.WIDTH/2,50,5,CustomFont.getFont("rumbletumble.TTF",  45));
		local = new Label("No armazém dos", Game.WIDTH/2, 110, CustomFont.getFont("K26ToyBlocks123.ttf", 20), Color.white, true);
		local2 = new Label("Brinquedos", Game.WIDTH/2, 130, CustomFont.getFont("K26ToyBlocks123.ttf", 20), Color.white, true);
        
    }
    
    @Override
    public void init() {
        handler.getGame().statesUseMaps = false;
        missao.doit();
        super.init();
    }

    @Override
    public void tick() {
        
        if(handler.getInputHandler().secondary.clicked)
        handler.getStateManager().setState(0);
        super.tick();
    }
    
    @Override
    public void render(Graphics g) {
        local.draw((Graphics2D)g);
        local2.draw((Graphics2D)g);
        missao.draw((Graphics2D)g);
        super.render(g);
    }
}
