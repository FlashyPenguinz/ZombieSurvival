package com.mudkiper202.ZombieSurvival.player;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.helpers.Timer;
import com.mudkiper202.ZombieSurvival.map.Map;

public class Bullet {

	private final float SPEED = 8;
	private final float WIDTH = 10, HEIGHT = 20;

	private Player player;
	private Map map;

	private List<Bullet> toRemoveBullets;
	
	private float x, y;

	private float rotation;

	public Bullet(Player player, float x, float y, float rot, List<Bullet> toRemoveBullets) {
		this.player = player;
		this.map = player.getMap();
		this.x = x;
		this.y = y;
		this.rotation = rot;
		this.toRemoveBullets = toRemoveBullets;
	}

	public void update() {
		move(SPEED);
		if(checkCollisions())
			remove();
	}
	
	public void move(float distance) {
		x += SPEED * Timer.delta * Math.sin(Math.toRadians(rotation));
		y -= SPEED * Timer.delta * Math.cos(Math.toRadians(rotation));
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

}
