package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.dto.IProjectService;
import ru.vpavlova.tm.api.service.dto.ITaskService;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.dto.Project;
import ru.vpavlova.tm.dto.User;
import ru.vpavlova.tm.marker.DBCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IConnectionService connectionService = serviceLocator.getConnectionService();

    @NotNull
    private final IProjectService projectService = serviceLocator.getProjectDTOService();

    @NotNull
    private final IUserService userService = serviceLocator.getUserDTOService();

    @NotNull
    private final ITaskService taskService = serviceLocator.getTaskDTOService();

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
    public void addAllTest() {
        final List<Project> projects = new ArrayList<>();
        final Project project1 = new Project();
        final Project project2 = new Project();
        projects.add(project1);
        projects.add(project2);
        projectService.addAll(projects);
        Assert.assertTrue(projectService.findOneById(project1.getId()).isPresent());
        Assert.assertTrue(projectService.findOneById(project2.getId()).isPresent());
        projectService.remove(projects.get(0));
        projectService.remove(projects.get(1));
    }

    @Test
    @Category(DBCategory.class)
    public void addTest() {
        final Project project = new Project();
        projectService.add(project);
        Assert.assertNotNull(projectService.findOneById(project.getId()));
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void clearTest() {
        projectService.clear();
        Assert.assertTrue(projectService.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAll() {
        final int projectSize = projectService.findAll().size();
        final List<Project> projects = new ArrayList<>();
        final Project project1 = new Project();
        final Project project2 = new Project();
        projects.add(project1);
        projects.add(project2);
        projectService.addAll(projects);
        Assert.assertEquals(2 + projectSize, projectService.findAll().size());
        projectService.remove(project1);
        projectService.remove(project2);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIdTest() {
        final Project project = new Project();
        final String projectId = project.getId();
        projectService.add(project);
        Assert.assertNotNull(projectService.findOneById(projectId));
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final Project project = new Project();
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertTrue(projectService.findOneById(projectId).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTestByUserId() {
        final Project project = new Project();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUserId(userId);
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertTrue(projectService.findOneById(userId, projectId).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByNameTest() {
        final Project project = new Project();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUserId(userId);
        project.setName("pr1");
        projectService.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(projectService.findOneByName(userId, name).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTest() {
        final Project project = new Project();
        projectService.add(project);
        final String projectId = project.getId();
        projectService.removeOneById(projectId);
        Assert.assertFalse(projectService.findOneById(projectId).isPresent());
    }


    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTestByUserId() {
        final Project project = new Project();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUserId(userId);
        projectService.add(project);
        final String projectId = project.getId();
        projectService.removeOneById(userId, projectId);
        Assert.assertFalse(projectService.findOneById(userId, projectId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIndexTest() {
        final Project project1 = new Project();
        final Project project2 = new Project();
        final Project project3 = new Project();
        final @NotNull Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project1.setUserId(userId);
        project2.setUserId(userId);
        project3.setUserId(userId);
        projectService.add(project1);
        projectService.add(project2);
        projectService.add(project3);
        Assert.assertTrue(projectService.findOneByIndex(userId, 0).isPresent());
        Assert.assertTrue(projectService.findOneByIndex(userId, 1).isPresent());
        Assert.assertTrue(projectService.findOneByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByNameTest() {
        final Project project = new Project();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUserId(userId);
        project.setName("pr1");
        projectService.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        projectService.removeOneByName(userId, name);
        Assert.assertFalse(projectService.findOneByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTest() {
        final Project project = new Project();
        projectService.add(project);
        projectService.remove(project);
        Assert.assertNotNull(projectService.findOneById(project.getId()));
    }

    @Test
    @Category(DBCategory.class)
    public void removeTestByUserIdAndObject() {
        final Project project = new Project();
        @NotNull final Optional<User> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUserId(userId);
        projectService.add(project);
        projectService.remove(userId, project);
        Assert.assertFalse(projectService.findOneById(userId, project.getId()).isPresent());
    }

}
