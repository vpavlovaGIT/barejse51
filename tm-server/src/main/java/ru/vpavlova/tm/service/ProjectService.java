package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IBusinessRepository;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.exception.empty.EmptyDescriptionException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.repository.ProjectRepository;

import java.sql.Connection;

public class ProjectService extends AbstractBusinessService<Project> implements IProjectService {

    public ProjectService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    public IBusinessRepository<Project> getRepository(@NotNull Connection connection) {
        return new ProjectRepository(connection);
    }

    @NotNull
    @Override
    @SneakyThrows
    public Project add(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (description.isEmpty()) throw new EmptyDescriptionException();
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setUserId(userId);
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IProjectRepository projectRepository = new ProjectRepository(connection);
            final Project projectAdd = projectRepository.add(project);
            connection.commit();
            return projectAdd;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
