package ru.vpavlova.tm.service;

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

    private final IUserRepository userRepository;

    public UserService(final IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        return userRepository.findByEmail(email);
    }


    @Override
    public boolean isLoginExist(final String login) {
        if (login == null || login.isEmpty()) return false;
        return findByLogin(login).isPresent();
    }

    @Override
    public boolean isEmailExist(final String email) {
        if (email == null || email.isEmpty()) return false;
        return findByEmail(email).isPresent();
    }

    @Override
    public User removeByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        return userRepository.removeByLogin(login);
    }

    @Override
    public User create(final String login, final String password) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        final User user = new User();
        user.setRole(Role.USER);
        user.setLogin(login);
        user.setPasswordHash(HashUtil.salt(password));
        return userRepository.add(user);
    }

    @Override
    public User create(final String login, final String password, final String email) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        if (isLoginExist(login)) throw new LoginExistsException();
        if (isEmailExist(email)) throw new EmailExistsException();
        final User user = create(login, password);
        if (user == null) return null;
        user.setEmail(email);
        return user;
    }

    @Override
    public User create(final String login, final String password, final Role role) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        if (role == null) throw new EmptyRoleException();
        final User user = create(login, password);
        if (user == null) return null;
        user.setRole(role);
        return user;
    }

    @Override
    public Optional<User> setPassword(final String userId, final String password) {
        if (userId == null || userId.isEmpty()) throw new EmptyIdException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        final Optional<User> user = findById(userId);
        final String hash = HashUtil.salt(password);
        user.ifPresent(u -> u.setPasswordHash(hash));
        return user;
    }

    @Override
    public Optional<User> updateUser(final String userId, final String firstName, final String lastName, final String middleName) {
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
        final Optional<User> user = findById(userId);
        user.ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setMiddleName(middleName);
        });
        return user;
    }

    @Override
    public Optional<User> lockUserByLogin(String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        final Optional<User> user = userRepository.findByLogin(login);
        user.ifPresent(u -> u.setLocked(true));
        user.orElseThrow(UserNotFoundException::new);
        return user;
    }

    @Override
    public Optional<User> unlockUserByLogin(String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        final Optional<User> user = userRepository.findByLogin(login);
        user.ifPresent(u -> u.setLocked(false));
        user.orElseThrow(UserNotFoundException::new);
        return user;
    }

}
