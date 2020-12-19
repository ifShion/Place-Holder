package com.unamedgroup.placeholder.graphics.states.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.states.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;


/**
 * @author Daniel Nogueria
 * Classe usada para o menu de pausa
 */
public class State_Pause extends State {
    public static int ID;
    public int previousState;
    private LabelList menu;
    private SpriteSheet background;

    public State_Pause(int id, Handler handler) {
        super(id, handler);
        ID = id;
        init();
    }

    @Override
    public void init() {
        background = new SpriteSheet("/spritesheet/fundoPausa.png");
        menu = new LabelList(Game.WIDTH/2, Game.HEIGHT/2, null, Color.WHITE, LabelList.CENTER);
        menu.add("Continuar");
        menu.add("Controles");
        menu.add("Sair");
        menu.setSelecionable(Color.yellow);
    }

    @Override
    public void tick() {
        if(handler.getInputHandler().up.clicked){
            menu.btn_UP();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if(handler.getInputHandler().down.clicked){
            menu.btn_DOWN();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
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
                    //TODO para controles
                    break;
                case 2:
                    handler.getGame().alternatingMaps = true;
                    handler.getStateManager().setState(Menu_Principal.ID);
                    handler.getGame().statesUseMaps=false;
                    handler.getStateManager().setPaused(false);
                    handler.getGame().getRoom().entities.clear();
                    handler.getSounds().stop("Music_alpha");
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
