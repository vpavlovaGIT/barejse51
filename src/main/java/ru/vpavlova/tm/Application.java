package ru.vpavlova.tm;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;

public class Application {

    public static void main(final String[] args) {
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}
