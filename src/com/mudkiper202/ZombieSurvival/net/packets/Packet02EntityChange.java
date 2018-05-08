package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet02EntityChange extends Packet {

	private int id;
	private int type; //0 - add, 1 - remove
	private float x, y;
	private float rotation;
	private String textureName;
	private int texX, texY;
	
	public Packet02EntityChange(byte[] data) {
		super(02);
		String[] array = readData(data).split(",");
		this.id = Integer.valueOf(array[0]);
		this.type = Integer.valueOf(array[1]);
		this.x = Float.valueOf(array[2]);
		this.y = Float.valueOf(array[3]);
		this.rotation = Float.valueOf(array[4]);
		this.textureName = String.valueOf(array[5]);
		this.texX = Integer.valueOf(array[6]);
		this.texY = Integer.valueOf(array[7]);
	}

	
	public Packet02EntityChange(int id, int type, float x, float y, float rotation, String textureName, int texX, int texY) {
		super(02);
		this.id = id;
		this.type = type;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.textureName = textureName;
		this.texX = texX;
		this.texY = texY;
	}

	@Override
	public byte[] getData() {
		return ("02"+this.id+","+this.type+","+x+","+y+","+rotation+","+textureName+","+texX+","+texY).getBytes();
	}
	
	public int getId() {
		return id;
	}

	public int getType() {
		return type;
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


	public String getTextureName() {
		return textureName;
	}

	public int getTexX() {
		return texX;
	}
	
	public int getTexY() {
		return texY;
	}
	
}
