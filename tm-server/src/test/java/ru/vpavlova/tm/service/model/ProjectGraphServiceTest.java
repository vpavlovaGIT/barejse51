package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.model.IProjectGraphService;
import ru.vpavlova.tm.api.service.model.ITaskGraphService;
import ru.vpavlova.tm.api.service.model.IUserGraphService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.entity.ProjectGraph;
import ru.vpavlova.tm.entity.UserGraph;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;
import ru.vpavlova.tm.service.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectGraphServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IConnectionService connectionService = serviceLocator.getConnectionService();

    @NotNull
    private final IProjectGraphService projectService = serviceLocator.getProjectService();

    @NotNull
    private final IUserGraphService userService = serviceLocator.getUserService();

    @NotNull
    private final ITaskGraphService taskService = serviceLocator.getTaskService();

    {
        TestUtil.initUser();
    }

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
    public void addAllProjectsTest() {
        final List<ProjectGraph> projects = new ArrayList<>();
        final ProjectGraph project1 = new ProjectGraph();
        final ProjectGraph project2 = new ProjectGraph();
        projects.add(project1);
        projects.add(project2);
        projectService.addAll(projects);
        Assert.assertTrue(projectService.findById(project1.getId()).isPresent());
        Assert.assertTrue(projectService.findById(project2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addProjectTest() {
        final ProjectGraph project = new ProjectGraph();
        projectService.add(project);
        Assert.assertNotNull(projectService.findById(project.getId()));
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void clearProjectTest() {
        projectService.clear();
        Assert.assertTrue(projectService.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllProjects() {
        final List<ProjectGraph> projects = new ArrayList<>();
        final ProjectGraph projectOne = new ProjectGraph();
        final ProjectGraph projectTwo = new ProjectGraph();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectService.addAll(projects);
        Assert.assertEquals(2, projectService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByIdTest() {
        final ProjectGraph project1 = new ProjectGraph();
        final String projectId = project1.getId();
        projectService.add(project1);
        Assert.assertNotNull(projectService.findById(projectId));
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByIndexTest() {
        final ProjectGraph project = new ProjectGraph();
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertTrue(projectService.findById(projectId).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByNameTest() {
        final ProjectGraph project = new ProjectGraph();
        final @NotNull Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUser(user.get());
        project.setName("project1");
        projectService.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(projectService.findOneByName(userId, name).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByIdTest() {
        final ProjectGraph project1 = new ProjectGraph();
        projectService.add(project1);
        final String projectId = project1.getId();
        projectService.removeById(projectId);
        Assert.assertFalse(projectService.findById(projectId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByIndexTest() {
        final ProjectGraph project1 = new ProjectGraph();
        final ProjectGraph project2 = new ProjectGraph();
        final ProjectGraph project3 = new ProjectGraph();
        final @NotNull Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project1.setUser(user.get());
        project2.setUser(user.get());
        project3.setUser(user.get());
        projectService.add(project1);
        projectService.add(project2);
        projectService.add(project3);
        Assert.assertTrue(projectService.findOneByIndex(userId, 0).isPresent());
        Assert.assertTrue(projectService.findOneByIndex(userId, 1).isPresent());
        Assert.assertTrue(projectService.findOneByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByNameTest() {
        final ProjectGraph project = new ProjectGraph();
        @NotNull final Optional<UserGraph> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUser(user.get());
        project.setName("pr1");
        projectService.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        projectService.removeOneByName(userId, name);
        Assert.assertFalse(projectService.findOneByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectTest() {
        final ProjectGraph project = new ProjectGraph();
        projectService.add(project);
        projectService.remove(project);
        Assert.assertNotNull(projectService.findById(project.getId()));
    }

}