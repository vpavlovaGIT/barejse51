package ru.vpavlova.tm.service.dto;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.ServiceLocator;
import ru.vpavlova.tm.api.service.dto.IUserService;
import ru.vpavlova.tm.bootstrap.Bootstrap;
import ru.vpavlova.tm.dto.User;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.service.ConnectionService;
import ru.vpavlova.tm.service.PropertyService;
import ru.vpavlova.tm.service.TestUtil;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @NotNull
    private final ServiceLocator serviceLocator = new Bootstrap();

    @NotNull
    private final IConnectionService connectionService = serviceLocator.getConnectionService();

    @NotNull
    private final IUserService userService = serviceLocator.getUserDTOService();

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
    public void addAllTest() {
        final List<User> users = new ArrayList<>();
        final User user1 = new User();
        final User user2 = new User();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertTrue(userService.findOneById(user1.getId()).isPresent());
        Assert.assertTrue(userService.findOneById(user2.getId()).isPresent());
        userService.remove(users.get(0));
        userService.remove(users.get(1));
    }

    @Test
    @Category(DBCategory.class)
    public void addTest() {
        final User user = new User();
        userService.add(user);
        Assert.assertNotNull(userService.findOneById(user.getId()));
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void findAll() {
        final int userSize = userService.findAll().size();
        userService.create("testFindAll", "test", "-");
        Assert.assertEquals(userSize + 1, userService.findAll().size());
        userService.removeByLogin("testFindAll");
    }

    @Test
    @Category(DBCategory.class)
    public void findByLogin() {
        final User user = new User();
        user.setLogin("testFind");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        Assert.assertTrue(userService.findByLogin(login).isPresent());
        userService.removeByLogin("testFind");
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIdTest() {
        final User user = new User();
        final String userId = user.getId();
        userService.add(user);
        Assert.assertNotNull(userService.findOneById(userId));
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void findOneByIndexTest() {
        final User user = new User();
        userService.add(user);
        final String userId = user.getId();
        Assert.assertTrue(userService.findOneById(userId).isPresent());
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void isLoginExist() {
        final User user = new User();
        user.setLogin("testExist");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        Assert.assertTrue(userService.isLoginExist(login));
        userService.remove(user);
    }

    @Test
    @Category(DBCategory.class)
    public void removeByLogin() {
        final User user = new User();
        user.setLogin("testRemoveByLogin");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        userService.removeByLogin(login);
        Assert.assertFalse(userService.isLoginExist(login));
    }

    @Test
    @Category(DBCategory.class)
    public void removeOneByIdTest() {
        final User user = new User();
        userService.add(user);
        final String userId = user.getId();
        userService.removeOneById(userId);
        Assert.assertFalse(userService.findOneById(userId).isPresent());
    }

    @Test
    @Category(DBCategory.class)
    public void removeTest() {
        final User user = new User();
        userService.add(user);
        userService.remove(user);
        Assert.assertNotNull(userService.findOneById(user.getId()));
    }

}
