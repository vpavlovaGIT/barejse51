package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.IUserService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.*;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.exception.user.EmailExistsException;
import ru.vpavlova.tm.exception.user.LoginExistsException;
import ru.vpavlova.tm.util.HashUtil;

import java.util.List;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return userRepository.removeById(id);
    }

    @Override
    public User findByLogin(final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        return userRepository.findByLogin(login);
    }


    @Override
    public User findByEmail(final String email) {
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isLoginExists(final String login) {
        if (login == null || login.isEmpty()) return false;
        return findByLogin(login) != null;
    }

    @Override
    public boolean isEmailExists(final String email) {
        if (email == null || email.isEmpty()) return false;
        return findByEmail(email) != null;
    }

    @Override
    public User removeUser(final User user) {
        if (user == null) return null;
        return userRepository.removeUser(user);
    }

    @Override
    public User removeById(final String id) {
        if (id == null || id.isEmpty()) throw new EmptyIdException();
        return userRepository.removeById(id);
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
        if (isLoginExists(login)) throw new LoginExistsException();
        if (isEmailExists(email)) throw new EmailExistsException();
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
    public User setPassword(final String userId, final String password) {
        if (userId == null || userId.isEmpty()) throw new EmptyPasswordException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        final User user = findById(userId);
        if (user == null) return null;
        final String hash = HashUtil.salt(password);
        user.setPasswordHash(hash);
        return user;
    }

    @Override
    public User updateUser(final String userId, final String firstName, final String lastName, final String middleName) {
        if (userId == null || userId.isEmpty()) throw new AccessDeniedException();
        final User user = findById(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        return user;
    }

}
