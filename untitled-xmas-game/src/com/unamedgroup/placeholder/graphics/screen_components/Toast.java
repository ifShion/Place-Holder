package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.main.Game;


public class Toast {
    private final String txt;
    private final int width = Game.WIDTH;
    private final int height = Game.HEIGHT/9;
    private final Font font;
    private  int x = 0;
    private  int y = 0;

    /**
     * @param txt
     * @param x
     * @param y
     * @param duration
     * @param font
     */
    public Toast(String txt, int x, int y, int duration, Font font) {
        this.txt = txt;
        this.x = x;
        this.y = y;
        this.duration = duration*60;
        this.font = font;
    }

    
   private int CenterY(int y1, int y2, int tam){
       return ((y1+y2)/2)+(tam/4);
   }
    
    
    private float alpha = 0.0f;
    private boolean drawing = false;
    private final int duration;
    private int cronometro;
    public void draw(Graphics2D g){
        if(drawing){
            if(cronometro==duration){
                if(alpha<0.9f){
                alpha+=0.01f;
                }else{
                    cronometro--;
                }
            }
            if(cronometro<duration && cronometro>0){
                cronometro--;
            }
            
            if(cronometro==0){
                alpha-=0.01f;
                if(alpha<=0){
                    alpha=0;
                    drawing=false;
                }
            }
            
            
            g.setColor(new Color(204, 231, 232));                                           // Cor do fundo
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            //g.fillRect(x, y, width, height);
            g.setColor(Color.white);                                              // Cor do texto
            g.setFont(font);
            //g.setFont(new Font("Dialog",Font.BOLD, 15));
            g.drawString(txt, (width-g.getFontMetrics().stringWidth(txt))/2, CenterY(y,height+y, g.getFontMetrics().getHeight()));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
    
    public void doit(){
        cronometro = duration;
        drawing = true;
    }
}
