package ru.vpavlova.tm.command.task;

import ru.vpavlova.tm.command.AbstractTaskCommand;

public class TaskClearCommand extends AbstractTaskCommand {

    @Override
    public String arg() {
        return null;
    }

    @Override
    public String name() {
        return "task-clear";
    }

    @Override
    public String description() {
        return "Clear all tasks.";
    }

    @Override
    public void execute() {
        System.out.println("[TASK CLEAR]");
        serviceLocator.getTaskService().clear();
        System.out.println("[OK]");
    }

}
