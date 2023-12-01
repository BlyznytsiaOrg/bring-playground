package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Scope;
import com.bobocode.bring.core.annotation.Service;
import com.bobocode.bring.core.domain.BeanScope;
import com.bobocode.bring.core.domain.ProxyMode;

@Service
@Scope(name = BeanScope.PROTOTYPE, proxyMode = ProxyMode.ON)
public class PrototypeBean {
}
