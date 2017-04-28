package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {

    private Key key;

    private Cipher cipher;

    public EncryptionUtils(String algorithm, String encodedKey) {
        this.key = new SecretKeySpec(Base64Utils.decodeString(encodedKey), algorithm);
        this.cipher = doCipherGetInstance(algorithm);
    }

    public String encrypt(String input) {
        doCipherInit(Cipher.ENCRYPT_MODE, key);
        return Base64Utils.encodeString(doCipherFinal(input.getBytes()));
    }

    public String decrypt(String input) {
        doCipherInit(Cipher.DECRYPT_MODE, key);
        return new String(doCipherFinal(Base64Utils.decodeString(input)));
    }

    private void doCipherInit(int i, Key key) {
        try {
            cipher.init(i, key);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid Key: " + e.getMessage());
        }
    }

    private byte[] doCipherFinal(byte[] input) {
        try {
            return cipher.doFinal(input);
        } catch (BadPaddingException e) {
            throw new RuntimeException("Bad Padding: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("Illegal Block Size: " + e.getMessage());
        }
    }

    private Cipher doCipherGetInstance(String algorithm) {
        try {
            return Cipher.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No Such Algorithm: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("No Such Padding: " + e.getMessage());
        }
    }
}
