package ru.vpavlova.tm.service.dto;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.dto.ISessionRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.dto.ISessionService;
import ru.vpavlova.tm.dto.Session;
import ru.vpavlova.tm.dto.User;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.repository.dto.SessionRepository;
import ru.vpavlova.tm.util.HashUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public final class SessionService extends AbstractService<Session> implements ISessionService {

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

    @SneakyThrows
    public boolean checkDataAccess(
            @Nullable final String login,
            @Nullable final String password
    ) {
        if (login.isEmpty() || password.isEmpty()) return false;
        final @NotNull Optional<User> user = serviceLocator.getUserDTOService().findByLogin(login);
        if (!user.isPresent()) return false;
        if (user.get().isLocked()) throw new AccessDeniedException();
        final String passwordHash = HashUtil.salt(serviceLocator.getPropertyService(), password);
        if (passwordHash.isEmpty()) return false;
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

    @Nullable
    @Override
    @SneakyThrows
    public Session open(
            @Nullable final String login,
            @Nullable final String password
    ) {
        final boolean check = checkDataAccess(login, password);
        if (!check) throw new AccessDeniedException();
        final @NotNull Optional<User> user = serviceLocator.getUserDTOService().findByLogin(login);
        if (!user.isPresent()) return null;
        @NotNull final Session session = new Session();
        session.setUserId(user.get().getId());
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
    @SneakyThrows
    public void validate(@Nullable final Session session) {
        if (session == null) throw new AccessDeniedException();
        if ((session.getSignature().isEmpty())) throw new AccessDeniedException();
        if ((session.getUserId()).isEmpty()) throw new AccessDeniedException();
        @Nullable final Session temp = session.clone();
        if (temp == null) throw new AccessDeniedException();
        @NotNull final String signatureSource = session.getSignature();
        @Nullable final Session sessionTarget = sign(temp);
        if (sessionTarget == null) throw new AccessDeniedException();
        @Nullable final String signatureTarget = sessionTarget.getSignature();
        final boolean check = signatureSource.equals(signatureTarget);
        if (!check) throw new AccessDeniedException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            if (!sessionRepository.findOneById(session.getId()).isPresent()) throw new AccessDeniedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void validateAdmin(@Nullable final Session session, @Nullable final Role role) {
        if (session == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        if (session.getUserId().isEmpty()) throw new AccessDeniedException();
        validate(session);
        final @NotNull Optional<User> user = serviceLocator.getUserDTOService().findOneById(session.getUserId());
        if (!user.isPresent()) throw new AccessDeniedException();
        if (user.get().getRole() != Role.ADMIN) throw new AccessDeniedException();
    }

    @Override
    @Nullable
    @SneakyThrows
    public Session close(@Nullable final Session session) {
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

    @Override
    @SneakyThrows
    public void addAll(@Nullable List<Session> entities) {
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

    @NotNull
    @Override
    @SneakyThrows
    public List<Session> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            return sessionRepository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    @SneakyThrows
    public Optional<Session> findOneById(
            @Nullable final String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ISessionRepository sessionRepository = new SessionRepository(entityManager);
            return sessionRepository.findOneById(id);
        } finally {
            entityManager.close();
        }
    }

    @SneakyThrows
    @Override
    public void removeOneById(
            @Nullable final String id
    ) {
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

}

