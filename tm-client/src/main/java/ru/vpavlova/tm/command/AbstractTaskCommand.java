package ru.vpavlova.tm.command;

import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.exception.entity.TaskNotFoundException;

import java.util.Optional;

public abstract class AbstractTaskCommand extends AbstractCommand {

    protected void showTask(@Nullable final Optional<Task> task) {
        Optional.ofNullable(task).orElseThrow(TaskNotFoundException::new);
        System.out.println("ID: " + task.get().getId());
        System.out.println("NAME: " + task.get().getName());
        System.out.println("DESCRIPTION: " + task.get().getDescription());
        System.out.println("STATUS: " + task.get().getStatus().getDisplayName());
        System.out.println("Start Date: " + task.get().getDateStart());
        System.out.println("Finish Date: " + task.get().getDateFinish());
        System.out.println("Created: " + task.get().getCreated());
    }

}
