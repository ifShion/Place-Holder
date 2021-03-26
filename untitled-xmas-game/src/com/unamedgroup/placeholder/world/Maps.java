package com.unamedgroup.placeholder.world;

import java.util.LinkedHashMap;
import java.util.Map;

import com.unamedgroup.placeholder.main.Handler;

/**
 * Essa parte do código é muito interessante. Oq ele faz aqui é cadastrar e armazenar todos os mapas do jogo
 * em uma estrura de dados chamada Mapa (Q conveniente!). Assim a única coisa q precisamos fazer é chamar esse 
 * mapa dentro dessa classe e pedir um objeto com uso de uma chave. Mas para que chave é essa? Como ela funciona?
 * Bem, a chave é um valor q tá linkado diretamente com a Room q é associada a ele na linha maps.put(Integer, Room);
 *   - Como nomear um chave para uma sala?: O primeiro número simboliza a fase q esse mapa será inserido e os outros
 * números indicam o número dessa sala nessa determinada fase.
 * Por exemplo, a sala tem número 3012. Isso quer dizer q esse mapa pertence a terceira fase do jogo e q é a sala
 * de número 12 nessa fase. Isso tbm permite dizer q há, ao menos 12 salas já cadastradas nessa fase.
 * É importante não repetir o número das chaves, se isso acontecer, o mapa anterior será sobrescrito e perdido. Essa
 * funcionalidade é muito boa para salvar mudanças no mapa.
 * @author Daniel Neves
 */
public class Maps {
    private Map<Integer, Room> maps;
    private Handler handler;

    public Maps(Handler handler) {
        this.handler = handler;
        maps = new LinkedHashMap<>();
        maps.put(0001, new Room("/map/worldCorredor.png", 0001,"/spriteSheetMapa1.png", 0, 0, handler)); // Cutscene do corredor
        maps.put(0002, new Room("/map/world_AB_Final.png", 0002, "/spriteSheetMapa1.png", 2, 7, handler));
        maps.put(1000, new Room("/map/world_AB_01.png", 1000, "/spriteSheetMapa1.png", 60, 27, handler));
        maps.put(1001, new Room("/map/world_AB_02.png", 1001, "/spriteSheetMapa1.png", 61, 25, handler));
        maps.put(1002, new Room("/map/world_AB_03.png", 1002, "/spriteSheetMapa1.png", 4, 75, handler));
        maps.put(2000, new Room("/map/world_AB_01.png", 2000, "/spriteSheetMapa1.png", 60, 27, handler));
        maps.put(2002, new Room("/map/world_AB_03.png", 2002, "/spriteSheetMapa1.png", 4, 75, handler));
        maps.put(1003, new Room("/map/world_AB_04.png", 1003, "/spriteSheetMapa1.png", 3, 67, handler));
    }

    public void tick(){
        handler.getGame().alternatingMaps = false;
        handler.getGame().setRoom(maps.get(handler.getGame().getCurrentMapID()));
        handler.getGame().currentMap = handler.getGame().maps.getMaps().get(handler.getGame().getCurrentMapID()).getMap();
        handler.getGame().getRoom().createEntities();
    }

    public Map<Integer, Room> getMaps() {
        return maps;
    }
}