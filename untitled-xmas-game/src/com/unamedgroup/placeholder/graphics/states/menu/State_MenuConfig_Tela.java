package com.unamedgroup.placeholder.graphics.states.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.CheckBox;
import com.unamedgroup.placeholder.graphics.screen_components.ComponentBox;
import com.unamedgroup.placeholder.graphics.screen_components.Label;
import com.unamedgroup.placeholder.graphics.states.Menu;
import com.unamedgroup.placeholder.main.Handler;

import font.CustomFont;

/**
 * @author Daniel Nogueira
 * Esse Estado existe para ser a tela de configurações de tela
 */
public class State_MenuConfig_Tela extends Menu {

    private Entity background;
    private SpriteSheet backgroundLayer;
    public static int ID;
    private Label label;
    private CheckBox checkBox;
    private ComponentBox componentBox;
    private int count;

    public State_MenuConfig_Tela(int id, Handler handler) {
        super(id, handler);
        ID = id;
        init();
    }

    @Override
    public void init(){
        super.init();
        label = new Label("Retornar", 85, 80, CustomFont.getFont("AtariSmall.ttf", 18f), Color.WHITE, true);
        checkBox = new CheckBox("Tela cheia", handler);
        checkBox.setxComponent(86);
        checkBox.setyComponent(50);
        background = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Menu_SnowFall.png"), 1, 0, 15, 20, 1, 0, 0, handler);
        background.getAnimation().setPlay(true);
        backgroundLayer = new SpriteSheet("/spritesheet/fundoPausa.png");
        componentBox = new ComponentBox();
        componentBox.add(checkBox);
        componentBox.add(label);
    }

    @Override
    public void tick(){
        super.tick();
        background.tick();
        if (handler.getInputHandler().up.clicked)
            componentBox.btnUp();
        if (handler.getInputHandler().down.clicked)
            componentBox.btnDown();
        if (componentBox.getSelectionIndex()==0)
            componentBox.getComponents().get(0).tick();
        if (componentBox.getSelectionIndex()==1){
            if (handler.getInputHandler().prime.clicked){
                super.status = "getout";
                super.screenDestiny = State_MenuConfig.ID;
            }
        }
        if (checkBox.isChanged()){
            handler.changeSize();
            checkBox.setChanged(false);
        }
        count++;
    }

    @Override
    public void render(Graphics g){
        background.render(g);
        g.drawImage(backgroundLayer.getSpriteSheet(), 0, 0, null);
        if (count>=50){
            Color flag = label.getColor();
            if (flag.equals(Color.WHITE)){
                if (componentBox.getSelectionIndex() == 1)
                    label.setColor(Color.YELLOW);
            }
            else
                if (componentBox.getSelectionIndex() == 1)
                    label.setColor(Color.WHITE);
            count=0;
        }
        
        componentBox.render(g);
        super.render(g);
    }
    
}
