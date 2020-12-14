package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;

import com.unamedgroup.placeholder.entities.Alpha;
import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Hud;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

import font.CustomFont;

/**
 * 
 * Teremos um papel importante com as classes states nesse projeto:
 *  Temos que cuidar de cada classe dessa como se fosse um jogo único,
 * então todas terão seu próprio mapa, Lista de entidades e jogador
 * @author Nathan
 */

 @SuppressWarnings("unused")
public class State_00 extends State {
    private Alpha alpha;
    private Hud hud;
    //private Entity porta;
    private BufferedImage portao, prateleira, caixote, bonequinho,presente01,presente02;
    private SpriteSheet armazem;

    public State_00(int id, Handler handler){
		super(id, handler);
        alpha = new Alpha(0, 0, 16, 24, Game.alpha, 4, 3, 1, 8, 18, 0, 0, handler);
        alpha.setMask(4, 7, 8, 17);
        alpha.setHp(5);
        handler.getGame().setPlayer(alpha);
        hud = new Hud(handler);
        //==================== Buffered Image =============================//
        armazem = new SpriteSheet("/spritesheet/armazem.png");
        portao = armazem.getSprite(0, 0, 64, 48);
        prateleira = armazem.getSprite(0, 64, 32, 16);
        caixote = armazem.getSprite(0, 48, 32, 16);
        bonequinho = armazem.getSprite(48, 80, 16, 16);
        presente01 = armazem.getSprite(32, 48, 16, 16);
        presente02 = armazem.getSprite(48, 48, 16, 16);
    }

    @Override
    public void init() {
        alpha.setX(getPlayerX());
        alpha.setY(getPlayerY());
    	handler.getGame().setPlayer(alpha);
        handler.getGame().updateEntities();
        handler.getGame().setCurrentMapID(2001);
    }
    @Override
    public void tick() {
        hud.tick();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(portao, 960-32-handler.getCamera().getX(), 440-4-handler.getCamera().getY(), null);


        drawPrateleiraComCaixote(g, 8, 860, 464);
        drawPrateleiraComCaixote(g, 3, 1060, 464);

        drawPrateleiraNaParede(g, 8, 915, 400);

        g.drawImage(armazem.getSprite(48, 64, 16, 16), 900-16-handler.getCamera().getX(), 464-handler.getCamera().getY(), null);

        g.drawImage(presente01, 1050-16-handler.getCamera().getX(), 464-handler.getCamera().getY(), null);
        g.drawImage(presente02, 1069-16-handler.getCamera().getX(), 464-handler.getCamera().getY(), null);
        

        hud.render(g);
    }

    private void drawPrateleiraComCaixote(Graphics g,int andares, int x, int y){
        for(int i=0;i<andares;i++){
            g.drawImage(prateleira, x-16-handler.getCamera().getX(), y-(i*16)-handler.getCamera().getY(), null);
            g.drawImage(caixote, x-16-handler.getCamera().getX(), -5+y-(i*16)-handler.getCamera().getY(), null);
            if(i+1==andares)
            g.drawImage(armazem.getSprite(0, 80, 32, 16), x-16-handler.getCamera().getX(), y-(i*16)-16-handler.getCamera().getY(), null);
        }
    }
    private void drawPrateleiraNaParede(Graphics g, int largura, int x, int y){
        for(int i=0;i<largura;i++){
            if(i==0)
                g.drawImage(armazem.getSprite(64, 0, 16, 16), x-16-handler.getCamera().getX(), y-handler.getCamera().getY(), null);
            else
                g.drawImage(armazem.getSprite(64, 32, 16, 16), x+(i*16)-16-handler.getCamera().getX(), y-handler.getCamera().getY(), null);
            if(i+1==largura){
                g.drawImage(armazem.getSprite(64, 16, 16, 16), x+(i*16)-16-handler.getCamera().getX(), y-handler.getCamera().getY(), null);
            }
            switch(i%5){
                case 1:
                    g.drawImage(bonequinho, x+(i*16)-16-handler.getCamera().getX(), y-handler.getCamera().getY()-5, null);
                break;
                case 0:
                    g.drawImage(presente02, x+(i*16)-16-handler.getCamera().getX(), y-handler.getCamera().getY()-5, null);
                break;
                case 2:
                    g.drawImage(presente01, x+(i*16)-16-handler.getCamera().getX(), y-handler.getCamera().getY()-5, null);
                break;
                case 3:
                    g.drawImage(armazem.getSprite(48, 64, 16, 16), x+(i*16)-16-handler.getCamera().getX(), y-handler.getCamera().getY()-5, null);
                break;
                case 4:
                    g.drawImage(armazem.getSprite(64, 48, 16, 32), x+(i*16)-16-handler.getCamera().getX(), -12+y-handler.getCamera().getY()-5, null);
                break;
            }
        }
    }
}
