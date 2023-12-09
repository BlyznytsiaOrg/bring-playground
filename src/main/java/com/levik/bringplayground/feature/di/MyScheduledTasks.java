package com.levik.bringplayground.feature.di;

import io.github.blyznytsiaorg.bring.core.annotation.Component;
import io.github.blyznytsiaorg.bring.core.annotation.ScheduledTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Slf4j
@Component
public class MyScheduledTasks {

    private final SingletonBean singletonBean;
    @ScheduledTask(value = "myTask", initialDelay = 1, period = 20, timeUnit = TimeUnit.SECONDS)
    public void scheduledMethod1() {
        log.info(Thread.currentThread().getName() + " scheduledMethod1 " + LocalDateTime.now());

        log.info("singletonBean -> " + singletonBean + " PrototypeBean ->" + singletonBean.getPrototypeBean());
    }
}
