package com.rdw.library.fileencryptdecrypt;

import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/*** Created by RAHUL D. WADHAI on 7/25/2017 ***/

public class AesFileEncryptAndDecrypt {

    /**
     * Here is Both function for encrypt and decrypt file in Sdcard folder. we
     * can not lock folder but we can encrypt file using AES in Android, it may
     * help you.
     *
     * @param algorithm      "AES"
     * @param transformation "AES/ECB/PKCS5Padding"
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */

    public static void encrypt(InputStream in, OutputStream out, String key, String algorithm, String transformation)
            throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] decodedKey = Base64.decode(key, Base64.DEFAULT);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, originalKey);
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        int b;
        byte[] d = new byte[4096];
        while ((b = in.read(d)) != -1) {
            cos.write(d, 0, b);
        }
        // Flush and close streams.
        cos.flush();
        cos.close();
        in.close();

    }

    /**
     * @param algorithm      "AES"
     * @param transformation "AES/ECB/PKCS5Padding"
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static void decrypt(InputStream in, OutputStream out, String key, String algorithm, String transformation)
            throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException{
        byte[] decodedKey = Base64.decode(key, Base64.DEFAULT);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, originalKey);
        CipherInputStream cis;
        cis = new CipherInputStream(in, cipher);
        int b;
        byte[] d = new byte[4096];
        while ((b = cis.read(d)) != -1) {
            out.write(d, 0, b);
        }
        // Flush and close streams.
        out.flush();
        out.close();
        cis.close();
    }

    /**
     * @key Secret key (Use following length in key 16,24 and 32) you can use your own key here.
     * @algorithm the name of the secret-key algorithm. AES
     */
    public static String secretKey(String key, String algorithm) {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), algorithm);
        return Base64.encodeToString(secretKey.getEncoded(), Base64.DEFAULT);
    }
}
