package com.levik.bringplayground.web.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.security.MessageDigest;

@UtilityClass
public class GenerateHashUtils {

    private static final String MD_5 = "MD5";

    @SneakyThrows
    public String generateHash(String input){
        MessageDigest md = MessageDigest.getInstance(MD_5);
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashText = no.toString(16);
        return hashText.substring(0,7);
    }
}
