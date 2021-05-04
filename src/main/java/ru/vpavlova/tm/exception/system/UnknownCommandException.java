package ru.vpavlova.tm.exception.system;

import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.exception.AbstractException;

public class UnknownCommandException extends AbstractException {

    public UnknownCommandException(@Nullable final String command) {
        super("Error! Command ``" + command + "`` not found...");
    }

}
