package com.unamedgroup.placeholder.graphics.states.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;

import com.unamedgroup.placeholder.entities.Alpha;
import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Gate;
import com.unamedgroup.placeholder.entities.GateControl;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.entities.Rattles;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Effects;
import com.unamedgroup.placeholder.graphics.screen_components.Hud;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.screen_components.Toast;
import com.unamedgroup.placeholder.graphics.states.State;
import com.unamedgroup.placeholder.graphics.states.menu.Menu_Creditos;
import com.unamedgroup.placeholder.graphics.states.menu.State_Pause;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.Room;

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

    private GateControl gateControl;
    private boolean ENDGAME;
    private Toast fimDeJogo;
    private int screenTime;


    public State_00(int id, Handler handler){
		super(id, handler);
        alpha = new Alpha(0, 0, 16, 24, Game.alpha, 5, 3, 1, 8, 18, 0, 0, handler);
        alpha.setMask(4, 7, 8, 17);
        alpha.setHp(5);
        handler.getGame().setPlayer(alpha);
        hud = new Hud(handler);
        ENDGAME = false;
        screenTime = 300;
    }

    @Override
    public void init() {
            alpha.setX(getPlayerX());
            alpha.setY(getPlayerY());
            handler.getGame().setPlayer(alpha);
            handler.getGame().updateEntities();
            handler.getGame().setCurrentMapID(1000);
            fimDeJogo = new Toast("Você conseguiu!!!", Game.WIDTH/2, 25, 20, CustomFont.getFont("AtariSmall.ttf", 25));
            gateControl = new GateControl(handler);
            Effects.transparencia = 0;
    }
    @Override
    public void tick() {
        hud.tick();
        if (handler.getInputHandler().escape.clicked){
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            //handler.getGame().alternatingMaps=true;
            handler.getStateManager().setState(State_Pause.ID);
            handler.getStateManager().setPaused(true);
            ((State_Pause)(handler.getStateManager().getCurrentState())).setPreviousState(this.id);

        }

        handler.getSounds().tick("Music_alpha", handler.getGameVolume());

        for (int i = 0; i < handler.getGame().getPlayer().getInventario().size(); i++) {
            if(handler.getGame().getPlayer().getInventario().get(i) instanceof Rattles){
			    ENDGAME = true;
                fimDeJogo.doit();
                handler.getGame().getPlayer().getInventario().remove(handler.getGame().getPlayer().getInventario().get(i));
            }
        }

        if(ENDGAME){
            handler.getSounds().stop("Music_alpha");
            screenTime--;

            if(screenTime<0) 
                if(Effects.TransitionDispose()){
                    handler.getGame().alternatingMaps = true;
                    handler.getStateManager().setState(Menu_Creditos.ID);
                    handler.getGame().statesUseMaps=false;
                    Room.entities.clear();
                    handler.getSounds().play("Music",handler.getGameVolume());
                }
        }
    }

    @Override
    public void render(Graphics g) {
        if(ENDGAME)
            fimDeJogo.render((Graphics2D)g);
        else
            hud.render(g);
        Effects.TransitionRender(g);
    }

}
