package com.flashypenguinz.ZombieSurvival.ui.components;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.flashypenguinz.ZombieSurvival.helpers.Artist;

public class TextBox implements Component {

	private float x, y;
	private float width, height;
	private float borderThickness;

	//private boolean drawCursor = false;
	//private int cursorPos = 0;

	private Text textModule;

	private String text = "";
	private boolean canPressBack = true;
	private long targetTime;
	
	private boolean spacesAllowed = false;
	
	public TextBox(float x, float y, float width, float height,
			float borderThickness, int textSize, boolean spacesAllowed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.borderThickness = borderThickness;
		this.spacesAllowed = spacesAllowed;
		this.textModule = new Text(x + 2, y+(height/2), "", Font.PLAIN, textSize);
		this.textModule.setColor(Color.black);
	}

	@Override
	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if(text.length() < 20) {
					if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)&&spacesAllowed) {
						text += " ";
					} else if (Character.isLetterOrDigit(Keyboard
							.getEventCharacter())) {
						text += Keyboard.getEventCharacter();
					}
				}
			}
		}
		if(!canPressBack)
			if(targetTime < System.currentTimeMillis()) 
				canPressBack = true;
		if (Keyboard.isKeyDown(Keyboard.KEY_BACK)&&canPressBack) {
			if (text.length() > 0) {
				text = text.substring(0, text.length() - 1);
				canPressBack = false;
				targetTime = System.currentTimeMillis()+120;
			}
		}
		this.textModule.setText(text);
	}

	@Override
	public void draw() {
		drawBase();
		this.textModule.setX(x
				+ (this.textModule.getFont()
						.getWidth(this.textModule.getText()) / 2)
				+ borderThickness);
		this.textModule.draw();
	}

	private void drawBase() {
		glPushMatrix();
		Artist.drawColoredQuad(x, y, width, height, 0, 1, 1, 1);
		glPopMatrix();

		glDisable(GL_TEXTURE_2D);
		glColor3f(0, 0, 0);
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glLineWidth(borderThickness);
		glBegin(GL_POLYGON);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
		glEnd();
		glLineWidth(1);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
		glColor3f(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
	}

	public String getText() {
		return text;
	}
	
}
