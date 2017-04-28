package symmetricencryption;

import utils.EncryptionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EncryptText {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the secret key: ");
        String secretKey = scanner.next().trim();
        EncryptionUtils encryptionUtils = new EncryptionUtils("AES", secretKey);
        System.out.println("Please enter the values you want to encrypt with ',' if you have more than one value");
        String valuesToEncrypt = scanner.next().trim();
        System.out.println("Decrypted value : Encrypted value: ");
        List<String> values = Arrays.asList(valuesToEncrypt.split("\\s*,\\s*"));
        values.forEach(value -> System.out.println(value + " : " + encryptionUtils.encrypt(value)));
        scanner.close();
    }

}


