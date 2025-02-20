package com.ToramiStore.ToramiStore.Services.impl;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenServiceImpl {
    public String generateToken() {
        return UUID.randomUUID().toString(); // Genera un token único
    }
}