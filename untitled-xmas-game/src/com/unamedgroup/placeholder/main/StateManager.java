package com.unamedgroup.placeholder.main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.unamedgroup.placeholder.graphics.states.*;

/**
 * Gerenciador de estados
 * @author Nathan
 */
public class StateManager{

    private static ArrayList<State> states = new ArrayList<>();
    private static int currentState = 2;
    private boolean paused=false;

    /**
     * CADA STATE CRIADO DEVE SER LISTADO NESSE CONSTRUTOR
     * @param handler
     */
    public StateManager(Handler handler) {
        states.add(new State_00(states.size(), handler));
        states.add(new State_01(states.size(), handler));
        states.add(new Cutscene_A01(states.size(), handler));
        states.add(new State_02(states.size(), handler));
        states.add(new Cutscene_Corredor(states.size(), handler));
        states.add(new Cutscene_Intro(states.size(),handler));
        states.add(new Menu_Principal(states.size(), handler));
        states.add(new Menu_Creditos(states.size(), handler));
        states.add(new State_Pause(states.size(), handler));
        states.add(new State_MenuConfig(states.size(), handler));
        states.add(new State_MenuConfig_Controle(states.size(), handler));
        states.add(new State_MenuConfig_Audio(states.size(), handler));
        states.add(new State_MenuConfig_Tela(states.size(), handler));
    }

    /**
     * Utilize para trocar os State atual
     * Ao trocar toda as variaváis do state serão reinicializadas
     * @param state
     */
    public void setState(int state){
        currentState = state;
        states.get(currentState).init();
    }

    public void setStatePause(int state){
        currentState = state;
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
        if (states.get(currentState).getClass() == State_Pause.class && paused){
            states.get(currentState).tick();
        }
        else if (!paused){
            //System.out.println("\nentrouTick\n");
            states.get(currentState).tick();
        }

    }

  
    public void render(Graphics g) {
        if (states.get(currentState).getClass() == State_Pause.class && paused){
            states.get(currentState).render(g);
        }
        else if (!paused){
            //System.out.println("\nentrouRender\n");
            states.get(currentState).render(g);
        }
    }

    public ArrayList<State> getStates(){
        return states;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
