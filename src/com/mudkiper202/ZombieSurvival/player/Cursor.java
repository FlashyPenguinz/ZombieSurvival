package com.mudkiper202.ZombieSurvival.player;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.ui.Image;

public class Cursor {

	private Texture texture;
	private float mouseX, mouseY;
	
	public Cursor(String name) {
		Image image = new Image(0, 0, 16, 16, name);
		this.texture = image.getTexture();
	}
	
	public void update() {
		this.mouseX = Mouse.getX();
		this.mouseY = Display.getHeight() - Mouse.getY();
	}
	
	public void draw() {
		Artist.drawTexturedQuad(mouseX-16, mouseY-16, 32, 32, texture, 0, 0, 1, 1);
	}
	
}
