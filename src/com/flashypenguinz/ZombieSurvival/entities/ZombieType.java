package com.flashypenguinz.ZombieSurvival.entities;

public enum ZombieType {

	NORMAL(0, 0), SPEED(0, 1), POWER(1, 0), ULTRA(1, 1);
	
	private int texX, texY;
	
	ZombieType(int texX, int texY) {
		this.texX = texX;
		this.texY = texY;
	}
	
	public int getTexX() {
		return texX;
	}
	
	public int getTexY() {
		return texY;
	}
	
}
