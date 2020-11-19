package com.unamedgroup.placeholder.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Central de controle das entradas do jogador
 * @author Daniel Neves
 *
 */

public class InputHandler implements KeyListener {
	public class Key {
		public int presses, absorbs;
		public boolean down;//Usado para teclas q ficarão pressionadas por muito tempo (como as teclas de navegação do personagem)
		public boolean clicked;//Usado para teclas q são precionadas por um único toque (como a tecla de menu)

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public List<Key> keys = new ArrayList<Key>();
	//Inicializa os comandos dos contrles
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

	public Key prime = new Key();
	public Key secondary = new Key();

	public Key escape = new Key();
	
	public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	private void toggle(KeyEvent ke, boolean pressed) {
		//Determina as teclas que executam os comandos deteminados acima.
		if (ke.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);

		if (ke.getKeyCode() == KeyEvent.VK_Z) prime.toggle(pressed);
		if (ke.getKeyCode() == KeyEvent.VK_X) secondary.toggle(pressed);

		if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
	}

	public void keyTyped(KeyEvent ke) {
		
	}
}
