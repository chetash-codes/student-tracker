package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashUtil {
	
	// Generate a salted hash from a plain password
	public static String hashPassword(String password) {
		byte[] salt = generateSalt();
		String saltBase64 = Base64.getEncoder().encodeToString(salt);
		String hash = sha512(password, salt);
		return saltBase64 + ":" + hash;
	}
	
	// Verify a plain password against a stored salted hash
	public static boolean verifyPassword(String password, String stored) {
		String[] parts = stored.split(":");
		if (parts.length != 2) return false;
		
		byte[] salt = Base64.getDecoder().decode(parts[0]);
		String hash = sha512(password, salt);
		return hash.equals(parts[1]);
	}
	
	// Internal method to hash with SHA-512
	private static String sha512(String password, byte[] salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] hashed = md.digest(password.getBytes());
			return Base64.getEncoder().encodeToString(hashed);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA not available", e);
		}
	}
	
	// Generate a random salt
	private static byte[] generateSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

}
