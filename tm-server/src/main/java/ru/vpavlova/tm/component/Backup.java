package ru.vpavlova.tm.component;


import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Backup implements Runnable {

    private static final int INTERVAL = 15;

    @NotNull
    public final Bootstrap bootstrap;

    @NotNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    public Backup(@NotNull Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void init() {
        load();
        start();
    }

    public void load() {
        bootstrap.backupService.load();
    }

    @Override
    public void run() {
        bootstrap.backupService.save();
    }

    public void start() {
        es.scheduleWithFixedDelay(this, 0, INTERVAL, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

}
