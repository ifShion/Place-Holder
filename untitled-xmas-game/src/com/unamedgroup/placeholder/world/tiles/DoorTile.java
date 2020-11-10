package com.unamedgroup.placeholder.world.tiles;

import java.awt.image.BufferedImage;
import java.util.TreeSet;

import com.unamedgroup.placeholder.entities.Player;
import com.unamedgroup.placeholder.main.Game;

public class DoorTile extends FreeTile {
    public int destiny;
    public int tpx;
    public int tpy;
    public Player player;

    //Mudar DoorTile para entidade, não fiz isso agora pq não sei como Animation funciona
    public DoorTile(int x, int y, BufferedImage sprite, Player player, int destiny , int tpx , int tpy) {
        super(x, y, sprite);

    }

    public void movePlayer() {
        if(super.calculateDistance(super.x, Game.player.getX(), super.y, Game.player.getY()) < 8) {
            Game.alternatingMaps = true;
            Game.entities = new TreeSet<>();
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
