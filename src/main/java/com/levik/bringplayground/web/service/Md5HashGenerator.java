package com.levik.bringplayground.web.service;

import io.github.blyznytsiaorg.bring.core.annotation.Service;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.security.MessageDigest;

@Service
public class Md5HashGenerator implements HashGenerator {

    private static final String MD_5 = "MD5";

    @SneakyThrows
    @Override
    public String generateHash(String input) {
        MessageDigest md = MessageDigest.getInstance(MD_5);
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashText = no.toString(16);
        return hashText.substring(0,7);
    }
}
