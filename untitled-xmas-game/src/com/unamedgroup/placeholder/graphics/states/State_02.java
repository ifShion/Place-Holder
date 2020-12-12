package com.unamedgroup.placeholder.graphics.states;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Button;
import com.unamedgroup.placeholder.main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * @author Daniel Nogueira
 * Essa classe servirá para ser a tela do menu de início do jogo
 */
public class State_02 extends State {

    private Button btnJogar, btnConfig, btnSair;
    private SpriteSheet background;

    /**
     * @param id
     * @param handler
     */
    public State_02(int id, Handler handler) {
        super(id, handler);
        init();
        background = new SpriteSheet("/menu_background.jpg");
    }
    @Override
    public void init() {
        btnJogar = new Button(handler.getGame().WIDTH / 2 - 45, handler.getGame().HEIGHT / 2 - 40, 90, 25, "Jogar",
                Color.LIGHT_GRAY, Color.red);
        btnConfig = new Button(handler.getGame().WIDTH / 2 - 45, handler.getGame().HEIGHT / 2 - 10, 90, 25, "Configurações",
                Color.LIGHT_GRAY, Color.red);
        btnSair = new Button(handler.getGame().WIDTH / 2 - 45, handler.getGame().HEIGHT / 2 + 20, 90, 25, "Sair",
                Color.LIGHT_GRAY, Color.red);
    }
    @Override
    public void tick() {

    }
    
    /**
     * Desenha o layout do menu
     * TODO desenhar um menu mais bonito
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(background.getSpriteSheet(), 0, 0, null);
        btnJogar.draw((Graphics2D) g);
        btnConfig.draw((Graphics2D) g);
        btnSair.draw((Graphics2D) g);
    }

    /**
     * retorna se uma cordenada está dentro da área do botão
     * @param b
     * @param x
     * @param y
     * @return
     */
    public boolean isInBox(Button b, int x, int y){
        return (x >= (b.getX()*handler.getGame().SCALE) && x <= ((b.getX() + b.getWidth())*handler.getGame().SCALE) &&
            y >= (b.getY()*handler.getGame().SCALE) && y <= ((b.getY() + b.getHeight())*handler.getGame().SCALE));
    }

    public void mouseAction(MouseEvent e, MouseListener ml){
        if (isInBox(btnJogar, e.getX(), e.getY())){   //caso do botão de jogar
            handler.getDisplay().getCanvas().removeMouseListener(ml);
            handler.getStateManager().setState(Cutscene_Intro.id);
        }
        if (isInBox(btnConfig, e.getX(), e.getY())){
            //TODO criar um estado para essa ala aqui
        }
        if (isInBox(btnSair, e.getX(), e.getY())){
            System.exit(0);
        }
    }


    public Button getBtnJogar(){
        return btnJogar;
    }

    public Button getBtnConfg(){
        return btnConfig;
    }

    public Button getBtnSair(){
        return btnSair;
    }

}