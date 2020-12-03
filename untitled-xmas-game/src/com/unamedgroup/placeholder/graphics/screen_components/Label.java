package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Label {
    private int x, y;
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
}
