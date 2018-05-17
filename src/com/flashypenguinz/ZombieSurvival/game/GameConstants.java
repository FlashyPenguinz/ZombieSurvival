package com.flashypenguinz.ZombieSurvival.game;

import com.flashypenguinz.ZombieSurvival.data.TextureAtlas;

public class GameConstants {

	public static final int DISPLAY_WIDTH = 1024;
	public static final int DISPLAY_HEIGHT = 640;
	public static final String DISPLAY_TITLE = "Zombie Survival";
	public static final int DISPLAY_FPS = 128;

	public static final String TEXTURES_FOLDER = "res/textures/";
	public static final String SOUNDS_FOLDER = "res/sounds/";
	public static final TextureAtlas MAP_ATLAS = new TextureAtlas("tileAtlas");

	public static final int TILE_SIZE = 64;
	
	public static final String GAME_FONT = "Times New Roman";
	public static final int USERNAME_SIZE = 16;
	public static final float USERNAME_Y = 30;

}