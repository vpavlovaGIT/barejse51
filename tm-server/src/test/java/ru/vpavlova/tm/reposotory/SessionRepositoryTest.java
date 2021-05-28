package ru.vpavlova.tm.reposotory;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.ISessionRepository;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.SessionRepository;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SessionRepositoryTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    final Connection connection = connectionService.getConnection();

    @NotNull
    final ISessionRepository sessionRepository = new SessionRepository(connection);

    @After
    @SneakyThrows
    public void after() {
        connection.commit();
    }

    @Test
    @Category(DBCategory.class)
    public void addAllSessionTest() {
        final List<Session> sessions = new ArrayList<>();
        final Session session1 = new Session();
        final Session session2 = new Session();
        sessions.add(session1);
        sessions.add(session2);
        sessionRepository.addAll(sessions);
        Assert.assertTrue(sessionRepository.findById(session1.getId()).isPresent());
        Assert.assertTrue(sessionRepository.findById(session2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void addSessionTest() {
        final Session session = new Session();
        Assert.assertNotNull(sessionRepository.add(session));
    }

    @Test
    @Category(DBCategory.class)
    public void clearSessionTest() {
        sessionRepository.clear();
        Assert.assertTrue(sessionRepository.findAll().isEmpty());
    }

    @Test
    @Category(DBCategory.class)
    public void findAllSession() {
        final List<Session> sessions = new ArrayList<>();
        final Session session1 = new Session();
        final Session session2 = new Session();
        sessions.add(session1);
        sessions.add(session2);
        sessionRepository.addAll(sessions);
        Assert.assertEquals(2, sessionRepository.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findSessionOneByIdTest() {
        final Session session1 = new Session();
        final String sessionId = session1.getId();
        sessionRepository.add(session1);
        Assert.assertNotNull(sessionRepository.findById(sessionId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeSessionOneByIdTest() {
        final Session session = new Session();
        sessionRepository.add(session);
        final String sessionId = session.getId();
        Assert.assertNotNull(sessionRepository.removeById(sessionId));
    }

    @Test
    @Category(DBCategory.class)
    public void removeSessionTest() {
        final List<Session> sessionList = new ArrayList<>();
        for (@NotNull final Session session : sessionList) {
            sessionRepository.remove(session);
            Assert.assertNull(sessionRepository.findById(session.getId()));
        }
    }

}
