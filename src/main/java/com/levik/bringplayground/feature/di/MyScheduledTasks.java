package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Component;
import com.bobocode.bring.core.annotation.ScheduledTask;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MyScheduledTasks {
    @ScheduledTask(value = "myTask", initialDelay = 1, period = 20, timeUnit = TimeUnit.SECONDS)
    public void scheduledMethod1() {
        log.info(Thread.currentThread().getName() + " scheduledMethod1 " + LocalDateTime.now());
    }
}
