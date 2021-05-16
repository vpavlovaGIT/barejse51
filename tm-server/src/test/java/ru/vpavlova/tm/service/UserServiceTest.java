package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.repository.IUserRepository;
import ru.vpavlova.tm.api.service.IUserService;
import ru.vpavlova.tm.entity.User;
import ru.vpavlova.tm.marker.UnitCategory;
import ru.vpavlova.tm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IUserRepository userRepository = new UserRepository();

    @NotNull
    private final IUserService userService = new UserService(userRepository, propertyService);

    @Test
    @Category(UnitCategory.class)
    public void addAllUsersTest() {
        final List<User> users = new ArrayList<>();
        final User user1 = new User();
        final User user2 = new User();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertTrue(userService.findById(user1.getId()).isPresent());
        Assert.assertTrue(userService.findById(user2.getId()).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void addUserTest() {
        final User user = new User();
        Assert.assertNotNull(userService.add(user));
    }

    @Test
    @Category(UnitCategory.class)
    public void clearUserTest() {
        userService.clear();
        Assert.assertTrue(userService.findAll().isEmpty());
    }

    @Test
    @Category(UnitCategory.class)
    public void findAllUsers() {
        final List<User> users = new ArrayList<>();
        final User user1 = new User();
        final User user2 = new User();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertEquals(2, userService.findAll().size());
    }

    @Test
    @Category(UnitCategory.class)
    public void findUserByLogin() {
        final User user = new User();
        user.setLogin("test");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        Assert.assertTrue(userService.findByLogin(login).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void findUserOneByIdTest() {
        final User user1 = new User();
        final String userId = user1.getId();
        userService.add(user1);
        Assert.assertNotNull(userService.findById(userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserByLogin() {
        final User user = new User();
        user.setLogin("test");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        userService.removeByLogin(login);
        Assert.assertFalse(userService.isLoginExist(login));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserOneByIdTest() {
        final User user = new User();
        userService.add(user);
        final String userId = user.getId();
        Assert.assertNull(userService.removeById(userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserTest() {
        final List<User> users = new ArrayList<>();
        for (@NotNull final User user : users) {
            Assert.assertNotNull(userService.removeById(user.getId()));
            Assert.assertNull(userService.findById(user.getId()));
        }
    }

}
