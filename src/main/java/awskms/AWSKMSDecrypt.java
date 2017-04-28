package awskms;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.util.Base64;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AWSKMSDecrypt {

    private static AWSKMS AWS_CLIENT = AWSKMSClientBuilder.defaultClient();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the values you want to decrypt with ',' if you have more than one value: ");
        String valuesToEncrypt = scanner.next().trim();
        List<String> values = Arrays.asList(valuesToEncrypt.split("\\s*,\\s*"));
        values.forEach(value -> System.out.println(value + " : " + decrypt(value)));
        scanner.close();
    }

    private static String decrypt(String cipherText) {
        final byte[] cipherTextBytes = Base64.decode(cipherText);
        DecryptRequest req = new DecryptRequest()
                .withCiphertextBlob(ByteBuffer.wrap(cipherTextBytes));
        ByteBuffer plainText = AWS_CLIENT.decrypt(req).getPlaintext();
        return new String(plainText.array(), StandardCharsets.UTF_8);
    }

}
