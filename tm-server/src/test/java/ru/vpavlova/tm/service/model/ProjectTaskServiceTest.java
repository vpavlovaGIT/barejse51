package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IProjectService;
import ru.vpavlova.tm.api.service.model.IProjectTaskService;
import ru.vpavlova.tm.api.service.model.ITaskService;
import ru.vpavlova.tm.api.service.model.IUserService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;
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

    @Test
    @Category(DBCategory.class)
    public void bindTaskByProjectIdTest() {
        final Task task = new Task();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final Project project = projectService.add(userId, "testBind", "-");
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
        final Project project = new Project();
        final Task task1 = new Task();
        final Task task2 = new Task();
        final String projectId = project.getId();
        final @NotNull Optional<User> user = userService.findByLogin("test");
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
        final Task task = new Task();
        final Task task2 = new Task();
        final Task task3 = new Task();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final Project project = projectService.add(userId, "testBind", "-");
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
        final Task task = new Task();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final String taskId = task.getId();
        task.setUser(user.get());
        taskService.add(task);
        projectTaskService.unbindTaskFromProject(userId, taskId);
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
        projectTaskService.unbindTaskFromProject(userId, taskId);
        final Task task2 = taskService.findOneById(userId, taskId).get();
        Assert.assertNull(task2.getProject());
    }

}
