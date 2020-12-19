package com.unamedgroup.placeholder.graphics.states.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.states.Menu;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;


/**
 * @author Daniel Nogueira
 * Essa classe serve para ser as opções do menu principal
 */
public class State_MenuConfig extends Menu {

    public static int ID;
    private LabelList menu;
    private Entity background;
    private SpriteSheet backgroundLayer;


    public State_MenuConfig(int id, Handler handler) {
        super(id, handler);
        ID = id;
    }

    @Override
    public void init(){
        super.init();
        background = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Menu_SnowFall.png"), 1, 0, 15, 20, 1, 0, 0, handler);
        background.getAnimation().setPlay(true);
        backgroundLayer = new SpriteSheet("/spritesheet/fundoPausa.png");
        menu = new LabelList(Game.WIDTH/2-35, Game.HEIGHT/2-5, null, Color.WHITE, LabelList.LEFT);
        menu.add("Controle");
        menu.add("Audio");
        menu.add("Tela");
        menu.add("Retornar");
        menu.setSelecionable(Color.YELLOW);
    }
    
    @Override
    public void tick(){
        super.tick();
        background.tick();
        if(handler.getInputHandler().up.clicked){
            menu.btn_UP();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if(handler.getInputHandler().down.clicked){
            menu.btn_DOWN();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if(handler.getInputHandler().prime.clicked){
            switch (menu.getSeletion()) {
                case 0:
                    super.status = "getout";
                    super.screenDestiny = State_MenuConfig_Controle.ID;
                    handler.getSounds().play("Select", handler.getGameVolume());
                    break;
                case 1:
                    super.status = "getout";
                    super.screenDestiny = State_MenuConfig_Audio.ID;
                    handler.getSounds().play("Select", handler.getGameVolume());
                    break;
                case 2:
                    super.status = "getout";
                    super.screenDestiny = State_MenuConfig_Tela.ID;
                    break;
                case 3:
                    super.status = "getout";
                    super.screenDestiny = Menu_Principal.ID;
                    handler.getSounds().play("Select", handler.getGameVolume());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void render(Graphics g){
        background.render(g);
        g.drawImage(backgroundLayer.getSpriteSheet(), 0, 0, null);
        menu.render((Graphics2D) g);
        super.render(g);
    }
}
