package com.unamedgroup.placeholder.world;

import java.util.LinkedHashMap;
import java.util.Map;

import com.unamedgroup.placeholder.main.Game;

public class Maps {
    private Map<Integer, Room> maps = new LinkedHashMap<>();
    private Room room;

    public Maps() {
        maps.put(1001, new Room("/worldTest.png"));
        maps.put(1002, new Room("/worldTest2.png"));
    }

    public void tick(){
        Game.alternatingMaps = false;
        Game.room = maps.get(Game.currentMapID);
        Game.room.tick();
    }

    public Map<Integer, Room> getMaps() {
        return maps;
    }
}