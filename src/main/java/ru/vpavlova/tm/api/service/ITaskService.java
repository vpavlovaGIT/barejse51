package ru.vpavlova.tm.api.service;

import ru.vpavlova.tm.api.IBusinessService;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public interface ITaskService extends IBusinessService<Task> {

    List<Task> findAll(Comparator<Task> comparator);

    Task add(String userId, String name, String description);

}
