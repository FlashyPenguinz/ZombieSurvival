package com.mudkiper202.ZombieSurvival.sound;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

import com.mudkiper202.ZombieSurvival.game.GameConstants;

public class AudioMaster {

	public static List<Integer> buffers = new ArrayList<Integer>();
	public static List<Integer> sources = new ArrayList<Integer>();
	
	public static void create() {
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setListenerData() {
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static int loadSound(String name) {
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveData waveFile = null;
		try {
			waveFile = WaveData.create(new BufferedInputStream(new FileInputStream(GameConstants.SOUNDS_FOLDER+name+".wav")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
	}
	
	public static void cleanUp() {
		for(int buffer: buffers)
			AL10.alDeleteBuffers(buffer);
		for(int source: sources)
			AL10.alDeleteSources(source);
		AL.destroy();
	}
	
}
