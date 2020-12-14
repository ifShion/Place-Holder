package com.unamedgroup.placeholder.graphics.screen_components;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.entities.Key;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class Hud {
    private Handler handler;
    private final BufferedImage HP , KEYS;

    public Hud(Handler handler){
       this.handler = handler;
       KEYS = Game.hud.getSprite(8, 0, 8, 8);
       HP = Game.hud.getSprite(0, 0, 8, 8);
    }


    public void tick(){
    }

    public void render(Graphics g){
        //=========================// HP //=========================//
        for(int i = 0; i<handler.getGame().getPlayer().getHp();i++){
            g.drawImage(HP, 5+i*9, 5, null);
        }
        //=======================// CHAVES //=======================//
        handler.getGame().getPlayer().getInventario().forEach((i) -> {
            if(i instanceof Key){
                g.drawImage(KEYS, 5, 17, null);
            }

        });
    }

    
    
}
