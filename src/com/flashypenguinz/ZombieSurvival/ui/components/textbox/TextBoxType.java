package com.flashypenguinz.ZombieSurvival.ui.components.textbox;

public enum TextBoxType {

	USERNAME("[A-Za-z0-9\\_]"),
	EMAIL("[A-Za-z0-9\\$\\&\\+\\,\\:\\;\\=\\?\\@\\#\\|\\'\\<\\>\\.\\-\\_\\^\\*\\(\\)\\%\\!]"), 
	PASSWORD("[A-Za-z0-9\\$\\&\\+\\,\\:\\;\\=\\?\\@\\#\\|\\'\\<\\>\\.\\-\\_\\^\\*\\(\\)\\%\\!]");

	private String regex;

	TextBoxType(String regex) {
		this.regex = regex;
	}

	public String getRegex() {
		return regex;
	}

}
