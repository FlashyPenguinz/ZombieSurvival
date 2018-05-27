package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.ui.UIState;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;
import com.flashypenguinz.ZombieSurvival.ui.components.TextButton;
import com.flashypenguinz.ZombieSurvival.ui.components.textbox.TextBox;
import com.flashypenguinz.ZombieSurvival.ui.components.textbox.TextBoxType;
import com.flashypenguinz.ZombieSurvival.user.User;

public class Signup extends Page {

	public Signup(GameManager gm) {
		super();
		Text signup = new Text(Display.getWidth()/2, 70, "Sign Up", Font.BOLD, 48);
		
		Text backText = new Text(60, 40, "Back", Font.BOLD, 35);
		backText.setColor(Color.darkGray);
		TextButton back = new TextButton(backText, () -> {
			gm.setUIState(UIState.MAIN_MENU);
		});
		
		Text username = new Text(Display.getWidth()/2-250, 200, "Username:", Font.BOLD, 18);
		TextBox usernameInput = new TextBox(Display.getWidth()/2-160, 180, 200, 40, 3, 15, 20, TextBoxType.USERNAME);
		Text usernameError = new Text(Display.getWidth()/2+130, 200, "Username is taken", Font.BOLD, 19);
		usernameError.setColor(Color.red);
		usernameError.setVisible(false);
		
		Text email = new Text(Display.getWidth()/2-250, 250, "Email:", Font.BOLD, 18);
		TextBox emailInput = new TextBox(Display.getWidth()/2-160, 230, 400, 40, 3, 15, 60, TextBoxType.EMAIL);
		Text emailError = new Text(Display.getWidth()/2+320, 250, "Email is not valid", Font.BOLD, 19);
		emailError.setColor(Color.red);
		emailError.setVisible(false);
		Text emailError2 = new Text(Display.getWidth()/2+310, 250, "Email is taken", Font.BOLD, 19);
		emailError2.setColor(Color.red);
		emailError2.setVisible(false);
		
		Text password = new Text(Display.getWidth()/2-250, 300, "Password:", Font.BOLD, 18);
		TextBox passwordInput = new TextBox(Display.getWidth()/2-160, 280, 400, 40, 3, 15, 50, TextBoxType.PASSWORD);
		passwordInput.setPassword(true);
		
		Text confirmPassword = new Text(Display.getWidth()/2-250, 350, "Confirm Password:", Font.BOLD, 18);
		TextBox confirmPasswordInput = new TextBox(Display.getWidth()/2-160, 330, 400, 40, 3, 15, 50, TextBoxType.PASSWORD);
		confirmPasswordInput.setPassword(true);
		Text passwordError = new Text(Display.getWidth()/2+345, 350, "Passwords don't match", Font.BOLD, 19);
		passwordError.setColor(Color.red);
		passwordError.setVisible(false);
		
		TextButton login = new TextButton(new Text(Display.getWidth()/2, 400, "Or login!", Font.BOLD, 26), () -> {
			gm.setUIState(UIState.LOGIN);
		});
		
		Text fieldError = new Text(Display.getWidth()/2, 420, "Please fill out all fields", Font.BOLD, 18);
		fieldError.setVisible(false);
		fieldError.setColor(Color.red);
		TextButton submit = new TextButton(new Text(Display.getWidth()/2, 500, "Submit", Font.BOLD, 34), () -> {
			usernameError.setVisible(false);
			emailError.setVisible(false);
			emailError2.setVisible(false);
			passwordError.setVisible(false);
			fieldError.setVisible(false);
			String sUsername = usernameInput.getText();
			String sEmail = emailInput.getText();
			String sPassword = passwordInput.getText();
			String sConfirmedPassword = confirmPasswordInput.getText();
			boolean error = false;
			if(sUsername.replaceAll(" ", "").isEmpty()||sEmail.replaceAll(" ", "").isEmpty()||sPassword.replaceAll(" ", "").isEmpty()||sConfirmedPassword.replaceAll(" ", "").isEmpty()) {
				fieldError.setVisible(true);
				return;
			}
			if(gm.getDatabaseManager().getUserDatabase().hasUsername(sUsername)) {
				usernameError.setVisible(true);
				error = true;
			}
			boolean emailValid = true;
			try {
				InternetAddress emailAddr = new InternetAddress(sEmail);
				emailAddr.validate();
			} catch (AddressException ex) {
				emailValid = false;
			}
			if(!emailValid) {
				emailError.setVisible(true);
				error = true;
			} else {
				if(gm.getDatabaseManager().getUserDatabase().hasEmail(sEmail)) {
					emailError2.setVisible(true);
					error = true;
				}
			}
			if(!sPassword.equals(sConfirmedPassword)) {
				passwordError.setVisible(true);
				error = true;
			}
			if(error)
				return;
			gm.getDatabaseManager().getUserDatabase().signup(sUsername, sEmail, sPassword);
			gm.user = new User(gm.getDatabaseManager().getUserDatabase().getUserId(sUsername), sUsername, sEmail, sPassword, 0);
			gm.setUIState(UIState.MAIN_MENU);
		});
		
		addComponent(signup);
		addComponent(back);
		addComponent(username);
		addComponent(usernameInput);
		addComponent(usernameError);
		addComponent(email);
		addComponent(emailInput);
		addComponent(emailError);
		addComponent(emailError2);
		addComponent(password);
		addComponent(passwordInput);
		addComponent(confirmPassword);
		addComponent(confirmPasswordInput);
		addComponent(passwordError);
		addComponent(login);
		addComponent(fieldError);
		addComponent(submit);
	}
}
