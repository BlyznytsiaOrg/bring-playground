package com.levik.bringplayground.web.exception;

import com.bobocode.bring.web.servlet.annotation.ResponseStatus;
import com.bobocode.bring.web.servlet.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HashNotFoundException extends RuntimeException {

    public HashNotFoundException(String message) {
        super(message);
    }
}
