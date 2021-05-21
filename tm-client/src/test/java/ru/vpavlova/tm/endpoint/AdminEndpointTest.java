package ru.vpavlova.tm.endpoint;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.endpoint.EndpointLocator;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.marker.IntegrationCategory;

public class AdminEndpointTest {

    @NotNull
    private final EndpointLocator endpointLocator = new Bootstrap();

    @NotNull
    private final AdminEndpointService adminEndpointService = new AdminEndpointService();

    @NotNull
    private final AdminEndpoint adminEndpoint = adminEndpointService.getAdminEndpointPort();

    @NotNull
    private final SessionEndpointService sessionEndpointService = new SessionEndpointService();

    @NotNull
    private final SessionEndpoint sessionEndpoint = sessionEndpointService.getSessionEndpointPort();

    @Nullable
    private Session sessionAdmin;

    @Before
    @SneakyThrows
    public void before() {
        sessionAdmin = endpointLocator.getSessionEndpoint().openSession("admin", "admin");
    }

    @After
    @SneakyThrows
    public void after() {
        endpointLocator.getSessionEndpoint().closeSession(sessionAdmin);
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void removeUserByLoginTest() {
        adminEndpoint.removeOneByLogin(sessionAdmin, "user");
    }


    @Test
    @Category(IntegrationCategory.class)
    public void lockUserByLoginTest() {
        adminEndpoint.lockUserByLogin(sessionAdmin, "user");
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void unlockUserByLoginTest() {
        @NotNull final Session session = sessionEndpoint.openSession("user", "user");
        adminEndpoint.unlockUserByLogin(session, "user");
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void createUserTest() {
        endpointLocator.getAdminEndpoint().createUser(sessionAdmin,"login1", "password1");
        Assert.assertNotNull(endpointLocator.getUserEndpoint().findUserByLogin(sessionAdmin,"test1"));
    }

}
