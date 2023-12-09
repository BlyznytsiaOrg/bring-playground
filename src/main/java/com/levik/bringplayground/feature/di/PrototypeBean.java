package com.levik.bringplayground.feature.di;

import io.github.blyznytsiaorg.bring.core.annotation.Scope;
import io.github.blyznytsiaorg.bring.core.annotation.Service;
import io.github.blyznytsiaorg.bring.core.domain.BeanScope;
import io.github.blyznytsiaorg.bring.core.domain.ProxyMode;

@Service
@Scope(name = BeanScope.PROTOTYPE, proxyMode = ProxyMode.ON)
public class PrototypeBean {
}
