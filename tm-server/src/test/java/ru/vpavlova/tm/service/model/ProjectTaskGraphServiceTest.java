package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IProjectGraphService;
import ru.vpavlova.tm.api.service.model.IProjectTaskGraphService;
import ru.vpavlova.tm.api.service.model.ITaskGraphService;
import ru.vpavlova.tm.api.service.model.IUserGraphService;
import ru.vpavlova.tm.entity.ProjectGraph;
import ru.vpavlova.tm.entity.TaskGraph;
import ru.vpavlova.tm.entity.UserGraph;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.Optional;

public class ProjectTaskGraphServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectTaskGraphService projectTaskService = new ProjectTaskGraphService(connectionService);

    @NotNull
    private final ITaskGraphService taskService = new TaskGraphService(connectionService);

    @NotNull
    private final IProjectGraphService projectService = new ProjectGraphService(connectionService);

    @NotNull
    private final IUserGraphService userService = new UserGraphService(propertyService, connectionService);

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
        final TaskGraph task = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final ProjectGraph project = projectService.add(userId, "testBind", "-");
        final String projectId = project.getId();
        final String taskId = task.getId();
        task.setUser(user.get());
        taskService.add(task);
        projectTaskService.bindTaskByProject(userId, projectId, taskId);
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllByProjectIdTest() {
        final ProjectGraph project = new ProjectGraph();
        final TaskGraph task1 = new TaskGraph();
        final TaskGraph task2 = new TaskGraph();
        final String projectId = project.getId();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        task1.setUser(user.get());
        task2.setUser(user.get());
        task1.setProject(project);
        task2.setProject(project);
        taskService.add(task1);
        taskService.add(task2);
        Assert.assertFalse(projectTaskService.findAllTaskByProjectId(userId, projectId).isEmpty());
        Assert.assertEquals(1, projectTaskService.findAllTaskByProjectId(userId, projectId).size());
    }

    @Test
    @Category(DBCategory.class)
    public void removeAllByProjectIdTest() {
        final TaskGraph task = new TaskGraph();
        final TaskGraph task2 = new TaskGraph();
        final TaskGraph task3 = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final ProjectGraph project = projectService.add(userId, "testBind", "-");
        final String projectId = project.getId();
        task.setUser(user.get());
        task.setProject(project);
        task2.setUser(user.get());
        task2.setProject(project);
        task3.setUser(user.get());
        task3.setProject(project);
        taskService.add(task);
        taskService.add(task2);
        taskService.add(task3);
        Assert.assertEquals(3, projectTaskService.findAllTaskByProjectId(userId, projectId).size());
        projectTaskService.removeProjectById(userId, projectId);
        Assert.assertTrue(projectTaskService.findAllTaskByProjectId(userId, projectId).isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void unbindTaskFromProjectIdTest() {
        final TaskGraph task = new TaskGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final String taskId = task.getId();
        task.setUser(user.get());
        taskService.add(task);
        projectTaskService.unbindTaskFromProject(userId, taskId);
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
        projectTaskService.unbindTaskFromProject(userId, taskId);
        final TaskGraph task2 = taskService.findOneById(userId, taskId).get();
        Assert.assertNull(task2.getProject());
    }

}
