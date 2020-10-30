package org.example;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Secure {
    private static byte[] hash;

    public static String ScannerPassword() {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader("src/main/resources/login.txt"));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String password = "";
        try {
            assert input != null;
            password = input.readLine();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return password;
    }

    private static String secureRandom() {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(ScannerPassword().getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    public static String getHash() {
        return secureRandom();
    }
}
