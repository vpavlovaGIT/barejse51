package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IProjectService;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.model.ProjectService;

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
        final List<ProjectDTO> projects = new ArrayList<>();
        final ProjectDTO project1 = new ProjectDTO();
        final ProjectDTO project2 = new ProjectDTO();
        projects.add(project1);
        projects.add(project2);
        projectService.addAll(projects);
        Assert.assertTrue(projectService.findById(project1.getId()).isPresent());
        Assert.assertTrue(projectService.findById(project2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addProjectTest() {
        final ProjectDTO project = new ProjectDTO();
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
        final List<ProjectDTO> projects = new ArrayList<>();
        final ProjectDTO projectOne = new ProjectDTO();
        final ProjectDTO projectTwo = new ProjectDTO();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectService.addAll(projects);
        Assert.assertEquals(2, projectService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByIdTest() {
        final ProjectDTO project1 = new ProjectDTO();
        final String projectId = project1.getId();
        projectService.add(project1);
        Assert.assertNotNull(projectService.findById(projectId));
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByIndexTest() {
        final ProjectDTO project = new ProjectDTO();
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertTrue(projectService.findById(projectId).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findProjectOneByNameTest() {
        final ProjectDTO project = new ProjectDTO();
        final UserDTO user = new UserDTO();
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
        final ProjectDTO project1 = new ProjectDTO();
        projectService.add(project1);
        final String projectId = project1.getId();
        projectService.removeById(projectId);
        Assert.assertFalse(projectService.findById(projectId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeProjectOneByIndexTest() {
        final ProjectDTO project1 = new ProjectDTO();
        final ProjectDTO project2 = new ProjectDTO();
        final ProjectDTO project3 = new ProjectDTO();
        final UserDTO user = new UserDTO();
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
        final ProjectDTO project = new ProjectDTO();
        final UserDTO user = new UserDTO();
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
        final ProjectDTO project = new ProjectDTO();
        projectService.add(project);
        projectService.remove(project);
        Assert.assertNotNull(projectService.findById(project.getId()));
    }

}
