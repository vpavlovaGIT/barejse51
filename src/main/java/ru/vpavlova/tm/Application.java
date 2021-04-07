package ru.vpavlova.tm;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.util.SystemUtil;

public class Application {

    public static void main(final String[] args) {
        System.out.println("PID: " + SystemUtil.getPID());
        @NotNull final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run(args);
    }

}
