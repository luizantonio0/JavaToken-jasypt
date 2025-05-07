

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.BasicTokenEncryptor;
import com.Payload;
import com.TokenStorage;

public class Main {
    public static void main(String[] args) {
        Map<String, String> payloadMap = new HashMap<>();
        payloadMap.put("email", "Luiz@email.com");
        payloadMap.put("nickname", "Luiz");
        
        Payload payload = new Payload(payloadMap);

        BasicTokenEncryptor encryptor = new BasicTokenEncryptor("password", 1);
        String token = encryptor.newToken(payload, LocalDateTime.of(2025, 5, 7, 11, 0));
        System.out.println("Token: " + token);
         
        TokenStorage.createTokenFile(token, "token.txt");

        String descryptedToken = TokenStorage.readTokenFile("token.txt");

        descryptedToken = encryptor.decrypt(descryptedToken);
        System.out.println("Decrypted Token: " + descryptedToken);

        System.out.println(encryptor.verify(descryptedToken, payload, 10));
        


        
    }
}
