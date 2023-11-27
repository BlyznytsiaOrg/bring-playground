package com.levik.bringplayground.feature.di;


import com.bobocode.bring.core.annotation.Component;

@Component
public class Espresso implements Drink {
    @Override
    public String make() {
        return "Brewing a strong espresso!";
    }
}
