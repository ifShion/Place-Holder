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
    private Set<DoorTile> doors = new LinkedHashSet<>();

    public Room(String path) {
        super(path);
    }
    
    public void placeDoor(int x, int y, Player player, int destiny, int tpx, int tpy){
        doors.add(new DoorTile(x * World.TILE_SIZE, y * World.TILE_SIZE, Tile.DOOR_TILE, player, destiny, tpx * World.TILE_SIZE, tpy * World.TILE_SIZE));
    }

    private void createDoors(){
        switch(Game.currentMapID){
            case 1001:
                placeDoor(3, 11, State_00.alpha, 1001, 5, 9);
                placeDoor(5, 3, State_00.alpha, 1002, 4, 15);
                placeDoor(9, 3, State_00.alpha, 1001, 9, 10);
            case 1002:
                placeDoor(6, 15, State_00.alpha, 1001, 7, 10);
                placeDoor(12, 11, State_00.alpha, 1002, 8, 6);
                placeDoor(12, 12, State_00.alpha, 1002, 8, 6);
                placeDoor(13, 16, State_00.alpha, 1001, 8, 6);
            default:

        }
    }

    public void tick(){
        createDoors();
    }
    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
