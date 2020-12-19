package com.unamedgroup.placeholder.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.imageio.ImageIO;
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
    public Display() {
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE));
        canvas.setMaximumSize(new Dimension(Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE));
        canvas.setMinimumSize(new Dimension(Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE));
        canvas.setFocusable(true);
        

        jframe = new JFrame(Game.NAME);
        jframe.add(canvas);
        jframe.pack();

        Image iconImage = null;
		try {
			iconImage = ImageIO.read(getClass().getResource("/img/iconImage.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		jframe.setIconImage(iconImage);
        
        jframe.setVisible(true);
        jframe.setResizable(false);
        jframe.requestFocus();
        jframe.setAlwaysOnTop(false);
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
		jframe.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
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
