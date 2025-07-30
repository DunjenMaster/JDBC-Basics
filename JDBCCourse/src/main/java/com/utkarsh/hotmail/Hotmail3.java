package com.utkarsh.hotmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class Hotmail3 {
    private KeyStore loadKeyStore(StringBuilder outPath) throws IOException, GeneralSecurityException {
        String relativeCacertsPath = "/lib/security/cacerts".replace("/", File.separator);
        String filename = System.getProperty("java.home") + relativeCacertsPath;
        outPath.append(filename);

        try (FileInputStream is = new FileInputStream(filename)) {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "changeit";
            keystore.load(is, password.toCharArray());
            return keystore;
        }
    }

    public static void main(String[] args) {
        Hotmail3 loader = new Hotmail3();
        StringBuilder keystorePath = new StringBuilder();
        try {
            KeyStore ks = loader.loadKeyStore(keystorePath);
            System.out.println("Keystore loaded from: " + keystorePath);
            Enumeration<String> aliases = ks.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                System.out.println("Alias: " + alias);
                Certificate cert = ks.getCertificate(alias);
                if (cert != null) {
                    System.out.println("Certificate:\n" + cert.toString());
                } else {
                    System.out.println("No certificate for alias.");
                }
            }
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}