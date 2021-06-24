package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.ITaskDTOService;
import ru.vpavlova.tm.api.service.dto.IUserDTOService;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDTOServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final ITaskDTOService taskService = new TaskDTOService(connectionService);

    @NotNull
    private final IUserDTOService userService = new UserDTOService(propertyService, connectionService);

    @Test
    @Category(DBCategory.class)
    public void addAllTest() {
        final List<TaskDTO> tasks = new ArrayList<>();
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
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
        final TaskDTO task = new TaskDTO();
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
        final List<TaskDTO> tasks = new ArrayList<>();
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
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
        final TaskDTO task = new TaskDTO();
        final String taskId = task.getId();
        taskService.add(task);
        Assert.assertNotNull(taskService.findOneById(taskId));
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final TaskDTO task = new TaskDTO();
        taskService.add(task);
        final String taskId = task.getId();
        Assert.assertTrue(taskService.findOneById(taskId).isPresent());
        taskService.remove(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTestByUserId() {
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final TaskDTO task = new TaskDTO();
        taskService.add(task);
        final String taskId = task.getId();
        taskService.removeOneById(taskId);
        Assert.assertFalse(taskService.findOneById(taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTestByUserId() {
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        final TaskDTO task3 = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final TaskDTO task = new TaskDTO();
        taskService.add(task);
        taskService.remove(task);
        Assert.assertNotNull(taskService.findOneById(task.getId()));
    }

}
