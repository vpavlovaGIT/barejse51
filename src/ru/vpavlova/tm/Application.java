package ru.vpavlova.tm;

import ru.vpavlova.tm.constant.TerminalConst;

public class Application {

    public static void main(final String[] args) {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        parseArgs(args);
    }

    private static void parseArgs(final String[] args) {
        if (args == null || args.length == 0) return;
        final String arg = args[0];
        if (TerminalConst.CMD_ABOUT.equals(arg)) showAbout();
        if (TerminalConst.CMD_HELP.equals(arg)) showHelp();
        if (TerminalConst.CMD_VERSION.equals(arg)) showVersion();
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
    }

}
