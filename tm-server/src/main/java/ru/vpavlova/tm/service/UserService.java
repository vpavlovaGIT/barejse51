package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;
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
import ru.vpavlova.tm.util.HashUtil;

import java.util.List;
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

    @Override
    @SneakyThrows
    public void add(@Nullable final User user) {
        if (user == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.add(user);
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
    public User findByLogin(@Nullable final String login) {
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
        return userRepository.findByLogin(login);
    }

    @Override
    @Nullable
    @SneakyThrows
    public User findByEmail(@Nullable final String email) {
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            @Nullable final User user = userRepository.findUserByEmail(email);
            if (user == null) throw new EmptyEmailException();
            return user;
        } catch (final Exception e) {
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final User entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.removeById(entity.getId());
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
    public void removeByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.removeByLogin(login);
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
    public User create(@Nullable final String login, @Nullable final String password) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        if (password == null || password.isEmpty()) throw new EmptyPasswordException();
        @NotNull User user = new User();
        user.setLogin(login);
        user.setPasswordHash(HashUtil.md5(password));
        user.setRole(Role.USER);
        add(user);
        return user;
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

    @Override
    @SneakyThrows
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
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.setPassword(hash, userId);
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
        if (userId.isEmpty()) throw new EmptyUserIdException();
        @NotNull final Optional<User> user = findById(userId);
        if (!user.isPresent()) throw new ObjectNotFoundException();
        user.get().setFirstName(firstName);
        user.get().setLastName(lastName);
        user.get().setMiddleName(middleName);
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.updateUser(user.get());
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
    public void lockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @Nullable final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.lockUser(user);
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
    public void unlockUserByLogin(@Nullable String login) {
        if (login == null || login.isEmpty()) throw new EmptyLoginException();
        @Nullable final User user = findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.unlockUser(user);
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
    public void addAll(@Nullable List<User> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            entities.forEach(userRepository::add);
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
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.clear();
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
    public List<User> findAll() {
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
        return userRepository.findAll();
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<User> findById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
        return userRepository.findOneById(id);
    }

    @Override
    @SneakyThrows
    public void removeById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final SqlSession sqlSession = connectionService.getSqlSession();
        try {
            @NotNull final IUserRepository userRepository = sqlSession.getMapper(IUserRepository.class);
            userRepository.removeById(id);
            sqlSession.commit();
        } catch (@NotNull final Exception e) {
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
