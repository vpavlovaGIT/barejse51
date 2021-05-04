package ru.vpavlova.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.enumerated.Sort;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProjectListCommand extends AbstractProjectCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "project-list";
    }

    @NotNull
    @Override
    public String description() {
        return "Show project list.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        @NotNull final String userId = serviceLocator.getAuthService().getUserId();
        @NotNull final String sort = TerminalUtil.nextLine();
        @Nullable List<Project> list;
        if (!Optional.ofNullable(sort).isPresent()) list = serviceLocator.getProjectService().findAll(userId);
        else {
            @NotNull final Sort sortType = Sort.valueOf(sort);
            System.out.println(sortType.getDisplayName());
            list = serviceLocator.getProjectService().findAll(userId, sortType.getComparator());
        }
        int index = 1;
        for (@NotNull final Project project : list) {
            System.out.println(index + ". " + project);
            index++;
        }
    }

}
