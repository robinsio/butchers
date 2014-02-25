package org.robins.io.butchers.web.security;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaltedSHA256PasswordEncoder implements PasswordEncoder {

	private final String salt;

	private final MessageDigest digest;

	public SaltedSHA256PasswordEncoder(String salt) throws NoSuchAlgorithmException {

		this.salt = salt;
        this.digest = MessageDigest.getInstance("SHA-256");
	}

	@Override
	public String encode(CharSequence rawPassword) {

		String saltedPassword = rawPassword + this.salt;
		try {
			return new String(Hex.encode(this.digest.digest(saltedPassword.getBytes("UTF-8"))));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 not supported");
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {

		return this.encode(rawPassword).equals(encodedPassword);
	}
}