package ru.vpavlova.tm.component;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;

public class Backup extends Thread {

    @NotNull
    private static final int INTERVAL = 30000;

    @NotNull
    private static final String SAVE_COMMAND = "backup-save";

    @NotNull
    private static final String LOAD_COMMAND = "backup-load";

    @NotNull
    private Bootstrap bootstrap;

    public Backup(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        setDaemon(true);
    }

    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            save();
            Thread.sleep(INTERVAL);
        }
    }

    public void init() {
        load();
        start();
    }

    public void save() {
        bootstrap.parseCommand(SAVE_COMMAND);
    }

    public void load() {
        bootstrap.parseCommand(LOAD_COMMAND);
    }

}
