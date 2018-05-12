package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet05PlayerConnect extends Packet {

	private int id;
	
	public Packet05PlayerConnect(byte[] data) {
		super(05);
		this.id = Integer.valueOf(readData(data));
	}
	
	public Packet05PlayerConnect(int id) {
		super(05);
		this.id = id;
	}

	@Override
	public byte[] getData() {
		return ("05"+this.id).getBytes();
	}
	
	public int getId() {
		return id;
	}
	
}
