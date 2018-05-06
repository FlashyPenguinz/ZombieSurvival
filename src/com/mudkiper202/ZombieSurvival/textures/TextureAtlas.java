package com.mudkiper202.ZombieSurvival.textures;

import java.io.FileInputStream;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.mudkiper202.ZombieSurvival.game.GameConstants;

public class TextureAtlas {

	private Texture texture;
	
	public TextureAtlas(String path) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(GameConstants.TEXTURES_FOLDER+path+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.texture = texture;
	}
	
	public float[] getTextureCoords(int x, int y) {
		final float TEXTURE_PER_ROW = texture.getImageWidth() / 64f;
		final float TEXTURE_SIZE = 1f / TEXTURE_PER_ROW;
		float[] coords = new float[4];
		coords[0] = (float) (x * TEXTURE_SIZE);
		coords[1] = (float) (y * TEXTURE_SIZE);
		coords[2] = (float) (coords[0] + TEXTURE_SIZE);
		coords[3] = (float) (coords[1] + TEXTURE_SIZE);
		return coords;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
}
