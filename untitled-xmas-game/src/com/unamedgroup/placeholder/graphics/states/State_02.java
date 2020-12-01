package com.unamedgroup.placeholder.graphics.states;

import com.unamedgroup.placeholder.graphics.screen_components.Botao;
import com.unamedgroup.placeholder.main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author Daniel Nogueira
 * Essa classe servirá para ser a tela do menu de início do jogo
 */
public class State_02 extends State {

    private Botao btnJogar;

    /**
     * @param id
     * @param handler
     */
    public State_02(int id, Handler handler) {
        super(id, handler);
        init();
    }

    public void init() {
        btnJogar = new Botao(handler.getGame().WIDTH / 2 - 45, handler.getGame().HEIGHT / 2 - 40, 90, 25, "Jogar",
                Color.LIGHT_GRAY, Color.BLUE);
    }

    /**
     * @param g
     */
    public void render(Graphics g) {
        btnJogar.draw((Graphics2D) g);
    }

    public void tick(){
        //handler.getMouseInputHandler().mouseClicked(handler.getDisplay());
    }

}