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

    private Botao btnJogar, btnConfig, btnSair;

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
        btnConfig = new Botao(handler.getGame().WIDTH / 2 - 45, handler.getGame().HEIGHT / 2 - 10, 90, 25, "Configurações",
                Color.LIGHT_GRAY, Color.BLUE);
        btnSair = new Botao(handler.getGame().WIDTH / 2 - 45, handler.getGame().HEIGHT / 2 + 20, 90, 25, "Sair",
                Color.LIGHT_GRAY, Color.BLUE);
    }

    /**
     * Desenha o layout do menu
     * TODO desenhar um menu mais bonito
     * @param g
     */
    public void render(Graphics g) {
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
    public boolean isInBox(Botao b, int x, int y){
        return (x >= (b.getX()*3) && x <= ((b.getX() + b.getWidth())*handler.getGame().SCALE) && y >= (b.getY()*3) && y <= ((b.getY() + b.getHeight())*handler.getGame().SCALE));
    }

    public Botao getBotaoJogar(){
        return btnJogar;
    }

    public Botao getConfg(){
        return btnConfig;
    }

    public Botao getBtnSair(){
        return btnSair;
    }
}