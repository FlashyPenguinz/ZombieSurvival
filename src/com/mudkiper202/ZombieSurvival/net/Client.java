package com.mudkiper202.ZombieSurvival.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mudkiper202.ZombieSurvival.entities.Entity;
import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.net.packets.Packet;
import com.mudkiper202.ZombieSurvival.net.packets.Packet00Login;
import com.mudkiper202.ZombieSurvival.net.packets.Packet02EntityChange;
import com.mudkiper202.ZombieSurvival.net.packets.Packet03Move;
import com.mudkiper202.ZombieSurvival.net.packets.Packet04Rotation;
import com.mudkiper202.ZombieSurvival.net.packets.PacketType;

public class Client extends Thread {

	private static Client client;

	private GameManager gm;

	private DatagramSocket socket;
	private InetAddress ipAddress;
	private int port;

	public static Client getInstance() {
		return client;
	}

	public Client(GameManager gm, String ipAdress, int port) {
		this.gm = gm;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAdress);
			this.port = port;
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		client = this;
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(data, packet.getAddress(), packet.getPort());
		}
	}

	private void parsePacket(byte[] data, InetAddress ipAddress, int port) {
		String message = new String(data).trim();
		PacketType type = Packet.lookupPacket(message.substring(0, 2));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet00Login loginPacket = new Packet00Login(data);
			System.out.println("[" + ipAddress.getHostAddress() + ":" + port
					+ "] " + loginPacket.getUsername() + " has connected...");
			break;
		case DISCONNECT:
			break;
		case ENTITY_CHANGE:
			Packet02EntityChange changePacket = new Packet02EntityChange(data);
			if (changePacket.getType() == 0) {
				gm.addEntity(changePacket.getId(), changePacket.getX(),
						changePacket.getY(), changePacket.getRotation(),
						changePacket.getTextureName(), changePacket.getTexX(), changePacket.getTexY());
			}
			break;
		 case MOVE:
			 Packet03Move movePacket = new Packet03Move(data);
			 Entity movePlayer = getEntityById(movePacket.getId());
			 if(movePlayer != null) {
				 movePlayer.increasePosition(movePacket.getX(), movePacket.getY());
			 }
		 break;
		 case ROTATION:
			 Packet04Rotation rotationPacket = new Packet04Rotation(data);
			 Entity rotationPlayer = getEntityById(rotationPacket.getId());
			 if(rotationPlayer != null) {
				 rotationPlayer.setRotation(rotationPacket.getRotation());
			 }
		 break;
		}
	}

	public Entity getEntityById(int id) {
		for(Entity entity: gm.getEntities()) {
			if(entity.getId() == id) {
				return entity;
			}
		}
		return null;
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length,
				ipAddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
