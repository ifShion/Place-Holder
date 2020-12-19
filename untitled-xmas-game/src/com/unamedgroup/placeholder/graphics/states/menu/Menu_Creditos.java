package com.unamedgroup.placeholder.graphics.states.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.unamedgroup.placeholder.graphics.screen_components.LabelList;
import com.unamedgroup.placeholder.graphics.states.Menu;
import com.unamedgroup.placeholder.main.Game;
import com.unamedgroup.placeholder.main.Handler;


public class Menu_Creditos extends Menu {
    public static int ID;
    private LabelList grupo;

    public Menu_Creditos(int id, Handler handler) {
        super(id, handler);
        ID = id;
    }

    

    @Override
    public void init() {
        super.init();
        grupo = new LabelList(Game.WIDTH/2, Game.HEIGHT*3, null, Color.WHITE, LabelList.CENTER);
        
        
        grupo.add("UESB");
        grupo.add("Ciência da Computação");
        grupo.add("Algoritmo e programação 2");
        grupo.add("2019.2");
        grupo.add("");
        grupo.add("");
        grupo.add("");
        grupo.add("");
        grupo.add("==========================");
        grupo.add(" O Segredo da Fábrica ");
        grupo.add("==========================");
        grupo.add("");
        grupo.add("");
        grupo.add("=== Direção ===");
        grupo.add("Nathan");
        grupo.add("");
        grupo.add("=== Arte ====");
        grupo.add("Daniel Neves");
        grupo.add("");
        grupo.add("=== Roteiro ===");
        grupo.add("Daniel Nogueira");
        grupo.add("");
        grupo.add("=== Design de fases ==");
        grupo.add("Euler");
        grupo.add("");
        grupo.add("=== Trilha Sonora ===");
        grupo.add("Vitor Rosenbergre");
        grupo.add("");
        grupo.add("=== Gerente de Algoritmo ===");
        grupo.add("Ryan Sérgio");
        grupo.add("");
        grupo.add("");
        grupo.add("");
        grupo.add("=== Orientadora ===");
        grupo.add("Maísa S. Dos Santos Lopes");
        grupo.add("");
        grupo.add("");
        grupo.add("");
        grupo.add("");
        grupo.add("");
        grupo.add("Obrigado por jogar");
        grupo.add("Feliz Natal e boas festas!");

    }

    @Override
    public void tick() {
        super.tick();
        if(grupo.getBox().get(grupo.getBox().size()-2).getY()!=Game.HEIGHT/2)
            grupo.ScrollOn();
        if(handler.getInputHandler().prime.clicked){
            super.status = "getout";
            super.screenDestiny = Menu_Principal.ID;
        }

    }

    @Override
    public void render(Graphics g) {
        grupo.render((Graphics2D) g);
        super.render(g);
    }
    
}
