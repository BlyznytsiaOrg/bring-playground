package com.levik.bringplayground.web;

import com.bobocode.bring.web.servlet.BringServlet;
import com.bobocode.bring.web.servlet.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levik.bringplayground.web.dto.UserRequest;
import com.levik.bringplayground.web.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;


@AllArgsConstructor
@RequestMapping(path = "/api")
@RestController
public class ShortenController implements BringServlet {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping(path = "/shorten")
    public String shorten(@RequestBody UserRequest userRequest) {
        return objectMapper.writeValueAsString(new UserResponse(userRequest.getOriginalUrl()));
    }
}
