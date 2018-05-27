package com.flashypenguinz.ZombieSurvival.ui.components.textbox;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;
import com.flashypenguinz.ZombieSurvival.helpers.Timer;
import com.flashypenguinz.ZombieSurvival.ui.components.Component;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;

public class TextBox implements Component {

	private float x, y;
	private float width, height;
	private float borderThickness;

	private boolean password = false;
	private boolean focused = false;

	private TextBoxInput input;

	private boolean drawCursor = false;
	private float elapsedCursorTime;

	private Text textModule;

	public TextBox(float x, float y, float width, float height,
			float borderThickness, int textSize, int maximumCharacters,
			TextBoxType type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.borderThickness = borderThickness;
		this.input = new TextBoxInput(maximumCharacters, type);
		this.textModule = new Text(x + 7, y + (height / 2), "", Font.PLAIN,
				textSize, false);
		this.textModule.setColor(Color.black);
	}

	@Override
	public void update() {
		elapsedCursorTime += (Timer.delta);

		if (elapsedCursorTime > GameConstants.CURSOR_TIME) {
			if (drawCursor) {
				drawCursor = false;
			} else {
				drawCursor = true;
			}
			elapsedCursorTime = 0;
		}

		if (Mouse.isButtonDown(0)) {
			float mouseX = Mouse.getX();
			float mouseY = Display.getHeight() - Mouse.getY();
			if ((mouseX > x && mouseX < x + width)
					&& (mouseY > y && mouseY < y + height)) {
				mouseX -= this.x + borderThickness;
				if(mouseX > 0) {
					int lastPos = 0;
					for (int i = 0; i <= input.getText().length(); i++) {
						if(mouseX > textModule.getFont().getWidth(input.getText().substring(0, i)))
							lastPos = i;
					}
					input.cursorPos = lastPos;
				}
				focused = true;
				drawCursor = true;
				elapsedCursorTime = 0;
			} else {
				focused = false;
			}
		}

		if (focused) {
			input.update();
			textModule.setText(input.getText());
		}
		
	}

	@Override
	public void draw() {
		drawBase();
		if (password) {
			String dots = "";
			for (int i = 0; i < input.getText().length(); i++) {
				dots += "*";
			}
			textModule.setText(dots);
		}

		this.textModule.setY(this.y + (height/2) - this.textModule.getFont().getHeight(textModule.getText())/2);
		this.textModule.draw();

		if (focused) {
			if (drawCursor) {
				String subbedText = textModule.getText().substring(0,
						input.cursorPos);
				float x = this.x + borderThickness + 5
						+ this.textModule.getFont().getWidth(subbedText)
						- textModule.getTextSize() * 0.16f;
				float y = this.y + (height/2) - this.textModule.getFont().getHeight(textModule.getText())/2;
				GL11.glPushMatrix();
				Artist.drawColoredQuad(x, y, 2, this.textModule.getFont()
						.getHeight(), 0, 0, 0, 0);
				GL11.glPopMatrix();
			}
		}
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
		return input.getText();
	}

	public void setPassword(boolean password) {
		this.password = password;
	}

}
