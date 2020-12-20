package com.unamedgroup.placeholder.graphics.states.cutscene;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.entities.Alpha_topdown;
import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.states.State;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class Cutscene_Corredor extends State {
	public static int id;
    private Alpha_topdown alphinha;
    private String cena, printStatus;
    private BufferedImage carta, balaozim;
    private Entity butao, cartinha;

    public Cutscene_Corredor(int id, Handler handler) {
        super(id, handler);
        Cutscene_Corredor.id = id;
    }

    @Override
    public void init() {
        alphinha = new Alpha_topdown(135, 0,handler);
        carta = new SpriteSheet("/img/carta.png").getSprite(0, 0, 240, 160);
        balaozim = Game.hud.getSprite(16, 8, 8, 8);
        butao = new Entity(220, 140, 8, 8, Game.hud, 1, 1, 4, 2, 1, 0, 8, handler);
        butao.getAnimation().setPlay(true);
        cartinha = new Entity(135, 120, 16, 16, new SpriteSheet("/spritesheet/factory.png"), 1, 0, 0, 1, 1, 32, 32, handler);

        setPlayerCoordinatingByMap(false);
        handler.getGame().setCurrentMapID(0001);
        alphinha.getAnimation().setPlay(true);
        handler.getGame().setPlayer(alphinha);
        handler.getGame().updateEntities();

        cena = "inicio";
        printStatus = "";
        passos = 0;
        esperar = 0;
    }
    private int passos;
    private int esperar;
    @Override
    public void tick() {
        butao.tick();
        switch(cena){
            case "inicio":
                alphinha.toDown();
                if(passos==25){
                    cena = "esperar";
                    printStatus = "balao";
                }
                break;
            case "cena02":
                alphinha.toDown();
                if(passos==100)
                    cena = "carta";
                    handler.getSounds().play("Letter", handler.getGameVolume());
                break;
            case "cena03":
                alphinha.toLeft();
                if(passos==170)
                    cena = "cena04";
                break;
            case "cena04":
                alphinha.toDown();
                if(passos==250)
                    cena = "sair";
                break;
            case "carta":
                printStatus = "letter";
                passos--;
                if(handler.getInputHandler().prime.clicked){
                    printStatus = "";
                    cena = "esperar";
                    cartinha.setSprite(new SpriteSheet(""));
                }

                break;
            case "esperar":
                esperar++;
                passos--;
                if(esperar==170){
                    cena = "cena03";
                    balaozim =  Game.hud.getSprite(16, 0, 8, 8);
                    printStatus = "balao";
                }
                if(esperar==120){
                    cena = "cena02";
                    printStatus = "";
                }
                break;
            case "sair":
                handler.getGame().alternatingMaps=true;
                handler.getGame().updateEntities();
                handler.getStateManager().setState(Cutscene_A01.ID); 
                break;

        }

        passos++;
    }

    @Override
    public void render(Graphics g) {
        cartinha.render(g);
        switch (printStatus) {
            case "letter":
                g.drawImage(carta, 0, 0, null);
                butao.render(g);
                break;
            case "balao":
                g.drawImage(balaozim, (alphinha.getX()-handler.getCamera().getX()) + 16, alphinha.getY()-handler.getCamera().getY(), null);
            break;
            default:
                break;
        }
        
          
    }
    
}
