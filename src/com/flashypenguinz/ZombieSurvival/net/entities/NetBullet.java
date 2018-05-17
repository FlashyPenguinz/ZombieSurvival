package com.flashypenguinz.ZombieSurvival.net.entities;

import org.lwjgl.opengl.GL11;

import com.flashypenguinz.ZombieSurvival.helpers.Artist;

public class NetBullet extends NetEntity {

	private int playerId;
	
	public NetBullet(int id, int playerId, float x, float y, float rotation) {
		super(id, x, y, "", 0, 0, rotation);
		this.playerId = playerId;
	}

	@Override
	public void draw(float x, float y) {
		GL11.glPushMatrix();
		Artist.drawColoredQuad(x, y, 10, 20, getRotation(), 1, 1, 0);
		GL11.glPopMatrix();
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
}
