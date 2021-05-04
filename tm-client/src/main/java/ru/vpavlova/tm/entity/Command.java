package ru.vpavlova.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Command {

    @NotNull
    private String arg = "";

    @NotNull
    private  String name = "";

    @NotNull
    private String description = "";

    public Command(@NotNull String name, @NotNull String arg,@NotNull String description) {
        this.name = name;
        this.arg = arg;
        this.description = description;
    }

    @NotNull
    @Override
    public String toString() {
        @NotNull String result = "";
        if (name != null && !name.isEmpty()) result += name;
        if (arg != null && !arg.isEmpty()) result += " [" + arg + "] ";
        if (description != null && !description.isEmpty()) result += " - " + description;
        return result;
    }

}
