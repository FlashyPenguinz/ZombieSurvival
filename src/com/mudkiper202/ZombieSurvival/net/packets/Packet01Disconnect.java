package com.mudkiper202.ZombieSurvival.net.packets;

public class Packet01Disconnect extends Packet {
	
	public Packet01Disconnect() {
		super(01);
	}

	@Override
	public byte[] getData() {
		return ("01").getBytes();
	}

}
