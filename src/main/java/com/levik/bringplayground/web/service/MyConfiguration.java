package com.levik.bringplayground.web.service;

import com.bobocode.bring.core.annotation.Bean;
import com.bobocode.bring.core.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public HashGenerator incrementHashGenerator() {
        return new IncrementHashGenerator();
    }
}
