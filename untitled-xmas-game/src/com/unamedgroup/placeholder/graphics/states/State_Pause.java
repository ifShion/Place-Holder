package com.unamedgroup.placeholder.graphics.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;


/**
 * @author
 */
public class State_Pause extends State {

    public int previousState;
    private LabelList menu;
    private SpriteSheet background;

    public State_Pause(int id, Handler handler) {
        super(id, handler);
        init();
    }

    @Override
    public void init() {
        background = new SpriteSheet("/spritesheet/fundoPausa.png");
        menu = new LabelList(Game.WIDTH-170, Game.HEIGHT-90, null, Color.WHITE, LabelList.LEFT);
        menu.add("Continuar");
        menu.add("Controles");
        menu.add("Menu Principal");
        menu.setSelecionable(Color.yellow);
    }

    @Override
    public void tick() {
        if(handler.getInputHandler().up.clicked){
            menu.btn_UP();
        }
        if(handler.getInputHandler().down.clicked){
            menu.btn_DOWN();
        }
        if (handler.getInputHandler().prime.clicked){
            switch (menu.getSeletion()) {
                case 0:
                    try {
                        Thread.sleep(200);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.getStateManager().setStatePause(previousState);
                    handler.getStateManager().setPaused(false);
                    break;
                case 1:
                    //TODO
                    break;
                case 2:
                    //TODO
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background.getSpriteSheet(), 0, 0, null);
        menu.render((Graphics2D) g);
    }

    public int getPreviousState() {
        return previousState;
    }

    public void setPreviousState(int previousState) {
        this.previousState = previousState;
        init();
    }
}
