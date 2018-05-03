package com.mudkiper202.ZombieSurvival;

import org.lwjgl.opengl.Display;

import com.mudkiper202.ZombieSurvival.helpers.Artist;

public class MainGameLoop {

	public MainGameLoop() {
		Artist.createDisplay();
		
		while(!Display.isCloseRequested()) {
			
			Artist.updateDisplay();
		}
		
		Artist.destroyDisplay();
	}
	
	public static void main(String[] args) {
		new MainGameLoop();
	}
	
}
