package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.util.LinkedHashSet;
import java.util.Set;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.SpriteSheet;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.tiles.DoorTile;

public class Room extends World {
    private Set<DoorTile> doors;
    private int ID;
    private Handler handler;
    private SpriteSheet map;
    /**
     * Constrói o mundo novo. É bom ter uma sprite sheet pr´pria para os tiles de cada mapa
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
        doors.add(new DoorTile(x * World.TILE_SIZE, y * World.TILE_SIZE, handler.getGame().room.getMap().getSprite(16, 16, 16, 16), destiny, tpx * World.TILE_SIZE, tpy * World.TILE_SIZE, handler));
    }

    /**                     
     * Cria os Tiles de portas
     */
    public void createDoors(){
        doors = new LinkedHashSet<>();
        switch(handler.getGame().getCurrentMapID()){
            case 1001:
                placeDoor(3, 11, 1001, 5, 9);
                placeDoor(5, 3, 1002, 4, 15);
                placeDoor(9, 3, 1001, 9, 10);
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
