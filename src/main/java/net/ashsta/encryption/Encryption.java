package net.ashsta.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.regex.Pattern;

public class Encryption {

    private static final Pattern BASE_64_PATTERN = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");

    private static EncryptionSettings encryptionSettings = EncryptionSettings.DEFAULT;

    public static void setAlgorithm(EncryptionSettings.Algorithm algorithm) {
        encryptionSettings = new EncryptionSettings(algorithm, encryptionSettings.mode(), encryptionSettings.padding());
    }

    public static void setMode(EncryptionSettings.Mode mode) {
        encryptionSettings = new EncryptionSettings(encryptionSettings.algorithm(), mode, encryptionSettings.padding());
    }

    public static void setPadding(EncryptionSettings.Padding padding) {
        encryptionSettings = new EncryptionSettings(encryptionSettings.algorithm(), encryptionSettings.mode(), padding);
    }

    public static void setSettings(EncryptionSettings settings) {
        encryptionSettings = settings;
    }

    public static EncryptionSettings getSettings() {
        return encryptionSettings;
    }

    public static String encryptToBase64(byte[] data, byte[] key) {
        if (data == null || data.length == 0)
            return null;
        byte[] encryptBytes = performCipherAction(Cipher.ENCRYPT_MODE, data, key);
        return encryptBytes == null ? null : Base64.getEncoder().encodeToString(encryptBytes);
    }

    public static String decryptFromBase64(String base64, byte[] key) {
        if (base64 == null || base64.length() == 0 || !BASE_64_PATTERN.matcher(base64).matches())
            return null;
        byte[] encryptedBytes = Base64.getDecoder().decode(base64);
        if (encryptedBytes.length % encryptionSettings.getKeyLength() != 0)
            return null;
        byte[] decryptedBytes = performCipherAction(Cipher.DECRYPT_MODE, encryptedBytes, key);
        return decryptedBytes == null ? null : new String(decryptedBytes);
    }

    private static byte[] createFinalKey(byte[] key) {
        int keyLengthInBytes = encryptionSettings.getKeyLength();
        if (key.length == keyLengthInBytes)
            return key;
        byte[] finalKey = new byte[keyLengthInBytes];
        System.arraycopy(key, 0, finalKey, 0, Math.min(key.length, keyLengthInBytes));
        return finalKey;
    }

    private static byte[] performCipherAction(int cipherMode, byte[] data, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(encryptionSettings.getTransformation());
            SecretKeySpec secretKeySpec = new SecretKeySpec(createFinalKey(key), encryptionSettings.algorithm().toString());
            cipher.init(cipherMode, secretKeySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            return null;
        }
    }
}
