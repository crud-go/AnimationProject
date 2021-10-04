package com.test.animation.logic.Utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class ssl extends SSLSocketFactory {
    private SSLSocketFactory tB;

    public ssl() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, new TrustManager[] { systemDefaultTrustManager() }, null);
        this.tB = sSLContext.getSocketFactory();
    }

    private Socket a(Socket paramSocket) {
        if (paramSocket != null && paramSocket instanceof SSLSocket)
            ((SSLSocket)paramSocket).setEnabledProtocols(new String[] { "TLSv1.1", "TLSv1.2" });
        return paramSocket;
    }

    public Socket createSocket(String paramString, int paramInt) throws IOException, UnknownHostException {
        return a(this.tB.createSocket(paramString, paramInt));
    }

    public Socket createSocket(String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2) throws IOException, UnknownHostException {
        return a(this.tB.createSocket(paramString, paramInt1, paramInetAddress, paramInt2));
    }

    public Socket createSocket(InetAddress paramInetAddress, int paramInt) throws IOException {
        return a(this.tB.createSocket(paramInetAddress, paramInt));
    }

    public Socket createSocket(InetAddress paramInetAddress1, int paramInt1, InetAddress paramInetAddress2, int paramInt2) throws IOException {
        return a(this.tB.createSocket(paramInetAddress1, paramInt1, paramInetAddress2, paramInt2));
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException {
        return a(this.tB.createSocket(paramSocket, paramString, paramInt, paramBoolean));
    }

    public String[] getDefaultCipherSuites() {
        return this.tB.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.tB.getSupportedCipherSuites();
    }

    public X509TrustManager systemDefaultTrustManager() {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore)null);
            TrustManager[] arrayOfTrustManager = trustManagerFactory.getTrustManagers();
            if (arrayOfTrustManager.length == 1 && arrayOfTrustManager[0] instanceof X509TrustManager)
                return (X509TrustManager)arrayOfTrustManager[0];
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unexpected default trust managers:");
            stringBuilder.append(Arrays.toString((Object[])arrayOfTrustManager));
            throw new IllegalStateException(stringBuilder.toString());
        } catch (GeneralSecurityException generalSecurityException) {
            throw new AssertionError();
        }
    }
}
