package com.github.queerzard.pixieoffice.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static byte[] objectToBytes(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static int getRadialDistance(int fromX, int fromY, int toX, int toY) {
        // Calculate the differences in X and Y coordinates
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;

        // Use the Pythagorean theorem to calculate the distance
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Return the distance as an integer
        return (int) Math.round(distance);
    }

    public static Object parseObject(byte[] data) {
        Object object;
        try {
            object = new ObjectInputStream(new ByteArrayInputStream(data)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return object;
    }


    public static byte[] encryptBytes(String password, byte[] plainBytes) throws Exception {
        Key encryptionKey = new SecretKeySpec((password).getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        return encryptedBytes;
    }

    public static byte[] decryptBytes(String password, byte[] encryptedBytes) throws Exception {
        Key encryptionKey = new SecretKeySpec((password).getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, encryptionKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return decryptedBytes;
    }

    private static String hash(String hashType, String inputHash) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance(hashType);
        messageDigest.update(inputHash.getBytes());

        byte[] digest = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte byt : digest)
            stringBuffer.append(String.format("%02x", byt & 0xff));
        return stringBuffer.toString();
    }

    @SneakyThrows
    public static String md5(String input) {
        return hash("MD5", input);
    }

    @SneakyThrows
    public static String sha1(String input) {
        return hash("SHA-1", input);
    }

    @SneakyThrows
    public static String sha256(String input) {
        return hash("SHA-256", input);
    }

    @SneakyThrows
    public static String sha384(String input) {
        return hash("SHA-384", input);
    }

    @SneakyThrows
    public static String sha512(String input) {
        return hash("SHA-512", input);
    }


}
