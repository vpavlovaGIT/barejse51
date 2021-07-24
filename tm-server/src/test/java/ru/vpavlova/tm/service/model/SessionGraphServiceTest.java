package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.model.ISessionGraphService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.entity.SessionGraph;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.TestUtil;

import java.util.ArrayList;
import java.util.List;

public class SessionGraphServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IConnectionService connectionService = serviceLocator.getConnectionService();

    @NotNull
    private final ISessionGraphService sessionService = serviceLocator.getSessionService();

    {
        TestUtil.initUser();
    }

    @Before
    public void before() {
        connectionService.getEntityManager().getEntityManagerFactory().createEntityManager();
    }

    @After
    public void after() {
        connectionService.getEntityManager().getEntityManagerFactory().close();
    }

    @Test
    @Category(DBCategory.class)
    public void addAllSessionTest() {
        final List<SessionGraph> sessions = new ArrayList<>();
        final SessionGraph session1 = new SessionGraph();
        final SessionGraph session2 = new SessionGraph();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertTrue(sessionService.findById(session1.getId()).isPresent());
        Assert.assertTrue(sessionService.findById(session2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addSessionTest() {
        final SessionGraph session = new SessionGraph();
        sessionService.add(session);
        Assert.assertNotNull(sessionService.findById(session.getId()));
    }

    @Test
    @Category(DBCategory.class)
    public void clearSessionTest() {
        sessionService.clear();
        Assert.assertTrue(sessionService.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllSession() {
        final List<SessionGraph> sessions = new ArrayList<>();
        final SessionGraph session1 = new SessionGraph();
        final SessionGraph session2 = new SessionGraph();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertEquals(2, sessionService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findSessionOneByIdTest() {
        final SessionGraph session1 = new SessionGraph();
        final String sessionId = session1.getId();
        sessionService.add(session1);
        Assert.assertNotNull(sessionService.findById(sessionId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeSessionOneByIdTest() {
        final SessionGraph session = new SessionGraph();
        sessionService.add(session);
        final String sessionId = session.getId();
        sessionService.removeById(sessionId);
        Assert.assertFalse(sessionService.findById(sessionId).isPresent());
    }

}
