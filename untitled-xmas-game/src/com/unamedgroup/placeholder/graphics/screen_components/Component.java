package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Graphics;

/**
 * @author Daniel Nogueira
 * Essa classe abstrata serve para ser base para outras
 */
public abstract class Component {

    protected float xComponemt, yComponent;
    
    public abstract void init();
    public abstract void tick();
    public abstract void render(Graphics g);

    public float getxComponemt() {
        return xComponemt;
    }

    public void setxComponemt(float xComponemt) {
        this.xComponemt = xComponemt;
    }

    public float getyComponent() {
        return yComponent;
    }

    public void setyComponent(float yComponent) {
        this.yComponent = yComponent;
    }
}
