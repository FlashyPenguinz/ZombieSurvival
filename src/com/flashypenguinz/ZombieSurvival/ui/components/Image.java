package com.flashypenguinz.ZombieSurvival.ui.components;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;

public class Image implements Component {

	private float x, y;
	private float width, height;
	
	private Texture texture;

	public Image(float x, float y, float width, float height, String path) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(
					GameConstants.TEXTURES_FOLDER + path + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		this.texture = texture;
	}

	@Override
	public void update() {}
	
	@Override
	public void draw() {
		Artist.drawTexturedQuad(x, y, width, height, texture);
	}
	
	public Texture getTexture() {
		return texture;
	}

}
