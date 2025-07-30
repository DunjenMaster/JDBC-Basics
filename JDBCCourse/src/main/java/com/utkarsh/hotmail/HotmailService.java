package com.utkarsh.hotmail;

import org.junit.Test;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;

public class HotmailService {
    @Test
    public void whenLoadingDefaultKeyStore_thenCertificatesArePresent() throws NoSuchAlgorithmException, KeyStoreException {
        // Print the trust store path
        String trustStorePath = System.getProperty("javax.net.ssl.trustStore");
        if (trustStorePath == null) {
            String javaHome = System.getProperty("java.home");
            trustStorePath = javaHome + "\\lib\\security\\cacerts";
        }
        System.out.println("Trust store path: " + trustStorePath);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);

        List<TrustManager> trustManagers = Arrays.asList(trustManagerFactory.getTrustManagers());
        List<X509Certificate> certificates = trustManagers.stream()
                .filter(X509TrustManager.class::isInstance)
                .map(X509TrustManager.class::cast)
                .map(trustManager -> Arrays.asList(trustManager.getAcceptedIssuers()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        certificates.forEach(cert -> System.out.println("Trusted certificate subject: " + cert.getSubjectX500Principal().getName()));

        assertFalse(certificates.isEmpty());
    }
}