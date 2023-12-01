package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Component
public class SingletonBean {

    @Getter
    private final PrototypeBean prototypeBean;

}
