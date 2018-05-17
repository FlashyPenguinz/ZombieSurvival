package com.flashypenguinz.ZombieSurvival.data;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;

public class TextureAtlas {

	private Texture texture;
	private String textureName;
	
	public TextureAtlas(String path) {
		this.textureName = path;
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(GameConstants.TEXTURES_FOLDER+path+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
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
	
	public String getTextureName() {
		return textureName;
	}
	
}
