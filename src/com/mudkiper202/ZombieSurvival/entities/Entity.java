package com.mudkiper202.ZombieSurvival.entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.mudkiper202.ZombieSurvival.data.TextureAtlas;
import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.net.Client;
import com.mudkiper202.ZombieSurvival.net.packets.Packet03EntityMove;

public class Entity {

	private int id;
	
	private float x, y;
	private float width, height;

	private TextureAtlas texture;
	private int texX, texY;

	private float rotation;
	
	private boolean edited = false;
	
	public Entity(int id, float x, float y, TextureAtlas texture, int texX, int texY, float rotation) {
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
	
	public void draw() {
		float[] texCoords = texture.getTextureCoords(texX, texY);
		GL11.glPushMatrix();
		Artist.drawTexturedQuad(x, y, width, height, rotation,
				texture.getTexture(), texCoords[0], texCoords[1], texCoords[2],
				texCoords[3]);
		GL11.glPopMatrix();
	}
	
	public void draw(float x, float y) {
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
		edited = true;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		edited = true;
	}

	public void setRotation(float rot) {
		this.rotation = rot;
	}
	
	public void updateServer() {
		if(edited) {
			Packet03EntityMove movePacket = new Packet03EntityMove(id, x, y, rotation);
			Client.getInstance().sendData(movePacket.getData());
		}
		edited = false;
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
