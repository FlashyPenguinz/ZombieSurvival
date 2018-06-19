package com.flashypenguinz.ZombieSurvival.net.entities;

public class SyncEntity {

	private int id;
	private float x, y, rotation;
	
	public SyncEntity(int id, float x, float y, float rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	public int getId() {
		return id;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRotation() {
		return rotation;
	}
	
}
