package com.mudkiper202.ZombieSurvival.ui;

import java.awt.Font;

public class TextBox {

	private float x, y;
	private float width, height;
	private float r, g, b;
	
	private Text textModule;
	
	private String text = "";
	
	public TextBox(float x, float y, float width, float height, float r, float b, float g) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r = r;
		this.g = g;
		this.b = b;
		this.textModule = new Text(x+2, y+2, "", Font.PLAIN, 12);
	}
	
}
