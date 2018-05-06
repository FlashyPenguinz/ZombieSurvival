package com.mudkiper202.ZombieSurvival.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mudkiper202.ZombieSurvival.game.GameConstants;

public class Map {

	public float x = 0, y = 0;

	private Tile[][] tileMap;

	public Map(int sizeX, int sizeY) {
		tileMap = new Tile[sizeX][sizeY];
		for (int x = 0; x < tileMap.length; x++)
			for (int y = 0; y < tileMap[x].length; y++) {
				tileMap[x][y] = new Tile(x * GameConstants.TILE_SIZE, y
						* GameConstants.TILE_SIZE, TileType.GRASS_FLOOR);
			}
	}

	public Map(int[][] map) {
		if (map.length == 0)
			return;

		tileMap = new Tile[map.length][map[0].length];
		for (int x = 0; x < map.length; x++)
			for (int y = 0; y < map[x].length; y++) {
				for (TileType type : TileType.values()) {
					if (type.getId() == map[x][y]) {
						tileMap[x][y] = new Tile(x * GameConstants.TILE_SIZE, y
								* GameConstants.TILE_SIZE, type);
						break;
					}
				}
			}
	}

	public void draw() {
		for (int x = 0; x < tileMap.length; x++)
			for (int y = 0; y < tileMap[x].length; y++) {
				tileMap[x][y].draw(this.x, this.y);
			}
	}

	public Tile getTile(int tileX, int tileY) {
		return tileMap[tileX][tileY];
	}
	
	public int size() {
		return tileMap.length;
	}
	
	public static int[][] loadMapFile(String name) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("res/data/"+name+".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			
			int rows = 0;
			int columns = 0;
			
			String line;
			List<String> lines = new ArrayList<String>();
			
			while((line = reader.readLine())!=null) {
				columns = line.split(",").length;
				rows++;
				lines.add(line);
			}
			
			reader.close();
			
			int[][] map = new int[rows][columns];
			
			for(int i = 0; i < columns; i++) {
				int[] row = new int[lines.get(i).split(",").length];
				for (int j = 0; j < row.length; j++) {
					String number = (lines.get(i).split(",")[j].trim());
					row[j] = Integer.valueOf(number);
				}
				map[i] = row;
			}
			
			int[][] tempMap = new int[columns][rows];
			
			for (int i = 0; i < map.length; i++) 
				for (int j = 0; j < map[i].length; j++) {
					tempMap[j][i] = map[i][j];
				}
			
			
			return tempMap;
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
