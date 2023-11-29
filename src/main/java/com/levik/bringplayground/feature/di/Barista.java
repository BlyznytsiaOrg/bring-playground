package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Autowired;
import com.bobocode.bring.core.annotation.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Barista {
    private final List<Drink> drinks;

    @Autowired
    public Barista(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public String prepareDrink() {
        String drinksText = drinks.stream()
                .map(Drink::make)
                .collect(Collectors.joining(", "));
        return "Barista is preparing a drink: " + drinksText;
    }
}
