package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IProjectTaskService;
import ru.vpavlova.tm.api.service.ITaskService;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;

import java.util.List;

public class ProjectTaskServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectTaskService projectTaskService = new ProjectTaskService(connectionService);

    @NotNull
    private final ITaskService taskService = new TaskService(connectionService);

    @Test
    @Category(DBCategory.class)
    public void bindTaskByProjectIdTest() {
        final TaskDTO task = new TaskDTO();
        final UserDTO user = new UserDTO();
        final ProjectDTO project = new ProjectDTO();
        final String userId = user.getId();
        final String projectId = project.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        projectTaskService.bindTaskByProject(userId, projectId, taskId);
        Assert.assertTrue(taskService.findById(userId, taskId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllByProjectIdTest() {
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        final ProjectDTO project = new ProjectDTO();
        final TaskDTO task1 = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        final String projectId = project.getId();
        task1.setUserId(userId);
        task2.setUserId(userId);
        task1.setProjectId(projectId);
        task2.setProjectId(projectId);
        final List<TaskDTO> taskList = projectTaskService.findAllTaskByProjectId(projectId, userId);
        Assert.assertEquals(2, taskList.size());
    }

    @Test
    @Category(DBCategory.class)
    public void removeAllByProjectIdTest() {
        final TaskDTO task = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        final TaskDTO task3 = new TaskDTO();
        final UserDTO user = new UserDTO();
        final ProjectDTO project = new ProjectDTO();
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
    @Category(DBCategory.class)
    public void unbindTaskFromProjectIdTest() {
        final TaskDTO task = new TaskDTO();
        final UserDTO user = new UserDTO();
        final String userId = user.getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        projectTaskService.unbindTaskFromProject(userId, taskId);
        Assert.assertTrue(taskService.findById(userId, taskId).isPresent());
        projectTaskService.unbindTaskFromProject(userId, taskId);
        final TaskDTO task2 = taskService.findById(userId, taskId).get();
        Assert.assertNull(task2.getProjectId());
    }

}
