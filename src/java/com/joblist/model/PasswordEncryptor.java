/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author esa
 */
public class PasswordEncryptor {
    private final static byte[] KEY = new byte[] 
    {
        -102, 9, -118, 106, 5, 21, -124, -124, 
        -53, -6, -63, -56, 45, 24 ,28 ,-60
    };
    
    private static byte[] addSalt(byte[] b, byte[] salt) {
        byte[] result = Arrays.copyOf(b, b.length + salt.length);
        System.arraycopy(salt, 0, result, b.length, salt.length);
        return result;
    }    
    
    public static byte[][] encrypt(String password, SecureRandom random) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(KEY, "AES");
        byte[] IVBytes = new byte[16];
        byte[] salt = new byte[random.nextInt(30) + 20];
        random.nextBytes(IVBytes);
        random.nextBytes(salt);
         
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IVBytes));
        byte[] bytePassword = password.getBytes("UTF-8");
        byte[] cipherBytes = addSalt(bytePassword, salt);
        return new byte[][]
        {
            cipher.doFinal(cipherBytes),
            IVBytes,
            salt
        };
     }
    
    public static boolean match(String password, byte[] cipherBytes, byte[] IVBytes, byte[] salt) 
            throws GeneralSecurityException, UnsupportedEncodingException
    {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(KEY, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IVBytes));
        String decrypted = new String(cipher.doFinal(cipherBytes), 0, password.length(), "UTF-8");
        return decrypted.compareTo(password) == 0;
    }
}
