package ru.vpavlova.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.util.Scanner;

public interface TerminalUtil {

    Scanner SCANNER = new Scanner(System.in);

    @NotNull
    static String nextLine() {
        return SCANNER.nextLine();
    }

    static Integer nextNumber() {
        @Nullable final String value = nextLine();
        try {
            return Integer.parseInt(value);
        } catch (@NotNull Exception e) {
            throw new IndexIncorrectException(value);
        }
    }

}
