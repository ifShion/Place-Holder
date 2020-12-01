package com.unamedgroup.placeholder.graphics.states;

import java.awt.Graphics;

import com.unamedgroup.placeholder.main.Handler;


/**
 * 
 * @author Nathan
 */
public abstract class State {
    protected int id;
    protected Handler handler;

    public State(int id, Handler handler){
        this.id = id;
        this.handler = handler;
    }


    public void init(){  //TODO vai ter alguma coisa nesses metodos ou pode colocar como abstrato?
        
    };
    public void tick(){ //TODO vai ter alguma coisa nesses metodos ou pode colocar como abstrato?

    }
    public void render(Graphics g){ //TODO vai ter alguma coisa nesses metodos ou pode colocar como abstrato?

    }

}
