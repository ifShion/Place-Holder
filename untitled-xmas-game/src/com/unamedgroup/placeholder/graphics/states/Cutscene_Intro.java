package com.unamedgroup.placeholder.graphics.states;

import java.awt.*;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class Cutscene_Intro extends State {
    public static int id;
    Entity intro;
    Toast apresentacao;
    float transparencia = 1;
    private int screenTime = 10;    //Segundos

    public Cutscene_Intro(int id, Handler handler) {
        super(id, handler);
        intro = new Entity(0, 0, 240, 160, new SpriteSheet("/Intro.png"), 1, 0, 4, 5, 1, 0, 0, handler);
        apresentacao = new Toast("Untitled-Team apresenta...", Game.WIDTH / 2, Game.HEIGHT / 2, 20, 500,
                new Font("Dialog", Font.PLAIN, 10));
        this.id = id;
    }

    @Override
    public void init() {
        apresentacao.doit();
        intro.getAnimation().setPlay(true);

    }

    int tick=0;
    @Override
    public void tick() {
        intro.tick();
        if(tick%3==0){
            if(screenTime>0){
                if (transparencia >= 0.1){
                    transparencia -= 0.1f;
                }
            }
            if(screenTime<=0){
                if (transparencia < 1){
                    transparencia += 0.1f;
                    if(transparencia==1)
                        handler.getStateManager().setState(Cutscene_Corredor.id);
                }
            }
        }
        if(tick%60==0){
            if(screenTime>0)
                screenTime--;
        tick=0;
        }
        tick++;

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(bf, 0, 0, null);
        intro.render(g);
        apresentacao.render((Graphics2D)g);
        g.setColor(Color.black);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
    }
    
}
