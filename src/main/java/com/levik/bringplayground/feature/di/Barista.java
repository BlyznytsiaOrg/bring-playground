package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Autowired;
import com.bobocode.bring.core.annotation.Service;

@Service
public class Barista {
    private final Drink drink;

    @Autowired
    public Barista(Drink latte) {
        this.drink = latte;
    }

    public String prepareDrink() {
        return "Barista is preparing a drink: " + drink.make();
    }
}
