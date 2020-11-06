package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Secure {

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
        MessageDigest messageDigest;
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
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while( md5Hex.length() < 32 ){
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }

    public static String getHash() {
        return secureRandom();
    }
}
