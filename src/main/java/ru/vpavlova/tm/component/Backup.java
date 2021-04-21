package ru.vpavlova.tm.component;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.command.data.BackupLoadCommand;
import ru.vpavlova.tm.command.data.BackupSaveCommand;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Backup implements Runnable {

    @NotNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    @NotNull
    private static final int INTERVAL = 30;

    @NotNull
    private Bootstrap bootstrap;

    public Backup(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void run() {
        bootstrap.parseCommand(BackupSaveCommand.BACKUP_SAVE);
    }

    public void load() {
        bootstrap.parseCommand(BackupLoadCommand.BACKUP_LOAD);
    }

    public void init() {
        load();
        start();
    }

    public void start() {
        es.scheduleWithFixedDelay(this, 0, INTERVAL, TimeUnit.SECONDS);
    }

    public void stop() {
        es.shutdown();
    }

}
