package com.levik.bringplayground.web.properties;

import io.github.blyznytsiaorg.bring.core.annotation.Component;
import io.github.blyznytsiaorg.bring.core.annotation.Value;
import lombok.Data;

@Data
@Component
public class ShortenProperties {

    @Value("shortenServerUrl")
    private String serverUrl;
}
