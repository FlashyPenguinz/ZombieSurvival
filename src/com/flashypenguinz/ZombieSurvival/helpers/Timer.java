package com.flashypenguinz.ZombieSurvival.helpers;

import org.lwjgl.Sys;

public class Timer {

	public static long lastFrame;
	public static float delta;
	
	public static long getCurrentTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
}
