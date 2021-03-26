package com.unamedgroup.placeholder.graphics.states.menu;

import java.awt.*;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.states.cutscene.Cutscene_Intro;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.graphics.states.Menu;

public class Menu_Principal extends Menu{
    public static int ID;
    private LabelList menu;
    private Entity background;

    public Menu_Principal(int id, Handler handler) {
        super(id, handler);
        ID = id;
    }

    @Override
    public void init() {
        super.init();
        playMusic();
        background = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Menu_SnowFall.png"), 1, 0, 15, 20, 1, 0, 0, handler);
        menu = new LabelList(5, 110, null, Color.WHITE, LabelList.LEFT);
        menu.add("Iniciar");
        menu.add("Opções");
        menu.add("Créditos");
        menu.add("Sair");
        menu.setSelecionable(Color.yellow);
        background.getAnimation().setPlay(true);
        handler.getCamera().setX(0);
        handler.getCamera().setY(0);
    }

    @Override
    public void tick() {
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
                    handler.getSounds().play("Select", handler.getGameVolume());
                    handler.getSounds().stop("Music");
                    super.screenDestiny = Cutscene_Intro.ID;
                                    
                    break;
                case 1:
                    super.status = "getout";
                    handler.getSounds().play("Select", handler.getGameVolume());
                    super.screenDestiny = State_MenuConfig.ID;
                    break;
                case 2:
                    super.status = "getout";
                    handler.getSounds().play("Select", handler.getGameVolume());
                    super.screenDestiny = Menu_Creditos.ID;
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        menu.render((Graphics2D) g);
        super.render(g);
    }

    public void playMusic(){
        handler.getSounds().tick("Music", handler.getGameVolume());
    }
}
