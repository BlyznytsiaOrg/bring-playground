package com.levik.bringplayground;

import io.github.blyznytsiaorg.bring.core.context.impl.BringApplicationContext;
import io.github.blyznytsiaorg.bring.web.BringWebApplication;
import com.levik.bringplayground.feature.di.Barista;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BringPlaygroundApplication {

	public static void main(String[] args) {
		BringApplicationContext bringApplicationContext = BringWebApplication.run(BringPlaygroundApplication.class);
		Barista barista = bringApplicationContext.getBean(Barista.class);
		log.info("Barista is {}", barista.prepareDrink());
	}

}
