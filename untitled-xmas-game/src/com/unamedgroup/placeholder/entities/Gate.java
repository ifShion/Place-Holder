package com.unamedgroup.placeholder.entities;

import java.awt.Graphics;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class Gate extends Door {
    private Entity[] lamp = new Entity[3];
    private boolean[] locks = new boolean[3];{
        locks[0] = false;
        locks[1] = false;
        locks[2] = false;
    }

    public Gate(Handler handler){
        super(0, 0, 0, 0, new SpriteSheet(""), 1, 1, 1, 1, 1, 1, 1, 1, true, handler);
    }

    public Gate(int x, int y, int destiny, int tpx, int tpy, Handler handler) {
        super(x, y, 32, 34, new SpriteSheet("/spritesheet/gate.png"), 5, 5, 1, 0, 0, destiny, tpx, tpy, true, handler);
        super.getAnimation().setPlay(false);

        lamp[0] = new Entity(x+3, y-5, 8, 8, new SpriteSheet("/spritesheet/gate.png"), 1, 1, 1, 2, 1, 0, 34, handler);
        lamp[1] = new Entity(x+13, y-7, 8, 8, new SpriteSheet("/spritesheet/gate.png"), 1, 1, 1, 2, 1, 0, 34, handler);
        lamp[2] = new Entity(x+23, y-5, 8, 8, new SpriteSheet("/spritesheet/gate.png"), 1, 1, 1, 2, 1, 0, 34, handler);
    }


    public void unlock(){
        for (int i = 0; i < locks.length; i++) {
            if(!locks[i]){
                locks[i] = true;
                return;
            }
        }
    }

    public void tolock(){
        for (int i = 0; i < locks.length; i++) {
            if(locks[i]){
                locks[i] = false;
                return;
            }
        }
    }

    @Override
    public void tick() {
        if(isColliding(this, handler.getGame().getPlayer())){
            if(handler.getInputHandler().up.clicked){
                for (int i = 0; i < locks.length; i++) {
                    if (!locks[i]) {
                        return;
                    }
                }
                super.locked = false;
            }
        }
        for (int i = 0; i < lamp.length; i++) {
            if(locks[i])
                lamp[i].getAnimation().setSpriteX(1);
            else
                lamp[i].getAnimation().setSpriteX(0);
        }
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < lamp.length; i++) {
            lamp[i].render(g);
        }
        super.render(g);
    }

    
    
}
