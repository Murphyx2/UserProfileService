package com.app.userprofile.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey;

	public String getEmailFromToken(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.get("email", String.class);
	}

	public String getIdFromToken(String token) {
		return Jwts.parser()
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.get("id", String.class);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
					.verifyWith( getSigningKey())
					.build()
					.parseSignedClaims(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public String generateMockToken(String email) {
		return Jwts.builder()
				.claim("email", email)  // Use "email" claim
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 900000)) // 15 minutes
				.signWith(getSigningKey())
				.compact();
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}
