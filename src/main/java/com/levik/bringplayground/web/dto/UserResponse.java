package com.levik.bringplayground.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {

    private final String shortenedUrl;
}
