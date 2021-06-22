package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.ISessionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.model.SessionService;

import java.util.ArrayList;
import java.util.List;

public class SessionServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final ISessionService sessionService = new SessionService(connectionService, serviceLocator);

    @Test
    @Category(DBCategory.class)
    public void addAllSessionTest() {
        final List<SessionDTO> sessions = new ArrayList<>();
        final SessionDTO session1 = new SessionDTO();
        final SessionDTO session2 = new SessionDTO();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertTrue(sessionService.findById(session1.getId()).isPresent());
        Assert.assertTrue(sessionService.findById(session2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addSessionTest() {
        final SessionDTO session = new SessionDTO();
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
        final List<SessionDTO> sessions = new ArrayList<>();
        final SessionDTO session1 = new SessionDTO();
        final SessionDTO session2 = new SessionDTO();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertEquals(2, sessionService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findSessionOneByIdTest() {
        final SessionDTO session1 = new SessionDTO();
        final String sessionId = session1.getId();
        sessionService.add(session1);
        Assert.assertNotNull(sessionService.findById(sessionId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeSessionOneByIdTest() {
        final SessionDTO session = new SessionDTO();
        sessionService.add(session);
        final String sessionId = session.getId();
        sessionService.removeById(sessionId);
        Assert.assertFalse(sessionService.findById(sessionId).isPresent());
    }

}
