package com.microservice.noteservice.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;
import java.util.Base64;

public class CryptoUtils {

    public static String decryptDataWithAES(String encryptedDataBase64, String aesKeyBase64) throws Exception {
        byte[] encryptedData = Base64.getDecoder().decode(encryptedDataBase64);
        byte[] aesKey = Base64.getDecoder().decode(aesKeyBase64);

        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] decrypted = cipher.doFinal(encryptedData);
        return new String(decrypted);
    }

    public static String decryptAESKeyWithRSA(String encryptedKeyBase64, PrivateKey privateKey) throws Exception {
        byte[] encryptedKey = Base64.getDecoder().decode(encryptedKeyBase64);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedKey = cipher.doFinal(encryptedKey);
        return Base64.getEncoder().encodeToString(decryptedKey);
    }
}
