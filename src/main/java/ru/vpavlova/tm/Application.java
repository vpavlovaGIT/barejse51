package ru.vpavlova.tm;

import ru.vpavlova.tm.constant.TerminalConst;

import java.util.Scanner;

public class Application {

    public static void main(final String[] args) {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        if (parseArgs(args)) System.exit(0);
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("ENTER COMMAND: ");
            final String command = scanner.nextLine();
            parseArg(command);
        }
    }

    private static void parseArg(String arg) {
        if (arg == null) return;
        switch (arg) {
            case TerminalConst.CMD_ABOUT:
                showAbout();
                break;
            case TerminalConst.CMD_HELP:
                showHelp();
                break;
            case TerminalConst.CMD_VERSION:
                showVersion();
                break;
            case TerminalConst.CMD_EXIT:
                exit();
                break;
            default:
                showIncorrectCommand();
        }
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

    private static void showHelp() {
        System.out.println("[HELP]");
        System.out.println(TerminalConst.CMD_ABOUT + " - Show developer info.");
        System.out.println(TerminalConst.CMD_VERSION + " - Show application version.");
        System.out.println(TerminalConst.CMD_HELP + " - Show terminal commands.");
        System.out.println(TerminalConst.CMD_EXIT + " - Close application.");
    }

}
