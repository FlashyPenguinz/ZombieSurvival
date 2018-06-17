package com.flashypenguinz.ZombieSurvival.net.packets;

public enum PacketType {
	INVALID(-1), LOGIN(00), DISCONNECT(01), PLAYER_CHANGE(02), ENTITY_MOVE(03),
		BULLET_CHANGE(04), BULLET_MOVE(05), MAP_INFO(06), ZOMBIE_CHANGE(07), 
		MAP_EDIT(10);

	private int packetId;

	PacketType(int packetId) {
		this.packetId = packetId;
	}

	public int getPacketId() {
		return packetId;
	}
}
