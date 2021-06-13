package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final ITaskService taskService = new TaskService(connectionService);

    @Test
    @Category(DBCategory.class)
    public void addAllTasksTest() {
        final List<TaskDTO> tasks = new ArrayList<>();
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        tasks.add(task1);
        tasks.add(task2);
        taskService.addAll(tasks);
        Assert.assertTrue(taskService.findById(task1.getId()).isPresent());
        Assert.assertTrue(taskService.findById(task2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addTaskTest() {
        final TaskDTO task = new TaskDTO();
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
        final List<TaskDTO> tasks = new ArrayList<>();
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        tasks.add(task1);
        tasks.add(task2);
        taskService.addAll(tasks);
        Assert.assertEquals(2, taskService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIdTest() {
        final TaskDTO task1 = new TaskDTO();
        final String taskId = task1.getId();
        taskService.add(task1);
        Assert.assertNotNull(taskService.findById(taskId));
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIndexTest() {
        @NotNull final Optional<TaskDTO> task = taskService.findByIndex("testUser", 0);
        Assert.assertNotNull(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIndexTestByUserId() {
        final TaskDTO task = new TaskDTO();
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        task.setUserId(userId);
        taskService.add(task);
        final String taskId = task.getId();
        Assert.assertTrue(taskService.findById(userId, taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByNameTest() {
        final TaskDTO task = new TaskDTO();
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        task.setUserId(userId);
        task.setName("project1");
        taskService.add(task);
        final String name = task.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(taskService.findByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByIdTest() {
        final TaskDTO task = new TaskDTO();
        taskService.add(task);
        final String taskId = task.getId();
        taskService.removeById(taskId);
        Assert.assertFalse(taskService.findById(taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByIndexTest() {
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        final TaskDTO task3 = new TaskDTO();
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        task1.setUserId(userId);
        task2.setUserId(userId);
        task3.setUserId(userId);
        taskService.add(task1);
        taskService.add(task2);
        taskService.add(task3);
        Assert.assertTrue(taskService.findByIndex(userId, 0).isPresent());
        Assert.assertTrue(taskService.findByIndex(userId, 1).isPresent());
        Assert.assertTrue(taskService.findByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByNameTest() {
        final TaskDTO task = new TaskDTO();
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        task.setUserId(userId);
        task.setName("task1");
        final String name = task.getName();
        taskService.add(task);
        taskService.removeByName(name, userId);
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskTest() {
        final TaskDTO task = new TaskDTO();
        taskService.add(task);
        taskService.remove(task);
        Assert.assertNotNull(taskService.findById(task.getId()));
    }

}
