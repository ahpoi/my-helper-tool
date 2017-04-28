package symmetricencryption;

import utils.EncryptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DecryptText {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the secret key: ");
        String secreteKey = scanner.next().trim();
        EncryptionUtils encryptionUtils = new EncryptionUtils("AES", secreteKey);
        System.out.println("Please enter the values you want to decrypt with ',' if you have more than one value");
        String valuesToEncrypt = scanner.next().trim();
        System.out.println("Encrypted value : Decrypted value: ");
        List<String> values = Arrays.asList(valuesToEncrypt.split("\\s*,\\s*"));
        values.forEach(value -> System.out.println(value + " : " + encryptionUtils.decrypt(value)));
        scanner.close();
    }

}
