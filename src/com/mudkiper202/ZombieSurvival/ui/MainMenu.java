package com.mudkiper202.ZombieSurvival.ui;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.game.GameState;

public class MainMenu {

	private Image logo;
	private TextButton playGame;

	public MainMenu(GameManager gm) {
		this.logo = new Image((Display.getWidth() / 2) - 256, 30, 512, 512,
				"title");
		this.playGame = new TextButton(new Text(Display.getWidth() / 2,
				Display.getHeight() / 2, "Play Game", Font.BOLD, 38), () -> {
			gm.setState(GameState.PLAYING);
			Mouse.setGrabbed(true);
		});
	}

	public void update() {
		playGame.update();
	}

	public void render() {
		logo.draw();
		playGame.draw();
	}

}
