package com.unamedgroup.placeholder.graphics.states;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.ComponentBox;
import com.unamedgroup.placeholder.graphics.screen_components.Label;
import com.unamedgroup.placeholder.graphics.screen_components.VolumeControl;
import com.unamedgroup.placeholder.main.Handler;

/**
 * @author Daniel Nogueira
 * Estado para o menu de áudio no menu principal
 */
public class State_MenuConfig_Audio extends Menu {

    public static int ID;
    private ComponentBox controle;
    private Entity background;
    private SpriteSheet backgroundLayer;
    private Label returnLabel;
    private VolumeControl volumeControl;
    private int count;

    
    public State_MenuConfig_Audio(int id, Handler handler) {
        super(id, handler);
        ID = id;
        initUnique();
    }

    public void initUnique(){
        super.init();
        background = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Menu_SnowFall.png"), 1, 0, 15, 20, 1, 0, 0, handler);
        background.getAnimation().setPlay(true);
        backgroundLayer = new SpriteSheet("/spritesheet/fundoPausa.png");
        returnLabel = new Label("Retornar", 100, 0, null, Color.WHITE, true);
        volumeControl = new VolumeControl(35, 50, null, Color.WHITE, handler);
        controle = new ComponentBox();
        controle.add(volumeControl);
        controle.add(returnLabel);
    }  

    @Override
    public void init(){
        super.init();
        background = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Menu_SnowFall.png"), 1, 0, 15, 20, 1, 0, 0, handler);
        background.getAnimation().setPlay(true);
        backgroundLayer = new SpriteSheet("/spritesheet/fundoPausa.png");
    }

    public void tick(){
        super.tick();
        background.tick();
        controle.tick();
        if (handler.getInputHandler().up.clicked){
            controle.btnUp();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if (handler.getInputHandler().down.clicked){
            controle.btnDown();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if (handler.getInputHandler().left.clicked && controle.getSelectionIndex()==0){
            ((VolumeControl)(controle.getComponents().get(0))).btnLeft();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if (handler.getInputHandler().right.clicked && controle.getSelectionIndex()==0){
            ((VolumeControl)(controle.getComponents().get(0))).btnRight();
            handler.getSounds().play("Menu_Navigate", handler.getGameVolume());
        }
        if (handler.getInputHandler().prime.clicked && controle.getSelectionIndex()==1){
            super.status = "getout";
            super.screenDestiny = State_MenuConfig.ID;
            handler.getSounds().play("Select", handler.getGameVolume());
        }
        count++;
    }

    public void render(Graphics g){
        background.render(g);
        g.drawImage(backgroundLayer.getSpriteSheet(), 0, 0, null);
        if (count>=60){
            Color flag = returnLabel.getColor();
            if (flag.equals(Color.WHITE)){
                if (controle.getSelectionIndex()==0){
                    ((VolumeControl) controle.getComponents().get(controle.getSelectionIndex())).setColor(Color.YELLOW);
                }
                else if (controle.getSelectionIndex() == 1) {
                    ((Label) controle.getComponents().get(controle.getSelectionIndex())).setColor(Color.YELLOW);
                }
            }
            else {
                if (controle.getSelectionIndex()==0){
                    ((VolumeControl) controle.getComponents().get(controle.getSelectionIndex())).setColor(Color.WHITE);
                }
                else if (controle.getSelectionIndex() == 1) {
                    ((Label) controle.getComponents().get(controle.getSelectionIndex())).setColor(Color.WHITE);
                }
            }
            count=0;
        }
        controle.render(g);
        super.render(g);
    }
}