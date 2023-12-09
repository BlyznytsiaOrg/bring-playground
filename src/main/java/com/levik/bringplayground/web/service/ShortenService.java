package com.levik.bringplayground.web.service;

import io.github.blyznytsiaorg.bring.core.annotation.Qualifier;
import io.github.blyznytsiaorg.bring.core.annotation.Service;
import com.levik.bringplayground.web.exception.HashNotFoundException;
import com.levik.bringplayground.web.properties.ShortenProperties;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShortenService {
    private static final String URL = "Sorry, we cannot found any URL by this hash %s";
    private final Map<String, String> longUrlToShortKey;
    private final Map<String, String> hashToLongUrl;
    private final ShortenProperties shortenProperties;

    private final HashGenerator hashGenerator;

    public ShortenService(ShortenProperties shortenProperties,
                          @Qualifier("md5HashGenerator") HashGenerator hashGenerator) {
        this.hashGenerator = hashGenerator;
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

        String hash = hashGenerator.generateHash(longUrl);
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
