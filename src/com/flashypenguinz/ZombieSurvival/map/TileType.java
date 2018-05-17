package com.flashypenguinz.ZombieSurvival.map;

public enum TileType {

	EMPTY(0, 0, 0, false), GRASS_FLOOR(1, 1, 0, false), WOODEN_FLOOR(2, 2, 0,
			false), STONE_WALL(3, 3, 0, true), BARRICADE(4, 4, 0, true), GRASS_WOOD_TRANSITION(
			5, 5, 0, false), GRASS_WOOD_TRANSITION_2(6, 6, 0, false), TREE_PIECE_0(
			10, 0, 1, true), TREE_PIECE_1(11, 1, 1, true), TREE_PIECE_2(12, 2,
			1, true), TREE_PIECE_3(13, 3, 1, true), TREE_PIECE_4(14, 4, 1, true), TREE_PIECE_5(
			15, 5, 1, true), TREE_PIECE_6(16, 6, 1, true), TREE_PIECE_7(17, 7,
			1, true);

	private int id;
	private int texX, texY;

	private boolean collidable;

	private TileType(int id, int texX, int texY, boolean collidable) {
		this.id = id;
		this.texX = texX;
		this.texY = texY;
		this.collidable = collidable;
	}

	public int getId() {
		return id;
	}

	public int getTexX() {
		return texX;
	}

	public int getTexY() {
		return texY;
	}

	public boolean isCollidable() {
		return collidable;
	}

}
