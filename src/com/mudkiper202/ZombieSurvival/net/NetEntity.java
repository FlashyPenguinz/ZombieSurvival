package com.mudkiper202.ZombieSurvival.net;

import org.lwjgl.util.vector.Vector2f;

import com.mudkiper202.ZombieSurvival.game.GameConstants;

public class NetEntity {

	private int id;
	
	private float x, y;
	private float width, height;

	private String texture;
	private int texX, texY;

	private float rotation;

	public NetEntity(int id, float x, float y, String texture, int texX, int texY, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = GameConstants.TILE_SIZE;
		this.height = GameConstants.TILE_SIZE;
		this.texture = texture;
		this.texX = texX;
		this.texY = texY;
		this.rotation = rotation;
	}

	public void increasePosition(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
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

	public String getTextureName() {
		return texture;
	}

	public Vector2f getTextureCoords() {
		return new Vector2f(texX, texY);
	}

	public float getRotation() {
		return rotation;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
