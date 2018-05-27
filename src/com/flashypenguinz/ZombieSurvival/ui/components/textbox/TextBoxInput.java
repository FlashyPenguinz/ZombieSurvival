package com.flashypenguinz.ZombieSurvival.ui.components.textbox;

import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.helpers.Timer;

public class TextBoxInput {
	
	private String text;
	private int maximumCharacter;
	private TextBoxType type;
	
	public int cursorPos = 0;
	
	private float elapsedBackSpaceTime = 0;
	private float elapsedBetweenBackSpaceTime = 0;
	
	public TextBoxInput(int maximumCharacters, TextBoxType type) {
		this.text = "";
		this.maximumCharacter = maximumCharacters;
		this.type = type;
	}
	
	public void update() {
		elapsedBackSpaceTime += Timer.delta;
		elapsedBetweenBackSpaceTime += Timer.delta;
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Pattern
						.matches(
								type.getRegex(),
								String.valueOf(Keyboard.getEventCharacter()))) {
					addCharacter(Keyboard.getEventCharacter());
				} else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
					addCharacter(' ');
				} else if(Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
					if(text.length() > 0) {
						removeCharacter();
					}
				} else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
					if(cursorPos != 0)
						cursorPos--;
				} else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
					if(cursorPos != text.length())
						cursorPos++;
				}
			}
		}
		
		if(!Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
			elapsedBackSpaceTime = 0;
		} else {
			if(elapsedBackSpaceTime > GameConstants.BACKSPACE_TIME) {
				if(elapsedBetweenBackSpaceTime > GameConstants.BETWEEN_BACKSPACE_TIME) {
					if(text.length() > 0) {
						removeCharacter();
					}
					elapsedBetweenBackSpaceTime = 0;
				}
			}
		}
	}

	private void addCharacter(char character) {
		if(text.length() < maximumCharacter) {
			String begin = text.substring(0, cursorPos);
			String end = text.substring(cursorPos, text.length());
			text = begin + character + end;
			cursorPos++;
		}
	}
	
	private void removeCharacter() {
		if(cursorPos != 0) {
			text = text.substring(0, cursorPos - 1) + text.substring(cursorPos, text.length());
			cursorPos--;
		}
	}
	
	public String getText() {
		return text;
	}

}
