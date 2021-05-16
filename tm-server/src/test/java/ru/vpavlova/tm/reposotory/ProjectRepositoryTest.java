package ru.vpavlova.tm.reposotory;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryTest {

    @NotNull
    private final IProjectRepository projectRepository = new ProjectRepository();

    @Test
    @Category(UnitCategory.class)
    public void addAllProjectsTest() {
        final List<Project> projects = new ArrayList<>();
        final Project project1 = new Project();
        final Project project2 = new Project();
        projects.add(project1);
        projects.add(project2);
        projectRepository.addAll(projects);
        Assert.assertTrue(projectRepository.findById(project1.getId()).isPresent());
        Assert.assertTrue(projectRepository.findById(project2.getId()).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void addProjectTest() {
        final Project project = new Project();
        Assert.assertNotNull(projectRepository.add(project));
    }

    @Test
    @Category(UnitCategory.class)
    public void clearProjectTest() {
        projectRepository.clear();
        Assert.assertTrue(projectRepository.findAll().isEmpty());
    }

    @Test
    @Category(UnitCategory.class)
    public void findAllProjects() {
        final List<Project> projects = new ArrayList<>();
        final Project projectOne = new Project();
        final Project projectTwo = new Project();
        projects.add(projectOne);
        projects.add(projectTwo);
        projectRepository.addAll(projects);
        Assert.assertEquals(2, projectRepository.findAll().size());
    }

    @Test
    @Category(UnitCategory.class)
    public void findProjectOneByIdTest() {
        final Project project1 = new Project();
        final String projectId = project1.getId();
        projectRepository.add(project1);
        Assert.assertNotNull(projectRepository.findById(projectId));
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
        projectRepository.add(project);
        final String name = project.getName();
        Assert.assertNotNull(name);
        Assert.assertTrue(projectRepository.findByName(userId, name).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void removeProjectByIdTest() {
        final Project project1 = new Project();
        projectRepository.add(project1);
        final String projectId = project1.getId();
        projectRepository.removeById(projectId);
        Assert.assertNull(projectRepository.findById(projectId));
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
        projectRepository.add(project1);
        projectRepository.add(project2);
        projectRepository.add(project3);
        Assert.assertTrue(projectRepository.findByIndex(userId, 0).isPresent());
        Assert.assertTrue(projectRepository.findByIndex(userId, 1).isPresent());
        Assert.assertTrue(projectRepository.findByIndex(userId, 2).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void removeProjectByNameTest() {
        final Project project = new Project();
        final User user = new User();
        final String userId = user.getId();
        project.setUserId(userId);
        project.setName("project1");
        final String name = project.getName();
        projectRepository.add(project);
        projectRepository.removeByName(name, userId);
        Assert.assertNull(projectRepository.findByName(name, userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeProjectTest() {
        final List<Project> projects = new ArrayList<>();
        for (@NotNull final Project project : projects) {
            Assert.assertNotNull(projectRepository.removeById(project.getId()));
            Assert.assertNull(projectRepository.findById(project.getId()));
        }
    }

}
