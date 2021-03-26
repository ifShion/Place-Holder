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
    private boolean playerCoordinatingByMap = true;
    private double playerX, playerY;

    public State(int id, Handler handler){
        this.id = id;
        this.handler = handler;
    }


    public abstract void init();
    public abstract void tick();
    public abstract void render(Graphics g);

    public int getId(){
        return id;
    }

    public boolean isPlayerCoordinatingByMap() {
        return this.playerCoordinatingByMap;
    }

    public void setPlayerCoordinatingByMap(boolean playerCoordinatingByMap) {
        this.playerCoordinatingByMap = playerCoordinatingByMap;
    }


    public double getPlayerX() {
        return this.playerX;
    }

    public void setPlayerX(double playerX) {
        this.playerX = playerX;
    }

    public double getPlayerY() {
        return this.playerY;
    }

    public void setPlayerY(double playerY) {
        this.playerY = playerY;
    }


}
