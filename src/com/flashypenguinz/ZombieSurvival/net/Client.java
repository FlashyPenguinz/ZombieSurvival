package com.flashypenguinz.ZombieSurvival.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.map.Map;
import com.flashypenguinz.ZombieSurvival.map.Tile;
import com.flashypenguinz.ZombieSurvival.net.entities.NetBullet;
import com.flashypenguinz.ZombieSurvival.net.entities.NetEntity;
import com.flashypenguinz.ZombieSurvival.net.entities.NetPlayer;
import com.flashypenguinz.ZombieSurvival.net.entities.NetZombie;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet00Login;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet02PlayerChange;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet03EntityMove;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet04BulletChange;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet05BulletMove;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet06MapInfo;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet07ZombieChange;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet10MapEdit;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet11Sync;
import com.flashypenguinz.ZombieSurvival.net.packets.PacketType;
import com.flashypenguinz.ZombieSurvival.player.Player;
import com.flashypenguinz.ZombieSurvival.sound.AudioMaster;
import com.flashypenguinz.ZombieSurvival.sound.Source;

public class Client extends Thread {

	private static Client client;

	public boolean connected = false;
	public boolean gotMap = false;

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
			byte[] data = new byte[8192];
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
				if(entity.getId() == gm.getPlayer().getId()) {
					Player player = gm.getPlayer();
					player.setPosition(packet.getX(), packet.getY());
					player.setRotation(packet.getRotation());
				} else {
					entity.setPosition(packet.getX(), packet.getY());
					entity.setRotation(packet.getRotation());
				}
			}
		} else if (type == PacketType.BULLET_CHANGE) {
			Packet04BulletChange packet = new Packet04BulletChange(data);
			if (packet.getType() == 0) {
				gm.getEntityManager().addEntity(
						new NetBullet(packet.getId(), packet.getPlayerId(),
								packet.getX(), packet.getY(), packet
										.getRotation()));
				Source player = new Source();
				player.setGain(0.25f);
				player.setPosition(
						(-(gm.getPlayer().getX() - packet.getX()))
								+ (Display.getWidth() / 2),
						(-(gm.getPlayer().getY() - packet.getY()))
								+ (Display.getHeight() / 2));
				player.play(AudioMaster.loadSound("gunshot"));
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
		} else if (type == PacketType.MAP_INFO) {
			Packet06MapInfo packet = new Packet06MapInfo(data);
			gm.setMap(new Map(packet.getMap()));
			gotMap = true;
		} else if (type == PacketType.ZOMBIE_CHANGE) {
			Packet07ZombieChange packet = new Packet07ZombieChange(data);
			if (packet.getType() == 0) {
				gm.getEntityManager().addEntity(
						new NetZombie(packet.getId(), packet.getX(), packet
								.getY(), packet.getZombieType()));
			} else {
				gm.getEntityManager().removeEntityById(packet.getId());
			}
		} else if(type == PacketType.MAP_EDIT) {
			Packet10MapEdit packet = new Packet10MapEdit(data);
			gm.getMap().setTile(packet.getLayer(), packet.getTileX(), packet.getTileY(), new Tile(packet.getTileX()*GameConstants.TILE_SIZE, packet.getTileY()*GameConstants.TILE_SIZE, packet.getTileType()));
		} else if(type == PacketType.SYNC) {
			if(!connected)
				return;
			Packet11Sync packet = new Packet11Sync(data);
			if(packet.getEntities() == null)
				return;
			if(gm.getEntityManager().getEntities().size() == packet.getEntities().size())
				return;
			List<Integer> ids = new ArrayList<Integer>();
			for (int i = 0; i < packet.getEntities().size(); i++) {
				ids.add(packet.getEntities().get(i).getId());
			}
			List<Integer> toRemove = new ArrayList<Integer>();
			for (NetEntity entity: gm.getEntityManager().getEntities()) {
				if(!ids.contains(entity.getId())) {
					toRemove.add(entity.getId());
				}
			}
			for(int entity: toRemove) {
				gm.getEntityManager().removeEntityById(entity);
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
