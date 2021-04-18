package ru.vpavlova.tm.component;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.command.data.BackupLoadCommand;
import ru.vpavlova.tm.command.data.BackupSaveCommand;

public class Backup extends Thread {

    @NotNull
    private static final int INTERVAL = 30000;

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
        bootstrap.parseCommand(BackupSaveCommand.BACKUP_SAVE);
    }

    public void load() {
        bootstrap.parseCommand(BackupLoadCommand.BACKUP_LOAD);
    }

}
