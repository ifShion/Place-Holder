package com.unamedgroup.placeholder.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


/**
 * Contém todas as propriedades da Janela
 * 
 * @author Nathan
 */
public class Display {
    private JFrame jframe; // Janela do jogo
    private Canvas canvas; // Tela de pintura

    /**
     * 
     * @param title Titulo da janela
     * @param width Largura
     * @param height Altura
     * @param scale Escala
     */
    public Display(String title, int width, int height, int scale) {
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width*scale,height*scale));
        canvas.setMaximumSize(new Dimension(width*scale,height*scale));
        canvas.setMinimumSize(new Dimension(width*scale,height*scale));
        canvas.setFocusable(false);
        

        jframe = new JFrame(title);
        jframe.add(canvas);
        jframe.pack();
        
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.setAlwaysOnTop(true);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    public JFrame getJframe() {
        return jframe;
    }

    public void setJframe(JFrame jframe) {
        this.jframe = jframe;
    }

    public BufferStrategy getBufferStrategy(){
        return canvas.getBufferStrategy();
    }
    
    public void createBufferStrategy(){
        canvas.createBufferStrategy(3);
    }

    public void setKeyListener(KeyListener k1){
        jframe.addKeyListener(k1);
    }
}
