package ru.vpavlova.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.Task;

public interface ITaskService extends IBusinessService<Task> {

    @NotNull
    Task add(@Nullable String userId, @Nullable String name, @Nullable String description);

}
