package com.mudkiper202.ZombieSurvival.data;

public class AABB {

	private float x, y;
	private float width, height;

	public AABB(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void update(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isColliding(AABB box) {
		if (x < box.getX() + box.getWidth() && x + width > box.getX()
				&& y < box.getY() + box.getHeight() && y + height > box.getY())
			return true;
		return false;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
