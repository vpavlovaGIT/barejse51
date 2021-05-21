package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.repository.ITaskRepository;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.Task;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.ProjectRepository;
import ru.vpavlova.tm.repository.TaskRepository;

import java.util.List;

public class ProjectTaskServiceTest {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final ITaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(projectRepository, taskRepository);

    @NotNull
    private final ITaskService taskService = new TaskService(taskRepository);

    @Test
    @Category(UnitCategory.class)
    public void bindTaskByProjectIdTest() {
        final Task task = new Task();
        final User user = new User();
        final Project project = new Project();
        final String userId = user.getId();
        final String projectId = project.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        Assert.assertTrue(projectTaskService.bindTaskByProject(userId, projectId, taskId).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void findAllByProjectIdTest() {
        final User user = new User();
        final String userId = user.getId();
        final Project project = new Project();
        final Task task1 = new Task();
        final Task task2 = new Task();
        final String projectId = project.getId();
        task1.setUserId(userId);
        task2.setUserId(userId);
        task1.setProjectId(projectId);
        task2.setProjectId(projectId);
        final List<Task> taskList = projectTaskService.findAllTaskByProjectId(projectId, userId);
        Assert.assertEquals(2, taskList.size());
    }

    @Test
    @Category(UnitCategory.class)
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
        taskService.add(task);
        taskService.add(task2);
        taskService.add(task3);
        Assert.assertEquals(3, projectTaskService.findAllTaskByProjectId(userId, projectId).size());
        projectTaskService.removeProjectById(userId, projectId);
        Assert.assertTrue(projectTaskService.findAllTaskByProjectId(userId, projectId).isEmpty());
    }

    @Test
    @Category(UnitCategory.class)
    public void unbindTaskFromProjectIdTest() {
        final Task task = new Task();
        final User user = new User();
        final String userId = user.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        Assert.assertTrue(projectTaskService.unbindTaskFromProject(userId, taskId).isPresent());
        final Task task2 = projectTaskService.unbindTaskFromProject(userId, taskId).get();
        Assert.assertNull(task2.getProjectId());
    }

}