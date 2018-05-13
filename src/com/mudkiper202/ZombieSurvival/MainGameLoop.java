package com.mudkiper202.ZombieSurvival;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.helpers.Artist;
import com.mudkiper202.ZombieSurvival.net.packets.Packet01Disconnect;
import com.mudkiper202.ZombieSurvival.sound.AudioMaster;

public class MainGameLoop {

	public MainGameLoop() {
		Artist.createDisplay();
		try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		AudioMaster.create();
		AudioMaster.setListenerData();

		GameManager gm = new GameManager();

		while (!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			
			gm.update();
			gm.draw();
			
			Artist.updateDisplay();
		}

		if(gm.getClient().connected)
			gm.getClient().sendData(new Packet01Disconnect().getData());
		
		AudioMaster.cleanUp();
		Mouse.destroy();
		Artist.destroyDisplay();
	}

	public static void main(String[] args) {
		new MainGameLoop();
	}

}
