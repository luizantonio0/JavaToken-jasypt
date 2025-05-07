

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.BasicTokenEncryptor;
import com.Payload;

public class Main {
    public static void main(String[] args) {
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("email", "Luiz@email.com");
        payloadMap.put("nickname", "Luiz");
        
        Payload payload = new Payload(payloadMap);

        BasicTokenEncryptor encryptor = new BasicTokenEncryptor("password", 1);
        String token = encryptor.newToken(payload, LocalDateTime.of(2025, 5, 7, 11, 0));
        System.out.println("Token: " + token);
        
        String descryptedToken = encryptor.decrypt(token);
        System.out.println("Decrypted Token: " + descryptedToken);

        // List<String> tokens = Arrays.asList(descryptedToken.split("\\+"));
        System.out.println(encryptor.verify(descryptedToken, payload, 10));
        

        // Map<String, String> decryptedMap = new HashMap<>();

        // for (String tokenPart : tokens) {
        //     String[] keyValue = tokenPart.split("=");
        //     if (keyValue.length == 2) {
        //     decryptedMap.put(keyValue[0], keyValue[1]);
        //     }
        // }
        
        // System.out.println("Decrypted Map: " + decryptedMap);
        // System.out.println(payload.getPayload().equals(decryptedMap));
        
    }
}
