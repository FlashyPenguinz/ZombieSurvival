package com.flashypenguinz.ZombieSurvival.map;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;

public class Tile {

	private float x, y;

	private TileType type;

	public Tile(float x, float y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public void draw(float x, float y) {
		float[] texCoords = GameConstants.MAP_ATLAS.getTextureCoords(
				type.getTexX(), type.getTexY());
		Artist.drawTexturedQuad(this.x + x, this.y + y,
				GameConstants.TILE_SIZE, GameConstants.TILE_SIZE,
				GameConstants.MAP_ATLAS.getTexture(), texCoords[0],
				texCoords[1], texCoords[2], texCoords[3]);
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public TileType getType() {
		return type;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
