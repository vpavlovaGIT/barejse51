package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IProjectDTOService;
import ru.vpavlova.tm.api.service.dto.IProjectTaskDTOService;
import ru.vpavlova.tm.api.service.dto.ITaskDTOService;
import ru.vpavlova.tm.api.service.dto.IUserDTOService;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.dto.TaskDTO;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.Optional;

public class ProjectTaskDTOServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectTaskDTOService projectTaskService = new ProjectTaskDTOService(connectionService);

    @NotNull
    private final ITaskDTOService taskService = new TaskDTOService(connectionService);

    @NotNull
    private final IProjectDTOService projectService = new ProjectDTOService(connectionService);

    @NotNull
    private final IUserDTOService userService = new UserDTOService(propertyService, connectionService);

    @Test
    @Category(DBCategory.class)
    public void bindTaskByProjectIdTest() {
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final ProjectDTO project = projectService.add(userId, "testBind", "-");
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
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final ProjectDTO project = projectService.add(userId, "testFindAll", "-");
        final String projectId = project.getId();
        task.setUserId(userId);
        task.setProjectId(projectId);
        taskService.add(task);
        Assert.assertFalse(projectTaskService.findAllByProjectId(userId, projectId).isEmpty());
        Assert.assertEquals(1, projectTaskService.findAllByProjectId(userId, projectId).size());

        final TaskDTO task2 = new TaskDTO();
        task2.setUserId(userId);
        task2.setProjectId(projectId);
        taskService.add(task2);
        Assert.assertEquals(2, projectTaskService.findAllByProjectId(userId, projectId).size());

        final TaskDTO task3 = new TaskDTO();
        final @NotNull Optional<UserDTO> user2 = userService.findByLogin("test2");
        final String user2Id = user2.get().getId();
        task3.setUserId(user2Id);
        task3.setProjectId(projectId);
        taskService.add(task3);
        Assert.assertEquals(2, projectTaskService.findAllByProjectId(userId, projectId).size());
        Assert.assertEquals(1, projectTaskService.findAllByProjectId(user2Id, projectId).size());

        final TaskDTO task4 = new TaskDTO();
        final ProjectDTO project2 = projectService.add(userId, "testFindAll2", "-");
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
        final TaskDTO task = new TaskDTO();
        final TaskDTO task2 = new TaskDTO();
        final TaskDTO task3 = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final ProjectDTO project = projectService.add(userId, "testBind", "-");
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
        final TaskDTO task = new TaskDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        final String taskId = task.getId();
        task.setUserId(userId);
        taskService.add(task);
        projectTaskService.unbindTaskFromProject(userId, taskId);
        Assert.assertTrue(taskService.findOneById(userId, taskId).isPresent());
        projectTaskService.unbindTaskFromProject(userId, taskId);
        final TaskDTO task2 = taskService.findOneById(userId, taskId).get();
        Assert.assertNull(task2.getProjectId());
    }

}

