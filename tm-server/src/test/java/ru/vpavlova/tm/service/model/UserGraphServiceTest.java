package ru.vpavlova.tm.service.model;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.model.IUserGraphService;
import ru.vpavlova.tm.entity.UserGraph;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;

import java.util.ArrayList;
import java.util.List;

public class UserGraphServiceTest  {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IUserGraphService userService = new UserGraphService(propertyService, connectionService);

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
    public void addAllUsersTest() {
        final List<UserGraph> users = new ArrayList<>();
        final UserGraph user1 = new UserGraph();
        final UserGraph user2 = new UserGraph();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertTrue(userService.findById(user1.getId()).isPresent());
        Assert.assertTrue(userService.findById(user2.getId()).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void clearUserTest() {
        userService.clear();
        try {
            Assert.assertTrue(userService.findAll().isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Category(DBCategory.class)
    public void findAllUsers() {
        final List<UserGraph> users = new ArrayList<>();
        final UserGraph user1 = new UserGraph();
        final UserGraph user2 = new UserGraph();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertEquals(2, userService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findUserByLogin() {
        final UserGraph user = new UserGraph();
        user.setLogin("test");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        userService.removeByLogin("testFind");
    }

    @Test
    @Category(UnitCategory.class)
    public void findUserOneByIdTest() {
        final UserGraph user1 = new UserGraph();
        final String userId = user1.getId();
        userService.add(user1);
        Assert.assertNotNull(userService.findById(userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserByLogin() {
        final UserGraph user1 = new UserGraph();
        userService.add(user1);
        final String userId = user1.getId();
        userService.removeById(userId);
        Assert.assertNull(userService.findById(userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserOneByIdTest() {
        final UserGraph user = new UserGraph();
        userService.add(user);
        final String userId = user.getId();
        userService.removeById(userId);
        Assert.assertFalse(userService.findById(userId).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserTest() {
        final UserGraph user = new UserGraph();
        userService.add(user);
        userService.remove(user);
        Assert.assertNotNull(userService.findById(user.getId()));
    }

}

