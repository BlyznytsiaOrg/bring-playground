package com.levik.bringplayground.web.service;

import io.github.blyznytsiaorg.bring.core.annotation.Bean;
import io.github.blyznytsiaorg.bring.core.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public HashGenerator incrementHashGenerator() {
        return new IncrementHashGenerator();
    }
}
