package com.flashypenguinz.ZombieSurvival.net.entities;

import java.awt.Font;

import org.newdawn.slick.Color;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;

public class NetPlayer extends NetEntity {

	private Text username;
	private String usernameText;
	
	public NetPlayer(int id, String username, float x, float y, String textureName, int texX, int texY) {
		super(id, x, y, textureName, texX, texY, 0);
		this.usernameText = username;
	}
	
	@Override
	public void draw(float x, float y) {
		super.draw(x, y);
		if(this.username == null) {
			this.username = new Text(x, y-GameConstants.USERNAME_Y, usernameText, Font.BOLD, GameConstants.USERNAME_SIZE);
			this.username.setColor(Color.white);
		}
		this.username.setX(x);
		this.username.setY(y-GameConstants.USERNAME_Y);
		this.username.draw();
	}
	
	public String getUsername() {
		return usernameText;
	}
	
	public void setUsername(String username) {
		this.username.setText(username);
	}
	
}
