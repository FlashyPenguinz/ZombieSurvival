package com.flashypenguinz.ZombieSurvival.helpers;

import org.lwjgl.Sys;

public class Timer {

	public static long lastFrame;
	public static float delta;
	
	public static long getCurrentTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static void calculateDelta() {
		long currentFrameTime = Timer.getCurrentTime();
		Timer.delta = (currentFrameTime - Timer.lastFrame)/1000f;
		Timer.lastFrame = currentFrameTime;
	}
	
}
