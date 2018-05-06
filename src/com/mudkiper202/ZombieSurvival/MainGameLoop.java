package com.mudkiper202.ZombieSurvival;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.entities.Player;
import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.map.Map;
import com.mudkiper202.ZombieSurvival.textures.TextureAtlas;

public class MainGameLoop {

	public MainGameLoop() {
		Artist.createDisplay();

		Map map = new Map(16, 10);
		Player player = new Player(0, 0, new TextureAtlas("player"));

		GameManager gm = new GameManager(map, player);

		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			gm.update();
			gm.draw();

			Artist.updateDisplay();
		}

		Artist.destroyDisplay();
	}

	public static void main(String[] args) {
		new MainGameLoop();
	}

}
