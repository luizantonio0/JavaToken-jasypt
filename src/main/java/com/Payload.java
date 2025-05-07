package com;

import java.util.Map;

public class Payload {

    private final Map<String, String> payload;

    public Payload(Map<String, String> payload) {
        this.payload = payload;
    }

    public Map<String, String> getPayload() {
        return payload;
    }
    @Override
    public String toString() {
    return  payload.toString().replace("{", "").replace("}", "").replace(",", "+").replace(" ", ""); 
    }
}
