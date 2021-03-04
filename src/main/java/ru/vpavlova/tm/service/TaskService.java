package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;
import ru.vpavlova.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public class TaskService extends AbstractService<Task> implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(final ITaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll(Comparator<Task> comparator) {
        if (comparator == null) return null;
        return taskRepository.findAll(comparator);
    }

    @Override
    public Task add(final String userId, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) return null;
        final Task task = new Task();
        task.setUserId(userId);
        task.setName(name);
        task.setDescription(description);
        taskRepository.add(task);
        return task;
    }
    @Override
    public Task findOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return taskRepository.findOneByName(userId, name);

    }

    @Override
    public Task removeOneByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        return taskRepository.removeOneByName(userId, name);
    }

    @Override
    public Task updateTaskById(final String userId, final String id, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneById(userId, id);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateTaskByIndex(final String userId, final Integer index, final String name, final String description) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task startProjectById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Task task = findOneById(userId, id);
        if (task == null) return null;
        task.setStatus(Status.IN_PROGRESS);
        return task;
    }

    @Override
    public Task startProjectByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(Status.IN_PROGRESS);
        return task;
    }

    @Override
    public Task startProjectByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByName(userId, name);
        if (task == null) return null;
        task.setStatus(Status.IN_PROGRESS);
        return task;
    }

    @Override
    public Task finishProjectById(final String userId, final String id) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Task task = findOneById(userId, id);
        if (task == null) return null;
        task.setStatus(Status.COMPLETE);
        return task;
    }

    @Override
    public Task finishProjectByIndex(final String userId, final Integer index) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null || index < 0) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(Status.COMPLETE);
        return task;
    }

    @Override
    public Task finishProjectByName(final String userId, final String name) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByName(userId, name);
        if (task == null) return null;
        task.setStatus(Status.COMPLETE);
        return task;
    }

    @Override
    public Task changeProjectStatusById(final String userId, final String id, final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        final Task task = findOneById(userId, id);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeProjectStatusByIndex(final String userId, final Integer index, final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (index == null) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeProjectStatusByName(final String userId, final String name, final Status status) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        final Task task = findOneByName(userId, name);
        if (task == null) return null;
        task.setStatus(status);
        return task;
    }

}
