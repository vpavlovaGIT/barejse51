package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.endpoint.EndpointLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.marker.IntegrationCategory;

import java.util.List;

public class SessionEndpointTest {

    @NotNull
    final EndpointLocator endpointLocator = new Bootstrap();

    @Before
    @SneakyThrows
    public void before() {
        List<Session> sessionList = endpointLocator.getSessionEndpoint().listSession();
        for (Session session: sessionList) {
            endpointLocator.getSessionEndpoint().closeSession(session);
        }
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void openSessionTest() {
        final Session session = endpointLocator.getSessionEndpoint().openSession("test", "test");
        Assert.assertNotNull(session);
        endpointLocator.getSessionEndpoint().closeSession(session);
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void closeSessionTest() {
        final Session session = endpointLocator.getSessionEndpoint().openSession("test", "test");
        endpointLocator.getSessionEndpoint().closeSession(session);
        final long size = endpointLocator.getSessionEndpoint().listSession().size();
        Assert.assertEquals(0, size);
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void listSessionTest() {
        endpointLocator.getSessionEndpoint().openSession("test", "test");
        final List<Session> listSession = endpointLocator.getSessionEndpoint().listSession();
        Assert.assertEquals(1, listSession.size());
    }

}
