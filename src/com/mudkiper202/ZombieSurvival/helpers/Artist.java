package com.mudkiper202.ZombieSurvival.helpers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.mudkiper202.ZombieSurvival.GameConstants;

public class Artist {

	public static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(GameConstants.DISPLAY_WIDTH, GameConstants.DISPLAY_HEIGHT));
			Display.setTitle(GameConstants.DISPLAY_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glEnable(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GameConstants.DISPLAY_WIDTH, GameConstants.DISPLAY_HEIGHT, 0, -1, 1);
		glEnable(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void updateDisplay() {
		Display.sync(GameConstants.DISPLAY_FPS);
		Display.update();
	}
	
	public static void destroyDisplay() {
		Display.destroy();
	}
	
}
