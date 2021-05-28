package ru.vpavlova.tm.reposotory;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.TaskRepository;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    final Connection connection = connectionService.getConnection();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository(connection);

    @After
    @SneakyThrows
    public void after() {
        connection.commit();
    }

    @Test
    @Category(DBCategory.class)
    public void addAllTasksTest() {
        final List<Task> tasks = new ArrayList<>();
        final Task task1 = new Task();
        final Task task2 = new Task();
        tasks.add(task1);
        tasks.add(task2);
        taskRepository.addAll(tasks);
        Assert.assertTrue(taskRepository.findById(task1.getId()).isPresent());
        Assert.assertTrue(taskRepository.findById(task2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addTaskTest() {
        final Task task = new Task();
        Assert.assertNotNull(taskRepository.add(task));
    }

    @Test
    @Category(DBCategory.class)
    public void bindTaskByProjectIdTest() {
        final Task task = new Task();
        final User user = new User();
        final Project project = new Project();
        final String userId = user.getId();
        final String projectId = project.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskRepository.add(task);
        Assert.assertTrue(taskRepository.bindTaskByProject(userId, projectId, taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void clearTaskTest() {
        taskRepository.clear();
        Assert.assertTrue(taskRepository.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllTasks() {
        final List<Task> tasks = new ArrayList<>();
        final Task taskOne = new Task();
        final Task taskTwo = new Task();
        tasks.add(taskOne);
        tasks.add(taskTwo);
        taskRepository.addAll(tasks);
        Assert.assertEquals(2, taskRepository.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIdTest() {
        final Task task1 = new Task();
        final String taskId = task1.getId();
        taskRepository.add(task1);
        Assert.assertNotNull(taskRepository.findById(taskId));
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIndexTest() {
        @NotNull final Optional<Task> task = taskRepository.findByIndex("testUser", 0);
        Assert.assertNotNull(task);
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByIndexTestByUserId() {
        final Task task = new Task();
        final User user = new User();
        final String userId = user.getId();
        task.setUserId(userId);
        taskRepository.add(task);
        final String taskId = task.getId();
        Assert.assertTrue(taskRepository.findById(userId, taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void findTaskOneByNameTest() {
        final Task task = new Task();
        final User user = new User();
        final String userId = user.getId();
        task.setUserId(userId);
        task.setName("project1");
        taskRepository.add(task);
        final String name = task.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(taskRepository.findByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeAllByProjectIdTest() {
        final Task task = new Task();
        final Task task2 = new Task();
        final Task task3 = new Task();
        final User user = new User();
        final Project project = new Project();
        final String userId = user.getId();
        final String projectId = project.getId();
        task.setUserId(userId);
        task.setProjectId(projectId);
        task2.setUserId(userId);
        task2.setProjectId(projectId);
        task3.setUserId(userId);
        task3.setProjectId(projectId);
        taskRepository.add(task);
        taskRepository.add(task2);
        taskRepository.add(task3);
        Assert.assertEquals(3, taskRepository.findAllByProjectId(userId, projectId).size());
        taskRepository.removeAllByProjectId(userId, projectId);
        Assert.assertTrue(taskRepository.findAllByProjectId(userId, projectId).isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByIdTest() {
        final Task task1 = new Task();
        taskRepository.add(task1);
        final String taskId = task1.getId();
        taskRepository.removeById(taskId);
        Assert.assertNull(taskRepository.findById(taskId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByIndexTest() {
        final Task task1 = new Task();
        final Task task2 = new Task();
        final Task task3 = new Task();
        final User user = new User();
        final String userId = user.getId();
        task1.setUserId(userId);
        task2.setUserId(userId);
        task3.setUserId(userId);
        taskRepository.add(task1);
        taskRepository.add(task2);
        taskRepository.add(task3);
        Assert.assertTrue(taskRepository.findByIndex(userId, 0).isPresent());
        Assert.assertTrue(taskRepository.findByIndex(userId, 1).isPresent());
        Assert.assertTrue(taskRepository.findByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskOneByNameTest() {
        final Task task = new Task();
        final User user = new User();
        final String userId = user.getId();
        task.setUserId(userId);
        task.setName("project1");
        final String name = task.getName();
        taskRepository.add(task);
        taskRepository.removeByName(name, userId);
        Assert.assertNull(taskRepository.findByName(name, userId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeTaskTest() {
        final List<Task> tasks = new ArrayList<>();
        for (@NotNull final Task task : tasks) {
            Assert.assertNotNull(taskRepository.removeById(task.getId()));
            Assert.assertNull(taskRepository.findById(task.getId()));
        }
    }

    @Test
    @Category(DBCategory.class)
    public void unbindTaskFromProjectIdTest() {
        final Task task = new Task();
        final User user = new User();
        final String userId = user.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskRepository.add(task);
        Assert.assertTrue(taskRepository.unbindTaskFromProject(userId, taskId).isPresent());
        final Task task2 = taskRepository.unbindTaskFromProject(userId, taskId).get();
        Assert.assertNull(task2.getProjectId());
    }

}
