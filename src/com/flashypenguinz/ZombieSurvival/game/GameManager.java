package com.flashypenguinz.ZombieSurvival.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.flashypenguinz.ZombieSurvival.data.TextureAtlas;
import com.flashypenguinz.ZombieSurvival.entities.EntityManager;
import com.flashypenguinz.ZombieSurvival.map.Map;
import com.flashypenguinz.ZombieSurvival.map.Tile;
import com.flashypenguinz.ZombieSurvival.net.Client;
import com.flashypenguinz.ZombieSurvival.net.entities.NetEntity;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet01Disconnect;
import com.flashypenguinz.ZombieSurvival.player.Bullet;
import com.flashypenguinz.ZombieSurvival.player.Cursor;
import com.flashypenguinz.ZombieSurvival.player.Player;
import com.flashypenguinz.ZombieSurvival.ui.UIManager;
import com.flashypenguinz.ZombieSurvival.ui.UIState;
import com.flashypenguinz.ZombieSurvival.ui.pages.PauseMenu;
import com.flashypenguinz.ZombieSurvival.user.User;
import com.flashypenguinz.ZombieSurvival.user.database.DatabaseManager;

public class GameManager {

	private DatabaseManager dm;
	private EntityManager em;
	private UIManager um;

	private Client client;
	
	private GameState state = GameState.MAIN_MENU;
	
	private PauseMenu pauseMenu;

	public static boolean paused = false;

	public User user;
	
	private Map map;
	private Player player;
	private Cursor cursor;
	
	public GameManager() {
		this.pauseMenu = new PauseMenu();
		this.player = new Player("", this, (float) 15*GameConstants.TILE_SIZE, (float) 15*GameConstants.TILE_SIZE, new TextureAtlas("player"));
		this.cursor = new Cursor("cursor");
		this.dm = new DatabaseManager();
		this.em = new EntityManager(this);
		checkForCache();
		this.um = new UIManager(this);
		this.client = new Client(this, "25.13.40.96", 8192);
		
		/*this.map = new Map(Map.loadMapFile("res/data/pathfind.txt"));
		startPos = map.getTile(1, 0, 0);
		startPos.setType(TileType.BARRICADE);
		endPos = map.getTile(1, 6, 0);
		endPos.setType(TileType.BARRICADE);
		
		List<Node> nodes = PathFinder.pathFind(map, startPos.getX(), startPos.getY(), endPos.getX(), endPos.getY());
		for (int i = 0; i < nodes.size()-1; i++) {
			map.getTile(1, nodes.get(i).getX(), nodes.get(i).getY()).setType(TileType.WOODEN_FLOOR);
		}*/
	}

	public void update() {
		GL11.glClearColor(0, 1, 1, 1);
		if (state == GameState.PLAYING) {
			em.updateEntityLists();
			if (!paused) {
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					paused = true;
					Mouse.setGrabbed(false);
				}
				
				player.update();
				player.updateServer();
				cursor.update();

				map.x = -player.getX() + Display.getWidth() / 2;
				map.y = -player.getY() + Display.getHeight() / 2;
			} else {
				pauseMenu.update();
			}
			player.updateBullets();
		} else {
			um.update();
		}
	}
	
	public void draw() {
		if (state == GameState.PLAYING) {
			if (player != null) {
				map.draw(1);
				for (NetEntity entity : em.getEntities()) {
					if(!isIdLocal(entity.getId())) {
						entity.draw(
								(-(player.getX() - entity.getX()))
										+ (Display.getWidth() / 2),
								(-(player.getY() - entity.getY()))
										+ (Display.getHeight() / 2));
					}
				}
				player.draw();
				map.draw(2);
				cursor.draw();
				if (paused)
					pauseMenu.draw();
			}
		} else {
			um.draw();
		}
	}

	private boolean isIdLocal(int id) {
		if(player.getId() == id)
			return true;
		for(Bullet bullet: player.getBullets())
			if(bullet.getId() == id)
				return true;
		return false;
	}
	
	public void cleanUp() {		
		dm.closeConnection();
		if(client.connected)
			client.sendData(new Packet01Disconnect().getData());
		saveToCache();
	}
	
	private void checkForCache() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("userData.txt"));
			String line = "";
			String email = "";
			String password = "";
			while((line=reader.readLine()) != null) {
				String[] data = line.split(":");
				if(data[0].equals("email"))
					email = data[1];
				if(data[0].equals("password"))
					password = data[1];
			}
			reader.close();
			if(email.isEmpty()||password.isEmpty())
				return;
			String username = dm.getUserDatabase().getUsername(email);
			user = new User(dm.getUserDatabase().getUserId(username), username, email, password, dm.getUserDatabase().getUserLevel(username));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void saveToCache() {
		if(user != null) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("userData.txt"));
				writer.write("id:"+user.getUserId());
				writer.write("\nusername:"+user.getUsername());
				writer.write("\nemail:"+user.getEmail());
				writer.write("\npassword:"+user.getPassword());
				writer.write("\nlevel:"+user.getUserLevel());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
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

	public EntityManager getEntityManager() {
		return em;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}

	public GameState getState() {
		return state;
	}
	
	public void makeUser(User user) {
		this.user = user;
	}
	
	public void setUIState(UIState state) {
		um.setState(state);
	}
	
	public DatabaseManager getDatabaseManager() {
		return dm;
	}
	
}
