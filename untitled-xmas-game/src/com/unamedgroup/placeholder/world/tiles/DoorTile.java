package com.unamedgroup.placeholder.world.tiles;

import java.awt.image.BufferedImage;
import java.util.TreeSet;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.World;

public class DoorTile extends FreeTile {
    public int destiny;
    public int tpx;
    public int tpy;
    public Player player;

    //Mudar DoorTile para entidade, não fiz isso agora pq não sei como Animation funciona
    public DoorTile(int x, int y, BufferedImage sprite, Player player, int destiny , int tpx , int tpy, Handler handler) {
        super(x, y, sprite, handler);

        x = x/World.TILE_SIZE;
        y = y/World.TILE_SIZE;
        //Cria as portas do mapa, de fato. Isso faz com q aqueles pixels amarelos na imagem sejam apenas para referência
        handler.getGame().room.tiles[x + (y * Game.WIDTH)] = new FreeTile(x * World.TILE_SIZE , y * World.TILE_SIZE , Tile.DOOR_TILE, handler);
        
        this.destiny = destiny;
        this.tpx = tpx;
        this.tpy = tpy;
        this.player = player;
        
    }

    public void movePlayer() {
        if(super.calculateDistance(super.x, Game.player.getX(), super.y, Game.player.getY()) < 16) {
        	System.out.println("DoorTile.movePlayer()");
            handler.getGame().alternatingMaps = true;
            Game.entities = new TreeSet<>(Game.nodeSorter);
            Game.entities.add(player);
            handler.getGame().currentMapID = destiny;
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