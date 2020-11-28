package com.unamedgroup.placeholder.interfaces;

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


    public void init(){
        
    };
    public void tick(){

    }
    public void render(Graphics g){

    }

}
