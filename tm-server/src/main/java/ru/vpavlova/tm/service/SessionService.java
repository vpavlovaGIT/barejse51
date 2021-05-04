package ru.vpavlova.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.ISessionRepository;
import ru.vpavlova.tm.api.service.ISessionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.util.HashUtil;

import java.util.Optional;

public class SessionService extends AbstractService<Session> implements ISessionService {

    @NotNull
    private final ServiceLocator serviceLocator;

    @NotNull
    private final ISessionRepository sessionRepository;

    public SessionService(
            @NotNull final ServiceLocator serviceLocator,
            @NotNull final ISessionRepository sessionRepository
    ) {
        super(sessionRepository);
        this.serviceLocator = serviceLocator;
        this.sessionRepository = sessionRepository;
    }

    @Nullable
    @Override
    public Session open(
            @Nullable final String login,
            @Nullable final String password
    ) {
        final boolean check = checkDataAccess(login, password);
        if (!check) return null;
        final @NotNull Optional<User> user = serviceLocator.getUserService().findByLogin(login);
        if (!user.isPresent()) return null;
        @NotNull final Session session = new Session();
        session.setUserId(user.get().getId());
        session.setTimestamp(System.currentTimeMillis());
        sessionRepository.add(session);
        return sign(session);
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
        if (session.getTimestamp() == null) throw new AccessDeniedException();
        final Session temp = session.clone();
        if (temp == null) throw new AccessDeniedException();
        final String signatureSource = session.getSignature();
        final String signatureTarget = sign(temp).getSignature();
        final boolean check = signatureSource.equals(signatureTarget);
        if (!check) throw new AccessDeniedException();
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
    public Session close(@Nullable Session session) {
        sessionRepository.removeById(session.getId());
        return session;
    }

    @Override
    public boolean checkDataAccess(
            @Nullable final String login,
            @Nullable final String password
    ) {
        if (login.isEmpty() || password.isEmpty()) return false;
        final @NotNull Optional<User> user = serviceLocator.getUserService().findByLogin(login);
        if (!user.isPresent()) return false;
        final String passwordHash = HashUtil.salt(serviceLocator.getPropertyService(), password);
        if (passwordHash == null || passwordHash.isEmpty()) return false;
        return passwordHash.equals(user.get().getPasswordHash());
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
