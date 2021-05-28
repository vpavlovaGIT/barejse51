package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IUserService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.*;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.repository.UserRepository;
import ru.vpavlova.tm.util.HashUtil;

import java.sql.Connection;
import java.util.Optional;

public class UserService extends AbstractService<User> implements IUserService {

    @NotNull
    private final IPropertyService propertyService;

    public UserService(
            @NotNull IPropertyService propertyService,
            @NotNull IConnectionService connectionService) {
        super(connectionService);
        this.propertyService = propertyService;
    }

    public IUserRepository getRepository(@NotNull Connection connection) {
        return new UserRepository(connection);
    }

    @NotNull
    @Override
    public User findByLogin(@Nullable final String login) {
        if (login.isEmpty()) throw new EmptyLoginException();
        @NotNull final Connection connection = connectionService.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        return userRepository.findByLogin(login);
    }

    @Override
    @Nullable
    @SneakyThrows
    public User findByEmail(@Nullable final String email) {
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        @NotNull final Connection connection = connectionService.getConnection();
        @NotNull final IUserRepository userRepository = new UserRepository(connection);
        return userRepository.findByEmail(email);
    }

    @Override
    @SneakyThrows
    public void removeByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IUserRepository userRepository = new UserRepository(connection);
            userRepository.removeByLogin(login);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NotNull
    @Override
    public User create(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        @NotNull final User user = new User();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        return add(user);
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
        @NotNull final User user = new User();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(propertyService, password));
        user.setEmail(email);
        add(user);
    }

    @SneakyThrows
    @Override
    public void setPassword(
            @Nullable final String userId, @Nullable final String password
    ) {
        if (userId.isEmpty()) throw new EmptyUserIdException();
        if (password.isEmpty()) throw new EmptyPasswordException();
        @NotNull final Optional<User> user = findById(userId);
        if (!user.isPresent()) return;
        @Nullable final String hash = HashUtil.salt(propertyService, password);
        if (hash == null) return;
        user.get().setPasswordHash(hash);
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IUserRepository userRepository = new UserRepository(connection);
            userRepository.setPassword(hash, userId);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @NotNull
    @Override
    public User create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final Role role
    ) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        if (role == null) throw new EmptyRoleException();
        @NotNull final User user = create(login, password);
        if (user == null) return null;
        user.setRole(role);
        return user;
    }

    @Override
    @SneakyThrows
    public void updateUser(
            @Nullable final String userId,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) {
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
        @NotNull final Optional<User> user = findById(userId);
        if (!user.isPresent()) throw new ObjectNotFoundException();
        user.get().setFirstName(firstName);
        user.get().setLastName(lastName);
        user.get().setMiddleName(middleName);
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IUserRepository userRepository = new UserRepository(connection);
            userRepository.updateUser(user.get());
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    @SneakyThrows
    public void lockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @Nullable final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IUserRepository userRepository = new UserRepository(connection);
            userRepository.lockUser(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }

    }

    @Override
    @SneakyThrows
    public void unlockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @Nullable final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        @NotNull final Connection connection = connectionService.getConnection();
        try {
            @NotNull final IUserRepository userRepository = new UserRepository(connection);
            userRepository.unlockUser(user);
            connection.commit();
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

}
