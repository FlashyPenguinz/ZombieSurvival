package com.mudkiper202.ZombieSurvival.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.entities.Entity;
import com.mudkiper202.ZombieSurvival.entities.Player;
import com.mudkiper202.ZombieSurvival.map.Map;
import com.mudkiper202.ZombieSurvival.ui.MainMenu;

public class GameManager {

	private GameState state = GameState.MAIN_MENU;

	private MainMenu mainMenu;

	private Map map;

	private Player player;
	private List<Entity> entities;

	public GameManager(Map map, Player player) {
		this.mainMenu = new MainMenu(this);
		this.map = map;
		this.player = player;
		this.entities = new ArrayList<Entity>();
	}

	public void update() {
		if (state == GameState.MAIN_MENU) {
			GL11.glClearColor(0, 1, 1, 1);
			mainMenu.update();
		} else if (state == GameState.PLAYING) {
			player.update();

			map.x = -player.getX() + Display.getWidth() / 2;
			map.y = -player.getY() + Display.getHeight() / 2;
		}
	}

	public void draw() {
		if (state == GameState.MAIN_MENU) {
			mainMenu.render();
		} else if (state == GameState.PLAYING) {
			map.draw();
			player.draw();
		}
	}
	
	public void setState(GameState state) {
		this.state = state;
	}

}
