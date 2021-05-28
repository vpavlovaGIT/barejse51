package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.ISessionRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ISessionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.entity.UserNotFoundException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.repository.SessionRepository;
import ru.vpavlova.tm.util.HashUtil;

import java.sql.Connection;
import java.util.Optional;

public class SessionService extends AbstractService<Session> implements ISessionService {

    @NotNull
    private final ServiceLocator serviceLocator;

    public SessionService(
            @NotNull IConnectionService connectionService,
            @NotNull ServiceLocator serviceLocator
    ) {
        super(connectionService);
        this.serviceLocator = serviceLocator;
    }

    public ISessionRepository getRepository(@NotNull Connection connection) {
        return new SessionRepository(connection);
    }

    @Nullable
    @Override
    @SneakyThrows
    public Session open(
            @Nullable final String login,
            @Nullable final String password
    ) {
        final boolean check = checkDataAccess(login, password);
        if (!check) return null;
        @Nullable final User user = serviceLocator.getUserService().findByLogin(login);
        if (user == null) return null;
        @NotNull final Session session = new Session();
        session.setUserId(user.getId());
        @Nullable final Session signSession = sign(session);
        if (signSession == null) return null;
        final Connection connection = connectionService.getConnection();
        try {
            final ISessionRepository sessionRepository = new SessionRepository(connection);
            sessionRepository.add(signSession);
            connection.commit();
            return signSession;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public void validate(
            @Nullable Session session,
            @Nullable Role role
    ) {
        if (role == null) throw new AccessDeniedException();
    }

    @Override
    public void validate(@Nullable Session session) {
        if (session == null) throw new AccessDeniedException();
        if (session.getSignature() == null || session.getSignature().isEmpty()) throw new AccessDeniedException();
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new AccessDeniedException();
        @Nullable final Session temp = session.clone();
        if (temp == null) throw new AccessDeniedException();
        @NotNull final String signatureSource = session.getSignature();
        @Nullable final Session sessionTarget = sign(temp);
        if (sessionTarget == null) throw new AccessDeniedException();
        @Nullable final String signatureTarget = sessionTarget.getSignature();
        final boolean check = signatureSource.equals(signatureTarget);
        if (!check) throw new AccessDeniedException();
        final Connection connection = connectionService.getConnection();
        final ISessionRepository sessionRepository = new SessionRepository(connection);
        if (!sessionRepository.contains(session.getId())) throw new AccessDeniedException();
    }

    @Override
    @SneakyThrows
    public void validateAdmin(@Nullable final Session session, @Nullable final Role role) {
        if (session == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        if ((session.getUserId()).isEmpty()) throw new AccessDeniedException();
        validate(session);
        final @NotNull Optional<User> user = serviceLocator.getUserService().findById(session.getUserId());
        if (!user.isPresent()) throw new AccessDeniedException();
        if (user.get().getRole() != Role.ADMIN) throw new AccessDeniedException();
    }

    @Override
    @Nullable
    @SneakyThrows
    public Session close(@Nullable Session session) {
        final Connection connection = connectionService.getConnection();
        try {
            final ISessionRepository sessionRepository = new SessionRepository(connection);
            sessionRepository.removeById(session.getId());
            connection.commit();
            return session;
        } catch (@NotNull final Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean checkDataAccess(
            @Nullable final String login,
            @Nullable final String password
    ) {
        if (login.isEmpty() || password.isEmpty()) return false;
        if (password.isEmpty()) return false;
        final User user = serviceLocator.getUserService().findByLogin(login);
        if (user == null) throw new UserNotFoundException();
        final String passwordHash = HashUtil.md5(password);
        if (passwordHash == null || passwordHash.isEmpty()) return false;
        boolean check = passwordHash.equals(user.getPasswordHash());
        return (true);
    }

    @Nullable
    public Session sign(@Nullable final Session session) {
        if (session == null) return null;
        session.setSignature(null);
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @Nullable final String signature = HashUtil.salt(propertyService, session);
        session.setSignature(signature);
        return session;
    }

}
