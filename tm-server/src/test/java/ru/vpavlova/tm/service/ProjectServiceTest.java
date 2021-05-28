package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.DBCategory;

import java.util.ArrayList;
import java.util.List;

public class ProjectServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectService projectService = new ProjectService(connectionService);

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
        Assert.assertNotNull(projectService.add(project));
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
        final User user = new User();
        final String userId = user.getId();
        project.setUserId(userId);
        project.setName("project1");
        projectService.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(projectService.findByName(userId, name).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByIdTest() {
        final Project project1 = new Project();
        projectService.add(project1);
        final String projectId = project1.getId();
        Assert.assertNull(projectService.removeById(projectId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByIndexTest() {
        final Project project1 = new Project();
        final Project project2 = new Project();
        final Project project3 = new Project();
        final User user = new User();
        final String userId = user.getId();
        project1.setUserId(userId);
        project2.setUserId(userId);
        project3.setUserId(userId);
        projectService.add(project1);
        projectService.add(project2);
        projectService.add(project3);
        Assert.assertTrue(projectService.findByIndex(userId, 0).isPresent());
        Assert.assertTrue(projectService.findByIndex(userId, 1).isPresent());
        Assert.assertTrue(projectService.findByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByNameTest() {
        final Project project = new Project();
        final User user = new User();
        final String userId = user.getId();
        project.setUserId(userId);
        project.setName("project1");
        final String name = project.getName();
        projectService.add(project);
        projectService.removeByName(name, userId);
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectTest() {
        final List<Project> projects = new ArrayList<>();
        for (@NotNull final Project project : projects) {
            Assert.assertNotNull(projectService.removeById(project.getId()));
            Assert.assertNull(projectService.findById(project.getId()));
        }
    }

}
