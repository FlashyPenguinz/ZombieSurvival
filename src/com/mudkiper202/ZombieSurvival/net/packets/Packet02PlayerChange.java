package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet02PlayerChange extends Packet {

	private int type; //0 - add, 1 - remove
	private int id;
	private String username;
	private float x, y;
	private String textureName;
	private int texX, texY;
	
	public Packet02PlayerChange(byte[] data) {
		super(02);
		String[] array = readData(data).split(",");
		this.type = Integer.valueOf(array[0]);
		this.id = Integer.valueOf(array[1]);
		this.username = String.valueOf(array[2]);
		this.x = Float.valueOf(array[3]);
		this.y = Float.valueOf(array[4]);
		this.textureName = String.valueOf(array[5]);
		this.texX = Integer.valueOf(array[6]);
		this.texY = Integer.valueOf(array[7]);
	}

	
	public Packet02PlayerChange(int type, int id, String username, float x, float y, String textureName, int texX, int texY) {
		super(02);
		this.type = type;
		this.id = id;
		this.username = username;
		this.x = x;
		this.y = y;
		this.textureName = textureName;
		this.texX = texX;
		this.texY = texY;
	}

	@Override
	public byte[] getData() {
		return ("02"+this.type+","+this.id+","+this.username+","+x+","+y+","+textureName+","+texX+","+texY).getBytes();
	}
	
	public int getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
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
