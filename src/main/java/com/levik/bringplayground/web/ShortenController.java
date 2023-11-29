package com.levik.bringplayground.web;

import com.bobocode.bring.web.servlet.BringServlet;
import com.bobocode.bring.web.servlet.annotation.*;
import com.bobocode.bring.web.servlet.http.HttpHeaders;
import com.bobocode.bring.web.servlet.http.HttpStatus;
import com.bobocode.bring.web.servlet.http.ResponseEntity;
import com.levik.bringplayground.web.dto.UserRequest;
import com.levik.bringplayground.web.dto.UserResponse;
import com.levik.bringplayground.web.service.ShortenService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;

@AllArgsConstructor
@RestController
public class ShortenController implements BringServlet {

    private static final String LOCATION = "location";
    private final ShortenService shortenService;

    @SneakyThrows
    @PostMapping(path = "/api/shorten")
    public ResponseEntity<UserResponse> createShortUrl(@RequestBody UserRequest userRequest) {
        String shortUrl = shortenService.createShortUrl(userRequest.getOriginalUrl());
        return new ResponseEntity<>(new UserResponse(shortUrl), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping(path = "/short/{hash}")
    public ResponseEntity<?> getLongUrl(@PathVariable String hash) {
        String longUrl = shortenService.resolveHash(hash);

        var httpHeaders = new HttpHeaders();
        httpHeaders.set(LOCATION, longUrl);

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
}
