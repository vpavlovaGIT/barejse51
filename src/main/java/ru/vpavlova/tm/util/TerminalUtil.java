package ru.vpavlova.tm.util;

import java.util.Scanner;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    static String nextLine() {
        return SCANNER.nextLine();
    }

}
