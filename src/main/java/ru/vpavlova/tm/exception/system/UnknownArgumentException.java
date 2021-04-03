package ru.vpavlova.tm.exception.system;

import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.exception.AbstractException;

public class UnknownArgumentException extends AbstractException {

    public UnknownArgumentException(@Nullable final String arg) {
        super("Error! Argument ``" + arg + "`` not found...");
    }

}
