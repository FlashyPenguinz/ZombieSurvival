package com.flashypenguinz.ZombieSurvival.helpers;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;

public class Artist {

	public static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(GameConstants.DISPLAY_WIDTH,
					GameConstants.DISPLAY_HEIGHT));
			Display.setTitle(GameConstants.DISPLAY_TITLE);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		glEnable(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, GameConstants.DISPLAY_WIDTH, GameConstants.DISPLAY_HEIGHT,
				0, -1, 1);
		glEnable(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void updateDisplay() {
		Display.sync(GameConstants.DISPLAY_FPS);
		Display.update();
		long currentFrameTime = Timer.getCurrentTime();
		Timer.delta = (currentFrameTime - Timer.lastFrame)/10f;
		Timer.lastFrame = currentFrameTime;
	}

	public static void destroyDisplay() {
		Display.destroy();
	}
	
	public static void drawColoredQuad(float x, float y, float width,
			float height, float rotation, float r, float g, float b, float a) {
		glDisable(GL_TEXTURE_2D);
		glTranslatef(x, y, 0);
		glRotatef(rotation, 0, 0, 1);
		glColor4f(r, g, b, a);
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(width, 0);
		glVertex2f(width, height);
		glVertex2f(0, height);
		glEnd();
		glColor4f(1, 1, 1, 1);
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void drawColoredQuad(float x, float y, float width,
			float height, float rotation, float r, float g, float b) {
		glDisable(GL_TEXTURE_2D);
		glTranslatef(x, y, 0);
		glRotatef(rotation, 0, 0, 1);
		glColor3f(r, g, b);
		glBegin(GL_QUADS);
		glVertex2f(0, 0);
		glVertex2f(width, 0);
		glVertex2f(width, height);
		glVertex2f(0, height);
		glEnd();
		glColor3f(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
	}

	public static void drawTexturedQuad(float x, float y, float width, float height, Texture texture) {
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(1, 0);
		glVertex2f(x + width, y);
		glTexCoord2f(1, 1);
		glVertex2f(x + width, y + height);
		glTexCoord2f(0, 1);
		glVertex2f(x, y + height);
		glEnd();
	}
	
	public static void drawTexturedQuad(float x, float y, float width,
			float height, Texture texture, float texXMin, float texYMin,
			float texXMax, float texYMax) {
		texture.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(texXMin, texYMin);
		glVertex2f(x, y);
		glTexCoord2f(texXMax, texYMin);
		glVertex2f(x + width, y);
		glTexCoord2f(texXMax, texYMax);
		glVertex2f(x + width, y + height);
		glTexCoord2f(texXMin, texYMax);
		glVertex2f(x, y + height);
		glEnd();
	}

	public static void drawTexturedQuad(float x, float y, float width,
			float height, float rotation, Texture texture, float texXMin,
			float texYMin, float texXMax, float texYMax) {
		texture.bind();
		glTranslatef(x, y, 0);
		glRotatef(rotation, 0, 0, 1);
		glBegin(GL_QUADS);
		glTexCoord2f(texXMin, texYMin);
		glVertex2f(-width/2, -height/2);
		glTexCoord2f(texXMax, texYMin);
		glVertex2f(width/2, -height/2);
		glTexCoord2f(texXMax, texYMax);
		glVertex2f(width/2, height/2);
		glTexCoord2f(texXMin, texYMax);
		glVertex2f(-width/2, height/2);
		glEnd();
	}

}
