package ru.vpavlova.tm.component;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.command.AbstractCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FileScanner implements Runnable {

    private static final int INTERVAL = 3;

    @NotNull
    private static final String PATH = "./";

    @NotNull
    public final Bootstrap bootstrap;

    @NotNull
    private final Collection<String> commands = new ArrayList<>();

    @NotNull
    private final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    public FileScanner(@NotNull Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void init() {
        for (@NotNull final AbstractCommand command : bootstrap.getCommandService().getArgsCommands()) {
            commands.add(command.name());
        }
        es.scheduleWithFixedDelay(this,0,INTERVAL,TimeUnit.SECONDS);
    }

    public void run() {
        @NotNull final File file = new File(PATH);
        Arrays.stream(file.listFiles())
                .filter(File::isFile).collect(Collectors.toList())
                .stream()
                .filter(item -> commands.contains(item.getName()))
                .forEach(item -> {bootstrap.parseCommand(item.getName()); item.delete();});
    }

}
