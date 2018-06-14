package com.flashypenguinz.ZombieSurvival.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;

public class Map {

	public float x = 0, y = 0;

	private int rows, cols;
	
	private Tile[][] layer1;
	private Tile[][] layer2;
	
	private int[][][] rawMap;
	
	public Map(int[][][] layers) {
		if (layers[0].length == 0 || layers[1].length == 0)
			return;
		
		this.rawMap = layers;
		
		rows = layers[0].length;
		cols = layers[0][0].length;

		this.layer1 = new Tile[rows][cols];
		this.layer2 = new Tile[rows][cols];
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++) {
				for (TileType type : TileType.values()) {
					if (type.getId() == layers[0][x][y]) {
						this.layer1[x][y] = new Tile(x * GameConstants.TILE_SIZE, y
								* GameConstants.TILE_SIZE, type);
					}
					if (type.getId() == layers[1][x][y]) {
						this.layer2[x][y] = new Tile(x * GameConstants.TILE_SIZE, y
								* GameConstants.TILE_SIZE, type);
					}
				}
			}
	}

	public void draw() {
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++) {
				layer1[x][y].draw(this.x, this.y);
				layer2[x][y].draw(this.x, this.y);
			}
	}

	public Tile getTile(int layer, int tileX, int tileY) {
		if(layer == 1)
			return layer1[tileX][tileY];
		else
			return layer2[tileX][tileY];
	}

	public int sizeX() {
		return cols;
	}
	
	public int sizeY() {
		return rows;
	}
	
	public int[][][] getRawMap() {
		return rawMap;
	}
	
	public static int[][][] loadMapFile(String path) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			String prod = "";
			
			int rows = 0;
			int columns = 0;
			
			String line;
			
			boolean gotEnd = false;
			
			while((line = reader.readLine())!=null) {
				if(line.matches("END")) {
					gotEnd = true;
					prod += line+"\n";
					continue;
				}
				columns = line.split(",").length;
				if(!gotEnd)
					rows++;
				prod += line+"\n";
			}
			
			int[][][] finalMap = new int[2][rows][columns];
			
			BufferedReader mapReader = new BufferedReader(new StringReader(prod));
			
			List<String> lines = new ArrayList<String>();
			
			for (int i = 0; i < 2; i++) {
				
				int[][] map = new int[rows][columns];
				
				while((line = mapReader.readLine())!=null) {
					if(line.matches("END"))
						break;
					lines.add(line);
				}
				
				for(int j = 0; j < columns; j++) {
					int[] row = new int[lines.get(j).split(",").length];
					for (int k = 0; k < row.length; k++) {
						String number = (lines.get(j).split(",")[k].trim());
						row[k] = Integer.valueOf(number);
					}
					map[j] = row;
				}
				
				int[][] tempMap = new int[columns][rows];
				
				for (int j = 0; j < map.length; j++) 
					for (int k = 0; k < map[j].length; k++) {
						tempMap[k][j] = map[j][k];
					}
				
				lines.clear();
				finalMap[i] = map;
				
			}
			reader.close();
			return finalMap;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
