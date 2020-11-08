package com.unamedgroup.placeholder.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.unamedgroup.placeholder.entities.*;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.world.*;

/**
 * Inicializa o jogo, comanda as ações q o projeto fará dependendo 
 * do estado do jogo.
 * 
 * @version alpha 1.0
 * @author Daniel Neves
 * @author Nathan Ferraz
 * ...
 */

public class Game implements Runnable {
	private static final long serialVersionUID = 3L;

	public static final String NAME = "Place Holder";	// Titulo da jogo
	private static Display display; 					// Janela do jogo
	public static InputHandler input; 					// input vai controlar toda entrada de comandos do jogador.
	public static StateManager stateManager;		 	// stateManager vai gerenciar toda tela de pintura do jogo.
	/*---------------------------------------------------------------*/
	//Inicializando variáveis do Display
	public boolean isFullScreen;						// Estado do tela
	private Thread thread;
	private boolean isRunning;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 160;
	public static final int SCALE = 2;

	public boolean isPaused;
	/*---------------------------------------------------------------*/
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

	public static Random rand;

	/*----------------------------------------------------------------*/
	public static Camera camera;

	// Adicionei um objeto de teste para construir o mundo com colisão
	public static World worldTeste;

	public static SpriteSheet spriteTeste;				 
	/*----------------------------------------------------------------*/
	//Adicionei uma lista q deve conter todas as entidades do jogo para executar seu tick e render
	public static List<Entity> entities;
	public static Player player;
	/*----------------------------------------------------------------*/
	
	/**
	 * Inicializa todas as variáveis e objetos do jogo, como as entidades,
	 * menus e sprites.
	 */
	public Game() {
		display = new Display(Game.NAME, WIDTH, HEIGHT, SCALE);
		input = new InputHandler(display);
		stateManager = new StateManager();
		stateManager.init();
		rand = new Random();

		// setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize())); //fullscreen
		
		camera = new Camera();
		spriteTeste = new SpriteSheet("/testSpriteSheet1.png");

		entities = new ArrayList<>(10);
		player = new Player(WIDTH/2, HEIGHT/2, 16, 16, spriteTeste.getSprite(7 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), 1, 2);
		entities.add(player);

		worldTeste = new World("/worldTest.png");
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	/*----------------------------------------------------------------*/
	/**
	 * Executa todas as ações e macânicas de jogo.
	 */
	public void tick() {
		if(!stateManager.currentStateExist()) return;
		stateManager.tick();
		input.tick();
	}

	/**
	 * Desenha na tela os gráficos do jogo.
	 */
	public void render() {
		BufferStrategy bs = display.getBufferStrategy();
		if (bs == null) {
			display.createBufferStrategy();
			bs = display.getBufferStrategy();
			//requestFocus();
		}

		Graphics g = image.getGraphics();

		// Plano de fundo
		g.setColor(new Color(45, 45, 40));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Passa o renderizador para o State corrente
		if(stateManager.currentStateExist())	
			stateManager.render(g);
		g = bs.getDrawGraphics();
		
		//Desenho não pixelado (multiplicar as dimensões pela SCALE do jogo.)
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		g.dispose();
		bs.show();
	}

	/*----------------------------------------------------------------*/
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Controla os FPS (frames por segundo) do jogo e 
	 * a sua taxa de atualização.
	 */
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		while (isRunning) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
}
