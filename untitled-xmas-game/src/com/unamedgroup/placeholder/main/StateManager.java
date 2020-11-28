package com.unamedgroup.placeholder.main;

import java.util.ArrayList;
import java.awt.Graphics;
import com.unamedgroup.placeholder.interfaces.State;
import com.unamedgroup.placeholder.graphics.states.*;

/**
 * Gerenciador de estados
 * @author Nathan
 */
public class StateManager{

    private static ArrayList<State> states = new ArrayList<>();
    private static int currentState = 0;
    /**
     * CADA STATE CRIADO DEVE SER LISTADO NESSE CONTRUTOR
     */
    public StateManager(Handler handler) {
        states.add(new State_00(states.size(), handler));
        states.add(new State_01(states.size(), handler));
        states.add(new Cutscene_A01(states.size(), handler));
    }

    /**
     * Utilize para trocar os State atual
     * Ao trocar toda as variaváis do state serão reinicializadas
     */
    public void setState(int state){
        currentState = state;
        states.get(currentState).init();
    }
    public State getCurrentState(){
        return states.get(currentState);
    }

    /**
     * Verifica a existência de um estado atual
     * @return Retorna se existe um estado atual
     */
    public boolean currentStateExist(){
        return states.get(currentState) != null;
    }


    
    public void init() {
        states.get(currentState).init();
    }


    public void tick() {
        states.get(currentState).tick();

    }

  
    public void render(Graphics g) {
        states.get(currentState).render(g);

    }
    
}
