package com.unamedgroup.placeholder.main;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseInputHandler implements MouseListener {

    private StateManager stateManager;

    public MouseInputHandler(StateManager sm, Display display){
        display.setMouseListener(this);
        this.stateManager = sm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
