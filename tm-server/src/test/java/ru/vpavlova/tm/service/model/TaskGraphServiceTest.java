package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.model.ITaskGraphService;
import ru.vpavlova.tm.api.service.model.IUserGraphService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.entity.TaskGraph;
import ru.vpavlova.tm.entity.UserGraph;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskGraphServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IConnectionService connectionService = serviceLocator.getConnectionService();

    @NotNull
    private final ITaskGraphService taskService = serviceLocator.getTaskService();

    @NotNull
    private final IUserGraphService userService = serviceLocator.getUserService();

    {
        TestUtil.initUser();
    }

    @Before
    public void before() {
        connectionService.getEntityManager().getEntityManagerFactory().createEntityManager();
    }

    @After
    public void after() {
        connectionService.getEntityManager().getEntityManagerFactory().close();
    }

    @Test
    @Category(DBCategory.class)
    public void addAllTasksTest() {
        final List<TaskGraph> tasks = new ArrayList<>();
        final TaskGraph task1 = new TaskGraph();
        final TaskGraph task2 = new TaskGraph();
        tasks.add(task1);
        tasks.add(task2);
        taskService.addAll(tasks);
        Assert.assertTrue(taskService.findById(task1.getId()).isPresent());
        Assert.assertTrue(taskService.findById(task2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addTaskTest() {
        final TaskGraph task = new TaskGraph();
        taskService.add(task);
        Assert.assertNotNull(taskService.findById(task.getId()));
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void clearTaskTest() {
        taskService.clear();
        Assert.assertTrue(taskService.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllTasks() {
        final List<TaskGraph> tasks = new ArrayList<>();
        final TaskGraph task1 = new TaskGraph();
        final TaskGraph task2 = new TaskGraph();
        tasks.add(task1);
        tasks.add(task2);
        taskService.addAll(tasks);
        Assert.assertEquals(2, taskService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIdTest() {
        final TaskGraph task1 = new TaskGraph();
        final String taskId = task1.getId();
        taskService.add(task1);
        Assert.assertNotNull(taskService.findById(taskId));
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIndexTest() {
        @NotNull final Optional<TaskGraph> task = taskService.findOneByIndex("testUser", 0);
        Assert.assertNotNull(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIndexTestByUserId() {
        final TaskGraph task = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUser(user.get());
        taskService.add(task);
        final String taskId = task.getId();
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByNameTest() {
        final TaskGraph task = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUser(user.get());
        task.setName("pr1");
        taskService.add(task);
        final String name = task.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(taskService.findOneByName(userId, name).isPresent());
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByIdTest() {
        final TaskGraph task = new TaskGraph();
        taskService.add(task);
        final String taskId = task.getId();
        taskService.removeById(taskId);
        Assert.assertFalse(taskService.findById(taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByIndexTest() {
        final TaskGraph task1 = new TaskGraph();
        final TaskGraph task2 = new TaskGraph();
        final TaskGraph task3 = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task1.setUser(user.get());
        task2.setUser(user.get());
        task3.setUser(user.get());
        taskService.add(task1);
        taskService.add(task2);
        taskService.add(task3);
        Assert.assertTrue(taskService.findOneByIndex(userId, 0).isPresent());
        Assert.assertTrue(taskService.findOneByIndex(userId, 1).isPresent());
        Assert.assertTrue(taskService.findOneByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByNameTest() {
        final TaskGraph task = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task.setUser(user.get());
        task.setName("project1");
        taskService.add(task);
        final String name = task.getName();
        Assert.assertNotNull(name);
        taskService.removeOneByName(userId, name);
        Assert.assertFalse(taskService.findOneByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskTest() {
        final TaskGraph task = new TaskGraph();
        taskService.add(task);
        taskService.remove(task);
        Assert.assertNotNull(taskService.findById(task.getId()));
    }

}
