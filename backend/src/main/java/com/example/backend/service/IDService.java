package com.example.backend.service;

import java.util.UUID;

public class IDService {

    public static String generateID(){
        return UUID.randomUUID().toString();
    }
}
