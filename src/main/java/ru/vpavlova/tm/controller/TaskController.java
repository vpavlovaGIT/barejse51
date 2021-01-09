package ru.vpavlova.tm.controller;

import ru.vpavlova.tm.api.ITaskController;
import ru.vpavlova.tm.api.ITaskService;
import ru.vpavlova.tm.model.Task;
import ru.vpavlova.tm.util.TerminalUtil;

import java.util.List;

public class TaskController implements ITaskController {

    private final ITaskService taskService;

    public TaskController(final ITaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void showList() {
        System.out.println("[TASK LIST]");
        final List<Task> tasks = taskService.findAll();
        int index = 1;
        for (final Task task: tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
        System.out.println("[OK]");
    }

    @Override
    public void create() {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER NAME:");
        final String name = TerminalUtil.nextLine();
        System.out.println("ENTER DESCRIPTION:");
        final String description = TerminalUtil.nextLine();
        final Task task = taskService.add(name, description);
        if (task == null) {
            System.out.println("[FAIL]");
            return;
        }
        System.out.println("[OK]");
    }

    @Override
    public void clear() {
        System.out.println("[TASK CLEAR]");
        taskService.clear();
        System.out.println("[OK]");
    }

}
