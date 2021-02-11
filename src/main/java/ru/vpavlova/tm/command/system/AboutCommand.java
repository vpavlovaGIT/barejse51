package ru.vpavlova.tm.command.system;

import ru.vpavlova.tm.command.AbstractCommand;

public class AboutCommand extends AbstractCommand {

    @Override
    public String arg() {
        return "-a";
    }

    @Override
    public String name() {
        return "about";
    }

    @Override
    public String description() {
        return "Show developer info.";
    }

    @Override
    public void execute() {
        System.out.println("[ABOUT]");
        System.out.println("NAME: Victoria Pavlova");
        System.out.println("E-MAIL: vpavlova@tsconsulting.com");
    }

}
