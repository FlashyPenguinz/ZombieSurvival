package com.mudkiper202.ZombieSurvival;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.data.TextureAtlas;
import com.mudkiper202.ZombieSurvival.entities.Player;
import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.map.Map;

public class MainGameLoop {

	public MainGameLoop() {
		Artist.createDisplay();
		try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Map map = new Map(Map.loadMapFile("map"));
		Player player = new Player(map, 16*GameConstants.TILE_SIZE,16*GameConstants.TILE_SIZE, new TextureAtlas("player"));

		GameManager gm = new GameManager(map, player);

		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			System.out.println(Mouse.getX()+", "+Mouse.getY());
			
			gm.update();
			gm.draw();

			Artist.updateDisplay();
		}

		Mouse.destroy();
		
		Artist.destroyDisplay();
	}

	public static void main(String[] args) {
		new MainGameLoop();
	}

}
