package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.IUserService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.*;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.exception.user.EmailExistsException;
import ru.vpavlova.tm.exception.user.LoginExistsException;
import ru.vpavlova.tm.util.HashUtil;

import java.util.Optional;

public class UserService extends AbstractService<User> implements IUserService {

    @NotNull
    private final IUserRepository userRepository;

    public UserService(@NotNull final IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @NotNull
    @Override
    public Optional<User> findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        return userRepository.findByLogin(login);
    }

    @NotNull
    @Override
    public Optional<User> findByEmail(@Nullable final String email) {
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        return userRepository.findByEmail(email);
    }


    @Override
    public boolean isLoginExist(@Nullable final String login) {
        if (login == null || login.isEmpty()) return false;
        return findByLogin(login).isPresent();
    }

    @Override
    public boolean isEmailExist(@Nullable final String email) {
        if (email == null || email.isEmpty()) return false;
        return findByEmail(email).isPresent();
    }

    @NotNull
    @Override
    public User removeByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        return userRepository.removeByLogin(login);
    }

    @NotNull
    @Override
    public User create(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        @NotNull final User user = new User();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        return userRepository.add(user);
    }

    @NotNull
    @Override
    public User create(
            @Nullable final String login,
            @Nullable final String password,
            @Nullable final String email
    ) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        if (isLoginExist(login)) throw new LoginExistsException();
        if (isEmailExist(email)) throw new EmailExistsException();
        @NotNull final User user = create(login, password);
        if (user == null) return null;
        user.setEmail(email);
        return user;
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

    @NotNull
    @Override
    public Optional<User> setPassword(
            @Nullable final String userId,
            @Nullable final String password) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        @NotNull final Optional<User> user = findById(userId);
        @Nullable final String hash = HashUtil.salt(password);
        user.ifPresent(u -> u.setPasswordHash(hash));
        return user;
    }

    @NotNull
    @Override
    public Optional<User> updateUser(
            @Nullable final String userId,
            @Nullable final String firstName,
            @Nullable final String lastName,
            @Nullable final String middleName
    ) {
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
        @NotNull final Optional<User> user = findById(userId);
        user.ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setMiddleName(middleName);
        });
        return user;
    }

    @NotNull
    @Override
    public Optional<User> lockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final Optional<User> user = userRepository.findByLogin(login);
        user.ifPresent(u -> u.setLocked(true));
        user.orElseThrow(UserNotFoundException::new);
        return user;
    }

    @NotNull
    @Override
    public Optional<User> unlockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final Optional<User> user = userRepository.findByLogin(login);
        user.ifPresent(u -> u.setLocked(false));
        user.orElseThrow(UserNotFoundException::new);
        return user;
    }

}
