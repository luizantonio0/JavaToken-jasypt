package com;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.TextEncryptor;

public class BasicTokenEncryptor implements TextEncryptor {

    private final StandardPBEStringEncryptor encryptor;
    private int timeInHours;

    public BasicTokenEncryptor(String password) {
        super();
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setAlgorithm("PBEWithMD5AndDES");
        this.encryptor.setPassword(password);
    }

    public BasicTokenEncryptor(String password, int timeInHours) {
        super();
        this.timeInHours = timeInHours;
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setAlgorithm("PBEWithMD5AndDES");
        this.encryptor.setPassword(password);
    }

    public void setTimeInHours(int timeInHours) {
        this.timeInHours = timeInHours;
    }

    @Override
    public String encrypt(String token) {
        return this.encryptor.encrypt(token);
    }

    public String newToken(Payload payload, LocalDateTime date) {
        return encrypt(payload + "+" + date);
    }


    public boolean verify(String token, Payload payloadValues, int timeInHours){
        List<String> tokens = Arrays.asList(token.split("\\+"));

        LocalDateTime issueDate = LocalDateTime.parse(tokens.getLast());

        if (issueDate.isAfter(LocalDateTime.now())
        ){
            return false;
        }

        if (!(java.time.Duration.between(issueDate, LocalDateTime.now()).toHours() < timeInHours)){
            return false;
        }

        Map<String, String> decryptedMap = new HashMap<>();

        for (String tokenPart : tokens) {
            String[] keyValue = tokenPart.split("=");
            if (keyValue.length == 2) {
            decryptedMap.put(keyValue[0], keyValue[1]);
            }
        }   
   
        return (payloadValues.getPayload().equals(decryptedMap));
    }

    public boolean verifyEncriptedToken(String encriptedToken, Payload payloadValues, int timeInHours){
        String token = this.encryptor.decrypt(encriptedToken);

        return verify(token, payloadValues, timeInHours);
    }

    public boolean verifyEncriptedToken(String encriptedToken, Payload payloadValues){
        String token = this.encryptor.decrypt(encriptedToken);

        return verify(token, payloadValues, this.timeInHours);
    }

    public boolean verify(String encriptedToken, Payload payloadValues){
        return verify(encriptedToken, payloadValues, this.timeInHours);
    }

    @Override
    public String decrypt(String token){
        return this.encryptor.decrypt(token);
    }
}