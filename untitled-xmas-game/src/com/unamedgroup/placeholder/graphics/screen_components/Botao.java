package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Color;


/**
 * @author Daniel Nogueira
 */
public class Botao{

    private int x, y, width, height;
    private String text;
    private Color colorBack, colorLetter;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @param colorBack
     * @param colorLetter
     */
    public Botao(int x, int y, int width, int height, String text, Color colorBack, Color colorLetter) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.colorBack = colorBack;
        this.colorLetter = colorLetter;
    }

    /**
     * Desenha o botão na tela.
     * @param g
     */
    public void draw(Graphics2D g) {
        g.setColor(colorBack);
        g.fillRect(x, y, width, height);
        g.setColor(colorLetter);
        g.drawString(text, x + width / 2 - 15, y + height / 2 + 5);
    }


    public void mouseClicked(MouseEvent e) {
        if (e.getX()>=x && e.getX()<x+width){
            System.out.println("aaaaaaaaaaaaaaaaaaaa");
        }
        else {
            System.out.println("bbbbbbbbbbbbbbbbbbbbb");
        }

    }
}