package com.mudkiper202.ZombieSurvival.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.data.AABB;
import com.mudkiper202.ZombieSurvival.data.TextureAtlas;
import com.mudkiper202.ZombieSurvival.entities.Entity;
import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.helpers.Timer;
import com.mudkiper202.ZombieSurvival.map.Map;

public class Player extends Entity {

	private Map map;

	private Gun gun;
	
	private AABB aabb;

	private final float SPEED = 2;

	public Player(Map map, float x, float y, TextureAtlas texture) {
		super(x, y, texture, 0, 0, 0);
		this.map = map;
		this.gun = new Gun(this);
		this.aabb = new AABB(0, 0, GameConstants.TILE_SIZE - 35,
				GameConstants.TILE_SIZE - 35);
	}

	public void update() {
		checkInputs();
		calculateRotation();
		updateAABB(getX(), getY());
		gun.update();
	}

	@Override
	public void draw() {
		gun.drawBullets();
		GL11.glPushMatrix();
		Artist.drawTexturedQuad(GameConstants.DISPLAY_WIDTH / 2,
				GameConstants.DISPLAY_HEIGHT / 2, getWidth(), getHeight(),
				getRotation(), getTextureAtlas().getTexture(), 0,
				0, 1, 1);
		GL11.glPopMatrix();
	}
	
	public Map getMap() {
		return map;
	}

	private void checkInputs() {
		float xIncrease = 0;
		float yIncrease = 0;
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			yIncrease = -SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			yIncrease = SPEED;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xIncrease = -SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xIncrease = SPEED;
		}
		xIncrease *= Timer.delta;
		yIncrease *= Timer.delta;
		updateAABB(getX() + xIncrease, getY());
		if (checkCollisions() == false)
			increasePosition(xIncrease, 0);
		updateAABB(getX(), getY() + yIncrease);
		if (checkCollisions() == false)
			increasePosition(0, yIncrease);
	}

	private boolean checkCollisions() {
		if (aabb.getX() < 0
				|| aabb.getX() + aabb.getWidth() > (map.size() * GameConstants.TILE_SIZE)
				|| aabb.getY() < 0
				|| aabb.getY() + aabb.getHeight() > (map.size() * GameConstants.TILE_SIZE)) {
			updateAABB(getX(), getY());
			return true;
		}
		if (map.getTile(
				(int) Math.floor(aabb.getX() / GameConstants.TILE_SIZE),
				(int) Math.floor(aabb.getY() / GameConstants.TILE_SIZE))
				.getType().isCollidable()) return true;
		if (map.getTile(
				(int) Math.floor((aabb.getX()+aabb.getWidth()) / GameConstants.TILE_SIZE),
				(int) Math.floor(aabb.getY() / GameConstants.TILE_SIZE))
				.getType().isCollidable()) return true;
		if (map.getTile(
				(int) Math.floor((aabb.getX()+aabb.getWidth()) / GameConstants.TILE_SIZE),
				(int) Math.floor((aabb.getY()+aabb.getHeight()) / GameConstants.TILE_SIZE))
				.getType().isCollidable()) return true;
		if (map.getTile(
				(int) Math.floor(aabb.getX() / GameConstants.TILE_SIZE),
				(int) Math.floor((aabb.getY()+aabb.getHeight()) / GameConstants.TILE_SIZE))
				.getType().isCollidable()) return true;
		return false;
	}

	private void updateAABB(float x, float y) {
		aabb.update(x - 15, y - 15);
	}

	private void calculateRotation() {
		float mouseX = Mouse.getX();
		float mouseY = Display.getHeight() - Mouse.getY();
		float playerX = Display.getWidth() / 2;
		float playerY = Display.getHeight() / 2;
		float xDif = playerX - mouseX;
		float yDif = mouseY - playerY;
		float hypotonuse = (float) Math.sqrt(Math.pow(xDif, 2)
				+ Math.pow(yDif, 2));
		float angle = (float) (Math.toDegrees(Math.asin(yDif / hypotonuse)) + 90);
		if (Mouse.getX() < Display.getWidth() / 2)
			angle = -angle;
		super.setRotation(angle);
	}
}