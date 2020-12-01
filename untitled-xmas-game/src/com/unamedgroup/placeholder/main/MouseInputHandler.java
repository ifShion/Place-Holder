package com.unamedgroup.placeholder.main;

import java.awt.event.MouseListener;

import com.unamedgroup.placeholder.graphics.states.State_02;

import java.awt.event.MouseEvent;

/**
 * Classe para escutar eventos de mouse no estado do menu
 * @author Daniel Nogueira
 */
public class MouseInputHandler implements MouseListener {

    private StateManager stateManager;
    private Handler handler;

    /**
     * @param handler
     */
    public MouseInputHandler(Handler handler){
        this.handler = handler;
        handler.getDisplay().setMouseListener(this);
        this.stateManager = handler.getStateManager();
    }

    /**
     * Esse método ocorre toda vez que o botão é clicado.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        State_02 menu = null;
        if (stateManager.getCurrentState().getId()==3)
            menu = (State_02) stateManager.getCurrentState();

        if (menu.isInBox(menu.getBotaoJogar(), e.getX(), e.getY())){   //caso do botão de jogar
            handler.getDisplay().getCanvas().removeMouseListener(this);
            stateManager.setState(0);
        }
        if (menu.isInBox(menu.getConfg(), e.getX(), e.getY())){
            //TODO criar um estado para essa ala aqui
        }
        if (menu.isInBox(menu.getBtnSair(), e.getX(), e.getY())){
            System.exit(0);
        }
    }

    //Sem uso por enquanto
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
