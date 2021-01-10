package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Task;
import java.util.List;

public interface ITaskRepository {

    List<Task> findAll();

    void add(Task task);

    void remove(Task task);

    void clear();

}
