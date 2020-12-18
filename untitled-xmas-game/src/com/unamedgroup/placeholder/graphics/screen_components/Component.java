package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Graphics;

/**
 * @author Daniel Nogueira
 * Essa classe abstrata serve para ser base para outras
 */
public abstract class Component {

    protected float xComponent, yComponent;
    
    public abstract void init();
    public abstract void tick();
    public abstract void render(Graphics g);

    public float getxComponent() {
        return xComponent;
    }

    public void setxComponent(float xComponent) {
        this.xComponent = xComponent;
    }

    public float getyComponent() {
        return yComponent;
    }

    public void setyComponent(float yComponent) {
        this.yComponent = yComponent;
    }
}
