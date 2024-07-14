package com.quiz.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Securitit.
 * @note RC4加密算法实现.
 */
public class RC4Util {

    /**
     * 对文本内容进行加密.
     *
     * @param plainText 待加密明文内容.
     * @param rc4Key    RC4密钥.
     * @return 加密的密文.
     */
    public static String encodeText(String plainText, String rc4Key) {
        try {
            byte[] plainTextBytes = plainText.getBytes();
            byte[] rc4KeyBytes = rc4Key.getBytes();
            return Base64.encodeBase64String(rc4(plainTextBytes, rc4KeyBytes));
        } catch (Exception ex) {
            System.out.println("参数有空!!");
            return "";
        }
    }

    /**
     * 对文本密文进行解密.
     *
     * @param cipherText 待解密密文.
     * @param rc4Key     RC4密钥.
     * @return 解密的明文.
     */
    public static String decodeText(String cipherText, String rc4Key) {
        try {
            byte[] cipherTextBytes = Base64.decodeBase64(cipherText);
            byte[] rc4KeyBytes = rc4Key.getBytes();
            return new String(rc4(cipherTextBytes, rc4KeyBytes));
        } catch (Exception ex) {
            System.out.println("参数有空!!");
            return "";
        }
    }

    /**
     * 对字节数组密文进行加密/解密.
     *
     * @param data The data.
     * @param key  The key.
     * @return RC4 加密/解密字节数组
     */
    public static byte[] rc4(byte[] data, byte[] key) {
        if (data == null || data.length == 0 || key == null) return null;
        if (key.length < 1 || key.length > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        }
        final byte[] iS = new byte[256];
        final byte[] iK = new byte[256];
        int keyLen = key.length;
        for (int i = 0; i < 256; i++) {
            iS[i] = (byte) i;
            iK[i] = key[i % keyLen];
        }
        int j = 0;
        byte tmp;
        for (int i = 0; i < 256; i++) {
            j = (j + iS[i] + iK[i]) & 0xFF;
            tmp = iS[j];
            iS[j] = iS[i];
            iS[i] = tmp;
        }

        final byte[] ret = new byte[data.length];
        int i = 0, k, t;
        for (int counter = 0; counter < data.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + iS[i]) & 0xFF;
            tmp = iS[j];
            iS[j] = iS[i];
            iS[i] = tmp;
            t = (iS[i] + iS[j]) & 0xFF;
            k = iS[t];
            ret[counter] = (byte) (data[counter] ^ k);
        }
        return ret;
    }
}
