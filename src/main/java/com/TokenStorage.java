package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class TokenStorage {

    public static void createTokenFile(String token, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(token);
        } catch (java.io.IOException e) {
            System.err.println("Error writing token to file: " + e.getMessage());
        } 
    }

    public static String readTokenFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String token = reader.readLine();
            return token;
        } catch (java.io.IOException e) {
            System.err.println("Error reading token from file: " + e.getMessage());
        }
        return null;
    }
}
