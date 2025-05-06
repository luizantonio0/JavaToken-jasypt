package com;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.TextEncryptor;

import java.time.LocalDateTime;

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

    public void setPassword(String password) {
        this.encryptor.setPassword(password);
    }

    public void setTimeInHours(int timeInHours) {
        this.timeInHours = timeInHours;
    }

    public String encrypt(String token) {
        if(this.encryptor == null) {
            throw new RuntimeException("Encryptor not set");
        }
        return this.encryptor.encrypt(token);
    }

    public String newToken(String encryptedNickname, String encryptedPassword, LocalDateTime date) {
        return encrypt(encryptedNickname + "+" + encryptedPassword + "+" + date);
    }
    public String newToken(String encryptedNickname, String encryptedPassword, LocalDateTime date, String separator) {
        return encrypt(encryptedNickname + separator + encryptedPassword + separator + date);
    }

    public boolean verify(String token, String encryptedNickname, String encryptedPassword, int timeInHours){
        String[] tokens = token.split("\\+");

        if(!tokens[0].equals(encryptedNickname)){
            return false;
        }
        if(!tokens[1].equals(encryptedPassword)){
            return false;
        }
        System.out.println(tokens[0] + ":" + tokens[1]);
        System.out.println(encryptedNickname + ":" + encryptedPassword);

        LocalDateTime issueDate = LocalDateTime.parse(tokens[2]);

        return java.time.Duration.between(issueDate, LocalDateTime.now()).toHours() < timeInHours;
    }

    public boolean verifyEncriptedToken(String encriptedToken, String encryptedNickname, String encryptedPassword, int timeInHours){
        String token = this.encryptor.decrypt(encriptedToken);

        return verify(token, encryptedNickname, encryptedPassword, timeInHours);
    }

    public boolean verifyEncriptedToken(String encriptedToken, String encryptedNickname, String encryptedPassword){
        return verifyEncriptedToken(encriptedToken, encryptedNickname, encryptedPassword, this.timeInHours);
    }

    public boolean verify(String token, String encryptedNickname, String encryptedPassword){
       return verify(token, encryptedNickname, encryptedPassword, this.timeInHours);
    }

    public String decrypt(String token){
        return this.encryptor.decrypt(token);
    }
}
