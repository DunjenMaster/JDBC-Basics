package com.utkarsh.hotmail;

import org.junit.Test;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

public class Hotmail4 {
    @Test
    public void whenLoadingCacertsKeyStore_thenCertificatesArePresent() throws InvalidAlgorithmParameterException, KeyStoreException {
        KeyStore keyStore = loadKeyStore();
        PKIXParameters params = new PKIXParameters(keyStore);

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .collect(Collectors.toList());

        assertFalse(certificates.isEmpty());
    }

    private KeyStore loadKeyStore() throws KeyStoreException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try (var inputStream = getClass().getClassLoader().getResourceAsStream("cacerts")) {
                if (inputStream == null) {
                    throw new IllegalArgumentException("Keystore file not found");
                }
                keyStore.load(inputStream, "changeit".toCharArray());
            }
            return keyStore;
        } catch (Exception e) {
            throw new KeyStoreException("Failed to load keystore", e);
        }
    }
}