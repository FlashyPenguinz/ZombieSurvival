package com.mudkiper202.ZombieSurvival.player;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.mudkiper202.ZombieSurvival.game.GameManager;
import com.mudkiper202.ZombieSurvival.sound.AudioMaster;
import com.mudkiper202.ZombieSurvival.sound.Source;

public class Gun {

	private Player player;

	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Bullet> toRemoveBullets = new ArrayList<Bullet>();

	private boolean clicked = true;

	private Source soundPlayer;
	private int soundBuffer;
	
	public Gun(Player player) {
		this.player = player;
		this.soundPlayer = new Source();
		this.soundPlayer.setGain(.25f);
		this.soundBuffer = AudioMaster.loadSound("gunshot");
	}

	public void update() {
		if (Mouse.isButtonDown(0) && clicked == false) {
			makeBullet();
			clicked = true;
		}
		
		if (!Mouse.isButtonDown(0))
			clicked = false;
		
		if(GameManager.paused == true)
			clicked = true;
		
		for (Bullet bullet : bullets)
			bullet.update();
		for (Bullet bullet : toRemoveBullets)
			bullets.remove(bullet);
		toRemoveBullets.clear();
	}

	public void drawBullets() {
		for (Bullet bullet : bullets)
			bullet.draw();
	}

	private void makeBullet() {
		Bullet bullet = new Bullet(player, player.getX(), player.getY(),
				player.getRotation(), toRemoveBullets);
		bullets.add(bullet);
		
		soundPlayer.play(soundBuffer);
	}
}
