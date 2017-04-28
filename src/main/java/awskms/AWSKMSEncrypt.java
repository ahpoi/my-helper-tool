package awskms;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.util.Base64;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AWSKMSEncrypt {

    private static AWSKMS CLIENT = AWSKMSClientBuilder.defaultClient();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the AWS arn-key-id: ");
        String keyId = scanner.next().trim();
        System.out.println("Please enter the values you want to encrypt with ',' if you have more than one value: ");

        String valuesToEncrypt = scanner.next().trim();
        System.out.println("Decrypted value : Encrypted value: ");

        List<String> values = Arrays.asList(valuesToEncrypt.split("\\s*,\\s*"));
        values.forEach(value -> System.out.println(value + " : " + encrypt(keyId, value)));
        scanner.close();
    }

    public static String encrypt(String keyId, String valuesToEncrypt) {
        ByteBuffer plaintext = ByteBuffer.wrap(valuesToEncrypt.getBytes());
        EncryptRequest req = new EncryptRequest().withKeyId(keyId).withPlaintext(plaintext);
        ByteBuffer ciphertext = CLIENT.encrypt(req).getCiphertextBlob();

        byte[] base64EncodedValue = Base64.encode(ciphertext.array());
        return new String(base64EncodedValue, Charset.forName("UTF-8"));
    }

}