package ru.vpavlova.tm.service;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.vpavlova.tm.api.IPropertyService;
import ru.vpavlova.tm.api.service.IConnectionService;
import ru.vpavlova.tm.api.service.IUserService;
import ru.vpavlova.tm.dto.UserDTO;
import ru.vpavlova.tm.marker.DBCategory;
import ru.vpavlova.tm.marker.UnitCategory;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @NotNull
    private final IPropertyService propertyService = new PropertyService();

    @NotNull
    private final IConnectionService connectionService = new ConnectionService(propertyService);

    @NotNull
    private final IUserService userService = new UserService(propertyService, connectionService);

    @Test
    @Category(DBCategory.class)
    public void addAllUsersTest() {
        final List<UserDTO> users = new ArrayList<>();
        final UserDTO user1 = new UserDTO();
        final UserDTO user2 = new UserDTO();
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
        final List<UserDTO> users = new ArrayList<>();
        final UserDTO user1 = new UserDTO();
        final UserDTO user2 = new UserDTO();
        users.add(user1);
        users.add(user2);
        userService.addAll(users);
        Assert.assertEquals(2, userService.findAll().size());
    }

    @Test
    @Category(DBCategory.class)
    public void findUserByLogin() {
        final UserDTO user = new UserDTO();
        user.setLogin("test");
        userService.add(user);
        final String login = user.getLogin();
        Assert.assertNotNull(login);
        userService.removeByLogin("testFind");
    }

    @Test
    @Category(UnitCategory.class)
    public void findUserOneByIdTest() {
        final UserDTO user1 = new UserDTO();
        final String userId = user1.getId();
        userService.add(user1);
        Assert.assertNotNull(userService.findById(userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserByLogin() {
        final UserDTO user1 = new UserDTO();
        userService.add(user1);
        final String userId = user1.getId();
        userService.removeById(userId);
        Assert.assertNull(userService.findById(userId));
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserOneByIdTest() {
        final UserDTO user = new UserDTO();
        userService.add(user);
        final String userId = user.getId();
        userService.removeById(userId);
        Assert.assertFalse(userService.findById(userId).isPresent());
    }

    @Test
    @Category(UnitCategory.class)
    public void removeUserTest() {
        final UserDTO user = new UserDTO();
        userService.add(user);
        userService.remove(user);
        Assert.assertNotNull(userService.findById(user.getId()));
    }

}
