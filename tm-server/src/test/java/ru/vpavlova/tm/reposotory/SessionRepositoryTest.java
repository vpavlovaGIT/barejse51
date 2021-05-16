package ru.vpavlova.tm.reposotory;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.repository.ISessionRepository;
import ru.vpavlova.tm.entity.Session;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.SessionRepository;

import java.util.ArrayList;
import java.util.List;

public class SessionRepositoryTest {

    @NotNull
    private final ISessionRepository sessionRepository = new SessionRepository();

    @Test
    @Category(UnitCategory.class)
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
    @Category(UnitCategory.class)
    public void addSessionTest() {
        final Session session = new Session();
        Assert.assertNotNull(sessionRepository.add(session));
    }

    @Test
    @Category(UnitCategory.class)
    public void clearSessionTest() {
        sessionRepository.clear();
        Assert.assertTrue(sessionRepository.findAll().isEmpty());
    }

    @Test
    @Category(UnitCategory.class)
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
    @Category(UnitCategory.class)
    public void findSessionOneByIdTest() {
        final Session session1 = new Session();
        final String sessionId = session1.getId();
        sessionRepository.add(session1);
        Assert.assertNotNull(sessionRepository.findById(sessionId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeSessionOneByIdTest() {
        final Session session = new Session();
        sessionRepository.add(session);
        final String sessionId = session.getId();
        Assert.assertNotNull(sessionRepository.removeById(sessionId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeSessionTest() {
        final List<Session> sessionList = new ArrayList<>();
        for (@NotNull final Session session : sessionList) {
            sessionRepository.remove(session);
            Assert.assertNull(sessionRepository.findById(session.getId()));
        }
    }

}
