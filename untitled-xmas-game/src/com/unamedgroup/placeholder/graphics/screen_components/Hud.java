package com.unamedgroup.placeholder.graphics.screen_components;
import java.awt.Graphics;

import com.unamedgroup.placeholder.main.Handler;

public class Hud {
    private Handler handler;
    private Hp hp;

    public Hud(Handler handler){
       this.handler = handler;
       this.hp = new Hp(handler);
    }


    public void tick(){
    }

    public void render(Graphics g){
        hp.render(g);
    }

    
    
}
