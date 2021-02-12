package com.cbh.muonlineguildcommerce.security.provider;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class JKSProvider {

	private final String JKS_NAME = "exampleJKS";
	private final String JKS_PATH = "/" + JKS_NAME + ".jks";
	private final String JKS_KEY = "secret";
	private KeyStore keyStore;

	@PostConstruct
	public void init() {
		try {
			keyStore = KeyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream(JKS_PATH);
			keyStore.load(resourceAsStream, "secret".toCharArray());
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
			throw new RuntimeException("Exception ocurred while loading keystore.");
		}
	}

	public PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey(JKS_NAME, JKS_KEY.toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new RuntimeException("Exception ocurred while retrieving private key from keystore.");
		}
	}

	public PublicKey getPublicKey() {
		try {
			return keyStore.getCertificate(JKS_NAME).getPublicKey();
		} catch (KeyStoreException e) {
			throw new RuntimeException("Exception ocurred while retrieving public key from keystore.");
		}
	}
}
