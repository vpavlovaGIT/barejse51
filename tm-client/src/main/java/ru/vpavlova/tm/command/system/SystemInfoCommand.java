package ru.vpavlova.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.vpavlova.tm.command.AbstractCommand;
import ru.vpavlova.tm.util.NumberUtil;

public class SystemInfoCommand extends AbstractCommand {

    @NotNull
    @Override
    public String arg() {
        return "-i";
    }

    @NotNull
    @Override
    public String name() {
        return "info";
    }

    @NotNull
    @Override
    public String description() {
        return "Show system info.";
    }

    @Override
    public void execute() {
        System.out.println("[SYSTEM INFO]");
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + availableProcessors);
        final long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory: " + NumberUtil.formatBytes(freeMemory));
        final long maxMemory = Runtime.getRuntime().maxMemory();
        @NotNull final String maxMemoryFormat = NumberUtil.formatBytes(maxMemory);
        @NotNull final String maxMemoryValue = (maxMemory == Long.MAX_VALUE) ? "no limit" : maxMemoryFormat;
        System.out.println("Maximum memory: " + maxMemoryValue);
        final long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("Total memory available to JVM: " + NumberUtil.formatBytes(totalMemory));
        final long usedMemory = totalMemory - freeMemory;
        System.out.println("Used memory by JVM: " + NumberUtil.formatBytes(usedMemory));
        System.out.println("[OK]");
    }

}
