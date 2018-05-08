package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet03Move extends Packet {

	private int id;
	private float x;
	private float y;
	
	public Packet03Move(byte[] data) {
		super(03);
		String[] array = readData(data).split(",");
		this.id = Integer.valueOf(array[0]);
		this.x = Float.valueOf(array[1]);
		this.y = Float.valueOf(array[2]);
	}
	
	public Packet03Move(int id, float x, float y) {
		super(03);
		this.id = id;
		this.x = x;
		this.y = y;
	}

	@Override
	public byte[] getData() {
		return ("03"+this.id+","+this.x+","+this.y).getBytes();
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
