package com.levik.bringplayground.web.service;

import java.util.concurrent.atomic.AtomicInteger;

public class IncrementHashGenerator implements HashGenerator {

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String generateHash(String input) {
        return String.valueOf(counter.incrementAndGet());
    }
}
