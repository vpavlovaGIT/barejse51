package ru.vpavlova.tm.model;

public class Command {

    private String arg = "";

    private  String name = "";

    private String description = "";

    public Command() {
    }

    public Command(String name,String arg, String description) {
        this.name = name;
        this.arg = arg;
        this.description = description;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "";
        if (name != null && !name.isEmpty()) result += name;
        if (arg != null && !arg.isEmpty()) result += " [" + arg + "] ";
        if (description != null && !description.isEmpty()) result += " - " + description;
        return result;
    }
}
