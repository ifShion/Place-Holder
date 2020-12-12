package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Graphics2D;
import java.awt.Color;


/**
 * Essa classe é para a criação de botões para o menu e talvez para outras finalidades
 * @author Daniel Nogueira
 */
public class Button{

    private int x, y, width, height, reduceSpace=15;
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
    public Button(int x, int y, int width, int height, String text, Color colorBack, Color colorLetter) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.colorBack = colorBack;
        this.colorLetter = colorLetter;
        reduceSpace += align();
    }

    private int align(){
        int total=0;
        for (int i=0; i<text.length(); i++){
            if (i>4)
                total++;
        }
        return (int) (total*3.2);
    }

    /**
     * Desenha o botão na tela.
     * @param g
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(x-1, y-1, width+3, height+3);
        g.setColor(colorBack);
        g.fillRect(x, y, width, height);
        g.setColor(colorLetter);
        g.drawString(text, x + width / 2 - reduceSpace, y + height / 2 + 5);
    }

    //getters e setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColorBack() {
        return colorBack;
    }

    public void setColorBack(Color colorBack) {
        this.colorBack = colorBack;
    }

    public Color getColorLetter() {
        return colorLetter;
    }

    public void setColorLetter(Color colorLetter) {
        this.colorLetter = colorLetter;
    } 
}