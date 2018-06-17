package com.flashypenguinz.ZombieSurvival.net.entities;

import com.flashypenguinz.ZombieSurvival.entities.ZombieType;

public class NetZombie extends NetEntity {

	private ZombieType type;
	
	public NetZombie(int id, float x, float y, ZombieType type) {
		super(id, x, y, "zombie", 0, 0, 0);
		this.type = type;
		this.setTextureCoords(type.getTexX(), type.getTexY());
	}
	
	public ZombieType getType() {
		return type;
	}
	
}
