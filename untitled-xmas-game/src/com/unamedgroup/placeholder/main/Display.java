package com.unamedgroup.placeholder.main;

import java.awt.Canvas;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


/**
 * Cont�m todas as propriedades da Janela
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
        canvas.setFocusable(true);
        

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

    public Canvas getCanvas(){
        return this.canvas;
    }

	public void setFullScreen() {
        jframe.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        jframe.setLocation(0, 0); 
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); //width=1366,height=768
        System.out.println(d);

	}

	public void setRegularSize(int width, int height, int scale) {
        jframe.setSize(new Dimension(width*scale, height*scale));
        jframe.setLocation(400, 200);
        jframe.pack();
	}
}
