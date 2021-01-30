package ru.vpavlova.tm.util;

import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.util.Scanner;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    static String nextLine() {
        return SCANNER.nextLine();
    }

    static Integer nextNumber() {
        final String value = nextLine();
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IndexIncorrectException(value);
        }
    }

}
