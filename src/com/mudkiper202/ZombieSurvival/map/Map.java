package com.mudkiper202.ZombieSurvival.map;

import com.mudkiper202.ZombieSurvival.game.GameConstants;

public class Map {

	public float x = 0, y = 0;
	
	private Tile[][] tileMap;

	public Map(int sizeX, int sizeY) {
		tileMap = new Tile[sizeX][sizeY];
		for (int x = 0; x < tileMap.length; x++)
			for (int y = 0; y < tileMap[x].length; y++) {
				tileMap[x][y] = new Tile(x * GameConstants.TILE_SIZE, y
					* GameConstants.TILE_SIZE, GameConstants.MAP_ATLAS, 1, 0, false);
			}
	}
	
	public void draw() {
		for (int x = 0; x < tileMap.length; x++)
			for (int y = 0; y < tileMap[x].length; y++) {
				tileMap[x][y].draw(this.x, this.y);
			}
	}

}
