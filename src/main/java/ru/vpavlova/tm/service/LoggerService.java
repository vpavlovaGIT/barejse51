package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.service.ILoggerService;

import java.io.IOException;
import java.util.logging.*;

public class LoggerService implements ILoggerService {

    @NotNull
    private static final String COMMANDS = "COMMANDS";

    @NotNull
    private static final String COMMANDS_FILE = "./commands.txt";

    @NotNull
    private static final String ERRORS = "ERRORS";

    @NotNull
    private static final String ERRORS_FILE = "./errors.txt";

    @NotNull
    private static final String MESSAGES = "MESSAGES";

    @NotNull
    private static final String MESSAGES_FILE = "./messages.txt";

    @NotNull
    private final LogManager manager = LogManager.getLogManager();

    @NotNull
    private final Logger root = Logger.getLogger("");

    @NotNull
    private final Logger commands = Logger.getLogger(COMMANDS);

    @NotNull
    private final Logger errors = Logger.getLogger(ERRORS);

    @NotNull
    private final Logger messages = Logger.getLogger(MESSAGES);

    {
        init();
        registry(commands, COMMANDS_FILE, false);
        registry(errors, ERRORS_FILE, true);
        registry(messages, MESSAGES_FILE, true);
    }

    private void init() {
        try {
            manager.readConfiguration(LoggerService.class.getResourceAsStream("/logger.properties"));
        } catch (IOException e) {
            root.severe(e.getMessage());
        }
    }


    @NotNull
    private ConsoleHandler getConsoleHandler() {
        @NotNull final ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        return handler;
    }

    private void registry(
            @NotNull final Logger logger,
            @NotNull final String fileName,
            @NotNull final boolean isConsole
    ) {
        try {
            if (isConsole) logger.addHandler(getConsoleHandler());
            logger.setUseParentHandlers(false);
            logger.addHandler(new FileHandler(fileName));
        } catch (@NotNull  final IOException e) {
            root.severe(e.getMessage());
        }
    }

    @Override
    public void info(@Nullable final String message) {
        if (message == null || message.isEmpty()) return;
        messages.info(message);
    }

    @Override
    public void debug(@Nullable final String message) {
        if (message == null || message.isEmpty()) return;
        messages.fine(message);
    }

    @Override
    public void command(@Nullable final String message) {
        if (message == null || message.isEmpty()) return;
        commands.info(message);
    }

    @Override
    public void error(@Nullable final Exception e) {
        if (e == null) return;
        errors.log(Level.SEVERE, e.getMessage(), e);
    }

}
