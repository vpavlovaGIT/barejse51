package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.repository.ISessionRepository;
import ru.vpavlova.tm.api.service.ISessionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.SessionRepository;

import java.util.ArrayList;
import java.util.List;

public class SessionServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final ISessionRepository sessionRepository = new SessionRepository();

    @NotNull
    private final ISessionService sessionService = new SessionService(serviceLocator, sessionRepository);

    @Test
    @Category(UnitCategory.class)
    public void addAllSessionTest() {
        final List<Session> sessions = new ArrayList<>();
        final Session session1 = new Session();
        final Session session2 = new Session();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertTrue(sessionService.findById(session1.getId()).isPresent());
        Assert.assertTrue(sessionService.findById(session2.getId()).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void addSessionTest() {
        final Session session = new Session();
        Assert.assertNotNull(sessionService.add(session));
    }

    @Test
    @Category(UnitCategory.class)
    public void clearSessionTest() {
        sessionService.clear();
        Assert.assertTrue(sessionService.findAll().isEmpty());
    }

    @Test
    @Category(UnitCategory.class)
    public void findAllSession() {
        final List<Session> sessions = new ArrayList<>();
        final Session session1 = new Session();
        final Session session2 = new Session();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertEquals(2, sessionService.findAll().size());
    }

    @Test
    @Category(UnitCategory.class)
    public void findSessionOneByIdTest() {
        final Session session1 = new Session();
        final String sessionId = session1.getId();
        sessionService.add(session1);
        Assert.assertNotNull(sessionService.findById(sessionId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeSessionOneByIdTest() {
        final Session session = new Session();
        sessionService.add(session);
        final String sessionId = session.getId();
        Assert.assertNotNull(sessionService.removeById(sessionId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeSessionTest() {
        final List<Session> sessionList = new ArrayList<>();
        for (@NotNull final Session session : sessionList) {
            sessionService.remove(session);
            Assert.assertNull(sessionService.findById(session.getId()));
        }
    }

}
