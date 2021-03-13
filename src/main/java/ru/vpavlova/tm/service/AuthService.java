package ru.vpavlova.tm.service;

import ru.vpavlova.tm.api.service.IAuthService;
import ru.vpavlova.tm.api.service.IUserService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.exception.empty.EmptyLoginException;
import ru.vpavlova.tm.exception.empty.EmptyPasswordException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.util.HashUtil;

import java.util.Optional;

public class AuthService implements IAuthService {

    private final IUserService userService;

    private String userId;

    public AuthService(final IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<User> getUser() {
        final String userId = getUserId();
        return userService.findOneById(userId, userId);
    }

    @Override
    public String getUserId() {
        if (userId == null) throw new AccessDeniedException();
        return userId;
    }

    @Override
    public boolean isAuth() {
        return userId == null;
    }

    @Override
    public void logout() {
        userId = null;
    }

    @Override
    public void login(final String login, final String password) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        final Optional<User> user = userService.findByLogin(login);
        if (!user.isPresent()) throw new AccessDeniedException();
        final String hash = HashUtil.salt(password);
        if (!hash.equals(user.get().getPasswordHash())) throw new AccessDeniedException();
        userId = user.get().getId();
    }

    @Override
    public void registry(final String login, final String password, final String email) {
        userService.create(login, password, email);
    }

}
