package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.unamedgroup.placeholder.main.Game;

public class LabelList {
    private float x, y;
    private ArrayList<Label> box;
    private int fontSize;
    private Font font;
    private Color color, selectColor;
    private String lineUp;

    /**
     * 
     * @param x
     * @param y
     * @param font
     * @param color
     * @param lineup 
     */
    public LabelList(int x, int y, Font font, Color color, String lineup) {
        this.box = new ArrayList<>();
        this.x = x;
        this.y = y;
        if(font==null){this.font = CustomFont.getFont("AtariSmall.ttf", 18f);
        }else{this.font = font;}
        this.color = color;
        this.lineUp = lineup;
    }
    
    public void add(String txt){
        Label label = new Label(txt);
        label.setColor(color);
        box.add(label);
    }

    //===========// ARGUMENTOS E METODOS PARA ALINHAMENTO DO DESENHO DA LISTA //===========//
    public final static String CENTER = "Centro";
    public final static String LEFT = "Esquerda";
    public final static String RIGHT = "Direita";
    private void align(Graphics2D g){
        for(int i=0;i<box.size();i++){
            switch(lineUp){
                case"Centro":
                    box.get(i).setX(x-(g.getFontMetrics().stringWidth(box.get(i).getTxt())/2));
                break;
                case"Esquerda":
                    box.get(i).setX(x);
                break;
                case"Direita":
                    box.get(i).setX(Game.WIDTH-x-g.getFontMetrics().stringWidth(box.get(i).getTxt()));
                break;
            }
        int altura = ((g.getFontMetrics().getAscent()+g.getFontMetrics().getDescent())*(box.size()-2) + g.getFontMetrics().getDescent()*3);
        float Yp = y-(altura/2);
        box.get(i).setY(Yp+fontSize*i);
        
        }
    }
    
    //===========// ARGUMENTOS E METODOS PARA DESENHO DE BACKGROUND NA LISTA //===========//
    private boolean bgStatus = false;
    @SuppressWarnings("unused")
    private String bgTitle;
    private float bgAlpha;
    private Color bgColor;
    public void setBackground(String title, Color c, Float a){
        bgTitle = title;
        bgColor = c;
        bgAlpha = a;
        bgStatus = true;
    }
    
    //===========// ARGUMENTOS E METODOS PARA MENU DE SELEÇÃO //===========// 
    private ArrayList<Boolean> choices;
    public void setSelecionable(Color c){
        selectColor = c;
        choices = new ArrayList<>();
        box.forEach((_item) -> {
            choices.add(false);
        });
        choices.set(0, true);
        paintSelection(0);
    }
    
    
    private void paintSelection(int atual, int anterior){
        box.get(atual).setColor(selectColor);
        box.get(anterior).setColor(color);
        
    }
    private void paintSelection(int atual){
        box.get(atual).setColor(selectColor);
    }
    @SuppressWarnings("unused")
    private void paintSelection(){
        for(int i=0;i<choices.size();i++){
            if(choices.get(i)){
                box.get(i).setColor(Color.YELLOW);
            }else{
                box.get(i).setColor(Color.WHITE);
            }
        }
    }
  
    public void btn_UP(){
        for(int i=0;i<choices.size();i++){
            if(choices.get(i)){
                int j;
                if(i==0){
                    j=choices.size()-1;
                }else{
                    j=i-1;
                }
                choices.set(j, Boolean.TRUE);
                choices.set(i, Boolean.FALSE);
                paintSelection(j, i);
                break;
            }
        }
    }
    public void btn_DOWN(){
        for(int i=0;i<choices.size();i++){
            if(choices.get(i)){
                int j;
                if(i==choices.size()-1){
                    j=0;
                }else{
                    j=i+1;
                }
                choices.set(j, Boolean.TRUE);
                choices.set(i, Boolean.FALSE);
                paintSelection(j, i);
                break;
            }
        }
    }
    
    public int getSeletion(){
        for(int i=0;i<box.size();i++){
            if(choices.get(i)){
                return i;
            }
        }
       return 0;
    }
    
    //===================// Scroll //===================//
    public void ScrollOn(){
        y-=0.5f;
    }

    //===================// Getters and Setters //===================// 
    public ArrayList<Label> getBox() {
        return box;
    }

    public void setBox(ArrayList<Label> box) {
        this.box = box;
    }


    public void render(Graphics2D g){
        g.setFont(font);
        this.fontSize = g.getFontMetrics().getHeight();
        align(g);
        
        if(bgStatus){
            g.setColor(bgColor);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, bgAlpha));
            int size = 100;
            g.fillRect((int)x-size/2,(int) y-size/2, size, size);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            
            //new Text(background_title, x, y-100, new Font("Dialog",Font.BOLD, font.getSize()+8), Color.WHITE).draw(g);
        }
        
        for(int i=0;i<box.size();i++){
            g.setFont(font);
            g.setColor(box.get(i).getColor());
            g.drawString(box.get(i).getTxt(), box.get(i).getX(), box.get(i).getY());
        }
    }
}
