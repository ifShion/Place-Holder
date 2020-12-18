package com.unamedgroup.placeholder.graphics.screen_components;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * @author Daniel Nogueira
 * Uma classe para compactar Componentes e colocar alinhados
 */
public class ComponentBox extends Component {

    private ArrayList<Component> components;
    private int selectionIndex=0;

    public ComponentBox(){
        init();
    }

    public void add(Component c){
        components.add(c);
    }

    public void changeComponent(int i){
        if (selectionIndex+i<0) return;
        else if (selectionIndex+i>components.size()) return;
        else selectionIndex += i;
    }

    public void btnUp(){
        changeComponent(-1);
    }

    public void btnDown(){
        changeComponent(1);
    }

    @Override
    public void init() {
        components = new ArrayList<>();
    }

    @Override
    public void tick() {
        for (int i=0; i<components.size(); i++) {
            components.get(i).tick();
        }
    }

    @Override
    public void render(Graphics g) {
        for (int i=0; i<components.size(); i++) {
            components.get(i).render(g);
            if (components.size() > i+1){
                components.get(i+1).setyComponent(components.get(i).getyComponent()+35);
            }
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public int getSelectionIndex() {
        return selectionIndex;
    }

    public void setSelectionIndex(int selectionIndex) {
        this.selectionIndex = selectionIndex;
    }

    @Override
    public void setxComponent(float x){
        this.xComponent = x;
        if (!components.isEmpty())
            components.get(0).setxComponent(x);
    }

    @Override
    public void setyComponent(float y){
        this.yComponent = y;
        if (!components.isEmpty())
            components.get(0).setyComponent(y);
    }    

}
