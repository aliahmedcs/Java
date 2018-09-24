package com.magdsoft.sindbad.ws.helpers.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashPassword implements PasswordHasher{

	static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

	@Override
	public String hashPassword(String password) {
		return encoder.encode(password);
	}

	@Override
	public boolean isPasswordValid(String password, String hash) {
		return encoder.matches(password, hash);
	}

}
