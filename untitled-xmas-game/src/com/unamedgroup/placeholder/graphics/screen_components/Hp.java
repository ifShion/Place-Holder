package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.unamedgroup.placeholder.main.Handler;

public class Hp {
    private Handler handler;
    private BufferedImage bf;

    public Hp(Handler handler){
        this.handler = handler;
        bf = handler.getGame().hud.getSprite(0, 0, 8, 8);
    }

    public void render(Graphics g){
        for(int i = 0; i<handler.getGame().getPlayer().getHp();i++){
            g.drawImage(bf, 5+i*9, 5, null);
        }
    }

}
