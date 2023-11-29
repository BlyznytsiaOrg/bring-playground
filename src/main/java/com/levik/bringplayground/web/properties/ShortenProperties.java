package com.levik.bringplayground.web.properties;

import com.bobocode.bring.core.annotation.Component;
import com.bobocode.bring.core.annotation.Value;
import lombok.Data;

@Data
@Component
public class ShortenProperties {


    @Value("shortenServerUrl")
    private String serverUrl;
}
