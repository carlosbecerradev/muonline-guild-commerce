package com.cbh.muonlineguildcommerce.security.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTProvider {

	private final JKSProvider jksProvider;

	public String generateJWT(Authentication auth) {

		final Date EXPIRATION_TIME = Date.from(Instant.now().plus(1, ChronoUnit.DAYS)); // 1 DAY

		JwtBuilder jws = Jwts.builder()
				.setIssuer("carlosbecerradev")
				.setSubject(auth.getName())
				.setAudience("mu-guild")
				.setExpiration(EXPIRATION_TIME)
				.signWith(jksProvider.getPrivateKey());

		return jws.compact();
	}
}
