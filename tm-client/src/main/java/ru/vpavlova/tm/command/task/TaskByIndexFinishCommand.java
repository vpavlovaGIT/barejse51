package ru.vpavlova.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.command.AbstractTaskCommand;
import ru.vpavlova.tm.endpoint.Session;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.util.TerminalUtil;

public class TaskByIndexFinishCommand extends AbstractTaskCommand {

    @Nullable
    @Override
    public String arg() {
        return null;
    }

    @NotNull
    @Override
    public String name() {
        return "task-finish-status-by-index";
    }

    @NotNull
    @Override
    public String description() {
        return "Finish task status by index.";
    }

    @Override
    public void execute() {
        System.out.println("[FINISH PROJECT]");
        System.out.println("ENTER INDEX:");
        if (bootstrap == null) throw new ObjectNotFoundException();
        @Nullable final Session session = bootstrap.getSession();
        if (endpointLocator == null) throw new ObjectNotFoundException();
        @NotNull final Integer index = TerminalUtil.nextNumber() - 1;
        endpointLocator.getTaskEndpoint().finishTaskByIndex(session, index);
    }

}
