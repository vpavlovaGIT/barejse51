package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IProjectService;
import ru.vpavlova.tm.api.service.dto.IProjectTaskService;
import ru.vpavlova.tm.api.service.dto.ITaskService;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.dto.Project;
import ru.vpavlova.tm.dto.Task;
import ru.vpavlova.tm.dto.User;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.Optional;

public class ProjectTaskServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(connectionService);

    @NotNull
    private final ITaskService taskService = new TaskService(connectionService);

    @NotNull
    private final IProjectService projectService = new ProjectService(connectionService);

    @NotNull
    private final IUserService userService = new UserService(propertyService, connectionService);

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
    public void bindTaskByProjectIdTest() {
        final Task task = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final Project project = projectService.add(userId, "testBind", "-");
        final String projectId = project.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        projectTaskService.bindTaskByProject(userId, projectId, taskId);
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllByProjectIdTest() {
        final Task task = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final Project project = projectService.add(userId, "testFindAll", "-");
        final String projectId = project.getId();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskService.add(task);
        Assert.assertFalse(projectTaskService.findAllByProjectId(userId, projectId).isEmpty());
        Assert.assertEquals(1, projectTaskService.findAllByProjectId(userId, projectId).size());

        final Task task2 = new Task();
        task2.setUserId(userId);
        task2.setProjectId(projectId);
        taskService.add(task2);
        Assert.assertEquals(2, projectTaskService.findAllByProjectId(userId, projectId).size());

        final Task task3 = new Task();
        @NotNull final Optional<User> user2 = userService.findByLogin("test2");
        final String user2Id = user2.get().getId();
        task3.setUserId(user2Id);
        task3.setProjectId(projectId);
        taskService.add(task3);
        Assert.assertEquals(2, projectTaskService.findAllByProjectId(userId, projectId).size());
        Assert.assertEquals(1, projectTaskService.findAllByProjectId(user2Id, projectId).size());

        final Task task4 = new Task();
        final Project project2 = projectService.add(userId, "testFindAll2", "-");
        final String project2Id = project2.getId();
        task4.setUserId(userId);
        task4.setProjectId(project2Id);
        taskService.add(task4);
        Assert.assertEquals(2, projectTaskService.findAllByProjectId(userId, projectId).size());
        Assert.assertEquals(1, projectTaskService.findAllByProjectId(userId, project2Id).size());
    }

    @Test
    @Category(DBCategory.class)
    public void removeAllByProjectIdTest() {
        final Task task = new Task();
        final Task task2 = new Task();
        final Task task3 = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final Project project = projectService.add(userId, "testBind", "-");
        final String projectId = project.getId();
        task.setUserId(userId);
        task.setProjectId(projectId);
        task2.setUserId(userId);
        task2.setProjectId(projectId);
        task3.setUserId(userId);
        task3.setProjectId(projectId);
        taskService.add(task);
        taskService.add(task2);
        taskService.add(task3);
        Assert.assertEquals(3, projectTaskService.findAllByProjectId(userId, projectId).size());
        projectTaskService.removeProjectById(userId, projectId);
        Assert.assertTrue(projectTaskService.findAllByProjectId(userId, projectId).isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void unbindTaskFromProjectIdTest() {
        final Task task = new Task();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        projectTaskService.unbindTaskFromProject(userId, taskId);
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
        projectTaskService.unbindTaskFromProject(userId, taskId);
        final Task task2 = taskService.findOneById(userId, taskId).get();
        Assert.assertNull(task2.getProjectId());
    }

}

