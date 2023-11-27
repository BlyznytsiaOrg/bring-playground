package com.levik.bringplayground.web;

import com.bobocode.bring.web.servlet.BringServlet;
import com.bobocode.bring.web.servlet.annotation.*;
import com.levik.bringplayground.web.dto.UserRequest;
import com.levik.bringplayground.web.dto.UserResponse;


@RequestMapping(path = "/api")
@RestController
public class ShortenController implements BringServlet {

    @PostMapping(path = "/shorten")
    public UserResponse shorten(@RequestBody UserRequest userRequest) {
        return new UserResponse(userRequest.getOriginalUrl());
    }
}
