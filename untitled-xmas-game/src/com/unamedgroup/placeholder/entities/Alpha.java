package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.interfaces.GravityEffected;
import com.unamedgroup.placeholder.main.Handler;

public class Alpha extends Player implements GravityEffected {

	private double vspd;
	private boolean jump;
	private boolean pressedDown;
	
    public Alpha(int x, int y, int width, int height, SpriteSheet sprite, int depth, int speed, int animationSpeed,int numSpritesX, int numSpritesY, int initPosX, int initPosY, Handler handler) {
        super(x, y, width, height, sprite, depth, speed, animationSpeed, numSpritesX, numSpritesY, initPosX, initPosY, handler);
        walking = false;
        status = "";
    }

    public void tick(){
        super.tick();
        if(walking){
            super.getAnimation().setPlay(true);
            switch (status) {
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
        
        this.fall();
    }

	@Override
	public void fall() {
		vspd+=GravityEffected.GRAVITY;
		
		// sitema de pulo do personagem. Ele não pode pular duas vezes nem segurar o botão para pular assim q alcançar o chão
		if(handler.getInputHandler().prime.down && !pressedDown && !handler.getGame().room.isFree((int)x + super.getMaskX(),(int)(y+1) + super.getMaskY(), super.getMaskW(),super.getMaskH())) {
			jump = true;
			pressedDown = true;
		}else if(!handler.getInputHandler().prime.down) {
			pressedDown = false;
		}

		// altura que o jogador pula
        if(!handler.getGame().room.isFree((int)x + super.getMaskX(),(int)(y+1) + super.getMaskY(), super.getMaskW(),super.getMaskH()) && jump) {
            vspd = -7.5;
            jump = false;
        }
		
        // verifica se o local para onde o jogador está subindo ou caindo para está disponível
		if(!handler.getGame().room.isFree((int)x + super.getMaskX(),(int)(y+vspd) + super.getMaskY(),super.getMaskW(),super.getMaskH())) {
			int signVsp = 0;
			if(vspd >= 0) {
				signVsp = 1;
			}else {
				signVsp = -1;
			}
			
			// impossibilita o jogador atingir velocidades de queda muito altas
			if(vspd > 17) vspd = 17;
			
			// impossibilita o jogador se enterrar no chão
			while(handler.getGame().room.isFree((int)x + super.getMaskX(),(int)(y+signVsp) + super.getMaskY(),super.getMaskW(),super.getMaskH())) {
				y+=signVsp;
			}
			vspd = 0;
		}
		
		y = y + vspd;
	}
    
}
