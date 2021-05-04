package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.entity.Project;

import java.util.Comparator;
import java.util.List;

public class ProjectService extends AbstractBusinessService<Project> implements IProjectService {

    @NotNull
    private final IProjectRepository projectRepository;

    public ProjectService(@NotNull final IProjectRepository projectRepository) {
        super(projectRepository);
        this.projectRepository = projectRepository;
    }

    @NotNull
    @Override
    public Project add(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (name == null || name.isEmpty()) throw new EmptyNameException();
        if (description == null || description.isEmpty()) return null;
        @NotNull final Project project = new Project();
        project.setUserId(userId);
        project.setName(name);
        project.setDescription(description);
        projectRepository.add(userId, project);
        return project;
    }

}
