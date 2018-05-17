package com.flashypenguinz.ZombieSurvival;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.flashypenguinz.ZombieSurvival.game.GameManager;
import com.flashypenguinz.ZombieSurvival.helpers.Artist;
import com.flashypenguinz.ZombieSurvival.net.packets.Packet01Disconnect;
import com.flashypenguinz.ZombieSurvival.sound.AudioMaster;

public class MainGameLoop {

	public MainGameLoop() {
		Artist.createDisplay();
		try {
			Mouse.create();
			Keyboard.create();
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
		
		gm.cleanUp();
		AudioMaster.cleanUp();
		Mouse.destroy();
		Keyboard.destroy();
		Artist.destroyDisplay();
	}

	public static void main(String[] args) {
		new MainGameLoop();
	}

}
