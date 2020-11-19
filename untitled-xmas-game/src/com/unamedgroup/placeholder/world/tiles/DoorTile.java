package com.unamedgroup.placeholder.world.tiles;

import java.awt.image.BufferedImage;
import java.util.TreeSet;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.world.World;

public class DoorTile extends FreeTile {
    public int destiny;
    public int tpx;
    public int tpy;
    public Player player;

    //Mudar DoorTile para entidade, não fiz isso agora pq não sei como Animation funciona
    public DoorTile(int x, int y, BufferedImage sprite, Player player, int destiny , int tpx , int tpy) {
        super(x, y, sprite);

        x = x/World.TILE_SIZE;
        y = y/World.TILE_SIZE;
        //Cria as portas do mapa, de fato. Isso faz com q aqueles pixels amarelos na imagem sejam apenas para referência
        Game.room.tiles[x + (y * Game.room.WIDTH)] = new FreeTile(x * World.TILE_SIZE , y * World.TILE_SIZE , Tile.DOOR_TILE);
        
        this.destiny = destiny;
        this.tpx = tpx;
        this.tpy = tpy;
        this.player = player;
        
    }

    public void movePlayer() {
        if(super.calculateDistance(super.x, Game.player.getX(), super.y, Game.player.getY()) < 16) {
        	System.out.println("DoorTile.movePlayer()");
            Game.alternatingMaps = true;
            Game.entities = new TreeSet<>(Game.nodeSorter);
            Game.entities.add(player);
            Game.currentMapID = destiny;
            Game.player.setX(tpx);
            Game.player.setY(tpy);
           return;
        }
	}

    @Override
    public void tick() {
        super.tick();
        movePlayer();
    }
    
}