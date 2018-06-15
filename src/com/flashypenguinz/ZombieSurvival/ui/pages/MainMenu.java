package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.game.GameState;
import com.flashypenguinz.ZombieSurvival.net.entities.NetPlayer;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet00Login;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet02PlayerChange;
import com.flashypenguinz.ZombieSurvival.ui.UIState;
import com.flashypenguinz.ZombieSurvival.ui.components.Image;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;
import com.flashypenguinz.ZombieSurvival.ui.components.TextButton;

public class MainMenu extends Page {

	public MainMenu(GameManager gm) {
		super(true);
		Image logo = new Image((Display.getWidth() / 2) - 256, 30, 512, 512,
				"title");
		Text welcome = null;
		if(gm.user != null) {
			welcome = new Text(Display.getWidth()-120, 18, "Welcome, "+gm.user.getUsername(), Font.BOLD, 18);
			welcome.setColor(Color.gray);
		}
		TextButton playGame = new TextButton(
				new Text(Display.getWidth() / 2, Display.getHeight() / 2,
						"Play Game", Font.BOLD, 38),
				() -> {
					if(gm.user == null) {
						gm.setUIState(UIState.SIGNUP);
					} else if(gm.user.getUserLevel() == 0) {
						//gm.setUIState(UIState.VERIFY);
					//} else {
						GameManager.paused = false;
						gm.getClient().start();
						String username = gm.user.getUsername();
						Packet00Login loginPacket = new Packet00Login(username);
						gm.getClient().sendData(loginPacket.getData());
						while(true) {
							System.out.print("");
							if(gm.getClient().gotMap) 
								break;
						}
						gm.getPlayer().setId(
								gm.getEntityManager().getLatestEntityId()+1);
						gm.getPlayer().setUsername(username);
						Packet02PlayerChange changePacket = new Packet02PlayerChange(
								0, gm.getPlayer().getId(), username, gm.getPlayer()
								.getX(), gm.getPlayer().getY(), gm
								.getPlayer().getTextureAtlas()
								.getTextureName(), (int) gm.getPlayer()
								.getTextureCoords().x, (int) gm.getPlayer()
								.getTextureCoords().y);
						gm.getClient().sendData(changePacket.getData());
						gm.getEntityManager().addEntity(
								new NetPlayer(gm.getPlayer().getId(), username, gm
										.getPlayer().getX(), gm.getPlayer().getY(),
										gm.getPlayer().getTextureAtlas()
										.getTextureName(), (int) gm
										.getPlayer().getTextureCoords().x,
										(int) gm.getPlayer().getTextureCoords().y));
						gm.getClient().connected = true;
						gm.setState(GameState.PLAYING);
						Mouse.setGrabbed(true);
					}
				});
		addComponent(logo);
		addComponent(playGame);
		if(welcome != null)
			addComponent(welcome);
	}

}
