package com.unamedgroup.placeholder.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.entities.Projectile;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.world.Maps;
import com.unamedgroup.placeholder.world.Room;

/**
 * Inicializa o jogo, comanda as ações q o projeto fará dependendo 
 * do estado do jogo.
 * 
 * @version alpha 1.0
 * @author Daniel Neves
 * @author Daniel Nogueira
 * @author Nathan
 * ...
 */

@SuppressWarnings("unused")
public class Game implements Runnable {
	private static final long serialVersionUID = 3L;

	public static final String NAME = "O Segredo da Fábrica";	// Titulo da jogo
	private Display display; 									// Janela do jogo
	private Handler handler;									// Uma classe para fazer a comunicação entre diferentes classes
	/*---------------------------------------------------------------*/
	//Inicializando variáveis do Display
	public boolean isFullScreen;						// Estado do tela
	private Thread thread;								// Thread onde o jogo roda
	private boolean isRunning;							// Booleano para verificar se o jogo está rodando
	public static final int WIDTH = 240;				// Variável que define a largura da tela do jogo
	public static final int HEIGHT = 160;				// Variável que define a altura da tela do jogo
	public static final int SCALE = 3;
	
	public boolean isPaused;							// Booleano para verificar se o jogo está pausado ou não
	/*---------------------------------------------------------------*/
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

	public static Random rand;

	/*----------------------------------------------------------------*/							
	// Adicionei um objeto de teste para construir o mundo com colisão

	public static SpriteSheet walkerEnemy;
	public static SpriteSheet alpha;
	public static SpriteSheet nutCracker;
	public static SpriteSheet huggerEnemy;
	public static SpriteSheet hud;
	public static SpriteSheet key, sucker;
	public SpriteSheet currentMap;
	/*----------------------------------------------------------------*/
	public Room room;
	public Maps maps;
	private int currentMapID;	
	
	// Conserta isso aqui depois DAN S2: Tá resolvido. Se quisermos começar de outro mapa é só mudar isso, ou, quando tivermos um sistema de 
	// save e load pronto, sobrescrever essa variável.
	
	public boolean alternatingMaps;
	public boolean statesUseMaps;	
	/*----------------------------------------------------------------*/
	//Adicionei um conjunto q deve conter todas as entidades do jogo para executar seu tick e render
	public static Comparator<Entity> nodeSorter = (new Comparator<Entity>(){
		public int compare(Entity o1, Entity o2) {
			return o1.depth - o2.depth;
		}
	});
	
	public static List<Entity> entities = new LinkedList<>();
	public static List<Projectile> projectiles = new ArrayList<>();  
	private Player player;	// Player é instanciado pelo State
	
	/*----------------------------------------------------------------*/
	
	/**
	 * Inicializa todas as variáveis e objetos do jogo, como as entidades,
	 * menus e sprites.
	 */
	public Game() {
		init();
		// setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize())); //fullscreen
		
		alternatingMaps = true;
	}

	private void init(){
		walkerEnemy = new SpriteSheet("/spritesheet/walkerEnemySprite.png");
		alpha = new SpriteSheet("/spritesheet/alphaTestbackup.png");
		nutCracker = new SpriteSheet("/spritesheet/nutCracker.png");
		currentMap = new SpriteSheet("/spriteSheetMapa1.png");
		hud = new SpriteSheet("/spritesheet/hud.png");
		key = new SpriteSheet("/spritesheet/key.png");
		sucker = new SpriteSheet("/spritesheet/sucker.png");
		huggerEnemy = new SpriteSheet("/spritesheet/HuggerEnemy.png");

		display = new Display(Game.NAME, WIDTH, HEIGHT, SCALE);
		rand = new Random();

		handler = new Handler(this, display);
		maps = new Maps(handler);
	}

	/**
	 * Atualiza a lista de entidades 
	 * É util caso eu troque de jogador em mudanças de state
	 */
	public void updateEntities(){
		entities = new LinkedList<>();
		projectiles = new ArrayList<>();
		entities.add(player);
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
		handler.tick();
		if(statesUseMaps){
			if(!alternatingMaps) {
				for (int i = 0; i < entities.size(); i++) entities.get(i).tick();
				for (int i = 0; i < projectiles.size(); i++) projectiles.get(i).tick();
			}else
				maps.tick();
		}
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
		
		entities.sort(nodeSorter);
		for (Entity entity : entities) entity.render(g);
		for (int i = 0; i < projectiles.size(); i++) projectiles.get(i).render(g);
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


	/**
	 * Getters and Setters
	 */
	
	public int getCurrentMapID() {
		return this.currentMapID;
	}

	public void setCurrentMapID(int currentMapID) {
		statesUseMaps = true;
		this.currentMapID = currentMapID;
	}

	/**
	 * Troca o ID do mapa corrente e ativa a condição para trocar de mapa
	 * @param mapId
	 */
	public void changeCurrentMapID(int mapId){
		alternatingMaps = true;
		currentMapID = mapId;
	}


	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
