package com.mudkiper202.ZombieSurvival.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.game.GameConstants;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.textures.TextureAtlas;

public class Player extends Entity {

	private final float SPEED = 2;

	public Player(float x, float y, TextureAtlas texture) {
		super(x, y, texture, 0, 0, 0);
	}

	public void update() {
		checkInputs();
		calculateRotation();
	}

	@Override
	public void draw() {
		float[] texCoords = getTextureAtlas().getTextureCoords(
				(int) getTextureCoords().x, (int) getTextureCoords().y);
		GL11.glPushMatrix();
		Artist.drawTexturedQuad(GameConstants.DISPLAY_WIDTH / 2,
				GameConstants.DISPLAY_HEIGHT / 2, getWidth(), getHeight(),
				getRotation(), getTextureAtlas().getTexture(), texCoords[0],
				texCoords[1], texCoords[2], texCoords[3]);
		GL11.glPopMatrix();
	}

	private void checkInputs() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			increasePosition(0, -SPEED);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			increasePosition(0, SPEED);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			increasePosition(-SPEED, 0);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			increasePosition(SPEED, 0);
		}
	}

	private void calculateRotation() {
		float mouseX = Mouse.getX();
		float mouseY = Display.getHeight() - Mouse.getY();
		float playerX = Display.getWidth()/2;
		float playerY = Display.getHeight()/2;
		float xDif = playerX - mouseX;
		float yDif = mouseY - playerY;
		float hypotonuse = (float) Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2));
		float angle = (float) (Math.toDegrees(Math.asin(yDif / hypotonuse))+90);
		if(Mouse.getX()<Display.getWidth()/2)
			angle = -angle;
		super.setRotation(angle);
	}
}
