package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.service.ILoggerService;

import java.io.IOException;
import java.util.logging.*;

public class LoggerService implements ILoggerService {

    private static final String COMMANDS = "COMMANDS";
    private static final String COMMANDS_FILE = "./commands.txt";

    private static final String ERRORS = "ERRORS";
    private static final String ERRORS_FILE = "./errors.txt";

    private static final String MESSAGES = "MESSAGES";
    private static final String MESSAGES_FILE = "./messages.txt";

    private final LogManager manager = LogManager.getLogManager();
    private final Logger root = Logger.getLogger("");
    private final Logger commands = Logger.getLogger(COMMANDS);
    private final Logger errors = Logger.getLogger(ERRORS);
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


    private ConsoleHandler getConsoleHandler() {
        final ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        return handler;
    }

    private void registry(final Logger logger, final String fileName, final boolean isConsole) {
        try {
            if (isConsole) logger.addHandler(getConsoleHandler());
            logger.setUseParentHandlers(false);
            logger.addHandler(new FileHandler(fileName));
        } catch (final IOException e) {
            root.severe(e.getMessage());
        }
    }

    @Override
    public void info(final String message) {
        if (message == null || message.isEmpty()) return;
        messages.info(message);
    }

    @Override
    public void debug(final String message) {
        if (message == null || message.isEmpty()) return;
        messages.fine(message);
    }

    @Override
    public void command(final String message) {
        if (message == null || message.isEmpty()) return;
        commands.info(message);
    }

    @Override
    public void error(final Exception e) {
        if (e == null) return;
        errors.log(Level.SEVERE, e.getMessage(), e);
    }

}
