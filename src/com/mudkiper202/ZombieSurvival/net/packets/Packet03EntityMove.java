package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet03EntityMove extends Packet {

	private int id;
	private float x;
	private float y;
	private float rotation;
	
	public Packet03EntityMove(byte[] data) {
		super(03);
		String[] array = readData(data).split(",");
		this.id = Integer.valueOf(array[0]);
		this.x = Float.valueOf(array[1]);
		this.y = Float.valueOf(array[2]);
		this.rotation = Float.valueOf(array[3]);
	}
	
	public Packet03EntityMove(int id, float x, float y, float rotation) {
		super(03);
		this.id = id;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}

	@Override
	public byte[] getData() {
		return ("03"+this.id+","+this.x+","+this.y+","+this.rotation).getBytes();
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
	
	public float getRotation() {
		return rotation;
	}
	
}
