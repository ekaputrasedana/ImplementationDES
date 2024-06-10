package com.example.implementationdes;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;

public class DESUtil {
    private static final String ALGORITHM = "DES";

    // Method untuk menghasilkan kunci DES
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(56); // DES menggunakan ukuran kunci 56 bit
        return keyGen.generateKey();
    }

    // Method untuk mengenkripsi data
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.encodeToString(encryptedData, Base64.DEFAULT);
    }

    // Method untuk mendekripsi data
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.decode(encryptedData, Base64.DEFAULT);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    // Method untuk mendapatkan kunci dari string (misalnya dari SharedPreferences)
    public static SecretKey getKeyFromString(String keyString) {
        byte[] encodedKey = Base64.decode(keyString, Base64.DEFAULT);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, ALGORITHM);
    }

    // Method untuk mengubah kunci ke string (misalnya untuk disimpan ke SharedPreferences)
    public static String getStringFromKey(SecretKey key) {
        byte[] encodedKey = key.getEncoded();
        return Base64.encodeToString(encodedKey, Base64.DEFAULT);
    }
}
