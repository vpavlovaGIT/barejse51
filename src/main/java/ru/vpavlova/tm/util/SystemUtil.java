package ru.vpavlova.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SystemUtil {

    static long getPID() {
        @Nullable final String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        if (processName != null && processName.length() > 0) {
            try {
                return Long.parseLong(processName.split("@")[0]);
            } catch (@NotNull final Exception e) {
                return 0;
            }
        }
        return 0;
    }

}
