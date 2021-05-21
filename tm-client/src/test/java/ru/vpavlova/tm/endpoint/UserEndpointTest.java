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

public class UserEndpointTest {

    @NotNull
    final EndpointLocator endpointLocator = new Bootstrap();

    @Nullable
    private Session session;

    @Nullable
    private Session sessionAdmin;

    @Before
    @SneakyThrows
    public void before() {
        session = endpointLocator.getSessionEndpoint().openSession("test", "test");
        sessionAdmin = endpointLocator.getSessionEndpoint().openSession("admin", "admin");
    }

    @After
    @SneakyThrows
    public void after() {
        endpointLocator.getSessionEndpoint().closeSession(session);
        endpointLocator.getSessionEndpoint().closeSession(sessionAdmin);
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findUserByLoginTest() {
        User user = new User();
        user.setLogin("DEMO");
        final String login = user.getLogin();
        endpointLocator.getAdminEndpoint().addUser(sessionAdmin, user);
        final User userFound = endpointLocator.getUserEndpoint().findUserByLogin(sessionAdmin, login);
        Assert.assertEquals("DEMO", userFound.getLogin());
    }

    @Test
    @SneakyThrows
    @Category(IntegrationCategory.class)
    public void findUserOneBySessionTest() {
        final String login = endpointLocator.getUserEndpoint().findUserOneBySession(session).getLogin();
        Assert.assertEquals("test", login);
    }

}
