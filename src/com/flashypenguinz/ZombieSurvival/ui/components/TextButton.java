package com.flashypenguinz.ZombieSurvival.ui.components;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class TextButton implements Component {

	private Text text;

	private Runnable action;

	private boolean clicked = false;
	
	public TextButton(Text text, Runnable action) {
		this.text = text;
		this.action = action;
	}

	@Override
	public void update() {
		float mouseX = Mouse.getX();
		float mouseY = Display.getHeight() - Mouse.getY();
		float textWidth = text.getFont().getWidth(text.getText());
		float textHeight = text.getFont().getHeight(text.getText());
		if ((mouseX > text.getX() && mouseX < text.getX() + textWidth)
				&& (mouseY > text.getY() && mouseY < text.getY() + textHeight)) {
			text.setColor(Color.gray);
			if(Mouse.isButtonDown(0) && clicked == false) {
				action.run();
				clicked = true;
			}
		} else {
			text.setColor(Color.white);
		}
		if(!Mouse.isButtonDown(0))
			clicked = false;
	}

	@Override
	public void draw() {
		text.draw();
	}

}
