package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;

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
    private Entity porta;
    private BufferedImage portao;

    public State_00(int id, Handler handler){
		super(id, handler);
        alpha = new Alpha(0, 0, 16, 24, Game.alpha, 4, 3, 1, 8, 18, 0, 0, handler);
        alpha.setMask(4, 7, 8, 17);
        alpha.setHp(5);
        handler.getGame().setPlayer(alpha);
        hud = new Hud(handler);
        porta = new Entity(960-32, 440-4, 64, 48, new SpriteSheet("/spritesheet/armazem.png"), 5, 1, 0, 1, 1, 0, 0, handler);
        portao = new SpriteSheet("/spritesheet/armazem.png").getSprite(0, 0, 64, 48);
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
        //System.out.println("X:"+alpha.getX() +"Y:"+ alpha.getY());
    }

    @Override
    public void render(Graphics g) {
        hud.render(g);
        //g.drawImage(portao, 960-32, 440-4, null);
        porta.render(g);
    }
    
}
