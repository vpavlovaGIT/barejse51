package ru.vpavlova.tm.api;

import ru.vpavlova.tm.model.Task;

import java.util.List;

public interface ITaskService {

    List<Task> findAll();

    Task add(String name, String description);

    void add(Task task);

    void remove(Task task);

    void clear();

}
