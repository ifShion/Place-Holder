package com.unamedgroup.placeholder.entities.players;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;

public class Alpha extends Player {
    

    public Alpha(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed,int numSpritesX, int numSpritesY, int initPosX, int initPosY) {
        super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY);
        walking = false;
        status = "";
    }

    public void tick(){
        super.tick();
        if(walking){
            super.getAnimation().setPlay(true);
            switch (status) {
                case "up":
                super.getAnimation().setSpriteY(3);
                    break;
                case "down":
                super.getAnimation().setSpriteY(0);
                    break;
                case "right":
                super.getAnimation().setSpriteY(2);    
                    break;
                case "left":
                super.getAnimation().setSpriteY(1);    
                    break;
                default:
                    break;
            }
        }else{
            super.getAnimation().setSpriteX(0);
            super.getAnimation().setPlay(false);
        }
    }
    
}
