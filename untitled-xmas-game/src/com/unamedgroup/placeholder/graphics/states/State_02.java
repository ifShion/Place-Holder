package com.unamedgroup.placeholder.graphics.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.Button;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

/**
 * @author Daniel Nogueira Essa classe servirá para ser a tela do menu de início
 *         do jogo
 */
public class State_02 extends State {

    private Button btnJogar, btnConfig, btnSair;
    private SpriteSheet background;
    private boolean clickedJogar = false, clickedConfig = false, clickedSair = false;
    private boolean flag = true;
    private Graphics2D graphics = null;

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
        btnJogar = new Button(Game.WIDTH / 2 - 45, Game.HEIGHT / 2 - 40, 90, 25, "Jogar",
                Color.LIGHT_GRAY, Color.red);
        btnConfig = new Button(Game.WIDTH / 2 - 45, Game.HEIGHT / 2 - 10, 90, 25,
                "Configurações", Color.LIGHT_GRAY, Color.red);
        btnSair = new Button(Game.WIDTH / 2 - 45, Game.HEIGHT / 2 + 20, 90, 25, "Sair",
                Color.LIGHT_GRAY, Color.red);
    }

    @Override
    public void tick() {
        //handler.getSounds().tick("Music", handler.getGameVolume());
    }

    /**
     * Desenha o layout do menu TODO desenhar um menu mais bonito
     * 
     * @param g
     */
    @Override
    public void render(Graphics g) {
        if (flag) {
            graphics = (Graphics2D) g;
            flag = false;
        }
        g.drawImage(background.getSpriteSheet(), 0, 0, null); 
        btnJogar.draw((Graphics2D) g);
        btnConfig.draw((Graphics2D) g);
        btnSair.draw((Graphics2D) g);
    }

    /**
     * retorna se uma cordenada está dentro da área do botão
     * 
     * @param b
     * @param x
     * @param y
     * @return
     */
    public boolean isInBox(Button b, int x, int y) {
        return (x >= (b.getX() * Game.SCALE) && x <= ((b.getX() + b.getWidth()) * Game.SCALE)
                && y >= (b.getY() * Game.SCALE)
                && y <= ((b.getY() + b.getHeight()) * Game.SCALE));
    }

    public void mouseAction(MouseEvent e, MouseListener ml) {
        if (isInBox(btnJogar, e.getX(), e.getY())) { // caso do botão de jogar
            clickedJogar = true;
            renderClick();
            handler.getDisplay().getCanvas().removeMouseListener(ml);
            handler.getStateManager().setState(Cutscene_Intro.ID);
        }
        if (isInBox(btnConfig, e.getX(), e.getY())) {
            clickedConfig = true;
            renderClick();
            // TODO criar um estado para essa ala aqui
        }
        if (isInBox(btnSair, e.getX(), e.getY())) {
            clickedSair = true;
            renderClick();
            System.exit(0);
        }
    }

    //Só para dar um efeitinho quando clica no botão
    private void renderClick() {
        if (clickedJogar) {
            btnJogar.setColorBack(btnJogar.getColorBack().brighter());
            btnJogar.setColorLetter(btnJogar.getColorLetter().brighter());
            btnJogar.draw(graphics);
        }
        if (clickedConfig) {
            btnConfig.setColorBack(btnConfig.getColorBack().brighter());
            btnConfig.setColorLetter(btnConfig.getColorLetter().brighter());
            btnConfig.draw(graphics);
        }
        if (clickedSair) {
            btnSair.setColorBack(btnSair.getColorBack().brighter());
            btnSair.setColorLetter(btnSair.getColorLetter().brighter());
            btnSair.draw(graphics);
        }
        try {
            Thread.sleep(90);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
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