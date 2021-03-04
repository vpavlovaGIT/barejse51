package ru.vpavlova.tm.command.project;

import ru.vpavlova.tm.command.AbstractProjectCommand;
import ru.vpavlova.tm.enumerated.Sort;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;

public class ProjectListCommand extends AbstractProjectCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "project-list";
    }

    @Override
    public String description() {
        return "Show project list.";
    }

    @Override
    public void execute() {
        System.out.println("[PROJECT LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        final String userId = serviceLocator.getAuthService().getUserId();
        final String sort = TerminalUtil.nextLine();
        List<Project> list;
        if (sort == null || sort.isEmpty()) list = serviceLocator.getProjectService().findAll(userId);
        else {
            final Sort sortType = Sort.valueOf(sort);
            System.out.println(sortType.getDisplayName());
            list = serviceLocator.getProjectService().findAll(sortType.getComparator());
        }
        int index = 1;
        for (final Project project : list) {
            System.out.println(index + ". " + project);
            index++;
        }
    }

}
