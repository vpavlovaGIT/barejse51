package ru.vpavlova.tm.controller;

import ru.vpavlova.tm.api.controller.ICommandController;
import ru.vpavlova.tm.api.service.ICommandService;
import ru.vpavlova.tm.model.Command;
import ru.vpavlova.tm.util.NumberUtil;

public class CommandController implements ICommandController {

    private final ICommandService commandService;

    public CommandController(final ICommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("NAME: Victoria Pavlova");
        System.out.println("E-MAIL: vpavlova@tsconsulting.com");
    }

    @Override
    public void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.0.0");
    }

    @Override
    public void showCommands() {
        final Command[] commands = commandService.getTerminalCommands();
        for (final Command command: commands) System.out.println(command.getName());
    }

    @Override
    public void showArguments() {
        final Command[] commands = commandService.getTerminalCommands();
        for (final Command command: commands) {
            final String arg = command.getArg();
            if (arg == null) continue;
            System.out.println(arg);
        }
    }

    @Override
    public void showHelp() {
        System.out.println("[HELP]");
        final Command[] commands = commandService.getTerminalCommands();
        for (final Command command: commands) {
            final String name = command.getName();
            if (name == null) continue;
            System.out.println(command);
        }
    }

    @Override
    public void showSystemInfo() {
        System.out.println("[SYSTEM INFO]");
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + availableProcessors);
        final long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory: " + NumberUtil.formatBytes(freeMemory));
        final long maxMemory = Runtime.getRuntime().maxMemory();
        final String maxMemoryFormat = NumberUtil.formatBytes(maxMemory);
        final String maxMemoryValue = (maxMemory == Long.MAX_VALUE) ? "no limit" : maxMemoryFormat;
        System.out.println("Maximum memory: " + maxMemoryValue);
        final long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("Total memory available to JVM: " + NumberUtil.formatBytes(totalMemory));
        final long usedMemory = totalMemory - freeMemory;
        System.out.println("Used memory by JVM: " + NumberUtil.formatBytes(usedMemory));
        System.out.println("[OK]");
    }

}
