package com.unamedgroup.placeholder.entities;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;

public class Alpha_topdown extends Player {

    public Alpha_topdown(int x, int y, Handler handler) {
		super(x, y, 16, 24, new SpriteSheet("/spritesheet/alpha-topdown.png"), 1, 1, 5, 4, 4, 0, 0, handler);
		moveable = false;
    }

    public void toRight(){
		setX(getX() + speed);
		getAnimation().setSpriteY(1);
		direction = 1;
	}
	public void toLeft(){
		setX(getX() - speed);
		getAnimation().setSpriteY(2);
		direction = -1;
	}
	public void toUp(){
        //getAnimation().setHeight(16);
        setY(getY() - speed);
		getAnimation().setSpriteY(3);
		direction = 1;
	}
	public void toDown(){
        setY(getY() + speed);
		getAnimation().setSpriteY(0);
		direction = -1;
	}
    
}
