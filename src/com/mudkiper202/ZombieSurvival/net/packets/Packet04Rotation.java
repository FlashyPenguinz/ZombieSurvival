package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet04Rotation extends Packet {

	private int id;
	private float rotation;
	
	public Packet04Rotation(byte[] data) {
		super(04);
		String[] array = readData(data).split(",");
		this.id = Integer.valueOf(array[0]);
		this.rotation = Float.valueOf(array[1]);
	}
	
	public Packet04Rotation(int id, float rotation) {
		super(04);
		this.id = id;
		this.rotation = rotation;
	}

	@Override
	public byte[] getData() {
		return ("04"+this.id+","+this.rotation).getBytes();
	}
	
	public int getId() {
		return id;
	}

	public float getRotation() {
		return rotation;
	}
	
}
