package com.levik.bringplayground.feature.di;

import com.bobocode.bring.core.annotation.Component;
import com.bobocode.bring.core.annotation.ScheduledTask;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

//@Component
public class MyScheduledTasks {

    @ScheduledTask(value = "myTask", initialDelay = 1000, period = 5000, timeUnit = TimeUnit.MILLISECONDS)
    public void scheduledMethod1() {
        System.out.println(Thread.currentThread().getName() + " scheduledMethod1 " + LocalDateTime.now());
    }
}
