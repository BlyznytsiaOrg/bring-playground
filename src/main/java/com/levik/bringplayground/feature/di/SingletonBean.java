package com.levik.bringplayground.feature.di;

import io.github.blyznytsiaorg.bring.core.annotation.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Component
public class SingletonBean {

    @Getter
    private final PrototypeBean prototypeBean;

}
