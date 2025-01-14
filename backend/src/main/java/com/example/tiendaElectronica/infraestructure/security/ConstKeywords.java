package com.example.tiendaElectronica.infraestructure.security;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class ConstKeywords {
    public static final SecretKey SECRET_TOKEN = Keys.secretKeyFor(SignatureAlgorithm.HS256);

}