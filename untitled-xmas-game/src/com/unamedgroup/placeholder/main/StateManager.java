package com.unamedgroup.placeholder.main;

import java.util.ArrayList;
import java.awt.Graphics;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.graphics.states.*;


/**
 * Gerenciador de estados
 * @author Nathan
 */
public class StateManager implements State{

    public static ArrayList<State> states = new ArrayList<>();
    public static int currentState = 0;
    /**
     * CADA STATE CRIADO DEVE SER LISTADO NESSE CONTRUTOR
     */
    public StateManager() {
        states.add(new State_00(states.size()));
        states.add(new State_01(states.size()));
    }

    /**
     * Utilize para trocar os State atual
     * Ao trocar toda as variavùis do state serùo reinicializadas
     */
    public static void setState(int state){
        currentState = state;
        states.get(currentState).init();
    }

    /**
     * Verifica a existùncia de um estado atual
     * @return Retorna se existe um estado atual
     */
    public boolean currentStateExist(){
        return states.get(currentState) != null;
    }


    @Override
    public void init() {
        states.get(currentState).init();

    }

    @Override
    public void tick() {
        states.get(currentState).tick();

    }

    @Override
    public void render(Graphics g) {
        states.get(currentState).render(g);

    }
    
}
