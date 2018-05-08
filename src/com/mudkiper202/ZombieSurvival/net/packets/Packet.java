package com.mudkiper202.ZombieSurvival.net.packets;

public abstract class Packet {
	
	public byte packetId;
	
	public Packet(int packetId) {
		this.packetId = (byte) packetId;
	}
	
	public String readData(byte[] data) {
		String message = new String(data).trim();
		return message.substring(2);
	}
	
	public abstract byte[] getData();
	
	public static PacketType lookupPacket(String packetId) {
		try {
			return lookupPacket(Integer.parseInt(packetId));
		} catch (NumberFormatException e) {
			return PacketType.INVALID;
		}
	}
	
	public static PacketType lookupPacket(int id) {
		for(PacketType p: PacketType.values()) {
			if(p.getPacketId()==id) {
				return p;
			}
		}
		return PacketType.INVALID;
	}
	
}
