package com.flashypenguinz.ZombieSurvival.net.packets;

public class Packet06MapInfo extends Packet {

	private int[][][] map;
	
	public Packet06MapInfo(byte[] data) {
		super(06);
		int rows = readData(data).split("END")[0].split("S").length;
		int cols = readData(data).split("END")[0].split("S")[0].split(",").length;
		
		int[][][] map = new int[2][rows][cols];
		for (int i = 0; i < 2; i++) {
			String layer = readData(data).split("END")[i];
			String[] rawRows = layer.split("S");
			for (int j = 0; j < rows; j++) {
				String[] rawRow = rawRows[j].split(",");
				for (int k = 0; k < cols; k++) {
					map[i][j][k] = Integer.valueOf(rawRow[k]);
				}
			}
		}
		
		this.map = map;
	}

	public Packet06MapInfo(int[][][] map) {
		super(06);
		this.map = map;
	}

	@Override
	public byte[] getData() {
		String prod = "";
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				for (int k = 0; k < map[i][j].length; k++) {
					prod+=map[i][j][k]+",";
				}
				prod = prod.substring(0, prod.length()-1) + "S";
			}
			prod = prod.substring(0, prod.length()-1) + "END";
		}
		return ("06"+prod).getBytes();
	}
	
	public int[][][] getMap() {
		return map;
	}

}
