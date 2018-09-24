package com.magdsoft.sindbad.ws.helpers.password;

public interface PasswordHasher {
	 public String hashPassword(String password);
	 public boolean isPasswordValid(String password, String hash);

	 
}
