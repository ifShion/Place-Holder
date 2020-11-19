package com.unamedgroup.placeholder.world;

import java.awt.Graphics;
import java.util.LinkedHashSet;
import java.util.Set;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.graphics.states.State_00;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.world.tiles.DoorTile;
import com.unamedgroup.placeholder.world.tiles.Tile;

public class Room extends World {
    private Set<DoorTile> doors;
    private int ID;
    /**
     * Constrói o mundo novo
     * @param path
     * 
     * @author Daniel Neves
     */
    
    public Room(String path, int ID) {
        super(path);
        
        this.ID = ID;
    }
    
    public int getID() {
		return ID;
	}
    
    /**
     * Esse método serve para teleportar o player quando ele colidir com um DoorTile. Podemos modificar ele para
     * teleportar qualquer entidade tbm
     * @param x
     * @param y
     * @param player
     * @param destiny
     * @param tpx
     * @param tpy
     */
    public void placeDoor(int x, int y, Player player, int destiny, int tpx, int tpy){
    	System.out.println("Room.placeDoor()");
        doors.add(new DoorTile(x * World.TILE_SIZE, y * World.TILE_SIZE, Tile.DOOR_TILE, player, destiny, tpx * World.TILE_SIZE, tpy * World.TILE_SIZE));
    }

    /**
     * Cria os Tiles de portas
     */
    public void createDoors(){
        doors = new LinkedHashSet<>();
        switch(Game.currentMapID){
            case 1001:
                placeDoor(3, 11, State_00.alpha, 1001, 5, 9);
                placeDoor(5, 3, State_00.alpha, 1002, 4, 15);
                placeDoor(9, 3, State_00.alpha, 1001, 9, 10);
                break;
            case 1002:
                placeDoor(6, 15, State_00.alpha, 1001, 7, 10);
                placeDoor(12, 11, State_00.alpha, 1002, 8, 6);
                placeDoor(12, 12, State_00.alpha, 1002, 8, 6);
                placeDoor(13, 16, State_00.alpha, 1001, 8, 6);
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
