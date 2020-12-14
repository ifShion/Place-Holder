package com.unamedgroup.placeholder.main;

import java.awt.Graphics;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.unamedgroup.placeholder.sound.SoundManager;
import com.unamedgroup.placeholder.sound.Sounds;
import com.unamedgroup.placeholder.world.Camera;

/**
 * Essa classe serve para relacionar classes distintas sem um relacionamento
 * direto, o que ajuda a secar e melhorar a legibilidade do código
 * 
 * @author Daniel Nogueira
 */
public class Handler {
    private InputHandler inputHandler; // input vai controlar toda entrada de comandos do jogador.
    private StateManager stateManager; // stateManager vai gerenciar toda tela de pintura do jogo.
    private Game game; // guarda uma referência à classe jogo
    private Camera camera; // cria um objeto câmera
    private Display display; // cria um objeto display que lida com tudo em relação ao display
    private MouseInputHandler mouseInputHandler; // input para o mouse
    private SoundManager soundManager; // Classe para mexer com os sons
    private Sounds sounds;

    /**
     * @param game
     * @param display Recebe um parametro de game e camera para lidar com tudo que
     *                estiver lá, a partir daqui
     * @throws UnsupportedAudioFileException
     */
    Handler(Game game, Display display) throws UnsupportedAudioFileException {
        this.game = game;
        this.camera = new Camera();
        this.display = display;
        inputHandler = new InputHandler(display);
        stateManager = new StateManager(this);
        soundManager = new SoundManager();
        sounds = new Sounds();

        stateManager.init();
        mouseInputHandler = new MouseInputHandler(this);
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

    public MouseInputHandler getMouseInputHandler() {
        return mouseInputHandler;
    }

    public void setMouseInputHandler(MouseInputHandler mouseInputHandler) {
        this.mouseInputHandler = mouseInputHandler;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public void setSounds(Sounds sounds) {
        this.sounds = sounds;
    }
}
