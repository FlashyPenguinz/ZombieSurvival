package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import org.lwjgl.opengl.Display;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;

public class Signup extends Page {

	public Signup(GameManager gm) {
		super();
		Text signup = new Text(Display.getWidth()/2, 100, "Sign Up", Font.BOLD, 28);
		addComponent(signup);
	}
	
}
