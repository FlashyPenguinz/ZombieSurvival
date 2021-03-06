package com.flashypenguinz.ZombieSurvival.ui;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.game.GameState;
import com.flashypenguinz.ZombieSurvival.ui.pages.Login;
import com.flashypenguinz.ZombieSurvival.ui.pages.MainMenu;
import com.flashypenguinz.ZombieSurvival.ui.pages.Page;
import com.flashypenguinz.ZombieSurvival.ui.pages.Signup;

public class UIManager {

	private GameManager gm;
	
	private UIState state = UIState.MAIN_MENU;
	private UIState lastState;
	
	private Page page;
	
	public UIManager(GameManager gm) {
		this.gm = gm;
	}
	
	public void update() {
		checkState();
		page.update();
	}
	
	public void draw() {
		page.draw();
	}
	
	private void checkState() {
		if(gm.getState() == GameState.MAIN_MENU) {
			if(lastState!=null) 
				if(state==lastState) 
					return;
			lastState = state;
			switch(state) {
			default: break;
			case MAIN_MENU: 
				page = new MainMenu(gm);
				break;
			case SIGNUP: 
				page = new Signup(gm);
				break;
			case LOGIN:
				page = new Login(gm);
				break;
			}
		}
	}
	
	public UIState getState() {
		return state;
	}
	
	public void setState(UIState state) {
		this.state = state;
	}
	
}
