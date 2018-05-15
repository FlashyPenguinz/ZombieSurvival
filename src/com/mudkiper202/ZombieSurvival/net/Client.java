package com.mudkiper202.ZombieSurvival.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.net.entities.NetBullet;
import com.mudkiper202.ZombieSurvival.net.entities.NetEntity;
import com.mudkiper202.ZombieSurvival.net.entities.NetPlayer;
import com.mudkiper202.ZombieSurvival.net.packets.Packet;
import com.mudkiper202.ZombieSurvival.net.packets.Packet00Login;
import com.mudkiper202.ZombieSurvival.net.packets.Packet02PlayerChange;
import com.mudkiper202.ZombieSurvival.net.packets.Packet03EntityMove;
import com.mudkiper202.ZombieSurvival.net.packets.Packet04BulletChange;
import com.mudkiper202.ZombieSurvival.net.packets.Packet05BulletMove;
import com.mudkiper202.ZombieSurvival.net.packets.PacketType;

public class Client extends Thread {

	private static Client client;

	public boolean connected = false;

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
		if (type == PacketType.LOGIN) {
			Packet00Login loginPacket = new Packet00Login(data);
			System.out.println("[" + ipAddress.getHostAddress() + ":" + port
					+ "] " + loginPacket.getUsername() + " has connected...");
		} else if (type == PacketType.PLAYER_CHANGE) {
			Packet02PlayerChange packet = new Packet02PlayerChange(data);
			if (packet.getType() == 0) {
				gm.getEntityManager().addEntity(
						new NetPlayer(packet.getId(), packet.getUsername(),
								packet.getX(), packet.getY(), packet
										.getTextureName(), packet.getTexX(),
								packet.getTexY()));
			} else {
				gm.getEntityManager().removeEntityById(packet.getId());
			}
		} else if (type == PacketType.ENTITY_MOVE) {
			Packet03EntityMove packet = new Packet03EntityMove(data);
			NetEntity entity = gm.getEntityManager().getEntityById(
					packet.getId());
			if (entity != null) {
				entity.setPosition(packet.getX(), packet.getY());
				entity.setRotation(packet.getRotation());
			}
		} else if (type == PacketType.BULLET_CHANGE) {
			Packet04BulletChange packet = new Packet04BulletChange(data);
			if (packet.getType() == 0) {
				gm.getEntityManager().addEntity(
						new NetBullet(packet.getId(), packet.getPlayerId(),
								packet.getX(), packet.getY(), packet
										.getRotation()));
			} else {
				gm.getEntityManager().removeEntityById(packet.getId());
			}
		} else if (type == PacketType.BULLET_MOVE) {
			Packet05BulletMove packet = new Packet05BulletMove(data);
			NetEntity entity = gm.getEntityManager().getEntityById(
					packet.getId());
			if (entity != null) {
				entity.setPosition(packet.getX(), packet.getY());
			}
		}
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
