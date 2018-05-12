package com.mudkiper202.ZombieSurvival.entities;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.mudkiper202.ZombieSurvival.data.TextureAtlas;
import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.net.Client;
import com.mudkiper202.ZombieSurvival.net.packets.Packet03Move;
import com.mudkiper202.ZombieSurvival.net.packets.Packet04Rotation;

public class Entity {

	private int id;
	
	private float x, y;
	private float width, height;

	private TextureAtlas texture;
	private int texX, texY;
	private String textureName;

	private float rotation;

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
	
	public Entity(int id, float x, float y, String textureName, int texX, int texY, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = GameConstants.TILE_SIZE;
		this.height = GameConstants.TILE_SIZE;
		this.textureName = textureName;
		System.out.println("Makeing " + textureName);
		this.texX = texX;
		this.texY = texY;
		this.rotation = rotation;
	}

	public void checkForTextureAtlas() {
		if(this.texture == null) {
			this.texture = new TextureAtlas(textureName);
		}
	}
	
	public void draw() {
		if(this.textureName != null) {
			//System.out.println("Makeing " + textureName);
			this.texture = new TextureAtlas(textureName);
		}
		float[] texCoords = texture.getTextureCoords(texX, texY);
		GL11.glPushMatrix();
		Artist.drawTexturedQuad(x, y, width, height, rotation,
				texture.getTexture(), texCoords[0], texCoords[1], texCoords[2],
				texCoords[3]);
		GL11.glPopMatrix();
	}
	
	public void draw(float x, float y) {
		if(this.texture == null) {
			this.texture = new TextureAtlas(textureName);
			return;
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
	
	public void increasePositionWithServerUpdate(float dx, float dy) {
		this.x += dx;
		this.y += dy;
		Packet03Move movePacket = new Packet03Move(id, dx, dy);
		Client.getInstance().sendData(movePacket.getData());
	}

	public void setRotation(float rot) {
		this.rotation = rot;
	}
	
	public void setRotationWithServerUpdate(float rot) {
		this.rotation = rot;
		Packet04Rotation rotationPacket = new Packet04Rotation(id, rot);
		Client.getInstance().sendData(rotationPacket.getData());
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
