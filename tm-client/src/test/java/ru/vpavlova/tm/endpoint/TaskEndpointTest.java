package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.endpoint.EndpointLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.marker.IntegrationCategory;

import java.util.List;

public class TaskEndpointTest {

    @NotNull
    final EndpointLocator endpointLocator = new Bootstrap();

    @Nullable
    private Session session;

    @Nullable
    private Session sessionAdmin;

    @Before
    @SneakyThrows
    public void before() {
        session = endpointLocator.getSessionEndpoint().openSession("test", "test");
        sessionAdmin = endpointLocator.getSessionEndpoint().openSession("admin", "admin");
        endpointLocator.getTaskEndpoint().clearTasks(session);
        endpointLocator.getTaskEndpoint().clearTasks(sessionAdmin);
        endpointLocator.getProjectEndpoint().clear(session);
        endpointLocator.getProjectEndpoint().clear(sessionAdmin);
    }

    @After
    @SneakyThrows
    public void after() {
        endpointLocator.getSessionEndpoint().closeSession(session);
        endpointLocator.getSessionEndpoint().closeSession(sessionAdmin);
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void addTaskTest() {
        final String taskName = "nameTest";
        final String taskDescription = "nameTest";
        endpointLocator.getTaskEndpoint().addTask(session, taskName, taskDescription);
        final Task task = endpointLocator.getTaskEndpoint().findTaskOneByName(session, taskName);
        Assert.assertNotNull(task);
        Assert.assertEquals(taskName, task.getName());
        Assert.assertEquals(taskDescription, task.getDescription());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void bindTaskByProjectTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTestProject");
        final Task taskBind = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertNotNull(taskBind);
        Assert.assertEquals(project.getId(), taskBind.getProjectId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void changeTaskStatusByIdTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        taskEndpoint.changeTaskStatusById(session, task.getId(), Status.COMPLETE);
        final Task taskChange = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.COMPLETE, taskChange.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void changeTaskStatusByIndexTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        final List<Task> tasks = taskEndpoint.findAllTasks(session);
        int position = 0;
        for (Task t : tasks) {
            if (task.getId().equals(t.getId())) break;
            position++;
        }
        taskEndpoint.changeTaskStatusByIndex(session, position, Status.COMPLETE);
        final Task taskChanged = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.COMPLETE, taskChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void changeTaskStatusByNameTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        taskEndpoint.changeTaskStatusByName(session, task.getName(), Status.COMPLETE);
        final Task taskChanged = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.COMPLETE, taskChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findAllTaskTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        Assert.assertEquals(3, taskEndpoint.findAllTasks(session).size());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findTaskOneByIdTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        Assert.assertNotNull(taskEndpoint.findTaskById(session, task.getId()));
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findTaskOneByIndexTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        final Task taskFind = taskEndpoint.findTaskOneByIndex(session, 1);
        Assert.assertNotNull(taskFind);
        Assert.assertEquals(task.getId(), taskFind.getId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findTaskOneByNameTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        final Task taskFind = taskEndpoint.findTaskOneByName(session, "taskTest");
        Assert.assertNotNull(taskFind);
        Assert.assertEquals(task.getId(), taskFind.getId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void finishTaskByIdTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        taskEndpoint.finishTaskById(session, task.getId());
        final Task taskChange = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.COMPLETE, taskChange.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void finishTaskByIndexTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest0", "descrTest");
        final Task task = taskEndpoint.addTask(session, "taskTest", "descrTest");
        taskEndpoint.addTask(session, "taskTest3", "descrTest");
        taskEndpoint.finishTaskByIndex(session, 1);
        final Task taskChange = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.COMPLETE, taskChange.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void finishTaskByNameTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        taskEndpoint.finishTaskByIndex(session, 1);
        final Task taskChanged = taskEndpoint.findTaskOneByName(session, task.getName());
        Assert.assertEquals(Status.COMPLETE, taskChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void removeTaskOneByIdTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        Assert.assertNotNull(taskEndpoint.findTaskById(session, task.getId()));
        endpointLocator.getTaskEndpoint().removeTaskById(session, task.getId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void removeTaskOneByIndexTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        Assert.assertNotNull(taskEndpoint.findTaskById(session, task.getId()));
        endpointLocator.getTaskEndpoint().removeTaskOneByIndex(session, 0);
        Assert.assertNull(taskEndpoint.findTaskById(session, task.getId()));
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void startTaskByIdTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        taskEndpoint.startTaskById(session, task.getId());
        final Task taskChanged = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.IN_PROGRESS, taskChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void startTaskByIndexTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask13");
        taskEndpoint.startTaskByIndex(session, 1);
        final Task taskChanged = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(Status.IN_PROGRESS, taskChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void startTaskByNameTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        taskEndpoint.addTask(session, "taskTest1", "descriptionTestTask1");
        final Task task = taskEndpoint.addTask(session, "taskTest2", "descriptionTestTask2");
        taskEndpoint.addTask(session, "taskTest3", "descriptionTestTask3");
        taskEndpoint.startTaskByName(session, "taskTest");
        final Task taskChanged = taskEndpoint.findTaskOneByName(session, task.getName());
        Assert.assertEquals(Status.IN_PROGRESS, taskChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void unbindTaskFromProjectTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        projectEndpoint.addProject(session, "projectTest", "descrTest");
        taskEndpoint.unbindTaskFromProject(session, task.getId());
        Assert.assertNull(taskEndpoint.findTaskById(session, task.getId()).getProjectId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void updateTaskByIdTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final String newName = "taskTestNew";
        final String newDescription = "descriptionTestNew";
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        taskEndpoint.updateTaskById(session, task.getId(), newName, newDescription);
        final Task taskUpdate = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(newName, taskUpdate.getName());
        Assert.assertEquals(newDescription, taskUpdate.getDescription());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void updateTaskByIndexTest() {
        final TaskEndpoint taskEndpoint = endpointLocator.getTaskEndpoint();
        final String newName = "taskTestNew";
        final String newDescription = "descriptionTestNew";
        final Task task = taskEndpoint.addTask(session, "taskTest", "descriptionTestTask");
        taskEndpoint.updateTaskByIndex(session, 0, newName, newDescription);
        final Task taskUpdate = taskEndpoint.findTaskById(session, task.getId());
        Assert.assertEquals(newName, taskUpdate.getName());
        Assert.assertEquals(newDescription, taskUpdate.getDescription());
    }

}
