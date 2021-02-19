package ru.vpavlova.tm.command;

import ru.vpavlova.tm.entity.Task;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected void showTask(final Task task) {
        if (task == null) throw new NullPointerException();
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("STATUS: " + task.getStatus().getDisplayName());
    }

}