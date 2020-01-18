package com.camel.telegram.bot;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SslContext {
    public static SSLContext getTrustfulSSlContext() {
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, getTrustManager(), null);
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new IllegalStateException("JAX-RS (SSL) client initialization exception ", ex);
        }
        return sc;
    }

    private static TrustManager[] getTrustManager() {
        return new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] xcs, final String string) {
                    //intentionally left empty
                }

                @Override
                public void checkServerTrusted(final X509Certificate[] xcs, final String string) {
                    //intentionally left empty
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
        };
    }
}
