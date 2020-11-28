package com.unamedgroup.placeholder.world.tiles;

import java.awt.image.BufferedImage;
import com.unamedgroup.placeholder.main.Handler;
import com.unamedgroup.placeholder.world.World;

public class DoorTile extends FreeTile {
    public int destiny;
    public int tpx;
    public int tpy;

    //Mudar DoorTile para entidade, não fiz isso agora pq não sei como Animation funciona
    public DoorTile(int x, int y, BufferedImage sprite, int destiny , int tpx , int tpy, Handler handler) {
        super(x, y, sprite, handler);

        x = x/World.TILE_SIZE;
        y = y/World.TILE_SIZE;
        //Cria as portas do mapa, de fato. Isso faz com q aqueles pixels amarelos na imagem sejam apenas para referência
        handler.getGame().room.tiles[x + (y * handler.getGame().room.WIDTH)] = new FreeTile(x * World.TILE_SIZE , y * World.TILE_SIZE, sprite , handler);
        
        this.destiny = destiny;
        this.tpx = tpx;
        this.tpy = tpy;
        
    }

    public void movePlayer() {
        if(super.calculateDistance(super.x, handler.getGame().getPlayer().getX() + handler.getGame().getPlayer().getMaskX(), super.y, handler.getGame().getPlayer().getY() + handler.getGame().getPlayer().getMaskY()) < 16) {
        	System.out.println("DoorTile.movePlayer()");
            //Game.entities = new TreeSet<>(Game.nodeSorter);
            //Game.entities.add(player);
            handler.getGame().updateEntities(); // o metodo updateEntities substitui as linhas de codigo acima
            handler.getGame().changeCurrentMapID(destiny);
            handler.getGame().getPlayer().setX(tpx);
            handler.getGame().getPlayer().setY(tpy);
           return;
        }
	}

    @Override
    public void tick() {
        super.tick();
        movePlayer();
    }
    
}