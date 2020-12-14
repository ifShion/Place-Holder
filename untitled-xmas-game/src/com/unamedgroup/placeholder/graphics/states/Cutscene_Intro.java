package com.unamedgroup.placeholder.graphics.states;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;


public class Cutscene_Intro extends State {
    public static int id;
    private Entity intro,portaDoCativeiro,alphinha;
    private BufferedImage grades;
    private Toast apresentacao;
    private float transparencia = 1;
    private int screenTime = 5;    // Segundos
    private String evento = "comecar";

    public Cutscene_Intro(int id, Handler handler) {
        super(id, handler);
        intro = new Entity(0, 0, 240, 160, new SpriteSheet("/spritesheet/Intro.png"), 1, 0, 4, 5, 1, 0, 0, handler);
        alphinha = new Entity(29, 105, 8, 8, new SpriteSheet(""), 1, 0, 4, 2, 1, 0, 8, handler);
        portaDoCativeiro = new Entity(29, 105, 8, 8, new SpriteSheet(""), 1, 0, 4, 1, 1, 0, 0, handler);
        grades = new SpriteSheet("/spritesheet/Intro_grades.png").getSprite(0, 0, 240, 160);
        apresentacao = new Toast("Rare Candy Estúdios apresenta...", Game.WIDTH / 2, Game.HEIGHT / 2, 20,
                new Font("Dialog", Font.PLAIN, 10));
        Cutscene_Intro.id = id;
    }

    @Override
    public void init() {
        intro.getAnimation().setPlay(true);
    }

    int tick=0;
    @Override
    public void tick() {
        intro.tick();
        alphinha.tick();
        switch(evento){
            case"comecar":
                if(tick%3==0) 
                    if (transparencia >= 0.1){
                        transparencia -= 0.1f;
                    }else{
                        evento = "espera";
                    }
            break;
            case"anima":
                if(tick%5==0)
                    alphinha.setX(alphinha.getX()+1);
                if(alphinha.getX()==50)
                    evento = "apresenta";
                if(alphinha.getX()==107)
                    alphinha = new Entity(107, 105, 6, 8, new SpriteSheet("/spritesheet/Intro-moves.png"), 1, 0, 4, 2, 1, 0, 8, handler); 
                if(alphinha.getX()==108)
                    alphinha = new Entity(108, 105, 5, 8, new SpriteSheet("/spritesheet/Intro-moves.png"), 1, 0, 4, 2, 1, 0, 8, handler);
                if(alphinha.getX()==109)
                    alphinha = new Entity(109, 105, 4, 8, new SpriteSheet("/spritesheet/Intro-moves.png"), 1, 0, 4, 2, 1, 0, 8, handler); 
                if(alphinha.getX()==110)
                    alphinha.setSprite(new SpriteSheet(""));
                if(alphinha.getX()==111)
                    evento = "espera";
            break;
            case"apresenta":
                apresentacao.doit();
                evento = "anima";
            break;
            case"espera":
                if(tick%60==0){
                    if(screenTime>0)
                        screenTime--;
                    else
                        evento = "sair";
                    if(screenTime==3){
                        evento = "anima";
                        alphinha.setSprite(new SpriteSheet("/spritesheet/Intro-moves.png"));
                        portaDoCativeiro.setSprite(new SpriteSheet("/spritesheet/Intro-moves.png"));
                        alphinha.getAnimation().setPlay(true);
                    }
                }
            break;
            case"sair":
                if(tick%3==0) 
                    transparencia += 0.1f;
                if(transparencia==1)
                    handler.getStateManager().setState(Cutscene_Corredor.id);
            break;
            default:
        } 
        tick++;
    }

    
    @Override
    public void render(Graphics g) {
        intro.render(g);
        portaDoCativeiro.render(g);
        alphinha.render(g);
        g.drawImage(grades, 0, 0, null);
        apresentacao.render((Graphics2D)g);
        g.setColor(Color.black);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
    }
    
}
