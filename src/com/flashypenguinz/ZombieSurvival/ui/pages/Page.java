package com.flashypenguinz.ZombieSurvival.ui.pages;

import java.util.ArrayList;
import java.util.List;

import com.flashypenguinz.ZombieSurvival.ui.components.Component;

public class Page {

	private boolean visible;
	
	private List<Component> components;
	
	public Page() {
		this.visible = false;
		this.components = new ArrayList<Component>();
	}
	
	public Page(boolean visible) {
		this.visible = visible;
		this.components = new ArrayList<Component>();
	}
	
	public void update() {
		for(Component component: components)
			component.update();
	}
	
	public void draw() {
		for(Component component: components)
			component.draw();
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
