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
    private Game game;                 // guarda uma referência à classe jogo
    private Camera camera;             // cria um objeto câmera
    private Display display;           // cria um objeto display que lida com tudo em relação ao display

    /**
     * @param game
     * @param display
     * Recebe um parametro de game e camera para lidar com tudo que estiver lá, a partir daqui
     */
    Handler(Game game, Display display){
        this.game = game;
        this.camera = new Camera();
        this.display = display;
        inputHandler = new InputHandler(game, display);
        stateManager = new StateManager(this);
        stateManager.init();
    }

    public void tick(){
        inputHandler.tick();
        //Em caso de não existência de um estado, ele simplesmente não usa o método tick() por retornar a função
        if(!stateManager.currentStateExist()) return; 
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
    
}
