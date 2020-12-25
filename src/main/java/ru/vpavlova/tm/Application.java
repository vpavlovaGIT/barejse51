package ru.vpavlova.tm;

import ru.vpavlova.tm.constant.ArgumentConst;
import ru.vpavlova.tm.constant.TerminalConst;
import ru.vpavlova.tm.model.Command;
import ru.vpavlova.tm.repository.CommandRepository;
import ru.vpavlova.tm.util.NumberUtil;

import java.util.Scanner;

public class Application {

    private static final CommandRepository COMMAND_REPOSITORY = new CommandRepository();

    public static void main(final String[] args) {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        if (parseArgs(args)) System.exit(0);
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("ENTER COMMAND: ");
            final String command = scanner.nextLine();
            parseCommand(command);
        }
    }

    private static void parseArg(final String arg) {
        if (arg == null) return;
        switch (arg) {
            case ArgumentConst.ARG_ABOUT: showAbout(); break;
            case ArgumentConst.ARG_HELP: showHelp(); break;
            case ArgumentConst.ARG_VERSION: showVersion(); break;
            case ArgumentConst.ARG_INFO: showSystemInfo(); break;
            default: showIncorrectCommand();
        }
    }

    private static void parseCommand(final String command) {
        if (command == null) return;
        switch (command) {
            case TerminalConst.CMD_ABOUT: showAbout(); break;
            case TerminalConst.CMD_HELP: showHelp(); break;
            case TerminalConst.CMD_VERSION: showVersion(); break;
            case TerminalConst.CMD_INFO: showSystemInfo(); break;
            case TerminalConst.CMD_COMMANDS: showCommands(); break;
            case TerminalConst.CMD_ARGUMENTS: showArguments(); break;
            case TerminalConst.CMD_EXIT: exit(); break;
            default: showIncorrectCommand();
        }
    }

    private static void showIncorrectArgument() {
        System.out.println("Error! Argument not found...");
    }

    private static void showIncorrectCommand() {
        System.out.println("Error! Command not found...");
    }

    private static void exit() {
        System.exit(0);
    }

    private static boolean parseArgs(final String[] args) {
        if (args == null || args.length == 0) return false;
        final String arg = args[0];
        parseArg(arg);
        return true;
    }

    private static void showAbout() {
        System.out.println("[ABOUT]");
        System.out.println("NAME: Victoria Pavlova");
        System.out.println("E-MAIL: vpavlova@tsconsulting.com");
    }

    private static void showVersion() {
        System.out.println("[VERSION]");
        System.out.println("1.0.0");
    }

    private static void showCommands() {
        final Command[] commands = COMMAND_REPOSITORY.getTerminalCommands();
        for (final Command command: commands) System.out.println(command.getName());
    }

    private static void showArguments() {
        final Command[] commands = COMMAND_REPOSITORY.getTerminalCommands();
        for (final Command command: commands) {
            final String arg = command.getArg();
            if (arg == null) continue;
            System.out.println(arg);
        }
    }

    private static void showHelp() {
        System.out.println("[HELP]");
        final Command[] commands = COMMAND_REPOSITORY.getTerminalCommands();
        for (final Command command: commands) {
            final String name = command.getName();
            if (name == null) continue;
            System.out.println(command);
        }
    }

    private  static void showSystemInfo() {
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
