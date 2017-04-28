package symmetricencryption;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.util.Base64;

public class GenerateSecretKey {

    public static void main(String[] args) throws Exception {
        Key key = KeyGenerator.getInstance("AES").generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encodedKey);
    }

}
