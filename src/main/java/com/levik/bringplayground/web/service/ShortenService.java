package com.levik.bringplayground.web.service;

import com.bobocode.bring.core.annotation.Service;
import com.levik.bringplayground.web.exception.HashNotFoundException;
import com.levik.bringplayground.web.properties.ShortenProperties;
import com.levik.bringplayground.web.utils.GenerateHashUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShortenService {
    private static final String URL = "Sorry, we cannot found any URL by this hash %s";
    private final Map<String, String> longUrlToShortKey;
    private final Map<String, String> hashToLongUrl;
    private final ShortenProperties shortenProperties;

    public ShortenService(ShortenProperties shortenProperties) {
        this.longUrlToShortKey = new ConcurrentHashMap<>();
        this.hashToLongUrl = new ConcurrentHashMap<>();
        this.shortenProperties = shortenProperties;
    }

    public String createShortUrl(String longUrl) {
        Objects.requireNonNull(longUrl, "Url should not be null");

        if (longUrlToShortKey.containsKey(longUrl)) {
            String hash = longUrlToShortKey.get(longUrl);
            hashToLongUrl.put(hash, longUrl);
            return shortenProperties.getServerUrl() + hash;
        }

        String hash = GenerateHashUtils.generateHash(longUrl);
        hashToLongUrl.put(hash, longUrl);
        return shortenProperties.getServerUrl() + hash;
    }

    public String resolveHash(String hash) {
        Objects.requireNonNull(hash, "Url should not be null");

        if (hashToLongUrl.containsKey(hash)) {
            return hashToLongUrl.get(hash);
        }

        throw new HashNotFoundException(String.format(URL, hash));
    }
}
