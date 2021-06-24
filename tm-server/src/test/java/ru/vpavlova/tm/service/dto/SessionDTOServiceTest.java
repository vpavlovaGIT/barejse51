package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.dto.ISessionDTOService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.dto.SessionDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.ArrayList;
import java.util.List;

public class SessionDTOServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final ISessionDTOService sessionService = new SessionDTOService(connectionService, serviceLocator);

    @Test
    @Category(DBCategory.class)
    public void addAllTest() {
        final List<SessionDTO> sessions = new ArrayList<>();
        final SessionDTO session1 = new SessionDTO();
        final SessionDTO session2 = new SessionDTO();
        sessions.add(session1);
        sessions.add(session2);
        sessionService.addAll(sessions);
        Assert.assertTrue(sessionService.findOneById(session1.getId()).isPresent());
        Assert.assertTrue(sessionService.findOneById(session2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addTest() {
        final SessionDTO session = new SessionDTO();
        sessionService.add(session);
        Assert.assertNotNull(sessionService.findOneById(session.getId()));
    }

    @Test
    @Category(DBCategory.class)
    public void clearTest() {
        sessionService.clear();
        Assert.assertTrue(sessionService.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAll() {
        sessionService.clear();
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
    public void findOneByIdTest() {
        final SessionDTO session1 = new SessionDTO();
        final String sessionId = session1.getId();
        sessionService.add(session1);
        Assert.assertNotNull(sessionService.findOneById(sessionId));
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final SessionDTO session = new SessionDTO();
        sessionService.add(session);
        final String sessionId = session.getId();
        Assert.assertTrue(sessionService.findOneById(sessionId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTest() {
        final SessionDTO session = new SessionDTO();
        sessionService.add(session);
        final String sessionId = session.getId();
        sessionService.removeOneById(sessionId);
        Assert.assertFalse(sessionService.findOneById(sessionId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTest() {
        final SessionDTO session = new SessionDTO();
        sessionService.add(session);
        sessionService.remove(session);
        Assert.assertNotNull(sessionService.findOneById(session.getId()));
    }

}
