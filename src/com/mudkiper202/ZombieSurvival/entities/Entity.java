package com.mudkiper202.ZombieSurvival.entities;

import org.lwjgl.util.vector.Vector2f;

import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.textures.TextureAtlas;

public class Entity {

	private float x, y;
	private float width, height;

	private TextureAtlas texture;
	private int texX, texY;

	private float rotation;

	public Entity(float x, float y, TextureAtlas texture, int texX, int texY, float rotation) {
		this.x = x;
		this.y = y;
		this.width = GameConstants.TILE_SIZE;
		this.height = GameConstants.TILE_SIZE;
		this.texture = texture;
		this.texX = texX;
		this.texY = texY;
		this.rotation = rotation;
	}

	public void draw() {
		float[] texCoords = texture.getTextureCoords(texX, texY);
		Artist.drawTexturedQuad(x, y, width, height, rotation,
				texture.getTexture(), texCoords[0], texCoords[1], texCoords[2],
				texCoords[3]);
	}

	public void increasePosition(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}

	public void setRotation(float rot) {
		this.rotation = rot;
	}

	public void setTextureCoords(int texX, int texY) {
		this.texX = texX;
		this.texY = texY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public TextureAtlas getTextureAtlas() {
		return texture;
	}

	public Vector2f getTextureCoords() {
		return new Vector2f(texX, texY);
	}

	public float getRotation() {
		return rotation;
	}

}
