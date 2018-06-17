package com.flashypenguinz.ZombieSurvival.net.packets;

import com.flashypenguinz.ZombieSurvival.entities.ZombieType;

public class Packet07ZombieChange extends Packet {

	private int type; //0 - add, 1 - remove
	private int id;
	private float x, y;
	private ZombieType zombieType;
	
	public Packet07ZombieChange(byte[] data) {
		super(07);
		String[] array = readData(data).split(",");
		this.type = Integer.valueOf(array[0]);
		this.id = Integer.valueOf(array[1]);
		this.x = Float.valueOf(array[2]);
		this.y = Float.valueOf(array[3]);
		this.zombieType = ZombieType.valueOf(array[4]);
	}

	
	public Packet07ZombieChange(int type, int id, float x, float y, ZombieType zombieType) {
		super(07);
		this.type = type;
		this.id = id;
		this.x = x;
		this.y = y;
		this.zombieType = zombieType;
	}

	@Override
	public byte[] getData() {
		System.out.println(x+" "+y);
		return ("07"+this.type+","+this.id+","+x+","+y+","+this.zombieType.toString()).getBytes();
	}
	
	public int getType() {
		return type;
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

	public ZombieType getZombieType() {
		return zombieType;
	}
	
}
