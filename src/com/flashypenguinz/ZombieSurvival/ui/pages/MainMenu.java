package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.awt.Font;

import org.lwjgl.opengl.Display;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.ui.UIState;
import com.flashypenguinz.ZombieSurvival.ui.components.Image;
import com.flashypenguinz.ZombieSurvival.ui.components.Text;
import com.flashypenguinz.ZombieSurvival.ui.components.TextButton;

public class MainMenu extends Page {

	public MainMenu(GameManager gm) {
		super(true);
		Image logo = new Image((Display.getWidth() / 2) - 256, 30, 512, 512,
				"title");
		TextButton playGame = new TextButton(
				new Text(Display.getWidth() / 2, Display.getHeight() / 2,
						"Play Game", Font.BOLD, 38),
				() -> {
					if(gm.getUser() == null) {
						gm.setUIState(UIState.SIGNUP);
					}
					/**gm.setState(GameState.PLAYING);
					GameManager.paused = false;
					gm.getClient().start();
					String username = JOptionPane
							.showInputDialog("Enter in a username!");
					Packet00Login loginPacket = new Packet00Login(username);
					gm.getClient().sendData(loginPacket.getData());
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
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
					Mouse.setGrabbed(true);*/
				});
		addComponent(logo);
		addComponent(playGame);
	}

}
