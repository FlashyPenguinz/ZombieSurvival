package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet04BulletChange extends Packet {

	private int type; //0 - add, 1 - remove
	private int id;
	private int playerId;
	private float x, y;
	private float rotation;
	
	public Packet04BulletChange(byte[] data) {
		super(04);
		String[] array = readData(data).split(",");
		this.type = Integer.valueOf(array[0]);
		this.id = Integer.valueOf(array[1]);
		this.playerId = Integer.valueOf(array[2]);
		this.x = Float.valueOf(array[3]);
		this.y = Float.valueOf(array[4]);
		this.rotation = Float.valueOf(array[5]);
	}

	
	public Packet04BulletChange(int type, int id, int playerId, float x, float y, float rotation) {
		super(04);
		this.type = type;
		this.id = id;
		this.playerId = playerId;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}

	@Override
	public byte[] getData() {
		return ("04"+this.type+","+this.id+","+playerId+","+x+","+y+","+rotation).getBytes();
	}
	
	public int getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getRotation() {
		return rotation;
	}
	
}
