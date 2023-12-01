package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Autowired;
import com.bobocode.bring.core.annotation.PostConstruct;
import com.bobocode.bring.core.annotation.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Barista {
    private final List<Drink> drinks;
    public Barista(List<Drink> drinks) {
        this.drinks = drinks;
    }

    @PostConstruct
    public void onInit() {
        log.info("PostContract demo!!!");
    }

    public String prepareDrink() {
        String drinksText = drinks.stream()
                .map(Drink::make)
                .collect(Collectors.joining(", "));
        return "Barista is preparing a drink: " + drinksText;
    }
}
