package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import org.newdawn.slick.Color;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.ui.UIState;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;
import com.flashypenguinz.ZombieSurvival.ui.components.TextButton;

public class VerificationPage extends Page {

	public VerificationPage(GameManager gm) {
		super(false);
		Text backText = new Text(100, 100, "Back", Font.BOLD, 24);
		backText.setColor(Color.darkGray);
		TextButton back = new TextButton(backText, () -> {
			gm.setUIState(UIState.MAIN_MENU);
		});
		addComponent(back);
	}
	
}
