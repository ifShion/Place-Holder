package com.unamedgroup.placeholder.graphics.states.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Label;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.states.Menu;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

/**
 * @author Daniel Nogueira
 * Estado para o menu de controle no menu principal
 */
public class State_MenuConfig_Controle extends Menu {

    public static int ID;
    private LabelList controle;
    private Entity background;
    private SpriteSheet backgroundLayer;
    private SpriteSheet buttons;
    private Label returnLabel;
    private int count;

    public State_MenuConfig_Controle(int id, Handler handler) {
        super(id, handler);
        ID = id;
    }

	@Override
    public void init(){
        super.init();
        background = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Menu_SnowFall.png"), 1, 0, 15, 20, 1, 0, 0, handler);
        background.getAnimation().setPlay(true);
        backgroundLayer = new SpriteSheet("/spritesheet/fundoPausa.png");
        buttons = new SpriteSheet("/spritesheet/botões.png");
        returnLabel = new Label("Retornar", 63, 131, null, Color.WHITE, true);
        controle = new LabelList(Game.WIDTH/2-69, Game.HEIGHT/2-14, null, Color.WHITE, LabelList.LEFT);
        controle.add("Interagir");
        controle.add("Mover para direita");
        controle.add("Mover para esquerda");
        controle.add("Pausar o jogo");
        controle.add("Pular");
        controle.add("Atacar");
    }

    @Override
    public void tick(){
        super.tick();
        background.tick();
        if (handler.getInputHandler().prime.clicked){
            super.status = "getout";
            super.screenDestiny = State_MenuConfig.ID;  
        }
        count++;
    }

    @Override
    public void render(Graphics g){
        background.render(g);
        g.drawImage(backgroundLayer.getSpriteSheet(), 0, 0, null);
        g.drawImage(buttons.getSprite(0, 0, 32, 32).getScaledInstance(16, 16, 16), 30, 9, null);
        g.drawImage(buttons.getSprite(32, 0, 32, 32).getScaledInstance(16, 16, 16), 30, 30, null);
        g.drawImage(buttons.getSprite(64, 0, 32, 32).getScaledInstance(16, 16, 16), 30, 51, null);
        g.drawImage(buttons.getSprite(96, 0, 32, 32).getScaledInstance(16, 16, 16), 30, 72, null);
        g.drawImage(buttons.getSprite(128, 0, 32, 32).getScaledInstance(16, 16, 16), 30, 93, null);
        g.drawImage(buttons.getSprite(160, 0, 32, 32).getScaledInstance(16, 16, 16), 30, 114, null);
        controle.render((Graphics2D) g);
        if (count>=50){
            Color flag = returnLabel.getColor();
            if (flag.equals(Color.WHITE)){
                returnLabel.setColor(Color.YELLOW);
            }
            else {
                returnLabel.setColor(Color.WHITE);
            }
            count=0;
        }
        returnLabel.render((Graphics2D)g);
        super.render(g);
    }


    
}
