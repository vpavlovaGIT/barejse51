package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectServiceTest {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final IProjectService projectService = new ProjectService(projectRepository);

    @Test
    @Category(UnitCategory.class)
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
    @Category(UnitCategory.class)
    public void addProjectTest() {
        final Project project = new Project();
        Assert.assertNotNull(projectService.add(project));
    }

    @Test
    @Category(UnitCategory.class)
    public void clearProjectTest() {
        projectService.clear();
        Assert.assertTrue(projectService.findAll().isEmpty());
    }

    @Test
    @Category(UnitCategory.class)
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
    @Category(UnitCategory.class)
    public void findProjectOneByIdTest() {
        final Project project1 = new Project();
        final String projectId = project1.getId();
        projectService.add(project1);
        Assert.assertNotNull(projectService.findById(projectId));
    }

    @Test
    @Category(UnitCategory.class)
    public void findProjectOneByIndexTest() {
        @NotNull final Optional<Project> project = projectRepository.findByIndex("testUser", 0);
        Assert.assertNotNull(project);
    }

    @Test
    @Category(UnitCategory.class)
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
    @Category(UnitCategory.class)
    public void removeProjectOneByIdTest() {
        final Project project1 = new Project();
        projectService.add(project1);
        final String projectId = project1.getId();
        Assert.assertNull(projectService.removeById(projectId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeProjectOneByIdTestByUserId() {
        final Project project = new Project();
        final User user = new User();
        final String userId = user.getId();
        project.setUserId(userId);
        projectService.add(project);
        final String projectId = project.getId();
        Assert.assertNull(projectService.removeById(userId, projectId));
    }

    @Test
    @Category(UnitCategory.class)
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
    @Category(UnitCategory.class)
    public void removeProjectOneByNameTest() {
        final Project project = new Project();
        final User user = new User();
        final String userId = user.getId();
        project.setUserId(userId);
        project.setName("project1");
        projectService.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        Assert.assertNull(projectService.removeByName(userId, name));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeProjectTest() {
        final List<Project> projects = new ArrayList<>();
        for (@NotNull final Project project : projects) {
            Assert.assertNotNull(projectService.removeById(project.getId()));
            Assert.assertNull(projectService.findById(project.getId()));
        }
    }

}
