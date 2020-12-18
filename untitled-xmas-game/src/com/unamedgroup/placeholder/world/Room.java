package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.unamedgroup.placeholder.entities.*;
import com.unamedgroup.placeholder.entities.enemies.*;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class Room extends World {
    private int ID;
    private Handler handler;
    private SpriteSheet map;
    private double[] respawnPoint = new double[2];
    
    public static Comparator<Entity> nodeSorter = (new Comparator<Entity>() {
		public int compare(Entity o1, Entity o2) {
			return o1.depth - o2.depth;
		}
	});
    
	public static List<Entity> entities = new LinkedList<>();
    /**
     * Constrói o mundo novo. É bom ter uma sprite sheet própria para os tiles de cada mapa
     * são muitos tiles
     * @param path
     * @param ID
     * @param handler
     * @author Daniel Neves
     */
    
    public Room(String path, int ID, String map, double respawnX, double respawnY, Handler handler) {
        super(path, handler);
        
        this.map = new SpriteSheet(map);
        this.ID = ID;
        this.handler = handler;
        this.respawnPoint[0] = respawnX * World.TILE_SIZE;
        this.respawnPoint[1] = respawnY * World.TILE_SIZE;
    }
    
    public int getID() {
		return ID;
	}
    
    public SpriteSheet getMap() {
		return map;
	}

    public static void sortEntities() {
    	entities.sort(nodeSorter);
    }
    
    /**                     
     * Cria as Entidades presentes nas salas.
     * As cores no Mundo não são mais necessárias para inicializar novas entidades no mapa, ao invés disso, usaremos as variáveis
     * abaixo, bastando apenas enviar suas posições e possíveis especificações.
     * No entanto, as cores na imagem do mapa podem ser usadas como referência para tomada de decisão de onde e como posicionar as entidades.
     * Ao criar uma nova entidade instanciada numa sala, será tbm necesssário criar um método nessa claasse com suas especificações e as
     * variáveis da sua criação devem ser passadas como parâmetros do método.
     * Possíveis spawners não fazem parte da taxa de iteração das entidades, mas poderão tbm ser instanciados ou modificados aqui.
     */
    public void createEntities(){
        switch(handler.getGame().getCurrentMapID()){
            case 0001: // Mapa do corredor, uso numa cutscene
                break;
            case 1000: 
                //------- INIMIGO -------//
                createNewCannonEnemy(35, 6, 6);
                createNewWalkerEnemy(55, 7);
                createNewWalkerEnemy(65, 7);
                createNewWalkerEnemy(75, 7);
                createNewWalkerEnemy(85, 7);
                createNewWalkerEnemy(40, 28);
                createNewWalkerEnemy(79, 28);
                createNewHuggerEnemy(8, 6);
                createNewWalkerEnemy(15, 36);
                //createNewTrackerEnemy(40, 25);
                createNewCannonEnemy(116, 17, -6);
                //------- INTERAGIVEL -------//
                createNewKey(5,5);
                createNewSucker(4,19);
                createNewSucker(29,7);
                placeGateSwitch(31, 6, 0);
                //------- PORTAL -------//
                placeDoor(114, 34, 1001, 5 * World.TILE_SIZE, 26 * World.TILE_SIZE, true);
                //------- CENARIO -------//
                createForniture("prateleiraCheia", 8, 55, 26);
                createForniture("prateleiraMetalCheia", 5, 51, 29);
                createForniture("prateleiraMetalCheia", 8, 67, 29);
                //placeGateSwitch(60, 28, 0);
                //placeGateSwitch(65, 28, 1);
                //placeGateSwitch(70, 28, 2);
                break;

            case 1001:
                //------- PORTAL -------//
                placeDoor(5, 26, 2000, 114 * World.TILE_SIZE, 34 * World.TILE_SIZE, false);
                placeDoor(39, 26, 1002, 4 * World.TILE_SIZE, 75 * World.TILE_SIZE, false);
                //placeDoor(x, y, 1002, tpx, tpy, false);
                //placeDoor(x, y, 1003, tpx, tpy, false);
                placeGate(111, 24, 1000, 0, 0);
            break;
            case 1002:
                //------- INIMIGO -------//
                createNewTrackerEnemy(18, 70);
                createNewTrackerEnemy(35, 61);
                createNewTrackerEnemy(45, 44);
                createNewTrackerEnemy(25, 14);
                createNewTrackerEnemy(25, 32);
                createNewCannonEnemy(35,71, -1);
                createNewCannonEnemy(12,39, 1);
                createNewCannonEnemy(28,21, -1);
                createNewWalkerEnemy(16, 6);
                createNewWalkerEnemy(43, 10);
                createNewWalkerEnemy(4, 33);
                createNewWalkerEnemy(44, 63);
                //------- INTERAGIVEL -------//
                createNewKey(41,79);
                createNewSucker(32, 22);
                placeGateSwitch(45, 21, 1);
                //------- PORTAL -------//
                placeDoor(4, 75, 1001, 39 * World.TILE_SIZE, 26 * World.TILE_SIZE, false);
                placeDoor(42, 9, 1002, 42 * World.TILE_SIZE, 21 * World.TILE_SIZE, true);
                placeDoor(42, 21, 2002, 42 * World.TILE_SIZE, 9 * World.TILE_SIZE, false);
            break;
            case 2000:
                //------- INIMIGO -------//
                createNewCannonEnemy(35, 6, 6);
                createNewWalkerEnemy(55, 7);
                createNewWalkerEnemy(65, 7);
                createNewWalkerEnemy(75, 7);
                createNewWalkerEnemy(85, 7);
                createNewWalkerEnemy(40, 28);
                createNewWalkerEnemy(79, 28);
                createNewHuggerEnemy(8, 6);
                createNewWalkerEnemy(15, 36);
                //createNewTrackerEnemy(40, 25);
                createNewCannonEnemy(116, 17, -6);
                //------- INTERAGIVEL -------//
                createNewSucker(4,19);
                createNewSucker(29,7);
                placeGateSwitch(31, 6, 0);
                //------- PORTAL -------//
                placeDoor(114, 34, 1001, 5 * World.TILE_SIZE, 26 * World.TILE_SIZE, false);
                //------- CENARIO -------//
                createForniture("prateleiraCheia", 8, 55, 26);
                createForniture("prateleiraMetalCheia", 5, 51, 29);
                createForniture("prateleiraMetalCheia", 8, 67, 29);
            break;
            case 2002:
                //------- INIMIGO -------//
                createNewTrackerEnemy(18, 70);
                createNewTrackerEnemy(35, 61);
                createNewTrackerEnemy(45, 44);
                createNewTrackerEnemy(25, 14);
                createNewTrackerEnemy(25, 32);
                createNewCannonEnemy(35,71, -1);
                createNewCannonEnemy(12,39, 1);
                createNewCannonEnemy(28,21, -1);
                createNewWalkerEnemy(16, 6);
                createNewWalkerEnemy(43, 10);
                createNewWalkerEnemy(4, 33);
                createNewWalkerEnemy(44, 63);
                //------- INTERAGIVEL -------//
                placeGateSwitch(45, 21, 1);
                //------- PORTAL -------//
                placeDoor(4, 75, 1001, 39 * World.TILE_SIZE, 26 * World.TILE_SIZE, false);
                placeDoor(42, 9, 2002, 42 * World.TILE_SIZE, 21 * World.TILE_SIZE, false);
                placeDoor(42, 21, 2002, 42 * World.TILE_SIZE, 9 * World.TILE_SIZE, false);
            break;
            default:

        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
    
    /**
     * Esse método serve para teleportar o player quando ele colidir com um DoorTile. Podemos modificar ele para
     * teleportar qualquer entidade tbm
     * @param x
     * @param y
     * @param destiny
     * @param tpx
     * @param tpy
     */
    public void placeDoor(int x, int y, int destiny, int tpx, int tpy, boolean locked){
    	Room.entities.add(new Door(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 32, handler.getGame().getRoom().getMap(), 5, 3, 1, 7 * World.TILE_SIZE, 3 * World.TILE_SIZE, destiny, tpx, tpy, locked ,handler));
    }

    private void createNewCannonEnemy(int x, int y, int direction) {
    	CannonEnemy ce = new CannonEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 24, 32, direction, Game.nutCracker, 2, 2, 4, 8, 2, 0 , 0, handler);
    	ce.setMask(3, 1, 18, 31);
		Room.entities.add(ce);
	}
    
    private void createNewHuggerEnemy(int x, int y) {
    	HuggerEnemy he = new HuggerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 32, 32, Game.huggerEnemy, 3, 1, 5, 12, 8, 0, 0, handler);
    	he.setMask(0, 6, 16, 26);
		Room.entities.add(he);
	}
    
    private void createNewTrackerEnemy(int x, int y) {
    	TrackerEnemy te = new TrackerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 24, 32, Game.trackerEnemy, 3, 2, 7, 4, 2, 0, 0, handler);
		te.setMask(1, 0, 12, 10);
		Room.entities.add(te);
    }
    
    private void createNewWalkerEnemy(int x, int y) {
    	WalkerEnemy e = new WalkerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 3, 1, 3, 4, 2, 0 , 0, handler);
		Room.entities.add(e);
    }
    
    private void createNewKey(int x, int y){
        Key ke = new Key(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.key, 1, 0, 7, 8, 1, 0, 0, handler);
        ke.setMask(4, 4, 8, 8);
        Room.entities.add(ke);
    }

    private void createNewSucker(int x, int y){
        Sucker su = new Sucker(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.sucker, 1, 0, 7, 8, 4, 0, 0, handler);
        su.getAnimation().setSpriteY(Game.rand.nextInt(4));
        su.setMask(4, 4, 8, 8);
        Room.entities.add(su);
    }

    private void createForniture(String forniture,int qtd, int x, int y){
        ArrayList<Entity> forni = new ArrayList<>();
        x = x * World.TILE_SIZE;
        y = y * World.TILE_SIZE;
        switch(forniture){
            case "prateleiraCheia":
                for(int i=0;i<qtd;i++){
                    switch (i%5) {
                        case 0: // Presente
                            forni.add(new Entity(x+((1+i)*16), y-5, 16, 16, Game.forniture, 1, 1, 1, 1, 1, 48, 48, handler));
                            break;
                        case 1: // Bonequinho
                            forni.add(new Entity(x+((1+i)*16), y-5, 16, 16, Game.forniture, 1, 1, 1, 1, 1, 48, 80, handler));
                            break;
                        case 2: // Presente
                            forni.add(new Entity(x+((1+i)*16), y-5, 16, 16, Game.forniture, 1, 1, 1, 1, 1, 32, 48, handler));
                            break;
                        case 3: // Lego
                            forni.add(new Entity(x+((1+i)*16), y-5, 16, 16, Game.forniture, 1, 1, 1, 1, 1, 48, 64, handler));
                            break;
                        case 4: // Urso
                        forni.add(new Entity(x+((1+i)*16), y-18, 16, 32, Game.forniture, 1, 1, 1, 1, 1, 64, 48, handler));
                            break;
                        default:
                            break;
                    }
                }
            case "prateleira":
                forni.add(new Entity(x, y, 16, 16, Game.forniture, 2, 1, 1, 1, 1, 64, 0, handler));
                for(int i=0;i<qtd;i++){
                    forni.add(new Entity(x+(i*16)+16, y, 16, 16, Game.forniture, 0, 1, 1, 1, 1, 64, 32, handler));
                    if(i+1==qtd){
                        forni.add(new Entity(x+((1+i)*16)+16, y, 16, 16, Game.forniture, 2, 1, 1, 1, 1, 64, 16, handler));
                    }
                }
            break;
            case "prateleiraMetalCheia":
                for(int i=0;i<qtd;i++){
                    forni.add(new Entity(x, y-(i*16)-5, 32, 16, Game.forniture, 1, 1, 1, 1, 1, 0, 48, handler));
                }
            case "prateleiraMetal":
                for(int i=0;i<qtd;i++){
                    forni.add(new Entity(x, y-(i*16), 32, 16, Game.forniture, 1, 1, 1, 1, 1, 0, 64, handler));
                    if(i+1==qtd){
                        forni.add(new Entity(x, y-((1+i)*16), 32, 16, Game.forniture, 1, 1, 1, 1, 1, 0, 80, handler));
                    }
                }
                break;
            case "portao":
                forni.add(new Entity(x+9, y+4, 64, 48, Game.forniture, 1, 1, 1, 1, 1, 0, 0, handler));
            break;
            default:
                System.out.println("Classe Room - Forniture:"+forniture+" não encontrado");
                return;
        }
        if(!forni.isEmpty())
        forni.forEach((g)->{
            Room.entities.add(g);
        });
    }
    
    private void placeGate(int x, int y, int destiny, int tpx, int tpy){
        Room.entities.add(GateControl.getGate((double)x* World.TILE_SIZE,(double) y* World.TILE_SIZE-2, destiny, tpx, tpy, handler));
    }

    private void placeGateSwitch(int x, int y, int index){
        Room.entities.add(GateControl.getSwitch(index,x * World.TILE_SIZE, y * World.TILE_SIZE, handler));
    }

    //Modifiquei a condição de igualdade dos mapas para comparar seus arquivos de imagem, se forem os
    //mesmos arquivos, então eles são iguais. Pode ser útil no futuro.
    @Override
    public String toString() {
    	return super.path;
    }
    @Override
    public boolean equals(Object obj) {
        Room room = (Room) obj;
        return super.path.compareTo(room.path) == 0;
    }

    @Override
    public int hashCode() {
        return super.path.hashCode();
    }

	public double[] getRespawnPoint() {
		return respawnPoint;
	}
}
