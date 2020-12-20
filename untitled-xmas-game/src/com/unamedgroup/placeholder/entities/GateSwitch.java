package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class GateSwitch extends Entity {
    private int lockID;
    private boolean used;
    private Gate gate;
    public GateSwitch(int lockID,int x, int y,Handler handler) {
        super(x+4, y+8, 8, 8, new SpriteSheet("/spritesheet/gate.png"), 1, 1, 1, 2, 1, 16, 34, handler);
        this.lockID = lockID;
        used = false;
        gate = new Gate(handler);
        super.getAnimation().setPlay(false);
        setMask(-8, -8, 24, 24);
    }

    @Override
    public void tick() {
        if(isColliding(this, handler.getGame().getPlayer())){
            if(handler.getInputHandler().up.clicked){
                handler.getSounds().play("Interruptor1", handler.getGameVolume());
                if(used){

                    gate.tolock(lockID);
                    used = false;
                }else{
                    gate.unlock(lockID);
                    used = true;
                }
                super.getAnimation().nextSpriteX();
            }
        }
        super.tick();
    }


    
}
