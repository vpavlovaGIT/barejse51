package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.dto.ITaskService;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.dto.Task;
import ru.vpavlova.tm.dto.User;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;
import ru.vpavlova.tm.service.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IConnectionService connectionService = serviceLocator.getConnectionService();

    @NotNull
    private final ITaskService taskService = serviceLocator.getTaskDTOService();

    @NotNull
    private final IUserService userService = serviceLocator.getUserDTOService();

    {
        TestUtil.initUser();
    }

    @Test
    @Category(DBCategory.class)
    public void addAllTest() {
        final List<Task> tasks = new ArrayList<>();
        final Task task1 = new Task();
        final Task task2 = new Task();
        tasks.add(task1);
        tasks.add(task2);
        taskService.addAll(tasks);
        Assert.assertTrue(taskService.findOneById(task1.getId()).isPresent());
        Assert.assertTrue(taskService.findOneById(task2.getId()).isPresent());
        taskService.remove(tasks.get(0));
        taskService.remove(tasks.get(1));
    }

    @Test
    @Category(DBCategory.class)
    public void addTest() {
        final Task task = new Task();
        taskService.add(task);
        Assert.assertNotNull(taskService.findOneById(task.getId()));
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void clearTest() {
        taskService.clear();
        Assert.assertTrue(taskService.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAll() {
        final int taskSize = taskService.findAll().size();
        final List<Task> tasks = new ArrayList<>();
        final Task task1 = new Task();
        final Task task2 = new Task();
        tasks.add(task1);
        tasks.add(task2);
        taskService.addAll(tasks);
        Assert.assertEquals(2 + taskSize, taskService.findAll().size());
        taskService.remove(task1);
        taskService.remove(task2);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIdTest() {
        final Task task = new Task();
        final String taskId = task.getId();
        taskService.add(task);
        Assert.assertNotNull(taskService.findOneById(taskId));
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final Task task = new Task();
        taskService.add(task);
        final String taskId = task.getId();
        Assert.assertTrue(taskService.findOneById(taskId).isPresent());
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTestByUserId() {
        final Task task = new Task();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUserId(userId);
        taskService.add(task);
        final String taskId = task.getId();
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByNameTest() {
        final Task task = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUserId(userId);
        task.setName("pr1");
        taskService.add(task);
        final String name = task.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(taskService.findOneByName(userId, name).isPresent());
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTest() {
        final Task task = new Task();
        taskService.add(task);
        final String taskId = task.getId();
        taskService.removeOneById(taskId);
        Assert.assertFalse(taskService.findOneById(taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTestByUserId() {
        final Task task = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUserId(userId);
        taskService.add(task);
        final String taskId = task.getId();
        taskService.removeOneById(userId, taskId);
        Assert.assertFalse(taskService.findOneById(taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIndexTest() {
        final Task task1 = new Task();
        final Task task2 = new Task();
        final Task task3 = new Task();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task1.setUserId(userId);
        task2.setUserId(userId);
        task3.setUserId(userId);
        taskService.add(task1);
        taskService.add(task2);
        taskService.add(task3);
        Assert.assertTrue(taskService.findOneByIndex(userId, 0).isPresent());
        Assert.assertTrue(taskService.findOneByIndex(userId, 1).isPresent());
        Assert.assertTrue(taskService.findOneByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByNameTest() {
        final Task task = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUserId(userId);
        task.setName("pr1");
        taskService.add(task);
        final String name = task.getName();
        Assert.assertNotNull(name);
        taskService.removeOneByName(userId, name);
        Assert.assertFalse(taskService.findOneByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTest() {
        final Task task = new Task();
        taskService.add(task);
        taskService.remove(task);
        Assert.assertNotNull(taskService.findOneById(task.getId()));
    }

}
