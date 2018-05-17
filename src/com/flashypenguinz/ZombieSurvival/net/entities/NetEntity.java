package com.flashypenguinz.ZombieSurvival.net.entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.flashypenguinz.ZombieSurvival.data.TextureAtlas;
import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;

public class NetEntity {

	private int id;
	
	private float x, y;
	private float width, height;

	private String textureName;
	private TextureAtlas texture;
	private int texX, texY;

	private float rotation;

	public NetEntity(int id, float x, float y, String textureName, int texX, int texY, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = GameConstants.TILE_SIZE;
		this.height = GameConstants.TILE_SIZE;
		this.textureName = textureName;
		this.texX = texX;
		this.texY = texY;
		this.rotation = rotation;
	}

	public void draw(float x, float y) {
		if(this.texture == null) {
			this.texture = new TextureAtlas(textureName);
		}
		float[] texCoords = texture.getTextureCoords(texX, texY);
		GL11.glPushMatrix();
		Artist.drawTexturedQuad(x, y, width, height, rotation,
				texture.getTexture(), texCoords[0], texCoords[1], texCoords[2],
				texCoords[3]);
		GL11.glPopMatrix();
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
		return textureName;
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
