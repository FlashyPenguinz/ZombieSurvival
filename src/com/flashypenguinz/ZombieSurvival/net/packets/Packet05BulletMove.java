package com.flashypenguinz.ZombieSurvival.net.packets;

public class Packet05BulletMove extends Packet {

	private int id;
	private float x, y;
	
	public Packet05BulletMove(byte[] data) {
		super(05);
		String[] array = readData(data).split(",");
		this.id = Integer.valueOf(array[0]);
		this.x = Float.valueOf(array[1]);
		this.y = Float.valueOf(array[2]);
	}

	
	public Packet05BulletMove(int id, float x, float y) {
		super(05);
		this.id = id;
		this.x = x;
		this.y = y;
	}

	@Override
	public byte[] getData() {
		return ("05"+this.id+","+x+","+y).getBytes();
	}
	
	public int getId() {
		return id;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}
