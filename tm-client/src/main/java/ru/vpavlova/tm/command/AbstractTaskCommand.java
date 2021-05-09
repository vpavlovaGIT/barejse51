package ru.vpavlova.tm.command;

import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.endpoint.Task;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;

import java.util.Optional;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected void showTask(@Nullable final Task task) {
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("ID: " + task.getId());
        System.out.println("NAME: " + task.getName());
        System.out.println("DESCRIPTION: " + task.getDescription());
        System.out.println("STATUS: " + task.getStatus().value());
        System.out.println("Start Date: " + task.getDateStart());
        System.out.println("Finish Date: " + task.getDateFinish());
        System.out.println("Created: " + task.getCreated());
    }

}
