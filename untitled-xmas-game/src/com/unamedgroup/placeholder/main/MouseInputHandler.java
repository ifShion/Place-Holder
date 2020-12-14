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
        this.handler.getDisplay().getCanvas().addMouseListener(this);
        this.stateManager = this.handler.getStateManager();
    }

    /**
     * Esse método ocorre toda vez que o botão é clicado.
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        State_02 menu = null;
        if (stateManager.getCurrentState().getId()==3){
            menu = (State_02) stateManager.getCurrentState();
            menu.mouseAction(e, this);
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
