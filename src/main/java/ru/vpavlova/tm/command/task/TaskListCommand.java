package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.enumerated.Sort;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskListCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-list";
    }

    @Override
    public String description() {
        return "Show task list.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK LIST]");
        System.out.println("ENTER SORT:");
        System.out.println(Arrays.toString(Sort.values()));
        final String sort = TerminalUtil.nextLine();
        final String userId = serviceLocator.getAuthService().getUserId();
        List<Task> tasks;
        if (!Optional.ofNullable(sort).isPresent()) tasks = serviceLocator.getTaskService().findAll(userId);
        else {
            final Sort sortType = Sort.valueOf(sort);
            System.out.println(sortType.getDisplayName());
            tasks = serviceLocator.getTaskService().findAll(userId, sortType.getComparator());
        }
        int index = 1;
        for (final Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
    }

}
