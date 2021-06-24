package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IProjectService;
import ru.vpavlova.tm.api.service.model.ITaskService;
import ru.vpavlova.tm.api.service.model.IUserService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectService projectService = new ProjectService(connectionService);

    @NotNull
    private final IUserService userService = new UserService(propertyService, connectionService);

    @Test
    @Category(DBCategory.class)
    public void addAllProjectsTest() {
        final List<Project> projects = new ArrayList<>();
        final Project project1 = new Project();
        final Project project2 = new Project();
        projects.add(project1);
        projects.add(project2);
        projectService.addAll(projects);
        Assert.assertTrue(projectService.findById(project1.getId()).isPresent());
        Assert.assertTrue(projectService.findById(project2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addProjectTest() {
        final Project project = new Project();
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
        final List<Project> projects = new ArrayList<>();
        final Project projectOne = new Project();
        final Project projectTwo = new Project();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectService.addAll(projects);
        Assert.assertEquals(2, projectService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByIdTest() {
        final Project project1 = new Project();
        final String projectId = project1.getId();
        projectService.add(project1);
        Assert.assertNotNull(projectService.findById(projectId));
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByIndexTest() {
        final Project project = new Project();
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertTrue(projectService.findById(projectId).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByNameTest() {
        final Project project = new Project();
        final @NotNull Optional<User> user = userService.findByLogin("test");
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
        final Project project1 = new Project();
        projectService.add(project1);
        final String projectId = project1.getId();
        projectService.removeById(projectId);
        Assert.assertFalse(projectService.findById(projectId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByIndexTest() {
        final Project project1 = new Project();
        final Project project2 = new Project();
        final Project project3 = new Project();
        final @NotNull Optional<User> user = userService.findByLogin("test");
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
        final Project project = new Project();
        final @NotNull Optional<User> user = userService.findByLogin("test");
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
        final Project project = new Project();
        projectService.add(project);
        projectService.remove(project);
        Assert.assertNotNull(projectService.findById(project.getId()));
    }

}