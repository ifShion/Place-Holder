package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.screen_components.Effects;
import com.unamedgroup.placeholder.main.Handler;

/**
 * Utilize essa classe se quiser aplicar efeito de transição entre os states
 */
public abstract class Menu extends State {

    protected String status;
    protected int screenDestiny;

    public Menu(int id, Handler handler) {
        super(id, handler);
    }

    @Override
    public void init() {
        status = "start";
    }
    
    @Override
    public void tick() {
        switch (status) {
            case "start":
                if(Effects.TransitionOpen())
                    status = "running";
                break;
            case "running":
            break;
            case "getout":
                if(Effects.TransitionDispose())
                    handler.getStateManager().changeState(screenDestiny);
            break;
            default:
            break;
        }
        
    }

    @Override
    public void render(Graphics g) {
        Effects.TransitionRender(g);
    }
}
