package com.mudkiper202.ZombieSurvival.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.data.TextureAtlas;
import com.mudkiper202.ZombieSurvival.entities.Entity;
import com.mudkiper202.ZombieSurvival.map.Map;
import com.mudkiper202.ZombieSurvival.net.Client;
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

	private Client client;
	private List<Entity> entities;

	public GameManager(Map map) {
		this.mainMenu = new MainMenu(this);
		this.pauseMenu = new PauseMenu();
		this.map = map;
		this.player = new Player(map, 50, 50, new TextureAtlas("player"));
		this.cursor = new Cursor("cursor");
		this.entities = new ArrayList<Entity>();
		this.client = new Client(this, "25.13.40.96", 8192);
	}

	public void update() {
		if (state == GameState.MAIN_MENU) {
			GL11.glClearColor(0, 1, 1, 1);
			mainMenu.update();
		} else if (state == GameState.PLAYING) {
			if (!paused) {
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
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
			if (player != null) {
				map.draw();
				for (Entity entity : entities) {
					if (!(entity instanceof Player)) {
						entity.checkForTextureAtlas();
						entity.draw(
								(-(player.getX() - entity.getX()))
										+ (Display.getWidth() / 2),
								(-(player.getY() - entity.getY()))
										+ (Display.getHeight() / 2));
					}
				}
				player.draw();
				cursor.draw();
				if (paused)
					pauseMenu.draw();
			}
		}

	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Client getClient() {
		return client;
	}

	public Player getPlayer() {
		return player;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void addEntity(int id, float x, float y, float rotation,
			String textureName, int texX, int texY) {
		entities.add(new Entity(id, x, y, textureName, texX,
				texY, rotation));
	}
	
	public void removeEntity(int id) {
		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i).getId() == id) {
				entities.remove(i);
				return;
			}
		}
	}

}
