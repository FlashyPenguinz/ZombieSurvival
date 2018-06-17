package com.flashypenguinz.ZombieSurvival.net.packets;

import com.flashypenguinz.ZombieSurvival.map.TileType;

public class Packet10MapEdit extends Packet {

	private int layer;
	private int tileX, tileY;
	private TileType type;
	
	public Packet10MapEdit(byte[] data) {
		super(10);
		String[] array = readData(data).split(",");
		this.layer = Integer.valueOf(array[0]);
		this.tileX = Integer.valueOf(array[1]);
		this.tileY = Integer.valueOf(array[2]);
		this.type = TileType.valueOf(array[3]);
	}

	public Packet10MapEdit(int layer, int tileX, int tileY, TileType type) {
		super(10);
		this.layer = layer;
		this.tileX = tileX;
		this.tileY = tileY;
		this.type = type;
	}

	@Override
	public byte[] getData() {
		return ("10"+this.layer+","+this.tileX+","+this.tileY+","+type.toString()).getBytes();
	}
	
	public int getLayer() {
		return layer;
	}

	public int getTileX() {
		return tileX;
	}
	
	public int getTileY() {
		return tileY;
	}

	public TileType getTileType() {
		return type;
	}
	
}
