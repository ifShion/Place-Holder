package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.util.LinkedHashSet;
import java.util.Set;

import com.unamedgroup.placeholder.entities.enemies.CannonEnemy;
import com.unamedgroup.placeholder.entities.enemies.HuggerEnemy;
import com.unamedgroup.placeholder.entities.enemies.TrackerEnemy;
import com.unamedgroup.placeholder.entities.enemies.WalkerEnemy;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.tiles.DoorTile;

public class Room extends World {
    private Set<DoorTile> doors;
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
     * Esse método serve para teleportar o player quando ele colidir com um DoorTile. Podemos modificar ele para
     * teleportar qualquer entidade tbm
     * @param x
     * @param y
     * @param destiny
     * @param tpx
     * @param tpy
     */
    public void placeDoor(int x, int y, int destiny, int tpx, int tpy){
        doors.add(new DoorTile(x * World.TILE_SIZE, y * World.TILE_SIZE, handler.getGame().room.getMap().getSprite(12 * World.TILE_SIZE, 0 * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE), destiny, tpx * World.TILE_SIZE, tpy * World.TILE_SIZE, handler));
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
        switch(handler.getGame().getCurrentMapID()){
            case 1001:
            	createNewWalkerEnemy(30, 6);
            	createNewWalkerEnemy(32, 6);
            	createNewWalkerEnemy(34, 6);
            	createNewWalkerEnemy(27, 6);
            	createNewCannonEnemy(12, 68, 2);
            	createNewTrackerEnemy(5, 4);
            	createNewTrackerEnemy(4, 7);
            	createNewTrackerEnemy(7, 4);
            	createNewTrackerEnemy(12, 5);
            	createNewTrackerEnemy(8, 6);
            	createNewHuggerEnemy(22, 60);
                placeDoor(3, 11, 1001, 5, 9);
                placeDoor(5, 3, 1002, 4, 15);
                placeDoor(9, 3, 1001, 9, 14);
                break;
            case 1002:
                placeDoor(6, 15, 1001, 7, 10);
                placeDoor(12, 11, 1002, 8, 6);
                placeDoor(12, 12, 1002, 8, 6);
                placeDoor(13, 16, 1001, 8, 6);
                break;
            case 1000:
                placeDoor(50, 10, 1000, 50, 5);
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
    
    private void createNewCannonEnemy(int x, int y, int direction) {
    	CannonEnemy ce = new CannonEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 24, 32, direction, Game.nutCrackerTest, 1, 2, 4, 8, 2, 0 , 0, handler);
		Game.entities.add(ce);
		Game.enemies.add(ce);
	}
    
    private void createNewHuggerEnemy(int x, int y) {
    	HuggerEnemy he = new HuggerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 1, 1, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
		Game.entities.add(he);
		Game.enemies.add(he);
	}
    
    private void createNewTrackerEnemy(int x, int y) {
    	TrackerEnemy te = new TrackerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 1, 2, 3, 4, 1, 4 * World.TILE_SIZE, 0, handler);
		te.setMask(3, 4, 10, 8);
		Game.entities.add(te);
		Game.enemies.add(te);
    }
    
    private void createNewWalkerEnemy(int x, int y) {
    	WalkerEnemy e = new WalkerEnemy(x * World.TILE_SIZE, y * World.TILE_SIZE, 16, 16, Game.walkerEnemy, 1, 1, 3, 4, 2, 0 , 0, handler);
		Game.entities.add(e);
		Game.enemies.add(e);
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
