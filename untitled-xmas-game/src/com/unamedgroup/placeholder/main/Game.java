package com.unamedgroup.placeholder.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.states.*;
import com.unamedgroup.placeholder.world.Camera;
import com.unamedgroup.placeholder.world.Maps;
import com.unamedgroup.placeholder.world.Room;

/**
 * Inicializa o jogo, comanda as ações q o projeto fará dependendo 
 * do estado do jogo.
 * 
 * @version alpha 1.0
 * @author Daniel Neves
 * @author Nathan Ferraz
 * ...
 */

@SuppressWarnings("unused")
public class Game implements Runnable {
	private static final long serialVersionUID = 3L;

	public static final String NAME = "Place Holder";	// Titulo da jogo
	private Display display; 					// Janela do jogo
	public Handler handler;								// Uma classe para fazer a comunicação entre diferentes classes
	/*---------------------------------------------------------------*/
	//Inicializando variáveis do Display
	public boolean isFullScreen;						// Estado do tela
	private Thread thread;
	private boolean isRunning;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final int SCALE = 2;
	
	public boolean isPaused;
	/*---------------------------------------------------------------*/
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

	public static Random rand;

	/*----------------------------------------------------------------*/
	private Camera camera;

	// Adicionei um objeto de teste para construir o mundo com colisão

	public static SpriteSheet spriteTeste;				 
	/*----------------------------------------------------------------*/
	public Room room;
	public Maps maps;

	public int currentMapID = 1001;	
	// Conserta isso aqui depois DAN S2: Tá resolvido. Se quisermos começar de outro mapa é só mudar isso, ou, quando tivermos um sistema de 
	// save e load pronto, sobrescrever essa variável.
	public boolean alternatingMaps;
	/*----------------------------------------------------------------*/
	//Adicionei um conjunto q deve conter todas as entidades do jogo para executar seu tick e render
	public static Comparator<Entity> nodeSorter = (new Comparator<Entity>(){
		public int compare(Entity o1, Entity o2) {
			return o1.depth - o2.depth;
		};
	});
	public static Set<Entity> entities = new TreeSet<>(nodeSorter);	
	public static Player player;	// Player é instanciado pelo State
	
	/*----------------------------------------------------------------*/
	
	/**
	 * Inicializa todas as variáveis e objetos do jogo, como as entidades,
	 * menus e sprites.
	 */
	public Game() {
		spriteTeste = new SpriteSheet("/testSpriteSheet1.png");

		display = new Display(Game.NAME, WIDTH, HEIGHT, SCALE);
		rand = new Random();
		init();

		// setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize())); //fullscreen
		
		entities.add(player);

		
		alternatingMaps = true;
	}

	private void init(){
		camera = new Camera();
		handler = new Handler(this, camera, display);
		maps = new Maps(handler);
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
		if(!alternatingMaps) {
			room.tick();
			entities.forEach(entity -> entity.tick());
		}else
			maps.tick();
		
		handler.tick();
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
		if(!alternatingMaps)	
			room.render(g);
		
		for (Entity entity : entities) entity.render(g);
		if(handler.getStateManager().currentStateExist())	
			handler.getStateManager().render(g);
		
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
