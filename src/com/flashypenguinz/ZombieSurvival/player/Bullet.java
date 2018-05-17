package com.flashypenguinz.ZombieSurvival.player;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;
import com.flashypenguinz.ZombieSurvival.helpers.Timer;
import com.flashypenguinz.ZombieSurvival.map.Map;
import com.flashypenguinz.ZombieSurvival.net.entities.NetBullet;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet04BulletChange;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet05BulletMove;

public class Bullet {

	private final float SPEED = 8;
	private final float WIDTH = 10, HEIGHT = 20;

	private Player player;
	private Map map;

	private int id;
	
	private List<Bullet> toRemoveBullets;
	
	private float x, y;

	private float rotation;

	public Bullet(int id, Player player, float x, float y, float rot, List<Bullet> toRemoveBullets) {
		this.id = id;
		this.player = player;
		this.map = player.getMap();
		this.x = x;
		this.y = y;
		this.rotation = rot;
		this.toRemoveBullets = toRemoveBullets;
		player.getGameManager().getEntityManager().addEntity(new NetBullet(id, player.getId(), x, y, rot));
		Packet04BulletChange packet = new Packet04BulletChange(0, id, player.getId(), x, y, rot);
		player.getGameManager().getClient().sendData(packet.getData());
	}

	public void update() {
		move(SPEED);
		if(checkCollisions())
			remove();
	}
	
	public void move(float distance) {
		x += SPEED * Timer.delta * Math.sin(Math.toRadians(rotation));
		y -= SPEED * Timer.delta * Math.cos(Math.toRadians(rotation));
		Packet05BulletMove packet = new Packet05BulletMove(id, x, y);
		player.getGameManager().getClient().sendData(packet.getData());
	}
	
	public void draw() {
		float xDistance = player.getX() - x;
		float yDistance = player.getY() - y;
		float renderedX = (-xDistance) + (Display.getWidth() / 2);
		float renderedY = (-yDistance) + (Display.getHeight() / 2);
		GL11.glPushMatrix();
		Artist.drawColoredQuad(renderedX, renderedY, 10, 20, rotation, 1, 1, 0);
		GL11.glPopMatrix();
	}

	public void remove() {
		toRemoveBullets.add(this);
		player.getGameManager().getEntityManager().removeEntityById(id);
		Packet04BulletChange packet = new Packet04BulletChange(1, id, player.getId(), x, y, rotation);
		player.getGameManager().getClient().sendData(packet.getData());
	}
	
	private boolean checkCollisions() {
		if (x < 0 || x + WIDTH > (map.size() * GameConstants.TILE_SIZE)
				|| y < 0 || y + HEIGHT > (map.size() * GameConstants.TILE_SIZE)) {
			return true;
		}
		if (map.getTile(
				(int) Math.floor((x+(WIDTH/2)) / GameConstants.TILE_SIZE),
				(int) Math.floor((y+(HEIGHT/2)) / GameConstants.TILE_SIZE))
				.getType().isCollidable())
			return true;
		return false;
	}
	
	public int getId() {
		return id;
	}

}
