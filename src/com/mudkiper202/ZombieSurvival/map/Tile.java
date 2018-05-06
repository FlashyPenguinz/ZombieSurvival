package com.mudkiper202.ZombieSurvival.map;

import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.textures.TextureAtlas;

public class Tile {

	private float x, y;
	private TextureAtlas texture;
	private int texX, texY;
	
	private boolean collidable;
	
	public Tile(float x, float y, TextureAtlas texture, int texX, int texY, boolean collidable) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.texX = texX;
		this.texY = texY;
		this.collidable = collidable;
	}
	
	public void draw(float x, float y) {
		float[] texCoords = texture.getTextureCoords(texX, texY);
		Artist.drawTexturedQuad(this.x+x, this.y+y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, texture.getTexture(),
				texCoords[0], texCoords[1], texCoords[2], texCoords[3]);
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setTextureCoords(int texX, int texY) {
		this.texX = texX;
		this.texY = texY;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
}
