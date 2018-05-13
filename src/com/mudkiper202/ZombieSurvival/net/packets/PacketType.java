package com.mudkiper202.ZombieSurvival.net.packets;

public enum PacketType {
	INVALID(-1), LOGIN(00), DISCONNECT(01), PLAYER_CHANGE(02), ENTITY_MOVE(03);
	
	private int packetId;
	
	PacketType(int packetId) {
		this.packetId = packetId;
	}
	
	public int getPacketId() {
		return packetId;
	}
}
