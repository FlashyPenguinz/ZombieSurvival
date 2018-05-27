package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;


import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.ui.UIState;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;
import com.flashypenguinz.ZombieSurvival.ui.components.TextButton;
import com.flashypenguinz.ZombieSurvival.ui.components.textbox.TextBox;
import com.flashypenguinz.ZombieSurvival.ui.components.textbox.TextBoxType;import com.flashypenguinz.ZombieSurvival.user.User;


public class Login extends Page {

	public Login(GameManager gm) {
		super();
		Text login = new Text(Display.getWidth()/2, 70, "Login", Font.BOLD, 48);
		
		Text backText = new Text(60, 40, "Back", Font.BOLD, 35);
		backText.setColor(Color.darkGray);
		TextButton back = new TextButton(backText, () -> {
			gm.setUIState(UIState.MAIN_MENU);
		});
		
		Text email = new Text(Display.getWidth()/2-250, 250, "Email:", Font.BOLD, 18);
		TextBox emailInput = new TextBox(Display.getWidth()/2-160, 230, 400, 40, 3, 15, 60, TextBoxType.EMAIL);
		
		Text password = new Text(Display.getWidth()/2-250, 300, "Password:", Font.BOLD, 18);
		TextBox passwordInput = new TextBox(Display.getWidth()/2-160, 280, 400, 40, 3, 15, 50, TextBoxType.PASSWORD);
		passwordInput.setPassword(true);
		
		Text fieldError = new Text(Display.getWidth()/2, 420, "Please fill out all fields", Font.BOLD, 18);
		fieldError.setVisible(false);
		fieldError.setColor(Color.red);
		
		Text incorrectError = new Text(Display.getWidth()/2, 420, "Email or Password is incorrect!", Font.BOLD, 18);
		incorrectError.setVisible(false);
		incorrectError.setColor(Color.red);
		
		TextButton submit = new TextButton(new Text(Display.getWidth()/2, 500, "Submit", Font.BOLD, 34), () -> {
			fieldError.setVisible(false);
			incorrectError.setVisible(false);
			String sEmail = emailInput.getText();
			String sPassword = passwordInput.getText();
			if(sEmail.replaceAll(" ", "").isEmpty()||sPassword.replaceAll(" ", "").isEmpty()) {
				fieldError.setVisible(true);
				return;
			}
			boolean result = gm.getDatabaseManager().getUserDatabase().login(sEmail, sPassword);
			if(!result) {
				incorrectError.setVisible(true);
				return;
			}
			String username = gm.getDatabaseManager().getUserDatabase().getUsername(sEmail);
			gm.user = new User(gm.getDatabaseManager().getUserDatabase().getUserId(username), username, sEmail, sPassword, gm.getDatabaseManager().getUserDatabase().getUserLevel(username));
			gm.setUIState(UIState.MAIN_MENU);
		});
		
		addComponent(login);
		addComponent(back);
		addComponent(email);
		addComponent(emailInput);
		addComponent(password);
		addComponent(passwordInput);
		addComponent(fieldError);
		addComponent(incorrectError);
		addComponent(submit);
	}
	
}
