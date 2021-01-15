package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Task;
import java.util.List;

public interface ITaskService {

    List<Task> findAll();

    Task add(String name, String description);

    void add(Task task);

    void remove(Task task);

    void clear();

    Task findOneById(String id);

    Task findOneByIndex(Integer index);

    Task findOneByName(String name);

    Task removeOneById(String id);

    Task removeOneByIndex(Integer index);

    Task removeOneByName(String name);

    Task updateTaskById(String id, String name, String description);

    Task updateTaskByIndex(Integer index, String name, String description);

}
