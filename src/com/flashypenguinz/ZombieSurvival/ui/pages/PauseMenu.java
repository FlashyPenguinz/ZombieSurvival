package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;
import com.flashypenguinz.ZombieSurvival.ui.components.TextButton;

public class PauseMenu {
	
	private Text paused;
	private TextButton resume;
	
	public PauseMenu() {
		this.paused = new Text(Display.getWidth() / 2, 100, "Paused", Font.BOLD, 55);
		this.resume = new TextButton(new Text(Display.getWidth()/2, Display.getHeight()/2, "Resume Game", Font.BOLD, 24), ()-> {
			GameManager.paused = false;
			Mouse.setGrabbed(true);
		});
	}
	
	public void update() {
		resume.update();
	}
	
	public void draw() {
		Artist.drawColoredQuad(0, 0, Display.getWidth(), Display.getHeight(), 0, 0.4f, 0.4f, 0.4f, 0.45f);
		paused.draw();
		resume.draw();
	}
	
}
