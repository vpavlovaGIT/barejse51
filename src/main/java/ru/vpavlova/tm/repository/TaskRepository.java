package ru.vpavlova.tm.repository;

import ru.vpavlova.tm.api.ITaskRepository;
import ru.vpavlova.tm.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    private final List<Task> list = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return list;
    }

    @Override
    public void add(final Task task) {
        list.add(task);
    }

    @Override
    public void remove(final Task task) {
        list.remove(task);
    }

    @Override
    public void clear() {
        list.clear();
    }

}
