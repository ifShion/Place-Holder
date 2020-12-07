package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.entities.Alpha_topdown;
import com.unamedgroup.placeholder.main.Handler;

public class Cutscene_Corredor extends State {
    public static int id;
    private Alpha_topdown alphinha;

    public Cutscene_Corredor(int id, Handler handler) {
        super(id, handler);
        this.id = id;
        alphinha = new Alpha_topdown(135, 0,handler);
        

    }

    @Override
    public void init() {
        setPlayerCoordinatingByMap(false);
        handler.getGame().setCurrentMapID(2000);
        alphinha.getAnimation().setPlay(true);
        handler.getGame().setPlayer(alphinha);
        handler.getGame().updateEntities();
        

    }
    public int passos;
    public int esperar;
    @Override
    public void tick() {
        //alphinha.tick();
        if(passos<25)
            alphinha.toDown();
        else{
            if(esperar>70){
                if(passos<100)
                    alphinha.toDown();
                else{
                    if(passos<170){
                        alphinha.toLeft();
                    }else if(passos<250){
                        alphinha.toDown();
                    }else{
                        handler.getGame().alternatingMaps=true;
                        handler.getGame().updateEntities();
                        handler.getStateManager().setState(2);
                    }
                }
            }else{
                esperar++;
                passos--;
            }
        }
        passos++;
    }

    @Override
    public void render(Graphics g) {
    }

   
    
}
