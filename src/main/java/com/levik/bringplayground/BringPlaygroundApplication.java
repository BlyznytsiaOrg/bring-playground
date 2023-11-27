package com.levik.bringplayground;

import com.bobocode.bring.core.context.impl.BringApplicationContext;
import com.bobocode.bring.web.BringWebApplication;
import com.levik.bringplayground.feature.di.Barista;

public class BringPlaygroundApplication {

	public static void main(String[] args) {
		BringApplicationContext bringApplicationContext = BringWebApplication.run("com.levik.bringplayground", "com.levik.bringplayground.web");
		Barista barista = bringApplicationContext.getBean(Barista.class);
		System.out.println(barista.prepareDrink());
	}

}
