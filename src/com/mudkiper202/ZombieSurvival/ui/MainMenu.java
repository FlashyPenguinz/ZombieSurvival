package com.mudkiper202.ZombieSurvival.ui;

import java.awt.Font;

import javax.swing.JOptionPane;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.game.GameState;
import com.mudkiper202.ZombieSurvival.net.packets.Packet00Login;
import com.mudkiper202.ZombieSurvival.net.packets.Packet02EntityChange;

public class MainMenu {

	private Image logo;
	private TextButton playGame;

	public MainMenu(GameManager gm) {
		this.logo = new Image((Display.getWidth() / 2) - 256, 30, 512, 512,
				"title");
		this.playGame = new TextButton(
				new Text(Display.getWidth() / 2, Display.getHeight() / 2,
						"Play Game", Font.BOLD, 38),
				() -> {
					gm.setState(GameState.PLAYING);
					GameManager.paused = false;
					gm.getClient().start();
					String username = JOptionPane
							.showInputDialog("Enter in a username!");
					Packet00Login loginPacket = new Packet00Login(username);
					gm.getClient().sendData(loginPacket.getData());
					try {
						Thread.sleep(3000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					gm.getPlayer().setId(gm.getEntities().size());
					Packet02EntityChange entityChangePacket = new Packet02EntityChange(
							gm.getPlayer().getId(), 0, gm.getPlayer().getX(),
							gm.getPlayer().getY(),
							gm.getPlayer().getRotation(), gm.getPlayer()
									.getTextureAtlas().getTextureName(),
							(int) gm.getPlayer().getTextureCoords().x, (int) gm
									.getPlayer().getTextureCoords().y);
					gm.getClient().sendData(entityChangePacket.getData());
					gm.getEntities().add(gm.getPlayer());
					Mouse.setGrabbed(true);
				});
	}

	public void update() {
		playGame.update();
	}

	public void render() {
		logo.draw();
		playGame.draw();
	}

}
