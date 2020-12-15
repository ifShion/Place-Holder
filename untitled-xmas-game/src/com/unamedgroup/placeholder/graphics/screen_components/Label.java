package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Label {
    private float x, y;
    private String txt;
    private int xtemp, ytemp;
    private Font font;
    private Color color;
    private boolean toAlign;
    
    
    /**
     * 
     * @param txt Texto do Text
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param font Fonte
     * @param color Cor da Fonte
     * @param toAlign Centralizar?
     */
    public Label(String txt, int x, int y, Font font, Color color, boolean toAlign) {
        this.txt = txt;
        this.font = font;
        this.color = color;
        this.xtemp = x;
        this.ytemp = y;
        this.toAlign = toAlign;
    }

    public Label(String txt) {
        this.txt = txt;
    }
    
    private void align(Graphics2D g){
        if(toAlign){
            x = xtemp - g.getFontMetrics().stringWidth(txt)/2;
        }else{
            x = xtemp;
        }
        y = ytemp + g.getFontMetrics().getHeight();
    }
    
    
    public void render(Graphics2D g){
        g.setColor(color);
        g.setFont(font);
        align(g);
        g.drawString(txt, x, y);  
    }
    

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }


    public int getXtemp() {
        return this.xtemp;
    }

    public void setXtemp(int xtemp) {
        this.xtemp = xtemp;
    }

    public int getYtemp() {
        return this.ytemp;
    }

    public void setYtemp(int ytemp) {
        this.ytemp = ytemp;
    }

    public Font getFont() {
        return this.font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isToAlign() {
        return this.toAlign;
    }

    public boolean getToAlign() {
        return this.toAlign;
    }

    public void setToAlign(boolean toAlign) {
        this.toAlign = toAlign;
    }

}
