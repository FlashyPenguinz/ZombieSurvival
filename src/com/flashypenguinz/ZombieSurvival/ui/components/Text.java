package com.flashypenguinz.ZombieSurvival.ui.components;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import com.flashypenguinz.ZombieSurvival.game.GameConstants;

public class Text implements Component{

	private String text;
	private float x, y;
	
	private TrueTypeFont font;
	private Font awtFont;
	private Color color = Color.white;
	
	public Text(float x, float y, String text, int font, int fontSize) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.awtFont = new Font(GameConstants.GAME_FONT, font, fontSize);
		this.font = new TrueTypeFont(awtFont, true);
	}
	
	@Override
	public void update() {}
	
	@Override
	public void draw() {
		float textWidth = font.getWidth(text);
		float textHeight = font.getHeight(text);
		font.drawString(x-(textWidth/2), y-(textHeight/2), text, color);
		GL11.glColor3f(1, 1, 1);
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setTextSize(int size) {
		String font = awtFont.getFontName();
		int fontType = awtFont.getStyle();
		this.awtFont = new Font(font, fontType, size);
		this.font = new TrueTypeFont(awtFont, true);
	}
	
	public int getTextSize() {
		return this.awtFont.getSize();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public float getX() {
		return x-(font.getWidth(text)/2);
	}
	
	public float getY() {
		return y-(font.getHeight(text)/2);
	}
	
	public String getText() {
		return text;
	}
	
	public TrueTypeFont getFont() {
		return font;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
}
