package com.flashypenguinz.ZombieSurvival.pathfinding;

public class Node {

	private boolean passable;
	public Node parent;
	private int x, y;
	public int cost;
	public int depth;
	public float heuristic;
	
	public Node(int x, int y, boolean passable) {
		this.x = x;
		this.y = y;
		this.passable = passable;
	}

	public float calculateHValue(Node dest) {
		return Math.abs(x-dest.getX()) + Math.abs(y-dest.getY());
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
