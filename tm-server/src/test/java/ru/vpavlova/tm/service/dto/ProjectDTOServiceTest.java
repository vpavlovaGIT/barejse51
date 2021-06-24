package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.dto.IProjectDTOService;
import ru.vpavlova.tm.api.service.dto.IProjectTaskDTOService;
import ru.vpavlova.tm.api.service.dto.IUserDTOService;
import ru.vpavlova.tm.dto.ProjectDTO;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDTOServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IProjectDTOService projectService = new ProjectDTOService(connectionService);

    @NotNull
    private final IUserDTOService userService = new UserDTOService(propertyService, connectionService);

    @NotNull
    private final IProjectTaskDTOService projectTaskService = new ProjectTaskDTOService(connectionService);

    @Test
    @Category(DBCategory.class)
    public void addAllTest() {
        final List<ProjectDTO> projects = new ArrayList<>();
        final ProjectDTO project1 = new ProjectDTO();
        final ProjectDTO project2 = new ProjectDTO();
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
        final ProjectDTO project = new ProjectDTO();
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
        final List<ProjectDTO> projects = new ArrayList<>();
        final ProjectDTO project1 = new ProjectDTO();
        final ProjectDTO project2 = new ProjectDTO();
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
        final ProjectDTO project = new ProjectDTO();
        final String projectId = project.getId();
        projectService.add(project);
        Assert.assertNotNull(projectService.findOneById(projectId));
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final ProjectDTO project = new ProjectDTO();
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertTrue(projectService.findOneById(projectId).isPresent());
        projectService.remove(project);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTestByUserId() {
        final ProjectDTO project = new ProjectDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final ProjectDTO project = new ProjectDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final ProjectDTO project = new ProjectDTO();
        projectService.add(project);
        final String projectId = project.getId();
        projectService.removeOneById(projectId);
        Assert.assertFalse(projectService.findOneById(projectId).isPresent());
    }


    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTestByUserId() {
        final ProjectDTO project = new ProjectDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final ProjectDTO project1 = new ProjectDTO();
        final ProjectDTO project2 = new ProjectDTO();
        final ProjectDTO project3 = new ProjectDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final ProjectDTO project = new ProjectDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
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
        final ProjectDTO project = new ProjectDTO();
        projectService.add(project);
        projectService.remove(project);
        Assert.assertNotNull(projectService.findOneById(project.getId()));
    }

    @Test
    @Category(DBCategory.class)
    public void removeTestByUserIdAndObject() {
        final ProjectDTO project = new ProjectDTO();
        final @NotNull Optional<UserDTO> user = userService.findByLogin("test");
        final String userId = user.get().getId();
        project.setUserId(userId);
        projectService.add(project);
        projectService.remove(userId, project);
        Assert.assertFalse(projectService.findOneById(userId, project.getId()).isPresent());
    }

}
