package com.mudkiper202.ZombieSurvival.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.entities.Entity;
import com.mudkiper202.ZombieSurvival.map.Map;
import com.mudkiper202.ZombieSurvival.player.Cursor;
import com.mudkiper202.ZombieSurvival.player.Player;
import com.mudkiper202.ZombieSurvival.ui.MainMenu;
import com.mudkiper202.ZombieSurvival.ui.PauseMenu;

public class GameManager {

	private GameState state = GameState.MAIN_MENU;

	private MainMenu mainMenu;
	private PauseMenu pauseMenu;
	
	public static boolean paused = false;
	
	private Map map;
	private Player player;
	private Cursor cursor;
	private List<Entity> entities;
	
	public GameManager(Map map, Player player) {
		this.mainMenu = new MainMenu(this);
		this.pauseMenu = new PauseMenu(this);
		this.map = map;
		this.player = player;
		this.cursor = new Cursor("cursor");
		this.entities = new ArrayList<Entity>();
	}

	public void update() {
		if (state == GameState.MAIN_MENU) {
			GL11.glClearColor(0, 1, 1, 1);
			mainMenu.update();
		} else if (state == GameState.PLAYING) {
			if(!paused) {
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					paused = true;
					Mouse.setGrabbed(false);
				}
				
				player.update();
				cursor.update();
				
				map.x = -player.getX() + Display.getWidth() / 2;
				map.y = -player.getY() + Display.getHeight() / 2;
			} else {
				pauseMenu.update();
			}
		}
	}

	public void draw() {
		if (state == GameState.MAIN_MENU) {
			mainMenu.render();
		} else if (state == GameState.PLAYING) {
			map.draw();
			player.draw();
			cursor.draw();
			if(paused)
				pauseMenu.draw();
		}
		
	}
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
