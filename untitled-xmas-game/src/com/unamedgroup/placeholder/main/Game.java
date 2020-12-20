package com.unamedgroup.placeholder.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Toolkit;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.unamedgroup.placeholder.entities.Enemy;
import com.unamedgroup.placeholder.entities.Entity;
import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.states.menu.Menu_Principal;
import com.unamedgroup.placeholder.world.Maps;
import com.unamedgroup.placeholder.world.Room;

/**
 * Inicializa o jogo, comanda as ações q o projeto fará dependendo do estado do
 * jogo.
 * 
 * @version alpha 1.0
 * @author Daniel Neves
 * @author Daniel Nogueira
 * @author Nathan ...
 */

@SuppressWarnings("unused")
public class Game implements Runnable {
	private static final long serialVersionUID = 3L;

	public static final String NAME = "O Segredo da Fábrica"; // Titulo da jogo
	private Handler handler; // Uma classe para fazer a comunicação entre diferentes classes
	/*---------------------------------------------------------------*/
	// Inicializando variáveis do Display
	public boolean isFullScreen; // Estado do tela
	private Thread thread; // Thread onde o jogo roda
	private boolean isRunning; // Booleano para verificar se o jogo está rodando
	public static final int WIDTH = 240; // Variável que define a largura da tela do jogo
	public static final int HEIGHT = 160; // Variável que define a altura da tela do jogo
	public static final int SCALE = 3;

	/*---------------------------------------------------------------*/
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

	public static Random rand;

	/*----------------------------------------------------------------*/
	// Adicionei um objeto de teste para construir o mundo com colisão

	public static SpriteSheet walkerEnemy;
	public static SpriteSheet alpha;
	public static SpriteSheet nutCracker;
	public static SpriteSheet huggerEnemy;
	public static SpriteSheet trackerEnemy;
	public static SpriteSheet hud;
	public static SpriteSheet key, sucker, rattles;
	public static SpriteSheet forniture;

	public SpriteSheet currentMap;
	/*----------------------------------------------------------------*/
	private Room room;
	public Maps maps;
	private int currentMapID;


	public boolean alternatingMaps;
	public boolean statesUseMaps;
	/*----------------------------------------------------------------*/

	private Player player; // Player é instanciado pelo State

	/*----------------------------------------------------------------*/

	/**
	 * Inicializa todas as variáveis e objetos do jogo, como as entidades, menus e
	 * sprites.
	 * 
	 * @throws UnsupportedAudioFileException
	 */
	public Game() throws UnsupportedAudioFileException {
		init();
		alternatingMaps = true;
	}

	private void init() throws UnsupportedAudioFileException {
		walkerEnemy = new SpriteSheet("/spritesheet/walkerEnemySprite.png");
		alpha = new SpriteSheet("/spritesheet/alphaTestbackup.png");
		nutCracker = new SpriteSheet("/spritesheet/nutCracker.png");
		currentMap = new SpriteSheet("/spriteSheetMapa1.png");
		hud = new SpriteSheet("/spritesheet/hud.png");
		key = new SpriteSheet("/spritesheet/key.png");
		sucker = new SpriteSheet("/spritesheet/sucker.png");
		huggerEnemy = new SpriteSheet("/spritesheet/HuggerEnemy.png");
		trackerEnemy = new SpriteSheet("/spritesheet/trackerEnemySprite.png");
		forniture = new SpriteSheet("/spritesheet/armazem.png");
		rattles = new SpriteSheet("/spritesheet/rattles.png");

		rand = new Random();

		handler = new Handler(this);
		maps = new Maps(handler);

	}

	/**
	 * Atualiza a lista de entidades 
	 * É util caso eu troque de jogador em mudanças de state
	 */
	public void updateEntities(){
		Room.entities = new LinkedList<>();
		Room.entities.add(player);
	}
	
	public static void main(String[] args) throws UnsupportedAudioFileException {
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
				if (!handler.getStateManager().isPaused()){
					for (int i = 0; i < Room.entities.size(); i++) Room.entities.get(i).tick();
				}
			}else
				maps.tick();
		}
	}

	/**
	 * Desenha na tela os gráficos do jogo.
	 */
	public void render() {
		BufferStrategy bs = handler.getDisplay().getBufferStrategy();
		if (bs == null) {
			handler.getDisplay().createBufferStrategy();
			bs = handler.getDisplay().getBufferStrategy();
		}

		Graphics g = image.getGraphics();
		// Plano de fundo
		g.setColor(new Color(45, 45, 40));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Passa o renderizador para o State corrente
		if(!alternatingMaps)	
			room.render(g);

		Room.sortEntities();
		for (Entity entity : Room.entities) entity.render(g);
			

		if(handler.getStateManager().currentStateExist())	
			handler.getStateManager().render(g);


		g = bs.getDrawGraphics();
		
		//Desenho não pixelado (multiplicar as dimensões pela SCALE do jogo.)
		if (isFullScreen){
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, (int) d.getWidth(), (int) d.getHeight());
			g.drawImage(image, 0, 0, (int) d.getWidth() , (int) d.getHeight(), null);
			//Toolkit.getDefaultToolkit().getScreenSize()
		} else {
			g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		}
		
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

	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
