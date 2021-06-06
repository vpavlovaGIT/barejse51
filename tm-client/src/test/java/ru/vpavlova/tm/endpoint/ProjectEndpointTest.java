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

public class ProjectEndpointTest {

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
    public void addProjectTest() {
        final String projectName = "nameTest";
        final String projectDescription = "nameTest";
        endpointLocator.getProjectEndpoint().addProject(session, projectName, projectDescription);
        final Project project = endpointLocator.getProjectEndpoint().findProjectOneByName(session, projectName);
        Assert.assertNotNull(project);
        Assert.assertEquals(projectName, project.getName());
        Assert.assertEquals(projectDescription, project.getDescription());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void changeProjectStatusByIdTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTest");
        projectEndpoint.changeProjectStatusById(session, project.getId(), Status.COMPLETE);
        final Project projectChanged = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.COMPLETE, projectChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void changeProjectStatusByIndexTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTest");
        final List<Project> projects = projectEndpoint.findAllProjects(session);
        int position = 0;
        for (Project t : projects) {
            if (project.getId().equals(t.getId())) break;
            position++;
        }
        projectEndpoint.changeProjectStatusByIndex(session, position, Status.COMPLETE);
        final Project projectChanged = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.COMPLETE, projectChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void changeProjectStatusByNameTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTest");
        projectEndpoint.changeProjectStatusByName(session, project.getName(), Status.COMPLETE);
        final Project projectChanged = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.COMPLETE, projectChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findAllProjectTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        Assert.assertEquals(3, projectEndpoint.findAllProjects(session).size());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findProjectOneByIdTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTest");
        Assert.assertNotNull(projectEndpoint.findProjectById(session, project.getId()));
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findProjectOneByIndexTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        final Project projectFind = projectEndpoint.findProjectByIndex(session, 1);
        Assert.assertNotNull(projectFind);
        Assert.assertEquals(project.getId(), projectFind.getId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findProjectOneByNameTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        final Project projectFind = projectEndpoint.findProjectOneByName(session, "projectTest");
        Assert.assertNotNull(projectFind);
        Assert.assertEquals(project.getId(), projectFind.getId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void finishProjectByIdTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTest");
        projectEndpoint.finishProjectById(session, project.getId());
        final Project projectChange = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.COMPLETE, projectChange.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void finishProjectByIndexTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        projectEndpoint.finishProjectByIndex(session, 1);
        final Project projectChange = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.COMPLETE, projectChange.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void finishProjectByNameTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        projectEndpoint.finishProjectByIndex(session, 1);
        final Project projectChange = projectEndpoint.findProjectOneByName(session, project.getName());
        Assert.assertEquals(Status.COMPLETE, projectChange.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void removeProjectOneByIdTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        Assert.assertNotNull(projectEndpoint.findProjectById(session, project.getId()));
        endpointLocator.getProjectEndpoint().removeProjectById(session, project.getId());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void removeProjectOneByIndexTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        Assert.assertNotNull(projectEndpoint.findProjectById(session, project.getId()));
        endpointLocator.getProjectEndpoint().removeProjectOneByIndex(session, 0);
        Assert.assertNull(projectEndpoint.findProjectById(session, project.getId()));
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void startProjectByIdTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final Project project = projectEndpoint.addProject(session, "projectTest", "descriptionTest");
        projectEndpoint.startProjectById(session, project.getId());
        final Project projectChanged = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.IN_PROGRESS, projectChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void startProjectByIndexTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        projectEndpoint.startProjectByIndex(session, 1);
        final Project projectChanged = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(Status.IN_PROGRESS, projectChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void startProjectByNameTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        projectEndpoint.addProject(session, "projectTest1", "descriptionTest1");
        final Project project = projectEndpoint.addProject(session, "projectTest2", "descriptionTest2");
        projectEndpoint.addProject(session, "projectTest3", "descriptionTest3");
        projectEndpoint.startProjectByName(session, "projectTest");
        final Project projectChanged = projectEndpoint.findProjectOneByName(session, project.getName());
        Assert.assertEquals(Status.IN_PROGRESS, projectChanged.getStatus());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void updateProjectByIdTest() {
        final ProjectEndpoint projectEndpoint = endpointLocator.getProjectEndpoint();
        final String newName = "projectTestNew";
        final String newDescription = "descriptionTestNew";
        final Project project = projectEndpoint.addProject(session, "projectTest", "descrTest");
        projectEndpoint.updateProjectById(session, project.getId(), newName, newDescription);
        final Project projectUpdate = projectEndpoint.findProjectById(session, project.getId());
        Assert.assertEquals(newName, projectUpdate.getName());
        Assert.assertEquals(newDescription, projectUpdate.getDescription());
    }

}
