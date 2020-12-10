package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.util.LinkedHashSet;
import java.util.Set;

import com.unamedgroup.placeholder.entities.Door;
import com.unamedgroup.placeholder.entities.Key;
import com.unamedgroup.placeholder.entities.Sucker;
import com.unamedgroup.placeholder.entities.enemies.CannonEnemy;
import com.unamedgroup.placeholder.entities.enemies.HuggerEnemy;
import com.unamedgroup.placeholder.entities.enemies.TrackerEnemy;
import com.unamedgroup.placeholder.entities.enemies.WalkerEnemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;

public class Room extends World {
    private Set<Door> doors;
    private int ID;
    private Handler handler;
    private SpriteSheet map;
    /**
     * Constrói o mundo novo. É bom ter uma sprite sheet própria para os tiles de cada mapa
     * são muitos tiles
     * @param path
     * @param ID
     * @param handler
     * @author Daniel Neves
     */
    
    public Room(String path, int ID, String map, Handler handler) {
        super(path, handler);
        
        this.map = new SpriteSheet(map);
        this.ID = ID;
        this.handler = handler;
    }
    
    public int getID() {
		return ID;
	}
    
    public SpriteSheet getMap() {
		return map;
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
        doors = new LinkedHashSet<>();
//    	Game.entities = new LinkedList<>();
//    	Game.enemies = new LinkedList<>();
//    	Game.projectiles = new ArrayList<>();
        switch(handler.getGame().getCurrentMapID()){
            case 1001:
                createNewCannonEnemy(39, 65, -6);
                createNewCannonEnemy(30, 65, -6);
                createNewCannonEnemy(41, 43, 2);
                createNewWalkerEnemy(31, 36);
                createNewWalkerEnemy(21, 37);
                createNewWalkerEnemy(21, 54);
                createNewWalkerEnemy(21, 18);
                createNewWalkerEnemy(32, 18);
                createNewCannonEnemy(21, 5, -6);
                createNewWalkerEnemy(35, 9);
                placeDoor(4, 21, 1001, 3, 60);
                createNewKey(8,69);
                createNewSucker(5,69);
                placeLockedDoor(12, 68, 1001, 150, 60);
                break;
            case 2000: // Mapa do corredor, uso numa cutscene
                break;
            default:

        }
    }

    public void tick(){
        doors.forEach(door-> door.tick());
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
    public void placeDoor(int x, int y, int destiny, int tpx, int tpy){
    	Game.entities.add(new Door(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 32, handler.getGame().room.getMap(), 2, 1, 1, 7 * World.TILE_SIZE, 3 * World.TILE_SIZE, destiny, tpx, tpy, false ,handler));
    }
    
    public void placeLockedDoor(int x, int y, int destiny, int tpx, int tpy){
    	Game.entities.add(new Door(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 32, handler.getGame().room.getMap(), 2, 1, 1, 7 * World.TILE_SIZE, 3 * World.TILE_SIZE, destiny, tpx, tpy, true ,handler));
    }

    private void createNewCannonEnemy(int x, int y, int direction) {
    	CannonEnemy ce = new CannonEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 24, 32, direction, Game.nutCrackerTest, 1, 2, 4, 8, 2, 0 , 0, handler);
    	ce.setMask(3, 1, 18, 31);
		Game.entities.add(ce);
	}
    
    private void createNewHuggerEnemy(int x, int y) {
    	HuggerEnemy he = new HuggerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 1, 1, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
		Game.entities.add(he);
	}
    
    private void createNewTrackerEnemy(int x, int y) {
    	TrackerEnemy te = new TrackerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 1, 2, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
		te.setMask(3, 4, 10, 8);
		Game.entities.add(te);
    }
    
    private void createNewWalkerEnemy(int x, int y) {
    	WalkerEnemy e = new WalkerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 1, 1, 3, 4, 2, 0 , 0, handler);
		Game.entities.add(e);
    }
    
    private void createNewKey(int x, int y){
        Key ke = new Key(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.key, 1, 0, 10, 8, 1, 0, 0, handler);
        ke.setMask(4, 4, 8, 8);
        Game.entities.add(ke);
    }

    private void createNewSucker(int x, int y){
        Sucker su = new Sucker(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.sucker, 1, 0, 10, 8, 1, 0, 0, handler);
        su.setMask(4, 4, 8, 8);
        Game.entities.add(su);
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
}
