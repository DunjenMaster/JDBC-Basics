package com.utkarsh.hotmail;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

public class HotmailService2 {
    private KeyStore loadKeyStore() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        String relativeCacertsPath = "/lib/security/cacerts".replace("/", File.separator);
        String filename = System.getProperty("java.home") + relativeCacertsPath;
        System.out.println("Loading keystore from: " + filename); // Print statement added
        FileInputStream is = new FileInputStream(filename);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        String password = "changeit";
        keystore.load(is, password.toCharArray());

        return keystore;
    }

    @Test
    public void whenLoadingCacertsKeyStore_thenCertificatesArePresent() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyStore keyStore = loadKeyStore();
        PKIXParameters params = new PKIXParameters(keyStore);

        Set<TrustAnchor> trustAnchors = params.getTrustAnchors();
        List<Certificate> certificates = trustAnchors.stream()
                .map(TrustAnchor::getTrustedCert)
                .collect(Collectors.toList());

        assertFalse(certificates.isEmpty());
    }
}