package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IProjectRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IProjectService;
import ru.vpavlova.tm.enumerated.Status;
import ru.vpavlova.tm.exception.empty.EmptyDescriptionException;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.empty.EmptyNameException;
import ru.vpavlova.tm.entity.Project;
import ru.vpavlova.tm.exception.empty.EmptyUserIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.system.IndexIncorrectException;

import java.util.List;
import java.util.Optional;

public class ProjectService extends AbstractService<Project> implements IProjectService {

    public ProjectService(@NotNull IConnectionService connectionService) {
        super(connectionService);
    }

    @Override
    @SneakyThrows
    public void add(@Nullable final Project entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();

        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.add(entity);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.add(project);
            sqlSession.commit();
            return project;
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void addAll(@Nullable List<Project> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            entities.forEach(projectRepository::add);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.clear();
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void remove(@Nullable final Project project) {
        if (project == null) throw new ObjectNotFoundException();
        final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.removeById(project.getId());
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<Project> findAll() {
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        return projectRepository.findAll();
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Project> findOneById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        return projectRepository.findOneByIdAndUserId(userId, id);
    }


    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.removeById(id);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @NotNull
    @Override
    public Optional<Project> findById(@Nullable String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        return projectRepository.findById(id);
    }

    @NotNull
    @SneakyThrows
    @Override
    public Optional<Project> findByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        return projectRepository.findByIndex(userId, index);
    }

    @NotNull
    @SneakyThrows
    @Override
    public Optional<Project> findByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (name.isEmpty()) throw new EmptyNameException();
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
        return projectRepository.findByName(userId, name);
    }

    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.removeOneByIdAndUserId(userId, id);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            @NotNull Optional<Project> project = projectRepository.findByIndex(userId, index);
            if (!project.isPresent()) throw new UserNotFoundException();
            projectRepository.removeOneByIdAndUserId(userId, project.get().getId());
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.removeByName(userId, name);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void changeStatusById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final Status status
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Project> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, id, status);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void changeStatusByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Project> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, entity.get().getId(), status);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void changeStatusByName(
            @Nullable final String userId,
            @Nullable final String name,
            @Nullable final Status status
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        if (status == null) throw new ObjectNotFoundException();
        @NotNull final Optional<Project> entity = findByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(status);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, entity.get().getId(), status);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @SneakyThrows
    @Override
    public void finishById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final Optional<Project> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, id, Status.COMPLETE);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @SneakyThrows
    @Override
    public void finishByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<Project> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, entity.get().getId(), Status.COMPLETE);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void finishByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.COMPLETE);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, entity.get().getId(), Status.COMPLETE);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void startById(
            @Nullable final String userId, @Nullable final String id
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final Optional<Project> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, id, Status.IN_PROGRESS);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void startByIndex(
            @Nullable final String userId, @Nullable final Integer index
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        @NotNull final Optional<Project> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, entity.get().getId(), Status.IN_PROGRESS);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @SneakyThrows
    @Override
    public void startByName(
            @Nullable final String userId, @Nullable final String name
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findByName(userId, name);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setStatus(Status.IN_PROGRESS);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.changeStatusById(userId, entity.get().getId(), Status.IN_PROGRESS);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void updateById(
            @Nullable final String userId,
            @Nullable final String id,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (id.isEmpty()) throw new EmptyIdException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findOneById(userId, id);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.updateById(userId, id, name, description);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @SneakyThrows
    @Override
    public void updateByIndex(
            @Nullable final String userId,
            @Nullable final Integer index,
            @Nullable final String name,
            @Nullable final String description
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (index < 0) throw new IndexIncorrectException();
        if (name.isEmpty()) throw new EmptyNameException();
        @NotNull final Optional<Project> entity = findByIndex(userId, index);
        if (!entity.isPresent()) throw new ObjectNotFoundException();
        entity.get().setName(name);
        entity.get().setDescription(description);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IProjectRepository projectRepository = sqlSession.getMapper(IProjectRepository.class);
            projectRepository.updateById(userId, entity.get().getId(), name, description);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
