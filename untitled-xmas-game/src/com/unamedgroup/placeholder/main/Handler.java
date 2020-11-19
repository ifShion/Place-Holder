package com.unamedgroup.placeholder.main;

import java.awt.Graphics;

import com.unamedgroup.placeholder.world.Camera;

/**
 * Essa classe serve para relacionar classes distintas sem um relacionamento direto, o que ajuda a secar e melhorar
 * a legibilidade do código
 * @author Daniel Nogueira
 */
public class Handler {
    private InputHandler inputHandler; // input vai controlar toda entrada de comandos do jogador.
    private StateManager stateManager; // stateManager vai gerenciar toda tela de pintura do jogo.
    private Game game;
    private Camera camera;

    /**
     * @param game
     * @param camera
     * Recebe um parametro de game e camera para lidar com tudo que estiver lá, a partir daqui
     */
    Handler(Game game, Camera camera){
        this.game = game;
        this.camera = camera;
        inputHandler = new InputHandler(game);
        stateManager = new StateManager(this);
    }

    public void tick(){
        inputHandler.tick();
        stateManager.tick();
    }

    public void render(Graphics g){
        stateManager.render(g);
    }

    //getters e setters
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void setInputHandler(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    
}
