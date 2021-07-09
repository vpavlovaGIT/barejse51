package ru.vpavlova.tm.service.model;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.model.ISessionGraphRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.model.ISessionGraphService;
import ru.vpavlova.tm.dto.Session;
import ru.vpavlova.tm.entity.SessionGraph;
import ru.vpavlova.tm.entity.UserGraph;
import ru.vpavlova.tm.enumerated.Role;
import ru.vpavlova.tm.exception.empty.EmptyIdException;
import ru.vpavlova.tm.exception.entity.ObjectNotFoundException;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.repository.model.SessionGraphRepository;
import ru.vpavlova.tm.util.HashUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public final class SessionGraphService extends AbstractGraphService<SessionGraph> implements ISessionGraphService {


    @NotNull
    private final ServiceLocator serviceLocator;

    public SessionGraphService(
            @NotNull IConnectionService connectionService,
            @NotNull ServiceLocator serviceLocator
    ) {
        super(connectionService);
        this.serviceLocator = serviceLocator;
    }

    @Override
    @SneakyThrows
    public void add(@Nullable final SessionGraph session) {
        if (session == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
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
    public void addAll(@NotNull List<SessionGraph> entities) {
        if (entities == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
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
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
            sessionRepository.clear();
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
    public List<SessionGraph> findAll() {
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
            return sessionRepository.findAll();
        } finally {
            entityManager.close();
        }
    }

    @NotNull
    @Override
    public Optional<SessionGraph> findById(
            @Nullable String id
    ) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
            return sessionRepository.findById(id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void removeById(@Nullable String id) {
        if (id.isEmpty()) throw new EmptyIdException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
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
    public SessionGraph open(
            @Nullable final String login,
            @Nullable final String password
    ) {
        final boolean check = checkDataAccess(login, password);
        if (!check) return null;
        final @NotNull Optional<UserGraph> user = serviceLocator.getUserService().findByLogin(login);
        if (!user.isPresent()) return null;
        @NotNull final SessionGraph session = new SessionGraph();
        session.setUser(user.get());
        @Nullable final SessionGraph signSession = sign(session);
        if (signSession == null) return null;
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
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
    public void validate(@Nullable final SessionGraph session) {
        if (session == null) throw new AccessDeniedException();
        if (session.getSignature().isEmpty()) throw new AccessDeniedException();
        if (session.getUser() == null) throw new AccessDeniedException();
        @Nullable final SessionGraph temp = session.clone();
        if (temp == null) throw new AccessDeniedException();
        @NotNull final String signatureSource = session.getSignature();
        @Nullable final SessionGraph sessionTarget = sign(temp);
        if (sessionTarget == null) throw new AccessDeniedException();
        @Nullable final String signatureTarget = sessionTarget.getSignature();
        final boolean check = signatureSource.equals(signatureTarget);
        if (!check) throw new AccessDeniedException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
            if (!sessionRepository.findById(session.getId()).isPresent()) throw new AccessDeniedException();
        } finally {
            entityManager.close();
        }
    }

    @Override
    @SneakyThrows
    public void validateAdmin(@Nullable final SessionGraph session, @Nullable final Role role) {
        if (session == null) throw new AccessDeniedException();
        if (role == null) throw new AccessDeniedException();
        if ((session.getUser().getId()).isEmpty()) throw new AccessDeniedException();
        validate(session);
        final @NotNull Optional<UserGraph> user = serviceLocator.getUserService().findById(session.getUser().getId());
        if (!user.isPresent()) throw new AccessDeniedException();
        if (user.get().getRole() != Role.ADMIN) throw new AccessDeniedException();
    }

    @Override
    @Nullable
    @SneakyThrows
    public SessionGraph close(@Nullable SessionGraph session) {
        if (session == null) return null;
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
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
        final @NotNull Optional<UserGraph> user = serviceLocator.getUserService().findByLogin(login);
        if (!user.isPresent()) return false;
        if (user.get().isLocked()) throw new AccessDeniedException();
        final String passwordHash = HashUtil.salt(serviceLocator.getPropertyService(), password);
        if (password.isEmpty()) return false;
        return passwordHash.equals(user.get().getPasswordHash());
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final SessionGraph entity) {
        if (entity == null) throw new ObjectNotFoundException();
        @NotNull final EntityManager entityManager = connectionService.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            @NotNull final ISessionGraphRepository sessionRepository = new SessionGraphRepository(entityManager);
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
    public SessionGraph sign(@Nullable final SessionGraph session) {
        if (session == null) return null;
        session.setSignature(null);
        @NotNull final IPropertyService propertyService = serviceLocator.getPropertyService();
        @Nullable final String signature = HashUtil.salt(propertyService, session);
        session.setSignature(signature);
        return session;
    }

}
