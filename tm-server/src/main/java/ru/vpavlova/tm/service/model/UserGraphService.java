package ru.vpavlova.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.model.IUserGraphRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IUserGraphService;
import ru.vpavlova.tm.entity.UserGraph;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.*;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.repository.model.UserGraphRepository;
import ru.vpavlova.tm.util.HashUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public final class UserGraphService extends AbstractGraphService<UserGraph> implements IUserGraphService {

    @NotNull
    private final IPropertyService propertyService;

    public UserGraphService(
            @NotNull IPropertyService propertyService,
            @NotNull IConnectionService connectionService) {
        super(connectionService);
        this.propertyService = propertyService;
    }

    @Override
    @SneakyThrows
    public void add(@Nullable final UserGraph user) {
        if (user == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.add(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<UserGraph> findByLogin(
            @Nullable final String login
    ) {
        if (login.isEmpty()) throw new EmptyLoginException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
        return userRepository.findByLogin(login);
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final UserGraph entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.removeById(entity.getId());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void removeByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.removeByLogin(login);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void create(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        @NotNull final UserGraph user = new UserGraph();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
    }

    @Override
    public void create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final String email
    ) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        @NotNull final UserGraph user = new UserGraph();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setEmail(email);
        add(user);
    }

    @Override
    @SneakyThrows
    public void setPassword(
            @Nullable final String userId, @Nullable final String password
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (password.isEmpty()) throw new EmptyPasswordException();
        @NotNull final Optional<UserGraph> user = findById(userId);
        if (!user.isPresent()) return;
        @Nullable final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) return;
        user.get().setPasswordHash(hash);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.update(user.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final Role role
    ) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        if (role == null) throw new EmptyRoleException();
        @NotNull final UserGraph user = new UserGraph();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setRole(role);
        add(user);
    }

    @Override
    @SneakyThrows
    public void updateUser(
            @Nullable final String userId,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final Optional<UserGraph> user = findById(userId);
        if (!user.isPresent()) throw new ObjectNotFoundException();
        user.get().setFirstName(firstName);
        user.get().setLastName(lastName);
        user.get().setMiddleName(middleName);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.update(user.get());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void lockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final Optional<UserGraph> userOptional = findByLogin(login);
        if (!userOptional.isPresent()) throw new UserNotFoundException();
        @NotNull final UserGraph user = userOptional.get();
        user.setLocked(true);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.update(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void unlockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final Optional<UserGraph> userOptional = findByLogin(login);
        if (!userOptional.isPresent()) throw new UserNotFoundException();
        @NotNull final UserGraph user = userOptional.get();
        user.setLocked(false);
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.update(user);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void addAll(@Nullable List<UserGraph> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            entities.forEach(userRepository::add);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }


    @Override
    @SneakyThrows
    public void clear() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.clear();
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public List<UserGraph> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
        return userRepository.findAll();
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<UserGraph> findById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
        return userRepository.findById(id);
    }

    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final IUserGraphRepository userRepository = new UserGraphRepository(entityManager);
            userRepository.removeById(id);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
