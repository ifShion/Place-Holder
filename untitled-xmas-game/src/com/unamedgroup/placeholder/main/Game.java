package com.unamedgroup.placeholder.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.world.Camera;

/**
 * Inicializa o jogo, comanda as ações q o projeto fará dependendo 
 * do estado do jogo.
 * 
 * @version alpha 1.0
 * @author Daniel Neves
 * ...
 */

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final String NAME = "Place Holder";
	private InputHandler input = new InputHandler(this);// input vai controlar toda entrada de comandos do jogador.
	/*---------------------------------------------------------------*/
	//Inicializando variáveis do Canvas
	public static JFrame frame;
	public boolean isFullScreen;
	private Thread thread;
	private boolean isRunning;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 160;
	public static final int SCALE = 2;

	public static String gameState = "MAIN MENU";

	public boolean isPaused;
	/*---------------------------------------------------------------*/
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

	public static Random rand;

	/*----------------------------------------------------------------*/
	public static Camera camera;
	/*----------------------------------------------------------------*/
	//Criar lista de Entidades para melhor gerenciamento de ticks e render
	public static Player player;
	/*----------------------------------------------------------------*/
	
	/**
	 * Inicializa todas as variáveis e obejetos do jogo, como as entidades,
	 * menus e sprites.
	 */
	public Game() {
		rand = new Random();
		input = new InputHandler(this);
		// setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));//fullscreen
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		
		camera = new Camera();
		player = new Player(WIDTH/2, HEIGHT/2, 16, 16, null, 1, 2, this, input);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	/**
	 * Cria e configura a janela do projeto.
	 */
	
	private void initFrame() {
		frame = new JFrame(Game.NAME);
		frame.add(this);
		// frame.setUndecorated(true); //fullscreen
		frame.setResizable(false);
		frame.pack();
		frame.setAlwaysOnTop(true);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/*----------------------------------------------------------------*/
	/**
	 * Executa todas as ações e macânicas de jogo.
	 */
	public void tick() {
		input.tick();

		player.tick();
	}

	/**
	 * Desenha na tela os gráficos do jogo.
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics g = image.getGraphics();
		// Plano de fundo
		g.setColor(new Color(45, 45, 40));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		/**/
		//Desenho pixelado
		player.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		//Desenho não pixelado (multiplicar as dimensões pela SCALE do jogo.)
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
