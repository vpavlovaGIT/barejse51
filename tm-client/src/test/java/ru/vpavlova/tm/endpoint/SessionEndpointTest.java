package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.endpoint.EndpointLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.exception.user.AccessDeniedException;
import ru.vpavlova.tm.marker.IntegrationCategory;

public class SessionEndpointTest {

    @NotNull
    final EndpointLocator endpointLocator = new Bootstrap();

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void openSession() {
        final Session session = endpointLocator.getSessionEndpoint().openSession("test", "test");
        Assert.assertNotNull(session);
        endpointLocator.getSessionEndpoint().closeSession(session);
    }

    @Test(expected = AccessDeniedException.class)
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void closeSession() {
        final Session session = endpointLocator.getSessionEndpoint().openSession("test", "test");
        endpointLocator.getSessionEndpoint().closeSession(session);
        endpointLocator.getTaskEndpoint().findAllTasks(session);
    }

}
