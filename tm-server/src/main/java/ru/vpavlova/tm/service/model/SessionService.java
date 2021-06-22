package ru.vpavlova.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.model.ISessionRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.model.ISessionService;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.repository.model.SessionRepository;
import ru.vpavlova.tm.util.HashUtil;

import javax.persistence.EntityManager;
import java.util.List;
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

    @Override
    @SneakyThrows
    public void add(@Nullable final Session session) {
        if (session == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            sessionRepository.add(session);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addAll(@NotNull List<Session> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            entities.forEach(sessionRepository::add);
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
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            sessionRepository.clear();
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public @NotNull List<Session> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
        return sessionRepository.findAll();
    }

    @NotNull
    @Override
    public Optional<Session> findById(@Nullable String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
        return sessionRepository.findById(id);
    }

    @Override
    public void removeById(@Nullable String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            sessionRepository.removeOneById(id);
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
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
        final @NotNull Optional<User> user = serviceLocator.getUserService().findByLogin(login);
        if (!user.isPresent()) return null;
        @NotNull final Session session = new Session();
        session.setUser(user.get());
        @Nullable final Session signSession = sign(session);
        if (signSession == null) return null;
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            sessionRepository.add(signSession);
            entityManager.getTransaction().commit();
            return signSession;
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void validate(@Nullable Session session) {
        if (session == null) throw new AccessDeniedException();
        if (session.getSignature() == null || session.getSignature().isEmpty()) throw new AccessDeniedException();
        if (session.getUser() == null) throw new AccessDeniedException();
        @Nullable final Session temp = session.clone();
        if (temp == null) throw new AccessDeniedException();
        @NotNull final String signatureSource = session.getSignature();
        @Nullable final Session sessionTarget = sign(temp);
        if (sessionTarget == null) throw new AccessDeniedException();
        @Nullable final String signatureTarget = sessionTarget.getSignature();
        final boolean check = signatureSource.equals(signatureTarget);
        if (!check) throw new AccessDeniedException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
        if (!sessionRepository.findById(session.getId()).isPresent()) throw new AccessDeniedException();
    }

    @Override
    @SneakyThrows
    public void validateAdmin(@Nullable final Session session, @Nullable final Role role) {
        if (session == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        if ((session.getUser().getId()).isEmpty()) throw new AccessDeniedException();
        validate(session);
        final @NotNull Optional<User> user = serviceLocator.getUserService().findById(session.getUser().getId());
        if (!user.isPresent()) throw new AccessDeniedException();
        if (user.get().getRole() != Role.ADMIN) throw new AccessDeniedException();
    }

    @Override
    @Nullable
    @SneakyThrows
    public Session close(@Nullable Session session) {
        if (session == null) return null;
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            sessionRepository.removeOneById(session.getId());
            entityManager.getTransaction().commit();
            return session;
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @SneakyThrows
    public boolean checkDataAccess(
            @Nullable final String login,
            @Nullable final String password
    ) {
        if (login.isEmpty() || password.isEmpty()) return false;
        final @NotNull Optional<User> user = serviceLocator.getUserService().findByLogin(login);
        if (!user.isPresent()) return false;
        if (user.get().isLocked()) throw new AccessDeniedException();
        final String passwordHash = HashUtil.salt(serviceLocator.getPropertyService(), password);
        if (password.isEmpty()) return false;
        return passwordHash.equals(user.get().getPasswordHash());
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final Session entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            sessionRepository.removeOneById(entity.getId());
            entityManager.getTransaction().commit();
        } catch (@NotNull final Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
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
