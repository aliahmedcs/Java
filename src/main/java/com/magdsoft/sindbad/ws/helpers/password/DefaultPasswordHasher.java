package com.magdsoft.sindbad.ws.helpers.password;

public class DefaultPasswordHasher extends HashPassword {
	private static DefaultPasswordHasher instance = new DefaultPasswordHasher();
	
	public static DefaultPasswordHasher getInstance() {
		return instance;
	}
	
	private DefaultPasswordHasher() {
	}
}
